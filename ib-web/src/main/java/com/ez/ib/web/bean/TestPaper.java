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
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id")
public class TestPaper {
    private Long id;
    private String name;
    private Subject subject;
    private LearnSegment learnSegment;
    private KnowledgeSystem knowledgeSystem;
}
