package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.LearnSegment;
import com.ez.ib.web.dao.LearnSegmentDao;
import com.ez.ib.web.service.LearnSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: LearnSegmentServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午6:24 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class LearnSegmentServiceImpl implements LearnSegmentService {

    @Autowired
    private LearnSegmentDao learnSegmentDao;

    @Override
    public List<LearnSegment> queryAllLearnSegment() {
        return learnSegmentDao.queryAllLearnSegment();
    }
}
