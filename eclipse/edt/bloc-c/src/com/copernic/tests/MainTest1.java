package com.copernic.tests;

import com.copernic.core.BlocPool;
import com.copernic.core.StructurePool;

public class MainTest1 {
	public static void main(String[] args) {
		StructurePool structs = new StructurePool() ;
		structs.scanADirectory("/home/jeanmarie/LAAS/bloc-c/trunk/lib/standard/structs/") ;
		BlocPool blocs = new BlocPool() ;
		blocs.scanADirectory("/home/jeanmarie/LAAS/bloc-c/trunk/lib/standard/blocs/") ;
//		StructureInterface struct_1 = new Structure("demo_struct1.xml") ;
//		BlocInterface ramp = new Bloc("Ramp.xml") ;
//		Generator gen = new Generator() ;
//		gen.printForOneBloc(ramp) ;
	}
}
