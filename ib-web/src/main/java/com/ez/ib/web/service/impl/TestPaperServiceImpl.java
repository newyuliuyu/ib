package com.ez.ib.web.service.impl;

import com.ez.common.util.IdGenerator;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.bean.TestPaperItem;
import com.ez.ib.web.dao.ItemDao;
import com.ez.ib.web.dao.TestPaperDao;
import com.ez.ib.web.service.TestPaperService;
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

    @Override
    @Transactional
    public void newTestPaper(TestPaper testPaper) {
        log.debug("保存试卷");
        testPaperDao.newTestPaper(testPaper);
    }

    @Override
    @Transactional
    public void savetestPaperItem(TestPaperItem testPaperItem) {
        testPaperDao.newTestPaper(testPaperItem.getTestPaper());
        log.debug("保存试卷与题目关系");
        testPaperDao.saveTestPaperItem(testPaperItem);
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
    public List<TestPaper> queryTestPapers(long subjectId, long learnSegmentId, String name) {
        log.debug("执行查询题目数据");
        if (StringUtils.isEmpty(name)) {
            name = null;
        } else {
            name = "%" + name + "%";
        }
        return testPaperDao.queryTestPapers(subjectId, learnSegmentId, name);
    }

    @Override
    @Transactional
    public void updateTestPaperKnowledgeSystem(TestPaper testPaper) {
        Assert.notNull(testPaper.getKnowledgeSystem(), "知识体系不能为空");
        testPaperDao.updateTestPaperKnowledgeSystem(testPaper.getId(), testPaper.getKnowledgeSystem().getId());
    }
}
