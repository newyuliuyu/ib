package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.Knowledge;
import com.ez.ib.web.bean.KnowledgeContentToId;
import com.ez.ib.web.bean.KnowledgeSystem;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.dao.KnowledgeDao;
import com.ez.ib.web.dao.TestPaperDao;
import com.ez.ib.web.service.KnowledgeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<KnowledgeContentToId> queryKnowledgesWithContent(List<String> contents) {
        if (contents == null || contents.isEmpty()) {
            return Lists.newArrayList();
        }

        List<String> uniqueContents = uniqueContents(contents);
        List<Knowledge> knowledges = knowledgeDao.queryKnowledgesWithContent(uniqueContents);
        Map<String, Knowledge> knowledgeMap = knowledges.stream().collect(Collectors.toMap(key -> key.getContent(), value -> value));

        List<KnowledgeContentToId> result = Lists.newArrayList();

        contents.stream().forEach(c -> {
            String[] values = getKnowledgeContents(c);
            StringBuilder contentValues = new StringBuilder();
            StringBuilder contentIds = new StringBuilder();
            boolean hasNotFind = false;
            for (String v : values) {

                Knowledge knowledge = knowledgeMap.get(v);
                String id = "无";
                if (knowledge != null) {
                    id = knowledge.getId().toString();
                } else {
                    hasNotFind = true;
                }
                contentValues.append(v).append("|");
                contentIds.append(id).append(",");
            }
            if (contentValues.length() > 0) {
                contentValues = contentValues.deleteCharAt(contentValues.length() - 1);
            }
            if (contentIds.length() > 0) {
                contentIds = contentIds.deleteCharAt(contentIds.length() - 1);
            }
            result.add(KnowledgeContentToId.builder()
                    .contents(contentValues.toString())
                    .hasNotFind(hasNotFind)
                    .ids(contentIds.toString())
                    .build());
        });
        return result;
    }

    private List<String> uniqueContents(List<String> contents) {
        Set<String> result = Sets.newHashSet();
        contents.stream().forEach(c -> {
            String[] values = getKnowledgeContents(c);
            for (String v : values) {
                result.add(v);
            }
        });
        return Lists.newArrayList(result);
    }

    private String[] getKnowledgeContents(String content) {
        return content.split("\\|");
    }
}
