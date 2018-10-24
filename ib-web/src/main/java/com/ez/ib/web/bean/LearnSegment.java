package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: LearnSegment <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:39 <br/>
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
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id")
public class LearnSegment {
    private int id;
    private String name;
}
