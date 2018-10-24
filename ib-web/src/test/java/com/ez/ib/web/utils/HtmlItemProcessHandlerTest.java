package com.ez.ib.web.utils;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: HtmlItemProcessHandlerTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-18 下午2:58 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class HtmlItemProcessHandlerTest {


    public String getHTML() throws Exception {
        Path docxfilepath = Paths.get("/home/liuyu/tmp/word2html/wordfile/123.docx");
        String saveDocxImageDir = "/home/liuyu/tmp/word2html";
        String htmlImageRootPath = "/home/liuyu/tmp/word2html";
        File docxfile = docxfilepath.toFile();
        FileInputStream in = new FileInputStream(docxfile);
        String html = DocxToHtml.toHtml(in, saveDocxImageDir, htmlImageRootPath);
        return html;
    }

    @Test
    public void testHtmlItemProcessHandler() throws Exception {
        String html = getHTML();
        HtmlItemProcessHandler handler = new HtmlItemProcessHandler(html);

        String result = handler.process();

        System.out.println(result);

    }
}