package com.ez.ib.web.utils;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-12-5 下午6:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ThreadTest {


    @Test
    public void test01() throws Exception {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int num = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try{
//                        Thread.sleep(100);
//                        System.out.println("****************************");
//                        System.out.println(Thread.currentThread().getName() + ":" + num);
//                        System.out.println("****************************");
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            });
        }

        System.out.println();
    }

    @Test
    public void test02() throws Exception {
        System.out.println(Integer.SIZE - 3);

        System.out.println("ctl:");
        printInt(ctlOf((-1 << 29), 0));
        System.out.println("CAPACITY:");
        printInt((1 << 29) - 1);
        System.out.println("RUNNING:");
        printInt(-1 << 29);
        System.out.println("SHUTDOWN:");
        printInt(0 << 29);
        System.out.println("STOP:");
        printInt(1 << 29);
        System.out.println("TIDYING:");
        printInt(2 << 29);
        System.out.println("TERMINATED:");
        printInt(3 << 29);

        System.out.println("workerCountOf:");
        System.out.println(workerCountOf(-536870912));
        System.out.println("runStateOf:");
        System.out.println(runStateOf(-536870912));
    }

    private int runStateOf(int c) {
        return c & ~((1 << 29) - 1);
    }

    private int workerCountOf(int c) {
        return c & ((1 << 29) - 1);
    }

    private int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private void printInt(int i) {
        System.out.println(i + "==" + Integer.toBinaryString(i));
    }
}
