import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import java.util.*;
import javax.swing.*;
import javax.xml.transform.OutputKeys;

public class IOclass {

    List<JTextField> textFields;
    List<JComboBox> comboBoxes;
    List<JCheckBox> checkBoxes;
    
    public IOclass () {
        textFields = new ArrayList<JTextField>();
        comboBoxes = new ArrayList<JComboBox>();
        checkBoxes = new ArrayList<JCheckBox>();
        
    }
    
    public void addInput(JTextField newInput){
        textFields.add(newInput);
    }
    
    public void addInput(JComboBox newInput){
        comboBoxes.add(newInput);
    }
    
    public void addInput(JCheckBox newInput){
        checkBoxes.add(newInput);
    }

    public void loadInputs(File inputFile){
        //message box reminding to save work and can cancel the current operation first
        
        try{
            //File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            
            doc.getDocumentElement().normalize();
            Element eElement;
            String tagname;
            
            for(int i=0;i<textFields.size(); i++){
                tagname = textFields.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                textFields.get(i).setText(eElement.getElementsByTagName("value").item(0).getTextContent());
            }
            
            for(int i=0; i<comboBoxes.size(); i++){
                tagname = comboBoxes.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                comboBoxes.get(i).setSelectedItem(eElement.getElementsByTagName("value").item(0).getTextContent());
                
            }
            
            //Not sure how to make the program load the checkbox states correctly
            for(int i=0; i<checkBoxes.size(); i++){
                tagname = checkBoxes.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                
                if(eElement.getElementsByTagName("value").item(0).getTextContent().equals("true")){
                	checkBoxes.get(i).setSelected(true);
                }
                else{
                	checkBoxes.get(i).setSelected(false);
                }
                	
            }
            
        } catch (Exception e) {e.printStackTrace();}
        
        
    }
    
    public void saveInputs(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("inputs");
            doc.appendChild(rootElement);
            
            Element newElement;
            Element newValueElement;
            String tagname;
            
            for(int i=0;i<textFields.size(); i++){
                tagname = textFields.get(i).getName();
                newElement = doc.createElement(tagname);
                rootElement.appendChild(newElement);
                
                newValueElement = doc.createElement("value");
                newValueElement.appendChild(doc.createTextNode(textFields.get(i).getText()));
                newElement.appendChild(newValueElement);
            }
            
            for(int i=0; i<comboBoxes.size(); i++){
                tagname = comboBoxes.get(i).getName();
                newElement = doc.createElement(tagname);
                rootElement.appendChild(newElement);
                
                newValueElement = doc.createElement("value");
                newValueElement.appendChild(doc.createTextNode((String)comboBoxes.get(i).getSelectedItem()));
                newElement.appendChild(newValueElement);
            }
            
            for(int i=0; i<checkBoxes.size(); i++){
                tagname = checkBoxes.get(i).getName();
                newElement = doc.createElement(tagname);
                rootElement.appendChild(newElement);
                
                newValueElement = doc.createElement("value");
                
                if(checkBoxes.get(i).isSelected()){
    	            newValueElement.appendChild(doc.createTextNode("true"));
                }
                else{
	                newValueElement.appendChild(doc.createTextNode("false"));
                }
                
                
                newElement.appendChild(newValueElement);
            }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(inputFile);
            
            transformer.transform(source, result);
        
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public String[] getCountyList(String StateAbbr, String filename){
    
    	String[] countyList = {"","",""};
    
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filename));
            
            doc.getDocumentElement().normalize();
            Element stateElement;
            Element eElement2;
            
            stateElement = (Element) doc.getElementsByTagName(StateAbbr).item(0);
            
            
            NodeList list = stateElement.getElementsByTagName("county");
            
            
            
            
            countyList = new String[list.getLength()];
            
            for(int i=0;i<list.getLength(); i++){
                countyList[i] = stateElement.getElementsByTagName("county").item(i).getTextContent();
   }

            
        } catch (Exception e) {e.printStackTrace();}
     
            return countyList;
            	
    }
}
















