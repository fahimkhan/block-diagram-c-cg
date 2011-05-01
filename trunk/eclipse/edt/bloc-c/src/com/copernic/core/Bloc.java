package com.copernic.core;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Bloc implements BlocInterface {

	public String blocName           = "" ;
	public String contentCSourceCode = "" ;
	
	@SuppressWarnings("unused")
	private Bloc() {}
	
	public Bloc(String fileName) {		
		load(fileName) ;
	}
	
	@Override
	public List<String> getInputsTypes() {
		return inputsTypes ;
	}
	@Override
	public List<String> getContext() {
		return context ;
	}
	@Override
	public List<String> getParameters() {
		return parameters ;
	}
	@Override
	public List<String> getBlackBox() {
		return blacBox ;
	}
	@Override
	public List<String> getOutputs() {
		return outputs ;
	}

	@Override
	public List<String>   getInputsNames() {
		return inputsNames ;
	}
	@Override
	public List<String>   getContextNames() {
		return contextNames ;
	}
	@Override
	public List<String>   getParametersNames() {
		return contextNames ;
	}
	@Override
	public List<String>   getBlackBoxNames() {
		return contextNames ;
	}
	@Override
	public List<String>   getOutputsNames() {
		return outputsNames ;
	}
	@Override
	public String getBlocName() {
		return blocName;
	}

	public void load(String fileName) {
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			System.out.println("load Bloc "+fileName) ;
			Document doc = docBuilder.parse (new File(fileName));

			// normalize text representation
			doc.getDocumentElement ().normalize ();
			
			if (!doc.getDocumentElement().getNodeName().equalsIgnoreCase("bloc")) {
				throw new Exception("Error loading bloc "+fileName+" : xml <root> is not \"bloc\"") ;
			}

			{
				NodeList listOfblocName = doc.getElementsByTagName("blocName") ;
				if (listOfblocName.getLength()==0) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><blocName> is not defined") ;
				}
				if (listOfblocName.getLength()>1) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><blocName> mutiple defined") ;
				}
				blocName = listOfblocName.item(0).getTextContent() ;
				if (this.blocName == null || this.blocName.length()==0) {
					throw new Exception("Error loading bloc "+fileName+" : <blocName>is null or \"\"") ;
				}
//				System.out.println("node = "+listOfblocName.item(0).getTextContent()) ;
			}

			{
				NodeList listOfblocContentCCode = doc.getElementsByTagName("contentCSourceCode") ;
				if (listOfblocContentCCode.getLength()==0) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><contentCSourceCode> is not defined") ;
				}
				if (listOfblocContentCCode.getLength()>1) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><contentCSourceCode> mutiple defined") ;
				}
				this.contentCSourceCode = listOfblocContentCCode.item(0).getTextContent() ;
				if (this.contentCSourceCode == null || this.contentCSourceCode.length()==0) {
					throw new Exception("Error loading bloc "+fileName+" : <contentCSourceCode>is null or \"\"") ;
				}
//				System.out.println("contentCSourceCode = "+listOfblocContentCCode.item(0).getTextContent()) ;
			}

			{
				NodeList listOfInputs = doc.getElementsByTagName("inputs") ;
				if (listOfInputs.getLength()==0) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><inputs> is not defined") ;
				}
				if (listOfInputs.getLength()>1) {
					throw new Exception("Error loading bloc "+fileName+" : xml <bloc><inputs> mutiple defined") ;
				}
				Node inputs = listOfInputs.item(0) ;
				NodeList eachInputs = inputs.getChildNodes() ;
				for(int s=0; s<eachInputs.getLength() ; s++){
					Node input_i = eachInputs.item(s) ;
					if (input_i.getNodeName().equalsIgnoreCase("input")) {
						NodeList input_i_nodes = input_i.getChildNodes() ;
						String inputName = null ;
						String inputType = null ;
						for(int s2=0; s2<eachInputs.getLength() ; s2++){
							Node input_i_node = input_i_nodes.item(s2) ;
							if (input_i_node.getNodeName().equalsIgnoreCase("inputName")) {
								inputName = input_i_node.getTextContent() ;
							}
							if (input_i_node.getNodeName().equalsIgnoreCase("inputType")) {
								inputType = input_i_node.getTextContent() ;
							}
						}
						if (inputName==null || inputType==null) {
							throw new Exception("Error loading bloc "+fileName+" : xml <bloc><inputs><input> contents bad defined") ;
						}
						inputsNames.add(inputName) ;
						inputsTypes.add(inputType) ;
						// TODO avec les types d'inputs, on dois pouvoir remonter aux structures.
						System.out.println("input : <"+inputName+"> <"+inputType+">");
					}
				}
//				if (this.contentCSourceCode == null || this.contentCSourceCode.length()==0) {
//					throw new Exception("Error loading bloc "+fileName+" : <contentCSourceCode>is null or \"\"") ;
//				}
//				System.out.println("contentCSourceCode = "+listOfInputs.item(0).getTextContent()) ;
			}

//			NodeList listOfblocName = doc.getElementsByTagName("blocName") ;
//			if (listOfblocName.getLength()==0) {
//				throw new Exception("Error loading bloc "+fileName+" : xml <bloc><blocName> is not defined") ;
//			}
//			if (listOfblocName.getLength()>1) {
//				throw new Exception("Error loading bloc "+fileName+" : xml <bloc><blocName> mutiple defined") ;
//			}
//			this.blocName = listOfblocName.item(0).getTextContent() ;
//			if (this.blocName == null || this.blocName.length()==0) {
//				throw new Exception("Error loading bloc "+fileName+" : <blocName>is null or \"\"") ;
//			}
//			System.out.println("node = "+listOfblocName.item(0).getTextContent()) ;
//
//			
//			
//			NodeList listOfPersons = doc.getElementsByTagName("person");
//			int totalPersons = listOfPersons.getLength();
//			System.out.println("Total no of people : " + totalPersons);
//
//			for(int s=0; s<listOfPersons.getLength() ; s++){
//
//
//				Node firstPersonNode = listOfPersons.item(s);
//				if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
//
//
//					Element firstPersonElement = (Element)firstPersonNode;
//
//					//-------
//					NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
//					Element firstNameElement = (Element)firstNameList.item(0);
//
//					NodeList textFNList = firstNameElement.getChildNodes();
//					System.out.println("First Name : " + 
//							((Node)textFNList.item(0)).getNodeValue().trim());
//
//					//-------
//					NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
//					Element lastNameElement = (Element)lastNameList.item(0);
//
//					NodeList textLNList = lastNameElement.getChildNodes();
//					System.out.println("Last Name : " + 
//							((Node)textLNList.item(0)).getNodeValue().trim());
//
//					//----
//					NodeList ageList = firstPersonElement.getElementsByTagName("age");
//					Element ageElement = (Element)ageList.item(0);
//
//					NodeList textAgeList = ageElement.getChildNodes();
//					System.out.println("Age : " + 
//							((Node)textAgeList.item(0)).getNodeValue().trim());
//
//					//------
//
//
//				}//end of if clause
//
//
//			}//end of for loop with s var


		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " 
					   + err.getLineNumber () + ", uri " + err.getSystemId ());
			   System.out.println(" " + err.getMessage ());

		   }catch (SAXException e) {
			   Exception x = e.getException ();
			   ((x == null) ? e : x).printStackTrace ();

		   }catch (Throwable t) {
			   t.printStackTrace ();
		   }
	}
	
	// on ne reimplemente par getBlocName.
	
	
	@Override
	public String getCSourceCode() {
		String sig     = getSignature() ;
		String content = getContentCSourceCode() ;
		String res = sig + " {\n" +
				content + "\n" +
						"}\n" ;
		return res ;
	}
	
	private String getSignature() {
		String res = "void "+getBlocName()+"_exec (" ;
		
		List<String> inputs_ = getInputsTypes() ;
		List<String> inputsNames_ = getInputsNames() ;
		
		if (inputs_.size()==0) {
			res += "void)" ;
		}
		
		for (int i = 0; i < inputs_.size(); i++) {
			if (i!=inputs_.size()-1) {
				res += inputs_.get(i)+"* "+inputsNames_.get(i)+", " ;
			} else {
				res += inputs_.get(i)+"* "+inputsNames_.get(i)+" )" ;
			}
		}
		return res ;
	}
	private String getContentCSourceCode() {
		return this.contentCSourceCode ;
	}
	

}
