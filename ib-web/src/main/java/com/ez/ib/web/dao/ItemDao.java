package com.ez.ib.web.dao;

import com.ez.ib.web.bean.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: ItemDao <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午5:40 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Repository
public interface ItemDao {

    void saveItems(@Param("items") List<Item> items);

    void saveItemStems(@Param("items") List<Item> items);

    void saveItemAnalysis(@Param("items") List<Item> items);

    void saveItemAnswers(@Param("items") List<Item> items);

    void saveItemComments(@Param("items") List<Item> items);

}
