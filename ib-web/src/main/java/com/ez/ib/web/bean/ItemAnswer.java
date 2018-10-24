package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: ItemAnswer <br/>
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
@ToString(of = {"id", "content"})
@EqualsAndHashCode(of = "id")
public class ItemAnswer {
    //题目解答
    private long id;
    private String content;
}
