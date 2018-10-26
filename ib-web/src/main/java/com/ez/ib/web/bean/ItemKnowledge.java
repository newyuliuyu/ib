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
    private Long id;
    private List<Knowledge> knowledges;

    public boolean isHasKnowledge() {
        return knowledges != null && !knowledges.isEmpty();
    }

    public String getKnowledgeIds() {
        if (isHasKnowledge()) {
            StringBuffer sb = new StringBuffer();
            for (Knowledge k : knowledges) {
                sb.append(k.getId()).append(",");
            }
            if (sb.length() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        return "";
    }

    public String getKnowledgeNames() {
        if (isHasKnowledge()) {
            StringBuffer sb = new StringBuffer();
            for (Knowledge k : knowledges) {
                sb.append(k.getContent()).append(",");
            }
            if (sb.length() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
        return "";
    }
}
