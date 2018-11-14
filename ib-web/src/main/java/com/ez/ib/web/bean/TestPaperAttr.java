package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: PaperTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:50 <br/>
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
public class TestPaperAttr {
    private Long testPaperId;
    private int itemNum;
    private int relationKnowledgeNum;
}
