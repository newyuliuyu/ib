package com.ez.ib.web.dao;

import com.ez.ib.web.bean.KnowledgeSystem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: KnowledgeDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-24 下午1:36 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface KnowledgeDao {
    List<KnowledgeSystem> queryKnowledgeSystem();
}
