package com.ez.ib.web.service;

import com.ez.ib.web.bean.Item;
import com.ez.ib.web.bean.ItemKnowledge;
import com.ez.ib.web.bean.ItemStem;

import java.util.List;

/**
 * ClassName: ItemService <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 上午10:32 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface ItemService {

    void udpateItemKnowledge(ItemKnowledge itemKnowledge);

    void updateItemItemStem(ItemStem itemStem);

    List<Item> queryItemWithTestPaper(long testPaperId);

    List<Item> queryItemWithKnowlege(List<Long> knowledgeIds,int addressType);


}
