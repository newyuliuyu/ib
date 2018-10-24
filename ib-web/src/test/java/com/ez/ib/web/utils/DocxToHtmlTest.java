package com.ez.ib.web.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: DocxToHtmlTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-12 下午2:51 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DocxToHtmlTest {

    @Test
    public void testDocxToHtml() throws Exception {
        Path docxfilepath = Paths.get("/home/liuyu/tmp/word2html/wordfile/22.docx");
        Path htmlfilepath = Paths.get("/home/liuyu/tmp/word2html/my.html");

        String saveDocxImageDir = "/home/liuyu/tmp/word2html";
        String htmlImageRootPath = "/home/liuyu/tmp/word2html";

        File docxfile = docxfilepath.toFile();
        File htmlfile = htmlfilepath.toFile();

        FileInputStream in = new FileInputStream(docxfile);
        FileOutputStream out = new FileOutputStream(htmlfile);
        DocxToHtml.toHtml(in, out, saveDocxImageDir, htmlImageRootPath);
    }
    @Test
    public void testDocxToHtmlString() throws Exception {
        Path docxfilepath = Paths.get("/home/liuyu/tmp/word2html/wordfile/22.docx");
//        Path htmlfilepath = Paths.get("/home/liuyu/tmp/word2html/my.html");

        String saveDocxImageDir = "/home/liuyu/tmp/word2html";
        String htmlImageRootPath = "/home/liuyu/tmp/word2html";

        File docxfile = docxfilepath.toFile();
//        File htmlfile = htmlfilepath.toFile();

        FileInputStream in = new FileInputStream(docxfile);
//        FileOutputStream out = new FileOutputStream(htmlfile);
        String html = DocxToHtml.toHtml(in, saveDocxImageDir, htmlImageRootPath);
        System.out.println("**********************************************************************");
        System.out.println(html);

        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("body");
       elements.select("div").removeAttr("style");
        System.out.println(elements.html());
    }

}