package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.Item;
import com.ez.ib.web.bean.ItemKnowledge;
import com.ez.ib.web.dao.*;
import com.ez.ib.web.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * ClassName: ItemServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 上午10:33 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private KnowledgeDao knowledgeDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private LearnSegmentDao learnSegmentDao;
    @Autowired
    private TestPaperDao testPaperDao;

    @Override
    @Transactional
    public void udpateItemKnowledge(ItemKnowledge itemKnowledge) {
        Assert.isTrue(itemKnowledge.getKnowledges() != null && !itemKnowledge.getKnowledges().isEmpty(), "保存題目知识点不能没有知识点");
        itemDao.deleteItemKnowledge(itemKnowledge);
        itemDao.saveItemKnowLedge(itemKnowledge);
    }

    @Override
    public List<Item> queryItemWithTestPaper(long testPaperId) {
        List<Item> items = itemDao.queryItemWithTestPaper(testPaperId);
        setItemAttr(items);
        return items;
    }

    private void setItemAttr(List<Item> items) {
        ItemAttrs itemAttrs = getItemAttr(items);
        items.forEach(item -> {
            item.setItemStem(itemAttrs.itemStem(item.getItemStem()));
            item.setAnalysis(itemAttrs.itemAnalysis(item.getAnalysis()));
            item.setAnswer(itemAttrs.itemAnswer(item.getAnswer()));
            item.setComment(itemAttrs.itemComment(item.getComment()));
            item.setSubject(itemAttrs.subject(item.getSubject()));
            item.setLearnSegment(itemAttrs.learnSegment(item.getLearnSegment()));

            item.setItemKnowledge(itemAttrs.itemKnowledge(ItemKnowledge.builder().id(item.getId()).build()));
        });
    }

    private ItemAttrs getItemAttr(List<Item> items) {
        ItemAttrIds ids = extractIds(items);
        ItemAttrs itemAttrs = new ItemAttrs();
        if (!ids.itemStemIds.isEmpty()) {
            itemAttrs.addStem(itemDao.queryItemStems(ids.getItemStemIds()));
        }
        if (!ids.itemAnalysisIds.isEmpty()) {
            itemAttrs.addAnalysis(itemDao.queryItemAnalysises(ids.getAnalysisIds()));
        }
        if (!ids.itemAnswerIds.isEmpty()) {
            itemAttrs.addAnswers(itemDao.queryItemAnswers(ids.getAnswerIds()));
        }
        if (!ids.itemCommentIds.isEmpty()) {
            itemAttrs.addComment(itemDao.queryItemComments(ids.getCommentIds()));
        }
        if (!ids.subjectIds.isEmpty()) {
            itemAttrs.addSubject(subjectDao.querySubjectWitdhIds(ids.getSubjectIds()));
        }
        if (!ids.learnSegmentIds.isEmpty()) {
            itemAttrs.addLearnSegment(learnSegmentDao.queryLearnSegmentWithIds(ids.getLearnSegmentIds()));
        }
        if (!ids.knowledgeIds.isEmpty()) {
            itemAttrs.addItemKnowledge(itemDao.queryItemKnowledge(ids.getKnowledgeIds()));
        }
        return itemAttrs;
    }

    private ItemAttrIds extractIds(List<Item> items) {
        ItemAttrIds ids = new ItemAttrIds();
        items.stream().forEach(item -> {
            ids.addItemStem(item.getItemStem());
            ids.addItemAnalysis(item.getAnalysis());
            ids.addItemAnswer(item.getAnswer());
            ids.addItemComment(item.getComment());
            ids.addSubject(item.getSubject());
            ids.addLearnSegment(item.getLearnSegment());
            ids.addKnowledge(item.getItemKnowledge());
        });
        return ids;
    }
}
