package com.example.lib;

import com.example.lib.exception.ConvertFail;
import com.example.lib.exception.ExceptionMessage;
import com.example.lib.parts.Binary;
import com.example.lib.parts.Body;
import com.example.lib.parts.Description;
import com.example.lib.parts.Xmlns;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class FicBook {
    private Xmlns[] xmlnsStrings;
    private Description description;
    private List<Body> bodies = new ArrayList<>();
    private Map<String, Binary> binaries;

    private String encoding = "utf-8";

    public FicBook(File file) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc;
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean foundIllegalCharacters = false;
            StringBuilder line = new StringBuilder(br.readLine().trim());
            if (!line.toString().startsWith("<")) {
                foundIllegalCharacters = true;
            }
            while (!line.toString().endsWith("?>")) {
                line.append("\n").append(br.readLine().trim());
            }
            int start = line.indexOf("encoding") + 8;
            String substring = line.substring(start);
            substring = substring.substring(substring.indexOf("\"") + 1);
            encoding = substring.substring(0, substring.indexOf("\"")).toLowerCase();
            if (foundIllegalCharacters) {
                StringBuilder text = new StringBuilder();
                String lineOfIllegalCharacters = br.readLine();
                if (lineOfIllegalCharacters != null && lineOfIllegalCharacters.contains("<")) {
                    lineOfIllegalCharacters = lineOfIllegalCharacters.substring(lineOfIllegalCharacters.indexOf("<"));
                }
                while (lineOfIllegalCharacters != null) {
                    text.append(lineOfIllegalCharacters);
                    lineOfIllegalCharacters = br.readLine();
                }
                doc = db.parse(new InputSource(new StringReader(text.toString())));
            } else {
                doc = db.parse(new InputSource(new InputStreamReader(inputStream, encoding)));
            }
        } catch (SAXException | IOException e) {
            throw new ConvertFail(ExceptionMessage.SOMETHING_WENT_WRONG.getMessage());
        }

        initXmlns(doc);
        description = new Description(doc);
        NodeList bodyNodes = doc.getElementsByTagName("body");
        for (int item = 0; item < bodyNodes.getLength(); item++) {
            bodies.add(new Body(bodyNodes.item(item)));
        }
        NodeList binary = doc.getElementsByTagName("binary");
        for (int item = 0; item < binary.getLength(); item++) {
            if (binaries == null) binaries = new HashMap<>();
            Binary binary1 = new Binary(binary.item(item));
            binaries.put(binary1.getId().replace("#", ""), binary1);
        }
    }

    private void setXmlnsStrings(ArrayList<Node> nodeList) {
        xmlnsStrings = new Xmlns[nodeList.size()];
        for (int index = 0; index < nodeList.size(); index++) {
            Node node = nodeList.get(index);
            xmlnsStrings[index] = new Xmlns(node);
        }
    }

    private void initXmlns(Document doc) {
        NodeList fictionBook = doc.getElementsByTagName("FictionBook");
        ArrayList<Node> xmlns = new ArrayList<>();
        for (int item = 0; item < fictionBook.getLength(); item++) {
            NamedNodeMap map = fictionBook.item(item).getAttributes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                xmlns.add(node);
            }
        }
        setXmlnsStrings(xmlns);
    }

    public Body getBody(byte i) {
        return bodies.get(i);
    }

    public String getTitle() {
        return description.getTitleInfo().getBookTitle();
    }

    public Annotation getAnnotation() {
        return description.getTitleInfo().getAnnotation();
    }
}
