package com.ez.ib.web.dao;

import com.ez.ib.web.bean.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: VideoDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-5-20 下午5:59 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface VideoDao {
    Video queryWithSubjectAndKnowledge(@Param("subject") String subject,
                                       @Param("knowledge") String knowledge);
}
