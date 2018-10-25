package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * ClassName: ItemAttrIds <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 下午2:15 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ItemAttrIds {
    Set<Long> itemStemIds = Sets.newHashSet();
    Set<Long> itemAnalysisIds = Sets.newHashSet();
    Set<Long> itemAnswerIds = Sets.newHashSet();
    Set<Long> itemCommentIds = Sets.newHashSet();
    Set<Integer> subjectIds = Sets.newHashSet();
    Set<Integer> learnSegmentIds = Sets.newHashSet();
    Set<Long> knowledgeIds = Sets.newHashSet();

    public void addItemStem(ItemStem itemStem) {
        if (itemStem != null && itemStem.getId() > 0) {
            itemStemIds.add(itemStem.getId());
        }
    }

    public void addItemAnalysis(ItemAnalysis itemAnalysis) {
        if (itemAnalysis != null && itemAnalysis.getId() > 0) {
            itemAnalysisIds.add(itemAnalysis.getId());
        }
    }

    public void addItemAnswer(ItemAnswer itemAnswer) {
        if (itemAnswer != null && itemAnswer.getId() > 0) {
            itemAnswerIds.add(itemAnswer.getId());
        }
    }

    public void addItemComment(ItemComment itemComment) {
        if (itemComment != null && itemComment.getId() > 0) {
            itemCommentIds.add(itemComment.getId());
        }
    }

    public void addSubject(Subject subject) {
        if (subject != null && subject.getId() > 0) {
            subjectIds.add(subject.getId());
        }
    }

    public void addLearnSegment(LearnSegment learnSegment) {
        if (learnSegment != null && learnSegment.getId() > 0) {
            learnSegmentIds.add(learnSegment.getId());
        }
    }

    public void addKnowledge(ItemKnowledge itemKnowledge) {
        if (itemKnowledge != null && itemKnowledge.getId() > 0) {
            knowledgeIds.add(itemKnowledge.getId());
        }
    }

    public List<Long> getItemStemIds() {
        return Lists.newArrayList(itemStemIds);
    }

    public List<Long> getAnalysisIds() {
        return Lists.newArrayList(itemAnalysisIds);
    }

    public List<Long> getAnswerIds() {
        return Lists.newArrayList(itemAnswerIds);
    }

    public List<Long> getCommentIds() {
        return Lists.newArrayList(itemCommentIds);
    }

    public List<Integer> getSubjectIds() {
        return Lists.newArrayList(subjectIds);
    }

    public List<Integer> getLearnSegmentIds() {
        return Lists.newArrayList(learnSegmentIds);
    }

    public List<Long> getKnowledgeIds() {
        return Lists.newArrayList(knowledgeIds);
    }
}
