package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.HttpReqUtils;
import com.ez.common.util.IdGenerator;
import com.ez.ib.web.bean.Item;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.bean.TestPaperItem;
import com.ez.ib.web.service.TestPaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: TestPaperController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-23 上午11:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@RequestMapping(value = "/testpaper")
@Slf4j
public class TestPaperController {
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private TestPaperService testPaperService;

    @RequestMapping(value = "/savetestpaperitem")
    public ModelAndView saveTestPaperItem(@RequestBody TestPaperItem testPaperItem,
                                          HttpServletRequest req,
                                          HttpServletResponse res) throws Exception {
        System.out.println();
        setTestPaperItemAllId(testPaperItem);
        testPaperService.savetestPaperItem(testPaperItem);
        return ModelAndViewFactory.instance().build();
    }


    private void setTestPaperItemAllId(TestPaperItem testPaperItem) {
        long id = idGenerator.nextId();
        TestPaper testPaper = testPaperItem.getTestPaper();
        testPaper.setId(id);
        for (Item item : testPaperItem.getItems()) {
            item.setSubject(testPaper.getSubject());
            item.setLearnSegment(testPaper.getLearnSegment());
            item.setAllId(id);
            id = idGenerator.nextId();
        }
    }


    @RequestMapping(value = "/search")
    public ModelAndView searchTestPaper(HttpServletRequest req,
                                        HttpServletResponse res) throws Exception {

        log.debug("查询题目controller");
        long subjectId = HttpReqUtils.getParamLong(req, "subjectId");
        long learnSegmentId = HttpReqUtils.getParamLong(req, "learnSegmentId");
        String name = HttpReqUtils.getParamString(req, "name");
        int pageNum = HttpReqUtils.getParamInt(req, "pageNum");
        if (pageNum == 0) {
            pageNum = 1;
        }
        int pageSize = HttpReqUtils.getParamInt(req, "pageSize");
        if (pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TestPaper> testPapers = testPaperService.queryTestPapers(subjectId, learnSegmentId, name);
        PageInfo<TestPaper> pageInfo = new PageInfo<>(testPapers);

        return ModelAndViewFactory.instance("/testpaper/list")
                .with("pageInfo", pageInfo)
                .with("testPapers", testPapers)
                .build();
    }

    @RequestMapping(value = "/update/knoledgesystem")
    public ModelAndView searchTestPaper(@RequestBody TestPaper testPaper,
                                        HttpServletRequest req,
                                        HttpServletResponse res) throws Exception {

        log.debug("searchTestPaper controller");
        testPaperService.updateTestPaperKnowledgeSystem(testPaper);
        
        return ModelAndViewFactory.instance()
                .build();
    }


}
