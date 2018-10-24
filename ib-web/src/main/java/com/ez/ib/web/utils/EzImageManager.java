package com.ez.ib.web.utils;

import fr.opensagres.poi.xwpf.converter.core.ImageManager;

import java.io.File;

/**
 * ClassName: EzImageManager <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-15 上午10:04 <br/>
 * 来源与fr.opensagres.poi.xwpf.converter.core.ImageManager
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class EzImageManager extends ImageManager {

    public EzImageManager(File saveDocxImageDir, String htmlfilepath) {
        super(saveDocxImageDir, htmlfilepath);
    }



}
