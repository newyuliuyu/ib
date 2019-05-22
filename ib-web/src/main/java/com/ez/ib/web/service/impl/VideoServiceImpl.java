package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.Video;
import com.ez.ib.web.dao.VideoDao;
import com.ez.ib.web.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: VideoServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-5-20 下午6:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;

    @Override
    public Video queryWithSubjectAndKnowledge(String subject, String knowledge) {
        return videoDao.queryWithSubjectAndKnowledge(subject, knowledge);
    }
}
