package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.ib.web.bean.Subject;
import com.ez.ib.web.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: SubjectController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午6:25 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Subject> subjects = subjectService.queryAllSubjects();
        return ModelAndViewFactory.instance().with("subjects", subjects).build();
    }
}
