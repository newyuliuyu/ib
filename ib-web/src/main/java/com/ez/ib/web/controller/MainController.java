package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: MainController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-10 下午1:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@RestController
public class MainController {

    @RequestMapping("/index2")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ModelAndViewFactory.instance("index").build();
    }

    @RequestMapping("/")
    public ModelAndView index2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ModelAndViewFactory.instance("redirect:/index.html").build();
    }

}
