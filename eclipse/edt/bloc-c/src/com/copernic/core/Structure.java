package com.copernic.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Structure implements StructureInterface {
	
	String                   structureName = "structureDefaultName"              ;
	primaryTypes             primaryType   = primaryTypes.NOT_A_PRIMARY_TYPE     ;
	List<String>             contenuTypes  = new ArrayList<String>() ;
	List<String>             contenuNames  = new ArrayList<String>() ;
	
	@SuppressWarnings("unused")
	private Structure() {}
	
	public Structure(String fileName) {
		load(fileName) ;
	}

	@Override
	public String getStructureName() {
		return this.structureName;
	}
	@Override
	public List<String> getContenuTypes() {
		return this.contenuTypes;
	}
	@Override
	public List<String> getContenuNames() {
		return this.contenuNames ;
	}

	@Override
	public primaryTypes getPrimaryType() {
		return primaryType ;
	}

	private void load(String fileName) {
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//			System.out.println("load struct "+fileName) ;
			Document doc = docBuilder.parse (new File(fileName));

			// normalize text representation
			doc.getDocumentElement ().normalize ();

			if (!doc.getDocumentElement().getNodeName().equalsIgnoreCase("struct")) {
				throw new Exception("Error loading struct "+fileName+" : xml <root> is not \"struct\"") ;
			}
			primaryType = primaryTypes.NOT_A_PRIMARY_TYPE ;

			{
				NodeList listOfStructureName = doc.getElementsByTagName("name") ;
				if (listOfStructureName.getLength()==0) {
					throw new Exception("Error loading struct "+fileName+" : xml <struct><name> is not defined") ;
				}
				if (listOfStructureName.getLength()>1) {
					throw new Exception("Error loading struct "+fileName+" : xml <struct><name> mutiple defined") ;
				}
				structureName = listOfStructureName.item(0).getTextContent() ;
				if (this.structureName == null || this.structureName.length()==0) {
					throw new Exception("Error loading struct "+fileName+" : <struct>is null or \"\"") ;
				}
//				System.out.println("node = "+listOfblocName.item(0).getTextContent()) ;
			}
			{
				NodeList listOfStructureDefinition = doc.getDocumentElement().getChildNodes() ;
				if (listOfStructureDefinition.getLength()==0) {
					throw new Exception("Error loading struct "+fileName+" : xml <struct> empty") ;
				}
				
				for (int i = 0; i < listOfStructureDefinition.getLength(); i++) {
					Node input_i = listOfStructureDefinition.item(i) ;
					if (input_i.getNodeType()==1 && !input_i.getNodeName().equalsIgnoreCase("name")) {
						String contentType = input_i.getNodeName() ;
						String contentName = input_i.getTextContent() ;						
//						System.out.println("type = "+contentType+ " : "+contentName);
						if (contentName == null || contentName.length()==0) {
							throw new Exception("Error loading struct "+fileName+" : contentName is null or \"\"") ;
						}
						getContenuTypes().add(contentType) ;
						getContenuNames().add(contentName) ;
					}
				}
			}
			
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

	@Override
	public String getCSourceCode() {
		switch (getPrimaryType()) {
		case NOT_A_PRIMARY_TYPE:
			String res = "typedef struct "+getStructureName()+" {\n" ;
			for (int i = 0; i < getContenuTypes().size(); i++) {
				res += getContenuTypes().get(i)+" "+getContenuNames().get(i)+" ;\n";
			}
			res += "} "+getStructureName()+" ;\n" ;
			return res ;
		default:
			return "" ;
		}
	}


}
