package com.ez.ib.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * ClassName: DocxToHtml <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-12 下午1:38 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DocxToHtml {

    public static void toHtml(InputStream in, OutputStream out, String saveDocxImageDir, String htmlImageRootPath) {
        DocxToHtmlInfo info = DocxToHtmlInfo.builder().in(in).out(out).saveDocxImageDir(saveDocxImageDir).htmlImageRootPath(htmlImageRootPath).build();
        DocxToHtmlHander docxToHtmlHander = new DocxToHtmlHander(info);
        try {
            docxToHtmlHander.toHtml();
        } catch (Exception e) {
            throw new RuntimeException("word转换成html出错", e);
        }
    }

    public static String toHtml(InputStream in, String saveDocxImageDir, String htmlImageRootPath) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        toHtml(in, out, saveDocxImageDir, htmlImageRootPath);
        byte[] bytes = out.toByteArray();
        return new String(bytes, Charset.forName("utf-8"));
    }

}
