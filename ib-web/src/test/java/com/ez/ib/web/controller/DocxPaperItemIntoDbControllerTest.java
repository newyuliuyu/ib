package com.ez.ib.web.controller;

import com.ez.ib.web.IbWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * ClassName: DocxPaperItemIntoDbControllerTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-15 下午1:23 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class DocxPaperItemIntoDbControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    @Test
    public void fetchDocxPaperItem() throws Exception {
        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get("/fetch/docx/paper/item")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .param("wordname", "22.docx");


        //.andDo(MockMvcResultHandlers.print())
//        MockMvcResultMatchers
        ResultActions ra = mvc.perform(req);
        byte data[] = ra.andReturn().getResponse().getContentAsByteArray();
        log.debug(new String(data));
    }
}