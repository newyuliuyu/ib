package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: Video <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-5-20 下午5:58 <br/>
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
public class Video {
    private String url;
    private String videoUrl;
    private String subject;
    private String knowledge;
}
