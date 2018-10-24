package com.ez.ib.web.service;

import com.ez.ib.web.bean.Subject;

import java.util.List;

/**
 * ClassName: SubjectService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午6:22 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface SubjectService {
    List<Subject> queryAllSubjects();
}
