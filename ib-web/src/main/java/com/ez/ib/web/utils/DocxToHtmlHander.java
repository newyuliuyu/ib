package com.ez.ib.web.utils;

import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.xhtml.DefaultContentHandlerFactory;
import fr.opensagres.poi.xwpf.converter.xhtml.IContentHandlerFactory;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import fr.opensagres.poi.xwpf.converter.xhtml.internal.XHTMLMapper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.xml.sax.ContentHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * ClassName: DocxToHtmlHander <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-12 下午1:59 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DocxToHtmlHander {
    private DocxToHtmlInfo info;


    public DocxToHtmlHander(DocxToHtmlInfo info) {
        this.info = info;
    }

    public void toHtml() throws IOException {
        Assert.notNull(info, "info 不能为null");

        XWPFDocument document = createXWPFDocument(info.getIn());
        try {
            XHTMLOptions options = createXHTMLOptions();
            ContentHandler contentHandler = createContentHandler(info.getOut(), null, options);
            covertToHtml(document, contentHandler, options);
        } finally {
            document.close();
        }
    }

    private XWPFDocument createXWPFDocument(InputStream is) throws IOException {
        XWPFDocument document = new XWPFDocument(is);
        return document;
    }

    private XHTMLOptions createXHTMLOptions() {

        Assert.isTrue(!StringUtils.isEmpty(info.getSaveDocxImageDir()), "saveDocxImageDir的值为空");
        Assert.isTrue(!StringUtils.isEmpty(info.getHtmlImageRootPath()), "htmlImageRootPath的值为空");

        XHTMLOptions options = XHTMLOptions.create();
//        ImageManager imageManager = new ImageManager(new File(info.getSaveDocxImageDir()), info.getHtmlImageRootPath());
        DocxAndHtmlImageManager imageManager = new DocxAndHtmlImageManager(info.getSaveDocxImageDir(), info.getHtmlImageRootPath());
        options.setImageManager(imageManager);
//        // 存放图片的文件夹
//        options.setExtractor(new FileImageExtractor(new File(info.getSaveDocxImageDir())));
//        // html中图片的路径
//        options.URIResolver(new BasicURIResolver(info.getHtmlImageRootPath()));
        return options;
    }

    private ContentHandler createContentHandler(OutputStream out, Writer writer, XHTMLOptions options) {
        IContentHandlerFactory factory = options.getContentHandlerFactory();
        if (factory == null) {
            factory = DefaultContentHandlerFactory.INSTANCE;
        }
        ContentHandler contentHandler = factory.create(out, writer, options);
        return contentHandler;
    }

    private void covertToHtml(XWPFDocument document, ContentHandler contentHandler, XHTMLOptions options) {
        try {
            options = options != null ? options : XHTMLOptions.getDefault();
            XHTMLMapper mapper = new XHTMLMapper(document, contentHandler, options);
            mapper.start();
        } catch (Exception e) {
            throw new XWPFConverterException(e);
        }
    }
}
