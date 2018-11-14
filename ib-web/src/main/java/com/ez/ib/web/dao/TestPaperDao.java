package com.ez.ib.web.dao;

import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.bean.TestPaperAttr;
import com.ez.ib.web.bean.TestPaperItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: TestPaperDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:52 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface TestPaperDao {
    void newTestPaper(@Param("testPaper") TestPaper testPaper);

    void saveTestPaperAttr(@Param("testPaperAttr") TestPaperAttr testPaperAttr);

    void saveTestPaperItem(@Param("testPaperItem") TestPaperItem testPaperItem);


    TestPaper get(@Param("id") long id);

    List<TestPaper> queryTestPapers(@Param("subjectId") long subjectId,
                                    @Param("learnSegmentId") long learnSegmentId,
                                    @Param("name") String name,
                                    @Param("beginTimestamp") long beginTimestamp,
                                    @Param("endTimestamp") long endTimestamp,
                                    @Param("showRelationKnowledgeState") int showRelationKnowledgeState);

    int updateTestPaperKnowledgeSystem(@Param("testPaperId") long testPaperId,
                                       @Param("knowledgeSystemId") long knowledgeSystemId);

    int updateTestPaperItemRelationKnowledgeNumAutomaticGrowth(@Param("testPaperId") long testPaperId);

    int updateTestPaperItemRelationKnowledgeNum(@Param("testPaperId") long testPaperId,
                                                @Param("relationKnowledgeNum") int relationKnowledgeNum);

    int deleteTestPaperItemItemstem(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItemItemanalysis(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItemItemanswer(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItemItemcomment(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItemItemknoledge(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItemItem(@Param("testPaperId") long testPaperId);

    int deleteTestPaperItem(@Param("testPaperId") long testPaperId);

    int deleteTestPaperAttr(@Param("testPaperId") long testPaperId);

    int deleteTestPaper(@Param("testPaperId") long testPaperId);
}
