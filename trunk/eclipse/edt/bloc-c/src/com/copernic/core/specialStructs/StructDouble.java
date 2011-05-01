package com.copernic.core.specialStructs;

import java.util.List;

import com.copernic.core.StructureInterface;
import com.copernic.core.primaryTypes;

public class StructDouble implements StructureInterface {

	@Override
	public primaryTypes getPrimaryType() {
		return primaryTypes.DOUBLE;
	}

	@Override
	public List<String> getContenuTypes() {
		return null ;
	}

	@Override
	public List<String> getContenuNames() {
		return null;
	}

	@Override
	public String getStructureName() {
		return "double" ;
	}

	@Override
	public String getCSourceCode() {
		return "";
	}

}
