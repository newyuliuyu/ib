package com.ez.ib.web;

import com.ez.ib.web.bean.EzConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
public class IbWebApplicationTests {

    @Autowired
    private EzConfig ezConfig;

    @Test
    public void contextLoads() {
        System.out.println();
    }

}
