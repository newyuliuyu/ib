package fr.opensagres.poi.xwpf.converter.xhtml.internal;

import org.apache.commons.io.output.NullOutputStream;
import uk.ac.ed.ph.snuggletex.jeuclid.SimpleMathMLImageSavingCallback;

import java.io.File;
import java.io.OutputStream;

/**
 * ClassName: ImageSavingCallback <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-9-25 下午5:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ImageSavingCallback extends SimpleMathMLImageSavingCallback {
    private File outputFile;
    private OutputStream out;
    private Throwable failure;

    public Throwable getFailure() {
        return failure;
    }

    public ImageSavingCallback(final File outputFile) {
        this.outputFile = outputFile;
        this.failure = null;
    }

    public ImageSavingCallback(final OutputStream out) {
        this.out = out;
        this.failure = null;
    }

    @Override
    public File getImageOutputFile(int mathmlCounter) {
        /* Valid input only produces 1 image, so ignore all others */
        System.out.println("********************getImageOutputFile************************************");
        return mathmlCounter == 0 ? outputFile : null;
    }

    @Override
    public OutputStream getImageOutputStream(int mathmlCounter) {
        /* (Not used here) */
        System.out.println("********************getImageOutputStream************************************");
        return mathmlCounter == 0 ? out : new NullOutputStream();
    }

    @Override
    public String getImageURL(int mathmlCounter) {
        /* Not needed here as we're throwing the resulting XML away */
        return "";
    }

    public void imageSavingFailed(Object imageOutputObject, int mathmlCounter,
                                  String contentType, Throwable exception) {
        this.failure = exception;
    }
}
