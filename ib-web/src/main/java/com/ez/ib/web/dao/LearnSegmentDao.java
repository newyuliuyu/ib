package com.ez.ib.web.dao;

import com.ez.ib.web.bean.LearnSegment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: LearnSegmentDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:46 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface LearnSegmentDao {
    List<LearnSegment> queryAllLearnSegment();
}
