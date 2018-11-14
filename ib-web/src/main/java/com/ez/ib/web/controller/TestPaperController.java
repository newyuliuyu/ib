package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.HttpReqUtils;
import com.ez.common.util.IdGenerator;
import com.ez.ib.web.bean.Item;
import com.ez.ib.web.bean.ItemKnowledge;
import com.ez.ib.web.bean.TestPaper;
import com.ez.ib.web.bean.TestPaperItem;
import com.ez.ib.web.service.TestPaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        log.debug("searchTestPaper controller");

        long subjectId = HttpReqUtils.getParamLong(req, "subjectId");
        long learnSegmentId = HttpReqUtils.getParamLong(req, "learnSegmentId");
        String beginDate = HttpReqUtils.getParamString(req, "beginDate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long beginTimestamp = 0L;
        if (!StringUtils.isEmpty(beginDate)) {
            Date date = dateFormat.parse(beginDate + " 0:0:0");
            beginTimestamp = date.getTime();
        }
        String endDate = HttpReqUtils.getParamString(req, "endDate");
        long endTimestamp = 0L;
        if (!StringUtils.isEmpty(beginDate)) {
            Date date = dateFormat.parse(endDate + " 23:59:59");
            endTimestamp = date.getTime();
        }

        String name = HttpReqUtils.getParamString(req, "name");
        int pageNum = HttpReqUtils.getParamInt(req, "pageNum");
        if (pageNum == 0) {
            pageNum = 1;
        }
        int pageSize = HttpReqUtils.getParamInt(req, "pageSize");
        if (pageSize == 0) {
            pageSize = 10;
        }
        //1 只显示关联知识点的　2显示未关联知识点的
        int showRelationKnowledgeState = HttpReqUtils.getParamInt(req, "showRelationKnowledgeState");
        PageHelper.startPage(pageNum, pageSize);
        List<TestPaper> testPapers = testPaperService.queryTestPapers(subjectId,
                learnSegmentId,
                name,
                beginTimestamp,
                endTimestamp, showRelationKnowledgeState);
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

    @RequestMapping(value = "/update/itemKnowledge/{testPaperId}")
    public ModelAndView udpateItemKnowledge(@PathVariable long testPaperId,
                                            @RequestBody ItemKnowledge itemKnowledge,
                                            HttpServletRequest req,
                                            HttpServletResponse res) throws Exception {

        log.debug("udpateItemKnowledge controller");
        testPaperService.updateItemKnowledge(testPaperId, itemKnowledge);

        return ModelAndViewFactory.instance()
                .build();
    }

    @RequestMapping(value = "/update/itemKnowledges/{testPaperId}")
    public ModelAndView udpateTestPaperItemKnowledges(@PathVariable long testPaperId,
                                                      @RequestBody List<ItemKnowledge> itemKnowledges,
                                                      HttpServletRequest req,
                                                      HttpServletResponse res) throws Exception {

        log.debug("udpateTestPaperItemKnowledges controller");
        testPaperService.udpateTestPaperItemKnowledges(testPaperId, itemKnowledges);

        return ModelAndViewFactory.instance()
                .build();
    }

    @RequestMapping(value = "/del")
    public ModelAndView deleteTestPaper(@RequestBody long testPaperId,
                                        HttpServletRequest req,
                                        HttpServletResponse res) throws Exception {

        log.debug("deleteTestPaper controller");
        testPaperService.deleteTestPaper(testPaperId);
        return ModelAndViewFactory.instance()
                .build();
    }


}
