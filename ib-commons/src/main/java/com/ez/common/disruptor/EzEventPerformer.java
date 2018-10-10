/**
 * Project Name:easytnt-ez
 * File Name:EzEventPerformer.java
 * Package Name:com.ez.framwork.fx.readdata
 * Date:2016年8月9日上午10:54:24
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.ez.common.disruptor;

import com.ez.common.progress.ProgressEvent;
import com.ez.common.progress.Progresses;
import com.ez.common.thread.ThreadExecutor;
import com.ez.common.util.OnlyNumberBuilder;
import com.ez.common.util.ThrowableToString;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ClassName: EzEventPerformer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月9日 上午10:54:24 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class EzEventPerformer implements Runnable {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ResultProcessor resultProcessor = new ResultProcessor() {

        @Override
        public void fail() {
            // TODO Auto-generated method stub

        }

        @Override
        public void sucess() {
            // TODO Auto-generated method stub

        }

        @Override
        public void finallys() {
            // TODO Auto-generated method stub

        }

    };

    private Disruptor<EzEvent> disruptor;
    private ArrayList<ProcessGroup> processGroups = Lists.newArrayList();
    private EzTask task;
    private String onlyId = OnlyNumberBuilder.build();
    private AtomicBoolean hasError = new AtomicBoolean(false);    // 标识disruptor处理过程中是否产生错误信息
    private AtomicBoolean hasWarn = new AtomicBoolean(false);
    private int totalTask = 0;
    private Progresses progresses;

    public void setProgresses(Progresses progresses) {
        this.progresses = progresses;
    }

    public EzEventPerformer setHasError(AtomicBoolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public AtomicBoolean getHasError() {
        return hasError;
    }

    public AtomicBoolean getHasWarn() {
        return hasWarn;
    }

    public EzEventPerformer setHasWarn(AtomicBoolean hasWarn) {
        this.hasWarn = hasWarn;
        return this;
    }

    public EzEventPerformer setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
        return this;
    }

    public EzEventPerformer setOnlyKey(String onlyKey) {
        this.onlyId = onlyKey;
        return this;
    }

    public String inputCache(String message) {
        Preconditions.checkNotNull(task, "任务列表不能为null");
        totalTask = task.getTaskTotlaNum();
        progresses = new Progresses();
        progresses.saveToCache(onlyId);
        ProgressEvent event = ProgressEvent.create(ProgressEvent::new).message(0, totalTask, message);
        log(event);
        return onlyId;
    }

    public String inputCache() {
        inputCache("开始...");
        return onlyId;
    }

    public EzEventPerformer setTask(EzTask task) {
        this.task = task;
        return this;
    }

    /**
     * disruptor采用handleEventWith执行 这种方式执行，disruptor将会把任务分配到每一个processor去执行
     *
     * @param processors
     * @return
     * @author zhenglian
     * @data 2016年9月28日 下午2:46:21
     */
    @SuppressWarnings("rawtypes")
    public EzEventPerformer addProcessor(Processor... processors) {
        Preconditions.checkNotNull(processors, "处理器不能为Null");
        processGroups.add(new ProcessGroup().setProcessor(processors));
        return this;
    }

    /**
     * disruptor采用workPool执行 这种方式执行，disruptor将会把任务分配到workPool中的某一个processor执行
     * 多个workPool之间按先后顺序进行执行
     * 即.addProcessorPool(processor1).addProcessorPool(processor2)
     * 先执行processor1,在执行processor2
     *
     * @param processors
     * @return
     * @author zhenglian
     * @data 2016年9月28日 下午2:46:39
     */
    @SuppressWarnings("rawtypes")
    public EzEventPerformer addProcessorPool(Processor... processors) {
        Preconditions.checkNotNull(processors, "处理器不能为Null");
        processGroups.add(new ProcessGroupPool().setProcessor(processors));
        return this;
    }

    @Override
    public void run() {
        process();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void process() {
        Preconditions.checkNotNull(task, "并行处理的任务不能为空");
        Long b = System.currentTimeMillis();
        disruptor = new Disruptor(FACTORY, Util.ceilingNextPowerOfTwo(1024), ThreadExecutor.createThreadFactory(),
                ProducerType.SINGLE, new BlockingWaitStrategy());
        // 处理异常
        disruptor.setDefaultExceptionHandler(new EzEventExceptionHandler(this));
        // 创建处理过程任务
        EventHandlerGroup<EzEvent> eventHandlerGroup = createHandler();
        // 创建记录器并创建任务处理器处理记录器
        CountDownLatch counter = new CountDownLatch(task.getTaskTotlaNum());
        countDownLatchHandle(eventHandlerGroup, counter);
        // 开始disruptor
        disruptor.start();
        // 考试启动任务，事件生产者
        startTask();
        try {
            counter.await();
        } catch (Exception e) {
            log.error(ThrowableToString.toString(e));
        }
        // 所有任务处理完毕以后关闭
        disruptor.shutdown();

        if (hasError.get()) {
            resultProcessor.fail();
        } else {
            resultProcessor.sucess();
        }
        resultProcessor.finallys();

        Long e = System.currentTimeMillis();
        log.debug("花费时间:{}s", (e - b) * 1.0 / 1000);
    }

    protected void log(ProgressEvent progressEvent) {
        if (progresses != null) {
            progresses.publish(progressEvent);
        }
    }

    /**
     * 这里充当了disruptor事件生产者，将任务包装到EzEvent中传递到执行器进行处理
     *
     * @author zhenglian
     * @data 2016年9月28日 下午3:15:30
     */
    private void startTask() {
        int idx = 1;
        while (task.next()) {
            Object obj = task.get();
            EzEvent event = new EzEvent();
            event.set(obj);
            event.setRowIdx(idx++);
            disruptor.publishEvent(new EzEventTranslator(event));
        }
    }

    class EzEventTranslator implements EventTranslator<EzEvent> {
        private EzEvent event;

        public EzEventTranslator(EzEvent event) {
            this.event = event;
        }

        @Override
        public void translateTo(EzEvent event, long sequence) {
            event.copy(this.event);
        }

    }

    @SuppressWarnings("unchecked")
    private void countDownLatchHandle(EventHandlerGroup<EzEvent> eventHandlerGroup, final CountDownLatch counter) {
        ProcessorHandler end = new ProcessorHandler(new Processor<EzEvent>() {
            private int completeNum = 0;

            @Override
            public Class<EzEvent> getObjClazz() {
                return EzEvent.class;
            }

            @Override
            public void process(EzEvent event) {
                counter.countDown();
                log.debug("已完量/未完成量：{}/{}", event.getRowIdx(), counter.getCount());
                log.debug("已完成:" + event.get().toString());

                completeNum++;

                ProgressEvent progressEvent = ProgressEvent.create(ProgressEvent::new);
                progressEvent.setTotalNum(totalTask);
                progressEvent.setCompleteNum(completeNum);
                StringBuilder message = new StringBuilder();

                if (event.isHasWarn()) {
                    hasWarn.compareAndSet(false, true);
                    progressEvent.setMsgType(2);
                    message.append(event.getMessage());
                }
                if (event.isHasError()) {
                    hasError.compareAndSet(false, true);
                    progressEvent.setMsgType(1);
                    message.append(event.getMessage());
                }
                progressEvent.setMessage(message.toString());
                log(progressEvent);
            }
        });
        if (eventHandlerGroup == null) {
            disruptor.handleEventsWith(end);
        } else {
            eventHandlerGroup.handleEventsWith(end);
        }
    }

    @SuppressWarnings("rawtypes")
    private EventHandlerGroup<EzEvent> createHandler() {
        EventHandlerGroup<EzEvent> eventHandlerGroup = null;
        for (ProcessGroup processGroup : processGroups) {
            List<Processor> processors = processGroup.getProcessors();
            if (processGroup instanceof ProcessGroupPool) {
                eventHandlerGroup = createHandleEventsWithWorkerPool(eventHandlerGroup, processors);
            } else {
                eventHandlerGroup = createHandleEventsWith(eventHandlerGroup, processors);
            }
        }
        return eventHandlerGroup;
    }

    @SuppressWarnings("rawtypes")
    private EventHandlerGroup<EzEvent> createHandleEventsWith(EventHandlerGroup<EzEvent> eventHandlerGroup,
                                                              List<Processor> processors) {

        if (eventHandlerGroup == null) {
            return disruptor.handleEventsWith(toArray(processors));
        } else {
            return eventHandlerGroup.handleEventsWith(toArray(processors));
        }
    }

    @SuppressWarnings("rawtypes")
    private EventHandlerGroup<EzEvent> createHandleEventsWithWorkerPool(EventHandlerGroup<EzEvent> eventHandlerGroup,
                                                                        List<Processor> processors) {
        if (eventHandlerGroup == null) {
            return disruptor.handleEventsWithWorkerPool(toArray(processors));
        } else {
            return eventHandlerGroup.handleEventsWithWorkerPool(toArray(processors));
        }
    }

    @SuppressWarnings("rawtypes")
    private ProcessorHandler[] toArray(List<Processor> processors) {
        ProcessorHandler[] processorHandlers = new ProcessorHandler[processors.size()];
        for (int i = 0; i < processors.size(); i++) {
            processorHandlers[i] = new ProcessorHandler(processors.get(i));
        }
        return processorHandlers;
    }

    class ProcessorHandler implements EventHandler<EzEvent>, WorkHandler<EzEvent> {
        @SuppressWarnings("rawtypes")
        private Processor processor;

        @SuppressWarnings("rawtypes")
        public ProcessorHandler(Processor processor) {
            this.processor = processor;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onEvent(EzEvent event) throws Exception {
            if (EzEvent.class.equals(processor.getObjClazz())) { // 这里是计数任务处理的个数的一个执行器
                processor.process(event);
            } else {
                if (event.isHasError()) {
                    return;
                }
                processor.process(event.get());
            }
        }

        @Override
        public void onEvent(EzEvent event, long sequence, boolean endOfBatch) throws Exception {
            onEvent(event);
        }
    }

    public static final EventFactory<EzEvent> FACTORY = new EventFactory<EzEvent>() {
        @Override
        public EzEvent newInstance() {
            return new EzEvent();
        }
    };

}
