package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: KnowledgeContentToId <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-8 下午2:35 <br/>
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
public class KnowledgeContentToId {
    private String contents;
    private String ids;
    private boolean hasNotFind;
}
