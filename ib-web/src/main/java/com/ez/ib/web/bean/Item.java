package com.ez.ib.web.bean;

import lombok.*;

import java.util.Date;

/**
 * ClassName: Item <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午4:54 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private Long id;
    private ItemStem itemStem;
    private ItemAnalysis analysis;
    private ItemAnswer answer;
    private ItemComment comment;
    private Subject subject;
    private LearnSegment learnSegment;
    private ItemKnowledge itemKnowledge;
    private long timestamp = 0L;

    public ItemKnowledge getItemKnowledge() {
        if (itemKnowledge == null) {
            return ItemKnowledge.builder().id(id).build();
        }
        return itemKnowledge;
    }

    public void setAllId(long id) {
        this.id = id;
        if (itemStem != null) {
            itemStem.setId(id);
        }
        if (analysis != null) {
            analysis.setId(id);
        }
        if (answer != null) {
            answer.setId(id);
        }
        if (comment != null) {
            comment.setId(id);
        }
    }

    public void setCreateDate(Date date) {
        this.timestamp = date.getTime();
    }

    public Date getCreateDate() {
        return new Date(timestamp);
    }
}
