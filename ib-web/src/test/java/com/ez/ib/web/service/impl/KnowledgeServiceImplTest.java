package com.ez.ib.web.service.impl;

import com.ez.common.json.Json2;
import com.ez.ib.web.IbWebApplication;
import com.ez.ib.web.bean.KnowledgeContentToId;
import com.ez.ib.web.service.KnowledgeService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: KnowledgeServiceImplTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-29 下午2:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class KnowledgeServiceImplTest {

    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void queryKnowledgesWithContent() throws Exception {
        List<String> contents = Lists.newArrayList();
        contents.add("集合的交、并、补运算");
        contents.add("全称命题、特称命题");
        contents.add("函数零点的应用");
        contents.add("倒数的计算");
        contents.add("定积分的计算");
        contents.add("含逻辑连接词的命题真假判断");
        contents.add("函数奇偶性的判定");
        contents.add("函数图像的识别");
        contents.add("充分条件、必要条件的应用");
        contents.add("集合的包含关系");
        contents.add("倒数几何意义的应用");
        contents.add("导数的综合应用");
        contents.add("导数的综合应用|利用定积分计算平面图形的面积|求函数的单调区间|函数零点的个数|导数几何意义的应用|函数图像的应用");
        contents.add("利用函数研究导数的极值、最值——利用导数求函数的极值问题");
        contents.add("倒数几何意义的应用");
        contents.add("导数的综合应用");

        List<KnowledgeContentToId> KnowledgeContentToIds = knowledgeService.queryKnowledgesWithContent("","",contents);

//        for(Knowledge knowledge : knowledges){
//            String json = Json2.toJson(knowledge);
//
//            System.out.println(json);
//        }
//
//        List<Knowledge> knowledges1 = Lists.newArrayList();
//        for(Knowledge c:knowledges){
//            knowledges1.add(Knowledge.builder().id(c.getId()).content(c.getContent()).build());
//        }

        String json = Json2.toJson(KnowledgeContentToIds);
        System.out.println(json);

    }

}