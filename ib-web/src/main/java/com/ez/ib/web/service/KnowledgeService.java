package com.ez.ib.web.service;

import com.ez.ib.web.bean.Knowledge;
import com.ez.ib.web.bean.KnowledgeContentToId;
import com.ez.ib.web.bean.KnowledgeSystem;

import java.util.List;

/**
 * ClassName: KnowledgeService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-24 下午1:35 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface KnowledgeService {
    List<KnowledgeSystem> queryKnowledgeSystem();

    List<Knowledge> queryKnowledges(long knowledgeSystemId,
                                    int subjectId,
                                    long learnSegmentId);

    List<Knowledge> queryKnowledgesWithTestPaperId(long testPaperId);

    List<KnowledgeContentToId> queryKnowledgesWithContent(List<String> contents);


}
