package com.ez.ib.web.utils;

import com.ez.common.util.FileUtil;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import org.apache.poi.util.IOUtils;

import java.io.*;

/**
 * ClassName: DocxAndHtmlImageManager <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-30 上午10:05 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DocxAndHtmlImageManager extends ImageManager {
    private String saveDocxImageDir;
    private String htmlImageRootPath;

    public DocxAndHtmlImageManager(String saveDocxImageDir, String htmlImageRootPath) {
        super(new File(saveDocxImageDir), htmlImageRootPath);
        this.saveDocxImageDir = FileUtil.getPath(saveDocxImageDir);
        this.htmlImageRootPath = FileUtil.getPath(htmlImageRootPath);
    }


    @Override
    public void extract(String imagePath, byte[] imageData) throws IOException {
//        super.extract(imagePath, imageData);
        extract2(getFileName(imagePath), imageData);
    }

    public void extract2(String imagePath, byte[] imageData)
            throws IOException {
        File imageFile = new File(saveDocxImageDir, imagePath);
        imageFile.getParentFile().mkdirs();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new ByteArrayInputStream(imageData);
            out = new FileOutputStream(imageFile);
            IOUtils.copy(in, out);
        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
            if (out != null) {
                IOUtils.closeQuietly(out);
            }
        }
    }

    @Override
    public String resolve(String uri) {
//        return super.resolve(uri);
        return getHtmlURl(uri);
    }

    private String getHtmlURl(String imagePath) {
        return htmlImageRootPath + "/" + getFileName(imagePath);
    }

    private String getFileName(String imagePath) {
        return new File(imagePath).getName();
    }
}
