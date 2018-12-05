package com.ez.ib.web.service.impl;

import com.ez.ib.web.IbWebApplication;
import com.ez.ib.web.bean.Item;
import com.ez.ib.web.service.ItemService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: ItemServiceImplTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 上午11:15 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class ItemServiceImplTest {
    @Autowired
    private ItemService itemService;


    @Test
    public void queryItemWithTestPaper() throws Exception {
        long testPaperId = 1054602800262205440L;
        List<Item> items = itemService.queryItemWithTestPaper(testPaperId);
        System.out.println();
    }

    @Test
    public void queryItemWithKnowlege() throws Exception {
        List<Item> items = itemService.queryItemWithKnowlege(Lists.newArrayList(7L, 16L, 3L), 0);
        System.out.println();
    }

}