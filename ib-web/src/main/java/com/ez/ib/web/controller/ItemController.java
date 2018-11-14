package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.ib.web.bean.Item;
import com.ez.ib.web.bean.ItemKnowledge;
import com.ez.ib.web.bean.ItemStem;
import com.ez.ib.web.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: ItemController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 下午4:16 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@Slf4j
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/search/{testPaperId}")
    public ModelAndView searchItemWithTestPaper(@PathVariable long testPaperId,
                                                HttpServletRequest req,
                                                HttpServletResponse res) throws Exception {
        log.debug("searchItemWithTestPaper controller...");
        List<Item> items = itemService.queryItemWithTestPaper(testPaperId);
        return ModelAndViewFactory.instance().with("items", items).build();
    }

    @RequestMapping(value = "/update/knowledge")
    public ModelAndView updateItemKnowledge(@RequestBody ItemKnowledge itemKnowledge,
                                            HttpServletRequest req,
                                            HttpServletResponse res) throws Exception {
        log.debug("updateItemKnowledge controller...");
        itemService.udpateItemKnowledge(itemKnowledge);
        return ModelAndViewFactory.instance().build();
    }

    @RequestMapping(value = "/update/itemstem")
    public ModelAndView updateItemItemStem(@RequestBody ItemStem itemStem,
                                           HttpServletRequest req,
                                           HttpServletResponse res) throws Exception {
        log.debug("updateItemKnowledge controller...");
        itemService.updateItemItemStem(itemStem);
        return ModelAndViewFactory.instance().build();
    }
}
