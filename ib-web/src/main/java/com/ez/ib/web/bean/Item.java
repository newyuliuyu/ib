package com.ez.ib.web.bean;

import lombok.*;

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
    private long id;
    private ItemStem itemStem;
    private ItemAnalysis analysis;
    private ItemAnswer answer;
    private ItemComment comment;
    private Subject subject;
    private LearnSegment learnSegment;

    public void setAllId(long id){
        this.id = id;
        if(itemStem != null){
            itemStem.setId(id);
        }
        if(analysis != null){
            analysis.setId(id);
        }
        if(answer != null){
            answer.setId(id);
        }
        if(comment != null){
            comment.setId(id);
        }
    }
}
