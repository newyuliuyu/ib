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

    int saveItems(@Param("items") List<Item> items);

    int saveItemStems(@Param("items") List<Item> items);

    int saveItemAnalysis(@Param("items") List<Item> items);

    int saveItemAnswers(@Param("items") List<Item> items);

    int saveItemComments(@Param("items") List<Item> items);

    int updateItemItemStem(@Param("itemStem") ItemStem itemStem);

    int saveItemKnowLedge(@Param("itemKnowledge") ItemKnowledge itemKnowledge);

    int saveItemKnowLedges(@Param("itemKnowledges") List<ItemKnowledge> itemKnowledges);

    int deleteItemKnowledge(@Param("itemKnowledge") ItemKnowledge itemKnowledge);

    int deleteItemKnowledges(@Param("itemKnowledges") List<ItemKnowledge> itemKnowledges);


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

    List<Long> queryItemIdWithKnowledge(@Param("ids") List<Long> ids);

    List<Item> queryItemWithIds(@Param("ids") List<Long> ids);


}
