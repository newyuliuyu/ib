package com.ez.ib.web.utils;

import lombok.*;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * ClassName: DocxToHtmlInfo <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-12 下午2:38 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = {"saveDocxImageDir", "htmlImageRootPath"})
public class DocxToHtmlInfo {

    private InputStream in;
    private OutputStream out;
    private String saveDocxImageDir;
    private String htmlImageRootPath;

}
