package com.ez.ib.web.bean;

import lombok.*;

import java.util.List;

/**
 * ClassName: ItemKnowledge <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-25 下午3:03 <br/>
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
@EqualsAndHashCode(of = "id")
public class ItemKnowledge {
    private long id;
    private List<Knowledge> knowledges;
}
