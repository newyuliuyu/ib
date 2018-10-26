package com.ez.ib.web.dao;

import com.ez.ib.web.IbWebApplication;
import com.ez.ib.web.bean.ItemKnowledge;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: ItemDaoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 下午5:29 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class ItemDaoTest {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void queryItemKnowledge() throws Exception {
        List<Long> ids = Lists.newArrayList(1054602800262205440L);
        List<ItemKnowledge> itemKnowledges = itemDao.queryItemKnowledge(ids);
        System.out.println();
    }
}