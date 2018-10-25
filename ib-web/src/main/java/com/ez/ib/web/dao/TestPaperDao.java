package com.ez.ib.web.dao;

import com.ez.ib.web.bean.TestPaper;
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

    void saveTestPaperItem(@Param("testPaperItem") TestPaperItem testPaperItem);


    TestPaper get(@Param("id") long id);

    List<TestPaper> queryTestPapers(@Param("subjectId") long subjectId,
                                    @Param("learnSegmentId") long learnSegmentId,
                                    @Param("name") String name);

    void updateTestPaperKnowledgeSystem(@Param("testPaperId") long testPaperId,
                                        @Param("knowledgeSystemId") long knowledgeSystemId);
}
