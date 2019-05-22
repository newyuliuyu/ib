package com.ez.ib.web;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * ClassName: DownloadXiaoshu <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-4-25 下午1:09 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DownloadXiaoshu {

    private String url = "https://www.biquke.com/bq/0/990/";

    @Test
    public void test01() throws Exception {
        List<Article> articles = getArticleAddress();

        Path path = Paths.get("/home/liuyu/tmp/a3.txt");
        FileOutputStream out = FileUtils.openOutputStream(path.toFile(), true);
        boolean isContinue=false;
        for (Article article : articles) {
            if(article.title.equalsIgnoreCase("第八百四十五章 流放")){
                isContinue=true;
            }

            if(!isContinue){
                continue;
            }

            String html = readContent(article.addr);
            out.write((article.title + "=================\t\n\t\n").getBytes("UTF-8"));
            out.write((html + "\t\n\t\n").getBytes("UTF-8"));
            System.out.println(article.title + "=====" + html.length());
        }
        out.close();

    }

    @Test
    public void test02() throws Exception {
        String html = readContent("3248375.html");

    }

    private List<Article> getArticleAddress() throws Exception {
        String html = readHTML(url);
        Document doc = Jsoup.parse(html, "https://baidu.com/test/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");

        Elements elements = doc.select("#list dd a");

        List<Article> articles = Lists.newArrayList();
        elements.forEach(x -> {
            String addr = x.attr("href");
            String title = x.attr("title");
            Article article = new Article();
            article.addr = addr;
            article.title = title;
            articles.add(article);
        });
        return articles;
    }

    private String readContent(String fileName) throws Exception {
        String html = readHTML(url + fileName);
        Document doc = Jsoup.parse(html, "https://baidu.com/test/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");

        Elements elements = doc.select("#content");

        String result = elements.html();
        result = result.replaceAll("&#xa0;", "");
        result = result.replaceAll("<br />", "");
        result = result.replaceAll("<br/>", "");


        String[] texts = result.split("\n");

        StringBuilder sb = new StringBuilder();
        for (String txt : texts) {
            if (StringUtils.isNotBlank(txt.trim())) {
                sb.append(txt).append("\n");
            }
        }
        return sb.toString();
    }

    class Article {
        String addr;
        String title;
    }

    public String readHTML(String urlpath) throws Exception {
        URL url = new URL(urlpath);
        URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
        urlconn.connect();
        HttpURLConnection httpconn = (HttpURLConnection) urlconn;
        int filesize = urlconn.getContentLength(); // 取数据长度
        InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream(), "UTF-8");
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer buffer = new StringBuffer();
        String line; // 用来保存每行读取的内容
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append(" "); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
//        System.out.print(buffer.toString());

        ((HttpURLConnection) urlconn).disconnect();

        return buffer.toString();
    }
}
