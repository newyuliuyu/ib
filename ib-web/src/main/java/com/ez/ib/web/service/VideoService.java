package com.ez.ib.web.service;

import com.ez.ib.web.bean.Video;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: VideoService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-5-20 下午6:03 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface VideoService {
    Video queryWithSubjectAndKnowledge(@Param("subject") String subject,
                                       @Param("knowledge") String knowledge);
}
