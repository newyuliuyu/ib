package com.ez.ib.web.service.impl;

import com.ez.ib.web.bean.KnowledgeSystem;
import com.ez.ib.web.dao.KnowledgeDao;
import com.ez.ib.web.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: KnowledgeServiceImpl <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-24 下午1:36 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDao knowledgeDao;

    @Override
    public List<KnowledgeSystem> queryKnowledgeSystem() {
        return knowledgeDao.queryKnowledgeSystem();
    }
}
