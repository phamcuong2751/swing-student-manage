package com.xml;

import models.MStudent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.geom.QuadCurve2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    private static final String FILENAME = "data-student.xml";

    public List<MStudent> read(String filePath) {
        List<MStudent> listStudent = new ArrayList<MStudent>();
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("student");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    MStudent student = new MStudent();
                    Element element = (Element) node;
                    student.setStudentID(element.getElementsByTagName("studentId").item(0).getTextContent());
                    student.setStudentName(element.getElementsByTagName("studentName").item(0).getTextContent());
                    student.setScore(Float.parseFloat(element.getElementsByTagName("score").item(0).getTextContent()));
                    student.setAddress(element.getElementsByTagName("address").item(0).getTextContent());
                    student.setImage(element.getElementsByTagName("image").item(0).getTextContent());
                    student.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    listStudent.add(student);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
        return listStudent;
    }

    public static void main(String[] args) {
        XMLReader xml = new XMLReader();
        List<MStudent> list = xml.read(FILENAME);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getStudentID());
            System.out.println(list.get(i).getStudentName());
            System.out.println(list.get(i).getAddress());
            System.out.println(list.get(i).getDescription() + "\n\n\n\n");
        }
    }

    public void write(MStudent student, String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            Element documentElement = document.getDocumentElement();

            //setting field student id
            Element textNode = document.createElement("studentId");
            textNode.setTextContent(student.getStudentID());
            //setting field student name
            Element textNode1 = document.createElement("studentName");
            textNode1.setTextContent(student.getStudentName());
            //setting field score
            Element textNode2 = document.createElement("score");
            textNode2.setTextContent(Float.toString(student.getScore()));
            //setting field image
            Element textNode3 = document.createElement("image");
            textNode3.setTextContent("");
            //setting field address
            Element textNode4 = document.createElement("address");
            textNode4.setTextContent(student.getAddress());
            //setting field description
            Element textNode5 = document.createElement("description");
            textNode5.setTextContent(student.getDescription());

            Element nodeElement = document.createElement("student");
            nodeElement.appendChild(textNode);
            nodeElement.appendChild(textNode1);
            nodeElement.appendChild(textNode2);
            nodeElement.appendChild(textNode3);
            nodeElement.appendChild(textNode4);
            nodeElement.appendChild(textNode5);

            documentElement.appendChild(nodeElement);
            document.replaceChild(documentElement, documentElement);

            Transformer tFormer =TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
            tFormer.transform(source, result);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
