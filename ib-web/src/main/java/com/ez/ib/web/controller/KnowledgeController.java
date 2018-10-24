package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.ib.web.bean.KnowledgeSystem;
import com.ez.ib.web.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: KnowledgeController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-24 下午1:40 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@Slf4j
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping(value = "/knowledgesystem/list")
    public ModelAndView queryKnowledgeSystem(HttpServletRequest req,
                                             HttpServletResponse res) throws Exception {
        log.debug("queryKnowledgeSystem controller...");
        List<KnowledgeSystem> knowledgeSystems = knowledgeService.queryKnowledgeSystem();
        return ModelAndViewFactory.instance().with("knowledgeSystems", knowledgeSystems).build();
    }
}
