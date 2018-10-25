package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.Knowledge;
import com.ez.ib.web.bean.KnowledgeSystem;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.dao.KnowledgeDao;
import com.ez.ib.web.dao.TestPaperDao;
import com.ez.ib.web.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * ClassName: KnowledgeServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-24 下午1:36 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDao knowledgeDao;

    @Autowired
    private TestPaperDao testPaperDao;

    @Override
    public List<KnowledgeSystem> queryKnowledgeSystem() {
        return knowledgeDao.queryKnowledgeSystem();
    }

    @Override
    public List<Knowledge> queryKnowledges(long knowledgeSystemId, int subjectId, long learnSegmentId) {
        return knowledgeDao.queryKnowledge(knowledgeSystemId, subjectId, learnSegmentId);
    }

    @Override
    public List<Knowledge> queryKnowledgesWithTestPaperId(long testPaperId) {
        TestPaper testPaper = testPaperDao.get(testPaperId);
        Assert.notNull(testPaper, "试卷不为null");
        Assert.notNull(testPaper.getKnowledgeSystem(), "试卷知识体系不为null");
        Assert.notNull(testPaper.getSubject(), "试卷所属科目不为null");
        Assert.notNull(testPaper.getLearnSegment(), "试卷所属学段不为null");
        return queryKnowledges(testPaper.getKnowledgeSystem().getId(), testPaper.getSubject().getId(), testPaper.getLearnSegment().getId());
    }
}
