package com.copernic.core;

import java.util.ArrayList;
import java.util.List;

public interface BlocInterface {
	
	public List<String> inputsTypes = new ArrayList<String>() ;
	public List<String> context     = new ArrayList<String>() ;
	public List<String> parameters  = new ArrayList<String>() ;
	public List<String> blacBox     = new ArrayList<String>() ;
	public List<String> outputs     = new ArrayList<String>() ;

	public List<String>   inputsNames    = new ArrayList<String>() ;
	public List<String>   contextNames   = new ArrayList<String>() ;
	public List<String>   parameterNames = new ArrayList<String>() ;
	public List<String>   blackBoxNames  = new ArrayList<String>() ;
	public List<String>   outputsNames   = new ArrayList<String>() ;

	public List<String> getInputsTypes() ;
	public List<String> getContext()     ;
	public List<String> getParameters()  ;
	public List<String> getBlackBox()    ;
	public List<String> getOutputs()     ;

	public List<String>   getInputsNames() ;
	public List<String>   getContextNames() ;
	public List<String>   getParametersNames() ;
	public List<String>   getBlackBoxNames() ;
	public List<String>   getOutputsNames() ;

	// parametrisation utilisateur
	public String getBlocName() ;
	
	// algorithme de generation de code en C
	public String getCSourceCode() ;
}
