package fr.opensagres.poi.xwpf.converter.xhtml.internal;

import com.google.common.io.Resources;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import uk.ac.ed.ph.snuggletex.*;
import uk.ac.ed.ph.snuggletex.jeuclid.JEuclidUtilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;

/**
 * ClassName: WordMathHelper <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-9-29 下午2:42 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class WordToMathParse {
    private XmlObject xmlObject;

    private String mathML;

    public WordToMathParse(XmlObject xmlObject) {
        this.xmlObject = xmlObject;
    }


    public String getLatext() {
        return fmath.conversion.ConvertFromMathMLToLatex.convertToLatex(mathML);
    }


    public byte[] getImageData2() throws Exception {
        ByteArrayOutputStream os = getByteArrayOutputStream2(getLatext());
        byte[] data = os.toByteArray();
        os.close();
        return data;
    }

    public byte[] getImageData2(String latext) throws Exception {
        ByteArrayOutputStream os = getByteArrayOutputStream2(latext);
        byte[] data = os.toByteArray();
        os.close();
        return data;
    }

    public void save2(String filePath) throws Exception {
        save(new File(filePath));
    }

    public void save2(File file) throws Exception {
        ByteArrayOutputStream os = getByteArrayOutputStream2(getLatext());
        FileOutputStream out = new FileOutputStream(file);
        out.write(os.toByteArray());
    }

    public ByteArrayOutputStream getByteArrayOutputStream2(String latext) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageSavingCallback callback = new ImageSavingCallback(os);
        /* Set up appropriate web output options */
        WebPageOutputOptions options = JEuclidUtilities.createWebPageOptions(true, callback);
        options.setErrorOutputOptions(DOMOutputOptions.ErrorOutputOptions.NO_OUTPUT);
        options.setMathVariantMapping(true);
        options.setAddingMathSourceAnnotations(false);
        options.setIndenting(false);

        SnuggleEngine engine = new SnuggleEngine();
        SnuggleSession session = engine.createSession();
        latext = "$ " + latext + " $";
        SnuggleInput input = new SnuggleInput(latext, "Form Input");
        session.parseInput(input);
        session.createWebPage(options);
        return os;
    }

    public byte[] getImageData() throws Exception {
        ByteArrayOutputStream os = getByteArrayOutputStream();
        byte[] data = os.toByteArray();
        os.close();
        return data;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() throws Exception {
        org.w3c.dom.Document document = convertStringToDocument(mathML);
        Converter mathMLConvert = Converter.getInstance();
        LayoutContextImpl localLayoutContextImpl = new LayoutContextImpl(LayoutContextImpl.getDefaultLayoutContext());
        localLayoutContextImpl.setParameter(Parameter.MATHSIZE, 18);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        mathMLConvert.convert(document, os, "image/png", localLayoutContextImpl);
        return os;
    }

    public void save(String filePath) throws Exception {
        save(new File(filePath));
    }

    public void save(File file) throws Exception {
        ByteArrayOutputStream os = getByteArrayOutputStream();
        FileOutputStream out = new FileOutputStream(file);
        out.write(os.toByteArray());
    }

    public void parse() throws Exception {
        mathML = OOMLToMathML();
    }


    //MathML转成Document
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

    private String OOMLToMathML() throws Exception {
        URI uri = Resources.getResource("oomlandmathml/omml2mml.xsl").toURI();
        File file = new File(uri);
        StreamSource stylesource = new StreamSource(file);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Node node = xmlObject.getDomNode();

        DOMSource source = new DOMSource(node);
        StringWriter stringwriter = new StringWriter();
        StreamResult result = new StreamResult(stringwriter);
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(source, result);

        String mathML = stringwriter.toString();
        stringwriter.close();

        // The native OMML2MML.XSL transforms OMML into MathML as XML having special
        // name spaces.
        // We don't need this since we want using the MathML in HTML, not in XML.
        // So ideally we should changing the OMML2MML.XSL to not do so.
        // But to take this example as simple as possible, we are using replace to get
        // rid of the XML specialities.
        mathML = mathML.replaceAll("xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"", "");
        mathML = mathML.replaceAll("xmlns:mml=\"http://www.w3.org/1998/Math/MathML\"", "");
        mathML = mathML.replaceAll("xmlns:mml", "xmlns");
        mathML = mathML.replaceAll("mml:", "");
        return "<math xmlns=\"http://www.w3.org/1998/Math/MathML\">" + mathML + "</math>";
    }
}
