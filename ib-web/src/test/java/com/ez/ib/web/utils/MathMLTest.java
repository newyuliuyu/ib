package com.ez.ib.web.utils;

import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;
import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;

/**
 * ClassName: MathMLTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-12-27 下午6:53 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class MathMLTest {

    @Test
    public void test() throws Exception {
        String mathML = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><msub><mrow><mi>S</mi></mrow><mrow><mo>△</mo><mi>O</mi><mi>M</mi><mi>N</mi></mrow></msub><mo  >=</mo><mfrac  ><mrow><mn>1</mn></mrow><mrow><mn>2</mn></mrow></mfrac><mi  >O</mi><mi  >M</mi><mo  >⋅</mo><mi  >O</mi><mi  >N</mi><mi  >s</mi><mi  >i</mi><mi  >n</mi><mi  >∠</mi><mi  >M</mi><mi  >O</mi><mi  >N</mi></math>";
        Converter mathMLConvert = Converter.getInstance();
        org.w3c.dom.Document document = convertStringToDocument(mathML);
        LayoutContextImpl localLayoutContextImpl = new LayoutContextImpl(LayoutContextImpl.getDefaultLayoutContext());
        localLayoutContextImpl.setParameter(Parameter.MATHSIZE, 18);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        mathMLConvert.convert(document, os, "image/png", localLayoutContextImpl);

        File file = new File("/home/liuyu/tmp/word2html/a.png");
        FileOutputStream out = new FileOutputStream(file);
        out.write(os.toByteArray());
    }

    @Test
    public void mathMLToLatex() throws Exception {
        String mathML = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><msub><mrow><mi>S</mi></mrow><mrow><mo>△</mo><mi>O</mi><mi>M</mi><mi>N</mi></mrow></msub><mo  >=</mo><mfrac  ><mrow><mn>1</mn></mrow><mrow><mn>2</mn></mrow></mfrac><mi  >O</mi><mi  >M</mi><mo  >⋅</mo><mi  >O</mi><mi  >N</mi><mi  >s</mi><mi  >i</mi><mi  >n</mi><mi  >∠</mi><mi  >M</mi><mi  >O</mi><mi  >N</mi></math>";
        String latex = fmath.conversion.ConvertFromMathMLToLatex.convertToLatex(mathML);
        System.out.println(latex);
    }

    private static org.w3c.dom.Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
