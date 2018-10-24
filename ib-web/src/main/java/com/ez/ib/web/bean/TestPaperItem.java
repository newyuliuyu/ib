package com.ez.ib.web.bean;

import lombok.*;

import java.util.List;

/**
 * ClassName: TestPaperItem <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午4:57 <br/>
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
public class TestPaperItem {
    private TestPaper testPaper;
    private List<Item> items;
}
