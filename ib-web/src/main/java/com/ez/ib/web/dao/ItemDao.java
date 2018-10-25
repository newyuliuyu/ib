package com.ez.ib.web.dao;

import com.ez.ib.web.bean.*;
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

    void deleteItemKnowledge(@Param("itemKnowledge") ItemKnowledge itemKnowledge);
    void saveItemKnowLedge(@Param("itemKnowledge") ItemKnowledge itemKnowledge);


    List<Item> queryItemWithTestPaper(@Param("testPaperId") long testPaperId);

    Item getItem(@Param("id") long id);

    List<ItemStem> queryItemStems(@Param("ids") List<Long> ids);

    ItemStem getItemStem(@Param("id") long id);

    List<ItemAnalysis> queryItemAnalysises(@Param("ids") List<Long> ids);

    ItemAnalysis getItemAnalysis(@Param("id") long id);

    List<ItemAnswer> queryItemAnswers(@Param("ids") List<Long> ids);

    ItemAnswer getItemAnswers(@Param("id") long id);

    List<ItemComment> queryItemComments(@Param("ids") List<Long> ids);

    ItemComment getItemComment(@Param("id") long id);

    List<ItemKnowledge> queryItemKnowledge(@Param("ids") List<Long> ids);

    ItemKnowledge getItemKnowledge(@Param("id") long id);


}
