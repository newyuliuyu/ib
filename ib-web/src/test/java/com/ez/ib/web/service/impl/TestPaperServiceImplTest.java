package com.ez.ib.web.service.impl;

import com.ez.ib.web.IbWebApplication;
import com.ez.ib.web.bean.*;
import com.ez.ib.web.service.TestPaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: TestPaperServiceImplTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午4:00 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class TestPaperServiceImplTest {

    @Autowired
    private TestPaperService testPaperService;

    @Test
    public void newTestPaper() throws Exception {
        Subject subject = Subject.builder().id(1002).build();
        LearnSegment learnSegment = LearnSegment.builder().id(3).build();
        for (int i = 1; i <= 13; i++) {
            TestPaper testPaper = TestPaper.builder()
                    .id((long)i)
                    .name("测试试卷" + i)
                    .subject(subject)
                    .learnSegment(learnSegment)
                    .build();
            testPaperService.newTestPaper(testPaper);
        }

    }

    @Test
    public void savetestPaperItem() throws Exception {

        long id = 1;
        Subject subject = Subject.builder().id(1003).build();
        LearnSegment learnSegment = LearnSegment.builder().id(1).build();
        TestPaper testPaper = TestPaper.builder()
                .id(id)
                .name("测试试卷1")
                .subject(subject)
                .learnSegment(learnSegment)
                .build();

        List<Item> items = Lists.newArrayList();
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));
        items.add(createItem(id++, subject, learnSegment));

        TestPaperItem testPaperItem = TestPaperItem.builder().testPaper(testPaper).items(items).build();

        testPaperService.savetestPaperItem(testPaperItem);

    }

    private Item createItem(long id, Subject subject, LearnSegment learnSegment) {
        ItemStem itemStem = ItemStem.builder().id(id).content("提干" + id).build();
        ItemAnswer itemAnswer = ItemAnswer.builder().id(id).content("题目解析" + id).build();
        ItemAnalysis itemAnalysis = ItemAnalysis.builder().id(id).content("解题思路分析" + id).build();
        ItemComment itemComment = ItemComment.builder().id(id).content("试题点评" + id).build();

        Item item = Item.builder().id(id).itemStem(itemStem).analysis(itemAnalysis)
                .answer(itemAnswer).comment(itemComment).subject(subject).learnSegment(learnSegment)
                .build();
        return item;

    }


    @Test
    public void queryTestPapers() throws Exception {
        PageHelper.startPage(0, 5);
        List<TestPaper> testPapers = testPaperService.queryTestPapers(0, 0, null);
        PageInfo<TestPaper> page = new PageInfo<>(testPapers);

        System.out.println( page.getPageNum());
        System.out.println( page.getPageSize());
        System.out.println( page.getStartRow());
        System.out.println( page.getEndRow());
        System.out.println( page.getTotal());
        System.out.println( page.getPages());


        System.out.println();
    }
}