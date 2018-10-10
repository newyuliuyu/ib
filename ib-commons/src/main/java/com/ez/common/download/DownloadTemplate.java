package com.ez.common.download;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * ClassName: DownloadTemplate <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午10:50 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface DownloadTemplate {

    String name();

    void tmplate(OutputStream os, Map<String, String[]> params)throws IOException;
}
