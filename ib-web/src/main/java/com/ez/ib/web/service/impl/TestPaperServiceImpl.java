package com.ez.ib.web.service.impl;

import com.ez.common.util.IdGenerator;
import com.ez.ib.web.bean.*;
import com.ez.ib.web.dao.ItemDao;
import com.ez.ib.web.dao.TestPaperDao;
import com.ez.ib.web.service.TestPaperService;
import com.ez.ib.web.utils.ItemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ClassName: TestPaperServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
@Slf4j
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired
    private TestPaperDao testPaperDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private EzConfig ezConfig;

    @Override
    @Transactional
    public void newTestPaper(TestPaper testPaper) {
        log.debug("保存试卷");
        testPaper.setTimestamp(System.currentTimeMillis());
        testPaperDao.newTestPaper(testPaper);
        testPaperDao.saveTestPaperAttr(testPaper.getTestPaperAttr());
    }

    @Override
    @Transactional
    public void savetestPaperItem(TestPaperItem testPaperItem) {
        TestPaper testPaper = testPaperItem.getTestPaper();
        TestPaperAttr testPaperAttr = TestPaperAttr.builder()
                .testPaperId(testPaper.getId())
                .itemNum(testPaperItem.getItems().size())
                .build();
        testPaper.setTestPaperAttr(testPaperAttr);

        this.newTestPaper(testPaper);


        log.debug("保存试卷与题目关系");
        testPaperDao.saveTestPaperItem(testPaperItem);
        log.debug("处理题目html地址访问");
        ItemUtils.deleteHTMLRootPath(testPaperItem.getItems(), ezConfig.getHtmlImageRootPath());
        log.debug("保存题目数据");
        itemDao.saveItems(testPaperItem.getItems());
        log.debug("保存题目题干信息");
        itemDao.saveItemStems(testPaperItem.getItems());
        log.debug("保存题目解析思路分析");
        itemDao.saveItemAnalysis(testPaperItem.getItems());
        log.debug("保存题目解答");
        itemDao.saveItemAnswers(testPaperItem.getItems());
        log.debug("保存题目点评");
        itemDao.saveItemComments(testPaperItem.getItems());
    }

    @Override
    public List<TestPaper> queryTestPapers(long subjectId,
                                           long learnSegmentId,
                                           String name,
                                           long beginTimestamp,
                                           long endTimestamp,
                                           int showRelationKnowledgeState) {
        log.debug("执行查询题目数据");
        if (StringUtils.isEmpty(name)) {
            name = null;
        } else {
            name = "%" + name + "%";
        }
        return testPaperDao.queryTestPapers(subjectId, learnSegmentId, name, beginTimestamp, endTimestamp, showRelationKnowledgeState);
    }

    @Override
    @Transactional
    public void updateTestPaperKnowledgeSystem(TestPaper testPaper) {
        Assert.notNull(testPaper.getKnowledgeSystem(), "知识体系不能为空");
        testPaperDao.updateTestPaperKnowledgeSystem(testPaper.getId(), testPaper.getKnowledgeSystem().getId());
    }

    @Override
    @Transactional
    public void updateItemKnowledge(long testPaperId, ItemKnowledge itemKnowledge) {
        Assert.isTrue(itemKnowledge.getKnowledges() != null && !itemKnowledge.getKnowledges().isEmpty(), "保存題目知识点不能没有知识点");
        int deleteItemKnowledgeNum = itemDao.deleteItemKnowledge(itemKnowledge);
        itemDao.saveItemKnowLedge(itemKnowledge);
        if (deleteItemKnowledgeNum == 0) {
            testPaperDao.updateTestPaperItemRelationKnowledgeNumAutomaticGrowth(testPaperId);
        }
    }

    @Override
    @Transactional
    public void udpateTestPaperItemKnowledges(long testPaperId, List<ItemKnowledge> itemKnowledges) {
        Assert.isTrue(!itemKnowledges.isEmpty(), "试卷题的题目关联的知识点不能为空");
        itemDao.deleteItemKnowledges(itemKnowledges);
        itemDao.saveItemKnowLedges(itemKnowledges);
        testPaperDao.updateTestPaperItemRelationKnowledgeNum(testPaperId, itemKnowledges.size());
    }

    @Override
    @Transactional
    public void deleteTestPaper(long testpaperId) {
        testPaperDao.deleteTestPaperItemItemstem(testpaperId);
        testPaperDao.deleteTestPaperItemItemanalysis(testpaperId);
        testPaperDao.deleteTestPaperItemItemanswer(testpaperId);
        testPaperDao.deleteTestPaperItemItemcomment(testpaperId);
        testPaperDao.deleteTestPaperItemItemknoledge(testpaperId);
        testPaperDao.deleteTestPaperItemItem(testpaperId);
        testPaperDao.deleteTestPaperItem(testpaperId);
        testPaperDao.deleteTestPaperAttr(testpaperId);
        testPaperDao.deleteTestPaper(testpaperId);
    }
}
