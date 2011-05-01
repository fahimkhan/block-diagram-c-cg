package com.copernic.core;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import com.copernic.core.specialStructs.StructChar;
import com.copernic.core.specialStructs.StructDouble;
import com.copernic.core.specialStructs.StructFloat;
import com.copernic.core.specialStructs.StructInt;
import com.copernic.core.specialStructs.StructUChar;

public class StructurePool {

	Map<String, StructureInterface> structureDatabase = new TreeMap<String, StructureInterface>() ;
	
	public StructurePool() {
		// les types primitifs
		StructureInterface si = new StructInt() ;
		structureDatabase.put(si.getStructureName(), si) ;
		si = new StructChar() ;
		structureDatabase.put(si.getStructureName(), si) ;
		si = new StructUChar() ;
		structureDatabase.put(si.getStructureName(), si) ;
		si = new StructFloat() ;
		structureDatabase.put(si.getStructureName(), si) ;
		si = new StructDouble() ;
		structureDatabase.put(si.getStructureName(), si) ;
	}
	
	public void scanADirectory(String directory) {
	    File folder = new File(directory);
	    if  (!folder.exists())
	    	return ;
	    File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml")) {
	    	  StructureInterface si = new Structure(listOfFiles[i].getAbsolutePath()) ;
	    	  structureDatabase.put(si.getStructureName(), si) ;
	    	  System.out.println(si.getCSourceCode()) ;
	      } else if (listOfFiles[i].isDirectory()) {
	    	  scanADirectory(listOfFiles[i].getAbsolutePath()) ;
	      }
	    }

	}
	
}
