package com.ez.ib.web.utils;

import com.ez.common.spring.SpringContextUtil;
import com.ez.ib.web.bean.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ClassName: ItemUtils <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-9 下午5:01 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ItemUtils {
    private static final String ROOT_PATH_TAG = "\\$\\{\\{rootPath\\}\\}";

    public static void deleteHTMLRootPath(List<Item> items, String rootPath) {
        for (Item item : items) {
            deleteHTMLRootPath(item, rootPath);
        }
    }

    public static void deleteHTMLRootPath(Item item, String rootPath) {
        deleteHTMLRootPath(item.getItemStem(), rootPath);
        deleteHTMLRootPath(item.getAnalysis(), rootPath);
        deleteHTMLRootPath(item.getAnswer(), rootPath);
        deleteHTMLRootPath(item.getComment(), rootPath);

    }

    public static void deleteHTMLRootPath(ItemStem itemStem, String rootPath) {
        if (itemStem != null && !StringUtils.isEmpty(itemStem.getContent())) {
            String content = deleteHTMLRootPath(itemStem.getContent(), rootPath);
            itemStem.setContent(content);
        }
    }

    public static void deleteHTMLRootPath(ItemAnalysis itemAnalysis, String rootPath) {
        if (itemAnalysis != null && !StringUtils.isEmpty(itemAnalysis.getContent())) {
            String content = deleteHTMLRootPath(itemAnalysis.getContent(), rootPath);
            itemAnalysis.setContent(content);
        }
    }

    public static void deleteHTMLRootPath(ItemAnswer itemAnswer, String rootPath) {
        if (itemAnswer != null && !StringUtils.isEmpty(itemAnswer.getContent())) {
            String content = deleteHTMLRootPath(itemAnswer.getContent(), rootPath);
            itemAnswer.setContent(content);
        }
    }

    public static void deleteHTMLRootPath(ItemComment itemComment, String rootPath) {
        if (itemComment != null && !StringUtils.isEmpty(itemComment.getContent())) {
            String content = deleteHTMLRootPath(itemComment.getContent(), rootPath);
            itemComment.setContent(content);
        }
    }

    private static String deleteHTMLRootPath(String content, String rootPath) {
        return content.replaceAll(rootPath, ROOT_PATH_TAG);
    }

    public static void solveHTMLRootPath(List<Item> items, String rootPath) {
        EzConfig ezConfig = SpringContextUtil.getBean("ezConfig");
        for (Item item : items) {
            solveHTMLRootPath(item, rootPath);
        }
    }

    public static void solveHTMLRootPath(Item item, String rootPath) {
        solveHTMLRootPath(item.getItemStem(), rootPath);
        solveHTMLRootPath(item.getAnalysis(), rootPath);
        solveHTMLRootPath(item.getAnswer(), rootPath);
        solveHTMLRootPath(item.getComment(), rootPath);
    }

    public static void solveHTMLRootPath(ItemStem itemStem, String rootPath) {
        if (itemStem != null && !StringUtils.isEmpty(itemStem.getContent())) {
            String content = solveHTMLRootPath(itemStem.getContent(), rootPath);
            itemStem.setContent(content);
        }
    }

    public static void solveHTMLRootPath(ItemAnalysis itemAnalysis, String rootPath) {
        if (itemAnalysis != null && !StringUtils.isEmpty(itemAnalysis.getContent())) {
            String content = solveHTMLRootPath(itemAnalysis.getContent(), rootPath);
            itemAnalysis.setContent(content);
        }
    }

    public static void solveHTMLRootPath(ItemAnswer itemAnswer, String rootPath) {
        if (itemAnswer != null && !StringUtils.isEmpty(itemAnswer.getContent())) {
            String content = solveHTMLRootPath(itemAnswer.getContent(), rootPath);
            itemAnswer.setContent(content);
        }
    }

    public static void solveHTMLRootPath(ItemComment itemComment, String rootPath) {
        if (itemComment != null && !StringUtils.isEmpty(itemComment.getContent())) {
            String content = solveHTMLRootPath(itemComment.getContent(), rootPath);
            itemComment.setContent(content);
        }
    }

    public static String solveHTMLRootPath(String content, String rootPath) {
        return content.replaceAll(ROOT_PATH_TAG, rootPath);
    }


}
