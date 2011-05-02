package com.copernic.core;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class BlocPool {

	Map<String, BlocInterface> blocDatabase = new TreeMap<String, BlocInterface>() ;
	
	public BlocPool() {
	}
	
	public void scanADirectory(String directory) {
	    File folder = new File(directory);
	    if  (!folder.exists()) {
			blocDatabase.clear() ;
	    	System.out.println("bloc load folder does not exist : "+directory);
	    } else {
		    File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml")) {
		    	  BlocInterface bi = new Bloc(listOfFiles[i].getAbsolutePath()) ;
		    	  blocDatabase.put(bi.getBlocName(), bi) ;
//		    	  System.out.println(bi.getCSourceCode()) ;
		      } else if (listOfFiles[i].isDirectory() && !listOfFiles[i].getName().startsWith(".")) {
		    	  scanADirectory(listOfFiles[i].getAbsolutePath()) ;
		      }
		    }
	    }
	    System.out.println("loading "+blocDatabase.size()+" blocs in "+directory) ;

	}

}
