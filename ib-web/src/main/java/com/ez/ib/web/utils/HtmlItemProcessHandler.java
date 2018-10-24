package com.ez.ib.web.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: ProcessItemHandler <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-18 下午2:47 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class HtmlItemProcessHandler {
    private String html;

    private String item = "^\\d+";
    private String analysisItem = "^【分析】";
    private String answerItem = "^【解答】";
    private String reviewItem = "^【点评】";
    // 创建 Pattern 对象
    private Pattern itemP = Pattern.compile(item);
    private Pattern analysisItemP = Pattern.compile(analysisItem);
    private Pattern answerItemP = Pattern.compile(answerItem);
    private Pattern reviewItemP = Pattern.compile(reviewItem);

    private Elements itemEs = new Elements();
    private Elements analysisItemEs = new Elements();
    private Elements answerItemEs = new Elements();
    private Elements reviewItemEs = new Elements();

    private int curState = 0;

    private Document doc;

    public HtmlItemProcessHandler(String html) {
        this.html = html;
    }

    public String process() {
        doc = Jsoup.parse(html);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().charset("UTF-8");
        doc.outputSettings().prettyPrint(false);

        Elements elements = getItemEs();
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

        Elements elements2 = doc.select("body");
        elements2.select("div").removeAttr("style");
        String result = elements2.html();
        return result;
    }

    private Elements getItemEs() {
        Elements elements = doc.select("body > div > p");
        return elements;
    }

    private void doWrap(Elements itemEs, Elements analysisItemEs, Elements answerItemEs, Elements reviewItemEs) {

        if (itemEs.size() > 0) {
//            itemEs.wrap("<div class=\"item\"></div>");
            Element wrap = null;
            for (Element e : itemEs) {
                if (wrap == null) {
                    wrap = e.wrap("<div class=\"ez-item\"></div>").parent();
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
                    wrap = e.wrap("<div class=\"ez-analysis-item\"></div>").parent();
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
                    wrap = e.wrap("<div class=\"ez-answer-item\"></div>").parent();
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
                    wrap = e.wrap("<div class=\"ez-review-item\"></div>").parent();
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
