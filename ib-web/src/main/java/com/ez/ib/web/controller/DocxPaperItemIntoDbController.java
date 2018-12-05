package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.common.util.FileUtil;
import com.ez.common.util.IdGenerator;
import com.ez.ib.web.bean.EzConfig;
import com.ez.ib.web.utils.DocxToHtml;
import com.ez.ib.web.utils.HtmlItemProcessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: DocxPaperItemIntoDbController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-15 上午10:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
public class DocxPaperItemIntoDbController {

//    @Value("${upload.file.dir:''}")
//    private String uploadFileDir;
//    @Value("${save.docx.image.dir:''}")
//    private String saveDocxImageDir;
//    @Value("${html.image.root.path:''}")
//    private String htmlImageRootPath;

    @Autowired
    private EzConfig ezConfig;

    @Autowired
    private IdGenerator idGenerator;

    @RequestMapping("/fetch/docx/paper/item")
    public ModelAndView fetchDocxPaperItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String docxname = request.getParameter("wordname");
        if (StringUtils.isEmpty(docxname)) {
            throw new RuntimeException("没有指定word文件名称不能为");
        }


        checkParam();

        Path path = Paths.get(ezConfig.getUploadFileDir(), docxname);
        FileInputStream in = new FileInputStream(path.toFile());
        String result = "";
        try {
            long id = idGenerator.nextId();

            String html = DocxToHtml.toHtml(in,
                    FileUtil.getPath(ezConfig.getSaveDocxImageDir()) + "/" + id,
                    FileUtil.getPath(ezConfig.getHtmlImageRootPath()) + "/" + id);
            HtmlItemProcessHandler htmlItemProcessHandler = new HtmlItemProcessHandler(html);
            result = htmlItemProcessHandler.process();
            System.out.println(result);

        } finally {
            in.close();
            path.toFile().deleteOnExit();
        }

//        String result = "llll";

        return ModelAndViewFactory.instance().with("html", result).build();
    }

    private String processURL(String url) {
        if (url.endsWith("/") || url.endsWith("\\")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    private void checkParam() {
        if (StringUtils.isEmpty(ezConfig.getUploadFileDir())) {
            throw new RuntimeException("word文件的根路径(uploadFileDir)为空");
        }
        if (StringUtils.isEmpty(ezConfig.getSaveDocxImageDir())) {
            throw new RuntimeException("保存word文件图片的根路径(saveDocxImageDir)为空");
        }
        if (StringUtils.isEmpty(ezConfig.getHtmlImageRootPath())) {
            throw new RuntimeException("html文件访问图片的根路径(htmlImageRootPath)为空");
        }
    }

}
