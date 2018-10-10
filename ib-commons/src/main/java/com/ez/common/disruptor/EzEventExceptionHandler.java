/**
 * Project Name:easytnt-ez
 * File Name:EventExceptionHandler.java
 * Package Name:com.ez.framwork.fx.event
 * Date:2016年8月9日上午10:58:21
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.ez.common.disruptor;

import com.ez.common.util.ThrowableToString;
import com.lmax.disruptor.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * ClassName: EventExceptionHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月9日 上午10:58:21 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class EzEventExceptionHandler implements ExceptionHandler<EzEvent> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private EzEventPerformer ezEventPerformer;

    public EzEventExceptionHandler(EzEventPerformer ezEventPerformer) {
        this.ezEventPerformer = ezEventPerformer;
    }

    @Override
    public void handleEventException(Throwable ex, long sequence, EzEvent event) {
        ezEventPerformer.getHasError().compareAndSet(false, true);
        event.setHasError(true);
        log.error("handle处理过程中出错了");
        String msg = MessageFormat.format("处理{0}数据出错", event.get().toString());
        log.error(msg);
        log.error(ThrowableToString.toString(ex));
        event.setMessage(msg + ThrowableToString.toString(ex));
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        ezEventPerformer.getHasError().compareAndSet(false, true);
        log.error("启动handle出错!");
        log.error(ThrowableToString.toString(ex));
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        ezEventPerformer.getHasError().compareAndSet(false, true);
        log.error("关闭handle出错!");
        log.error(ThrowableToString.toString(ex));
    }

}
