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
    
    List<JTextField> phaseTextFields;
    List<JComboBox> phaseComboBoxes;
    
    ArrayList<ArrayList<JComboBox>> allFeeds;
    ArrayList<ArrayList<JTextField>> allPercents;
    List<JButton> addFeedButtons;
    
    JButton setPhases;
    int numPhases;
    
    public IOclass () {
        textFields = new ArrayList<JTextField>();
        comboBoxes = new ArrayList<JComboBox>();
        checkBoxes = new ArrayList<JCheckBox>();
        
        phaseTextFields = new ArrayList<JTextField>();
        phaseComboBoxes = new ArrayList<JComboBox>();
        
        allFeeds = new ArrayList<ArrayList<JComboBox>>();
        allPercents = new ArrayList<ArrayList<JTextField>>();
        addFeedButtons = new ArrayList<JButton>();
        
        setPhases = new JButton();
        
        numPhases = 0;
        
    }
    
    public void addInput(JTextField newInput){
	   	textFields.add(newInput);
    }
    
    public void addInput(JComboBox newInput){
        comboBoxes.add(newInput);
    }
    
    public void addInput(JTextField newInput, boolean deletable){
    	if(!deletable){
	   		textFields.add(newInput);
	   	}
	   	else{
	   		phaseTextFields.add(newInput);
	   	}
    }
    
    public void addInput(JComboBox newInput, boolean deletable){
    	if(!deletable){
        	comboBoxes.add(newInput);
	   	}
	   	else{
	   		phaseComboBoxes.add(newInput);
	   	}
    }
    
    public void addInput(JCheckBox newInput){
	    checkBoxes.add(newInput);
    }
    
    
    
    public void addPhaseButton(JButton phaseButton){
		setPhases = phaseButton;
    }
    
    public void setNumPhases(int num){
    	numPhases = num;
    }

	public void removePhaseInfo(){
		phaseTextFields.clear();
		phaseComboBoxes.clear();
		allFeeds.clear();
		allPercents.clear();
		addFeedButtons.clear();
	}
	
	
	public void addFeedButton(JButton feedButton){
		addFeedButtons.add(feedButton);
	}
	
	public void addFeedArrayList(ArrayList<JComboBox> feeds){
		allFeeds.add(feeds);
	}
	
	public void addPercentsArrayList(ArrayList<JTextField> percents){
		allPercents.add(percents);
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
                if(tagname == "Number_Of_Phases"){
                	if(textFields.get(i) != null){
                		setPhases.doClick();	
                	}
                }
            }
            
            for(int i=0;i<phaseTextFields.size(); i++){
                tagname = phaseTextFields.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                phaseTextFields.get(i).setText(eElement.getElementsByTagName("value").item(0).getTextContent());
            }
            
            for(int i=0; i<comboBoxes.size(); i++){
                tagname = comboBoxes.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                comboBoxes.get(i).setSelectedItem(eElement.getElementsByTagName("value").item(0).getTextContent());
                
            }
            
            for(int i=0; i<phaseComboBoxes.size(); i++){
                tagname = phaseComboBoxes.get(i).getName();
                eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                phaseComboBoxes.get(i).setSelectedItem(eElement.getElementsByTagName("value").item(0).getTextContent());
                
            }
            
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
            
            //adding the appropriate number of feeds
            tagname = "metadata";
            eElement = (Element) doc.getElementsByTagName(tagname).item(0); 
            NodeList list = eElement.getElementsByTagName("FeedListSize");
            
            for(int i=0;i<list.getLength(); i++){
                int numFeedsInThisList = Integer.parseInt(eElement.getElementsByTagName("FeedListSize").item(i).getTextContent());
                for(int j=1;j<numFeedsInThisList;j++){
                	addFeedButtons.get(i).doClick();
                }
   			}
   			
            
            for(int i=0; i<allFeeds.size();i++){
            	for(int j=0; j<allFeeds.get(i).size();j++){
                	tagname = allFeeds.get(i).get(j).getName();
                	eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                	allFeeds.get(i).get(j).setSelectedItem(eElement.getElementsByTagName("value").item(0).getTextContent());
            	}
            }
            
            for(int i=0; i<allPercents.size();i++){
            	for(int j=0; j<allPercents.get(i).size();j++){ 
                	tagname = allPercents.get(i).get(j).getName();
                	eElement = (Element) doc.getElementsByTagName(tagname).item(0);
                	allPercents.get(i).get(j).setText(eElement.getElementsByTagName("value").item(0).getTextContent());
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
                
                if(!tagname.equals("Number_Of_Phases")){            
	                newValueElement.appendChild(doc.createTextNode(textFields.get(i).getText()));
	            }
	            else{
	                newValueElement.appendChild(doc.createTextNode(""+allFeeds.size()));
	            }
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
            
            for(int i=0;i<phaseTextFields.size(); i++){
                tagname = phaseTextFields.get(i).getName();
                newElement = doc.createElement(tagname);
                rootElement.appendChild(newElement);
                
                newValueElement = doc.createElement("value");
                newValueElement.appendChild(doc.createTextNode(phaseTextFields.get(i).getText()));
                newElement.appendChild(newValueElement);
            }
            
            for(int i=0; i<phaseComboBoxes.size(); i++){
                tagname = phaseComboBoxes.get(i).getName();
                newElement = doc.createElement(tagname);
                rootElement.appendChild(newElement);
                
                newValueElement = doc.createElement("value");
                newValueElement.appendChild(doc.createTextNode((String)phaseComboBoxes.get(i).getSelectedItem()));
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
            
            for(int i=0; i<allFeeds.size();i++){
            	for(int j=0; j<allFeeds.get(i).size();j++){
 	               tagname = allFeeds.get(i).get(j).getName();
 	               newElement = doc.createElement(tagname);
 	               rootElement.appendChild(newElement);
                
 	               newValueElement = doc.createElement("value");
 	               newValueElement.appendChild(doc.createTextNode((String)allFeeds.get(i).get(j).getSelectedItem()));
 	               newElement.appendChild(newValueElement);
            	}
            }
            
            for(int i=0; i<allPercents.size();i++){
            	for(int j=0; j<allPercents.get(i).size();j++){
	                tagname = allPercents.get(i).get(j).getName();
	                newElement = doc.createElement(tagname);
	                rootElement.appendChild(newElement);
                
	                newValueElement = doc.createElement("value");
	                newValueElement.appendChild(doc.createTextNode(allPercents.get(i).get(j).getText()));
	                newElement.appendChild(newValueElement);
            	}
            }
            
            //Feed list sizes metadata
            
            newElement = doc.createElement("metadata");
            rootElement.appendChild(newElement);
            for(int i=0; i<allFeeds.size();i++){
                newValueElement = doc.createElement("FeedListSize");
                newValueElement.appendChild(doc.createTextNode(""+allFeeds.get(i).size()));
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
            
            stateElement = (Element) doc.getElementsByTagName(StateAbbr).item(0);
            
            NodeList list = stateElement.getElementsByTagName("county");
             
            countyList = new String[list.getLength()];
            
            for(int i=0;i<list.getLength(); i++){
                countyList[i] = stateElement.getElementsByTagName("county").item(i).getTextContent();
   }

            
        } catch (Exception e) {e.printStackTrace();}
     
            return countyList;
            	
    }
    
    public String[] getFeedList(String filename){
    
    	String[] feedList = {"","",""};
    
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filename));
            
            doc.getDocumentElement().normalize();
            
            
            NodeList list = doc.getElementsByTagName("feed");
            
            feedList = new String[list.getLength()];
            
            for(int i=0;i<list.getLength(); i++){
                feedList[i] = doc.getElementsByTagName("feed").item(i).getTextContent();
   			}

            
        } catch (Exception e) {e.printStackTrace();}
     
            return feedList;
            	
    }
}
















