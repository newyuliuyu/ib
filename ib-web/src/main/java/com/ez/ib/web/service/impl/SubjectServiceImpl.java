package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.Subject;
import com.ez.ib.web.dao.SubjectDao;
import com.ez.ib.web.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SubjectServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午6:23 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> queryAllSubjects() {
        return subjectDao.queryAllSubjects();
    }
}
