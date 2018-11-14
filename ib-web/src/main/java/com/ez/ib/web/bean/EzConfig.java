package com.ez.ib.web.bean;

import lombok.*;

/**
 * ClassName: EzConfig <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-9 下午5:04 <br/>
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
public class EzConfig {
    private String uploadFileDir;
    private String saveDocxImageDir;
    private String htmlImageRootPath;
}
