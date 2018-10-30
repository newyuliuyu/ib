package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.HttpReqUtils;
import com.ez.ib.web.bean.Item;
import com.ez.ib.web.service.ItemService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: SearchItemController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-26 下午4:14 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Long> knowledgeIds = getknowledgeIds(request);

        int pageNum = HttpReqUtils.getParamInt(request, "pageNum");
        if (pageNum == 0) {
            pageNum = 1;
        }
        int pageSize = HttpReqUtils.getParamInt(request, "pageSize");
        if (pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Item> items = itemService.queryItemWithKnowlege(knowledgeIds);
        return ModelAndViewFactory.instance().with("items", items).build();
    }

    private List<Long> getknowledgeIds(HttpServletRequest req) {
        String knowlegeIds = HttpReqUtils.getParamString(req, "knowlegeIds");
        if (StringUtils.isEmpty(knowlegeIds)) {
            return null;
        }
        List<Long> dataset = Arrays
                .stream(knowlegeIds.split(","))
                .map(item -> Long.parseLong(item))
                .collect(Collectors.toList());
        return dataset;
    }
}
