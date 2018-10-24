package com.ez.ib.web;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: JsoupTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-18 上午10:07 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class JsoupTest {

    @Test
    public void testBaseURL() throws Exception {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"aa\">" +
                "<img src=\"../base.png\">\n" +
                "</div></body>\n" +
                "</html>";

        Document doc = Jsoup.parse(html, "https://baidu.com/test/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");


        Elements elements = doc.select("img");
        for (Element e : elements) {

            System.out.println(e.attr("abs:src"));

        }

    }
    @Test
    public void showTab() throws Exception {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<span>\t\tjj    jj</span>" +
                "</body>\n" +
                "</html>";

        html="<span>   jj    jj</span>";

        Document doc = Jsoup.parse(html, "https://baidu.com/test/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");
//        doc.outputSettings().prettyPrint(false);

//        Elements e = doc.select("body");
//        String html1 = e.html();
        System.out.println(doc.html());


    }

    @Test
    public void testWarpURL() throws Exception {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
//                "<img src=\"./base1.png\">\n" +
//                "<img src=\"./base2.png\">\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">1．已知a=（1，sin</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">x），b=（2，sin2x），其中x∈（0，π）．若|a•b|=|a|•|b|，则tanx的值等于（　　）</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">A．1 B．﹣1 C．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image1.png\" style=\"width:15.0pt;height:13.5pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\"> D．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image2.png\" style=\"width:17.25pt;height:27.75pt;\"></p>\n" +
                "</body>\n" +
                "</html>";

        Document doc = Jsoup.parse(html, "https://baidu.com/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");

        Elements elements = doc.select("p");
        Element warp = null;
        for (Element e : elements) {
//            System.out.println(e.html());
            if (warp == null) {
                warp = e.wrap("<div class=\"cc\"></div>").parent();
            } else {
                warp.appendChild(e);
            }

            System.out.println(warp.outerHtml());

        }
        log.debug("*******************************************");
        String result = doc.html();
        log.debug(result);
    }

    @Test
    public void parseItem() throws Exception {
        String html = "<div>\n" +
                " <p style=\"text-align:center;white-space:pre-wrap;\"><span style=\"font-size:17.0pt;font-weight:bold;white-space:pre-wrap;\">平面向量的数量积</span></p>\n" +
                " <p style=\"text-align:center;white-space:pre-wrap;\"><span style=\"font-size:8.0pt;font-weight:bold;white-space:pre-wrap;\">参考答案与试题解析</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">　</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">一</span><span style=\"font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">．选择题（共26小题）</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">1．已知a=（1，sin</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">x），b=（2，sin2x），其中x∈（0，π）．若|a•b|=|a|•|b|，则tanx的值等于（　　）</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">A．1 B．﹣1 C．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image1.png\" style=\"width:15.0pt;height:13.5pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\"> D．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image2.png\" style=\"width:17.25pt;height:27.75pt;\"></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【分析】先由条件</span><img src=\"/home/liuyu/tmp/word2html/word/media/image3.png\" style=\"width:30.0pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">=</span><img src=\"/home/liuyu/tmp/word2html/word/media/image4.png\" style=\"width:43.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">，判断</span><img src=\"/home/liuyu/tmp/word2html/word/media/image5.png\" style=\"width:28.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">；再利用两向量共线的坐标关系列x的三角等式；最后根据倍角公式与</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">弦切互</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">化公式求出答案．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【解答】解：因为</span><img src=\"/home/liuyu/tmp/word2html/word/media/image6.png\" style=\"width:100.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">，且</span><img src=\"/home/liuyu/tmp/word2html/word/media/image3.png\" style=\"width:30.0pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">=</span><img src=\"/home/liuyu/tmp/word2html/word/media/image7.png\" style=\"width:43.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">则cosθ=±1，即</span><img src=\"/home/liuyu/tmp/word2html/word/media/image8.png\" style=\"width:28.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">所以sin2x=2sin</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">x，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">即2sinxcosx=2sin</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">x，而x∈（0，π），</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">所以sinx=cosx，即tanx=1．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">故选：A．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【点评】本题考查向量数量积公式与两向量共线的条件，同时考查倍角公式及弦切互化公式．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">　</span></p>\n" +
                " <p><span style=\"font-family:'宋体';font-size:12.0pt;white-space:pre-wrap;\">2．已知|</span><img src=\"/home/liuyu/tmp/word2html/word/media/image9.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;white-space:pre-wrap;\">|=2，|</span><img src=\"/home/liuyu/tmp/word2html/word/media/image10.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">|=4，向量</span><img src=\"/home/liuyu/tmp/word2html/word/media/image11.png\" style=\"width:7.5pt;height:13.5pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">与</span><img src=\"/home/liuyu/tmp/word2html/word/media/image12.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">的夹角为60°，当（</span><img src=\"/home/liuyu/tmp/word2html/word/media/image13.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">+3</span><img src=\"/home/liuyu/tmp/word2html/word/media/image12.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">）⊥（k</span><img src=\"/home/liuyu/tmp/word2html/word/media/image13.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">﹣</span><img src=\"/home/liuyu/tmp/word2html/word/media/image12.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">）时，实数k的值是（　　）</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">A．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image14.png\" style=\"width:9.75pt;height:26.25pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\"> B．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image15.png\" style=\"width:9.75pt;height:26.25pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\"> C．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image16.png\" style=\"width:15.75pt;height:26.25pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\"> D．</span><img src=\"/home/liuyu/tmp/word2html/word/media/image17.png\" style=\"width:15.75pt;height:26.25pt;\"></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【分析】利用向量的数量积公式求出两个向量的数量积，利用向量垂直的充要条件列出方程，求出k的值．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【解答】解：依题意得</span><img src=\"/home/liuyu/tmp/word2html/word/media/image18.png\" style=\"width:22.5pt;height:15.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;white-space:pre-wrap;\">=|</span><img src=\"/home/liuyu/tmp/word2html/word/media/image19.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;white-space:pre-wrap;\">|•|</span><img src=\"/home/liuyu/tmp/word2html/word/media/image20.png\" style=\"width:7.5pt;height:15.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;white-space:pre-wrap;\">|•cos60°=2×4×</span><img src=\"/home/liuyu/tmp/word2html/word/media/image21.png\" style=\"width:9.75pt;height:26.25pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">=4，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">因为（</span><img src=\"/home/liuyu/tmp/word2html/word/media/image22.png\" style=\"width:28.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">）⊥（</span><img src=\"/home/liuyu/tmp/word2html/word/media/image23.png\" style=\"width:30.0pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">），</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">所以</span><img src=\"/home/liuyu/tmp/word2html/word/media/image24.png\" style=\"width:103.5pt;height:15.0pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">得ka</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">+（3k﹣1）a•b﹣3b</span><span style=\"font-family:'Calibri';font-size:12.0pt;vertical-align:super;\">2</span><span style=\"font-size:12.0pt;white-space:pre-wrap;\">=0，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">即k+3k﹣1﹣12=0，</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">解得k=</span><img src=\"/home/liuyu/tmp/word2html/word/media/image25.png\" style=\"width:15.75pt;height:26.25pt;\"><span style=\"font-size:12.0pt;white-space:pre-wrap;\">．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">故选：C．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">【点评】解决向量垂直的问题，应该利用向量垂直的充要条件：数量积为0即向量的坐标对应的乘积和为0．</span></p>\n" +
                " <p><span style=\"font-size:12.0pt;white-space:pre-wrap;\">　</span></p>\n" +
                " <p></p>\n" +
                "</div>";


        Document doc = Jsoup.parse(html, "https://baidu.com/");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");


        String item = "^\\d+";
        String analysisItem = "^【分析】";
        String answerItem = "^【解答】";
        String reviewItem = "^【点评】";
        // 创建 Pattern 对象
        Pattern itemP = Pattern.compile(item);
        Pattern analysisItemP = Pattern.compile(analysisItem);
        Pattern answerItemP = Pattern.compile(answerItem);
        Pattern reviewItemP = Pattern.compile(reviewItem);

        Elements itemEs = new Elements();
        Elements analysisItemEs = new Elements();
        Elements answerItemEs = new Elements();
        Elements reviewItemEs = new Elements();

        int curState = 0;

        Elements elements = doc.select("body > div > p");
        log.debug(elements.size() + "");
        for (Element e : elements) {
            String text = e.text().trim();
            System.out.println(text);

            Matcher itemM = itemP.matcher(text);
            Matcher analysisItemM = analysisItemP.matcher(text);
            Matcher answerItemM = answerItemP.matcher(text);
            Matcher reviewItemM = reviewItemP.matcher(text);

            if (itemM.find() && itemM.start() == 0) {
                doWrap(itemEs, analysisItemEs, answerItemEs, reviewItemEs);

                itemEs = new Elements();
                analysisItemEs = new Elements();
                answerItemEs = new Elements();
                reviewItemEs = new Elements();

                curState = 1;
            } else if (analysisItemM.find() && analysisItemM.start() == 0) {
                curState = 2;
            } else if (answerItemM.find() && answerItemM.start() == 0) {
                curState = 3;
            } else if (reviewItemM.find() && reviewItemM.start() == 0) {
                curState = 4;
            }
            if (curState == 1) {
                itemEs.add(e);
            } else if (curState == 2) {
                analysisItemEs.add(e);
            } else if (curState == 3) {
                answerItemEs.add(e);
            } else if (curState == 4) {
                reviewItemEs.add(e);
            }


            // 现在创建 matcher 对象
//            Matcher m = r.matcher(text);


        }
        doWrap(itemEs, analysisItemEs, answerItemEs, reviewItemEs);
        log.debug(doc.html());

    }

    private void doWrap(Elements itemEs, Elements analysisItemEs, Elements answerItemEs, Elements reviewItemEs) {

        if (itemEs.size() > 0) {
//            itemEs.wrap("<div class=\"item\"></div>");
            Element wrap = null;
            for (Element e : itemEs) {
                if (wrap == null) {
                    wrap = e.wrap("<div class=\"item\"></div>").parent();
                } else {
                    wrap.appendChild(e);
                }
            }
            if (wrap != null) {
                wrap.after("<br/>");
            }
        }
        if (itemEs.size() > 0) {
//            analysisItemEs.wrap("<div class=\"analysisItem\"></div>");
            Element wrap = null;
            for (Element e : analysisItemEs) {
                if (wrap == null) {
                    wrap = e.wrap("<div class=\"analysis-item\"></div>").parent();
                } else {
                    wrap.appendChild(e);
                }
            }
            if (wrap != null) {
                wrap.after("<br/>");
            }
        }
        if (answerItemEs.size() > 0) {
//            answerItemEs.wrap("<div class=\"answerItem\"></div>");
            Element wrap = null;
            for (Element e : answerItemEs) {
                if (wrap == null) {
                    wrap = e.wrap("<div class=\"answer-item\"></div>").parent();
                } else {
                    wrap.appendChild(e);
                }
            }
            if (wrap != null) {
                wrap.after("<br/>");
            }
        }
        if (reviewItemEs.size() > 0) {
//            reviewItemEs.wrap("<div class=\"reviewItem\"></div>");
            Element wrap = null;
            for (Element e : reviewItemEs) {
                if (wrap == null) {
                    wrap = e.wrap("<div class=\"review-item\"></div>").parent();
                } else {
                    wrap.appendChild(e);
                }
            }
            if (wrap != null) {
                wrap.after("<br/>");
            }
        }
    }
}
