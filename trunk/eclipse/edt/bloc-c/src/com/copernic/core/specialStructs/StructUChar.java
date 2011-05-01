package com.copernic.core.specialStructs;

import java.util.List;

import com.copernic.core.StructureInterface;
import com.copernic.core.primaryTypes;

public class StructUChar implements StructureInterface {

	@Override
	public primaryTypes getPrimaryType() {
		return primaryTypes.UCHAR;
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
		return "unsigned char" ;
	}

	@Override
	public String getCSourceCode() {
		return "";
	}

}
