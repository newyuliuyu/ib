package com.ez.ib.web.service;

import com.ez.ib.web.bean.ItemKnowledge;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.bean.TestPaperItem;

import java.util.List;

/**
 * ClassName: TestPaperService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:57 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface TestPaperService {
    void newTestPaper(TestPaper testPaper);

    List<TestPaper> queryTestPapers(long subjectId,
                                    long learnSegmentId,
                                    String name,
                                    long beginTimestamp,
                                    long endTimestamp,
                                    int showRelationKnowledgeState);

    void savetestPaperItem(TestPaperItem testPaperItem);

    void updateTestPaperKnowledgeSystem(TestPaper testPaper);

    void updateItemKnowledge(long testPaperId, ItemKnowledge itemKnowledge);

    void udpateTestPaperItemKnowledges(long testPaperId, List<ItemKnowledge> itemKnowledges);

    void deleteTestPaper(long testpaperId);
}
