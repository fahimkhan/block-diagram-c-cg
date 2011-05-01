package com.copernic.core;

import java.util.List;

public interface StructureInterface {

	
	// on est l'un ou l'autre des deux types possibles :
	// primitif ou composite
	public primaryTypes getPrimaryType() ;
	public List<String> getContenuTypes() ;
	public List<String> getContenuNames() ;
	

	// parametrisation utilisateur
	public String getStructureName() ;
	
	// algorithme de generation de code en C
	public String getCSourceCode() ;

}
