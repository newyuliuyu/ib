package fr.opensagres.poi.xwpf.converter.xhtml.internal;

import com.google.common.io.Resources;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import uk.ac.ed.ph.snuggletex.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;

/**
 * ClassName: MathToWordParse <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-9-29 下午3:09 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class MathToWordParse {


    public String latexToMathML(String latex) throws IOException {
        SnuggleEngine engine = new SnuggleEngine();
        SnuggleSession session = engine.createSession();
        SnuggleInput input = new SnuggleInput(latex);
        session.parseInput(input);
        XMLStringOutputOptions options = new XMLStringOutputOptions();
        options.setSerializationMethod(SerializationMethod.XML);
        options.setIndenting(true);
        options.setEncoding("UTF-8");
        options.setAddingMathSourceAnnotations(false);
        options.setUsingNamedEntities(true);
        String mathML = session.buildXMLString(options);

        //为了可以与omml融合
        mathML = mathML.replaceAll("&pm;", "±");
        mathML = mathML.replaceAll("&div;", "÷");

        return mathML;
    }

    public String latexToMathML2(String latex) throws IOException {
        return fmath.conversion.ConvertFromLatexToMathML.convertToMathML(latex);
    }


    public CTOMath getOMML(String mathML) throws Exception {
        URI uri = Resources.getResource("oomlandmathml/MML2OMML.XSL").toURI();
        File stylesheet = new File(uri);

//        File stylesheet = new File("/home/liuyu/workeidea/ez-report/report-commons/src/test/resources/MML2OMML.XSL");
        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = tFactory.newTransformer(stylesource);

        StringReader stringreader = new StringReader(mathML);
        StreamSource source = new StreamSource(stringreader);

        StringWriter stringwriter = new StringWriter();
        StreamResult result = new StreamResult(stringwriter);

        transformer.transform(source, result);

        String ooML = stringwriter.toString();
        stringwriter.close();

        System.out.println(ooML);
        CTOMathPara ctOMathPara = CTOMathPara.Factory.parse(ooML);

        CTOMath ctOMath = ctOMathPara.getOMathArray(0);

        //for making this to work with Office 2007 Word also, special font settings are necessary
        XmlCursor xmlcursor = ctOMath.newCursor();
        while (xmlcursor.hasNextToken()) {
            XmlCursor.TokenType tokentype = xmlcursor.toNextToken();
            if (tokentype.isStart()) {
                if (xmlcursor.getObject() instanceof CTR) {
                    CTR cTR = (CTR) xmlcursor.getObject();
                    cTR.addNewRPr2().addNewRFonts().setAscii("Cambria Math");
                    cTR.getRPr2().getRFonts().setHAnsi("Cambria Math");
                }
            }
        }
        return ctOMath;
    }

    public void insertWord(XWPFParagraph paragraph, String latex) throws Exception {
        String mathML = latexToMathML(latex);
        CTOMath ctOMath = getOMML(mathML);
        CTP ctp = paragraph.getCTP();
        CTOMath ctoMath = ctp.addNewOMath();
        ctoMath.set(ctOMath);
    }
}
