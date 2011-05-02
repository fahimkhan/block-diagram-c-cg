package com.copernic.ui.drawables;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.copernic.core.BlocInterface;

public class BlocUI {

	BlocInterface associatedBloc ;

	int positionU = 200 ; // a droite
	int positionV = 100 ; // en bas	

	Font localFont = new Font("Helvetica", Font.BOLD, 15) ;
	private FontMetrics metrics;
	
	boolean changesInUIProperties = true ;

	private String labelTitle = "" ;
	private int rectWidth;
	private int rectHeight;
	private int textHeight; 
	
 	public void drawme(Graphics2D g) {
 		g.translate(positionU, positionV) ;
 		
		g.setFont(localFont) ;
		metrics = g.getFontMetrics() ;
		if (changesInUIProperties) {
			
			if (associatedBloc!=null) {
				labelTitle = associatedBloc.getBlocName() ;
			} else {
				labelTitle = "???" ;
			}
			int textWidth  = metrics.stringWidth(labelTitle);
			textHeight = metrics.getHeight() ;
			rectWidth  = textWidth+10  ;
			rectHeight = textHeight+10 ;
		}
		
		g.drawRect(0, 0, rectWidth, rectHeight) ;
		g.drawString(labelTitle, 5, textHeight) ;

		g.translate(-positionU, -positionV) ;
	}
	
}
