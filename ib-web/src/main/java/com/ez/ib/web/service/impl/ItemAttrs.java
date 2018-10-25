package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.*;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * ClassName: ItemAttrs <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 下午2:14 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ItemAttrs {
    Map<Long, ItemStem> itemStemMap = Maps.newHashMap();
    Map<Long, ItemAnalysis> analysisMap = Maps.newHashMap();
    Map<Long, ItemAnswer> answerMap = Maps.newHashMap();
    Map<Long, ItemComment> commentMap = Maps.newHashMap();
    Map<Integer, Subject> subjectMap = Maps.newHashMap();
    Map<Integer, LearnSegment> learnSegmentMap = Maps.newHashMap();
    Map<Long, ItemKnowledge> knowledgeMap = Maps.newHashMap();

    public void addStem(List<ItemStem> itemStems) {
        if (itemStems == null) {
            return;
        }
        itemStems.stream().forEach(item -> itemStemMap.put(item.getId(), item));
    }

    public void addAnalysis(List<ItemAnalysis> itemAnalyses) {
        if (itemAnalyses == null) {
            return;
        }
        itemAnalyses.stream().forEach(item -> analysisMap.put(item.getId(), item));
    }

    public void addAnswers(List<ItemAnswer> itemAnswers) {
        if (itemAnswers == null) {
            return;
        }
        itemAnswers.stream().forEach(item -> answerMap.put(item.getId(), item));
    }

    public void addComment(List<ItemComment> itemComments) {
        if (itemComments == null) {
            return;
        }
        itemComments.stream().forEach(item -> commentMap.put(item.getId(), item));
    }

    public void addSubject(List<Subject> subjects) {
        if (subjects == null) {
            return;
        }
        subjects.stream().forEach(item -> subjectMap.put(item.getId(), item));
    }

    public void addLearnSegment(List<LearnSegment> learnSegments) {
        if (learnSegments == null) {
            return;
        }
        learnSegments.stream().forEach(item -> learnSegmentMap.put(item.getId(), item));
    }

    public void addItemKnowledge(List<ItemKnowledge> itemKnowledges) {
        if (itemKnowledges == null) {
            return;
        }
        itemKnowledges.stream().forEach(item -> knowledgeMap.put(item.getId(), item));
    }

    public ItemStem itemStem(ItemStem id) {
        if (id == null) {
            return null;
        }
        return itemStemMap.get(id.getId());
    }

    public ItemAnalysis itemAnalysis(ItemAnalysis id) {
        if (id == null) {
            return null;
        }
        return analysisMap.get(id.getId());
    }

    public ItemAnswer itemAnswer(ItemAnswer id) {
        if (id == null) {
            return null;
        }
        return answerMap.get(id.getId());
    }

    public ItemComment itemComment(ItemComment id) {
        if (id == null) {
            return null;
        }
        return commentMap.get(id.getId());
    }

    public Subject subject(Subject id) {
        if (id == null) {
            return null;
        }
        return subjectMap.get(id.getId());
    }

    public LearnSegment learnSegment(LearnSegment id) {
        if (id == null) {
            return null;
        }
        return learnSegmentMap.get(id.getId());
    }

    public ItemKnowledge itemKnowledge(ItemKnowledge id) {
        if (id == null) {
            return null;
        }
        return knowledgeMap.get(id.getId());
    }
}
