package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.ib.web.bean.LearnSegment;
import com.ez.ib.web.service.LearnSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: LearnSegmentController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午6:28 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */

@RestController
@RequestMapping(value = "/learnsegment")
public class LearnSegmentController {

    @Autowired
    private LearnSegmentService learnSegmentService;

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<LearnSegment> learnSegments = learnSegmentService.queryAllLearnSegment();
        return ModelAndViewFactory.instance().with("learnSegments", learnSegments).build();
    }
}
