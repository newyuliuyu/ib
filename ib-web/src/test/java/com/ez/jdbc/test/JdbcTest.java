package com.ez.jdbc.test;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * ClassName: JdbcTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-12-3 下午3:45 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class JdbcTest {
    private final String driverName = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://192.168.1.251:3306/test?autoReconnectForPools=true&allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true&rewriteBatchedStatements=true";
    private final String user = "root";
    private final String pwd = "newa_newc";
    private Connection con;

    @Before
    public void before() throws Exception {

    }

    private Connection getConnnection() throws Exception {
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(url, user, pwd);
        return con;
    }

    @Test
    public void insert() throws Exception {

        String sql = "insert into paper(zkzh,item,url) values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < 1000; i++) {
            pst.setString(1, "zkzh" + i);
            pst.setInt(2, 1);
            pst.setString(3, "jdjkfjkdfjkkjfkjfdjkfdjkfdjkfdjk");
            pst.addBatch();
        }
        pst.executeBatch();

    }


    @Test
    public void update() throws Exception {
//        Runnable runnable = getRunnable();
//        Thread thread1 = new Thread(runnable);
//        runnable = getRunnable();
//        Thread thread2 = new Thread(runnable);
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            Runnable runnable = getRunnable(i);
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();

        System.out.println();
    }

    public Runnable getRunnable(final int i) {
        Runnable runnable = () -> {
            long time = System.nanoTime();
            String sql = "UPDATE paper a SET a.datetimes=? WHERE a.datetimes=0 LIMIT 100";
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setLong(1, time);
                int row = pst.executeUpdate();
                pst.close();
                System.out.println(i + "===" + row);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
        return runnable;
    }

}
