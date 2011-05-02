package com.copernic.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.CubicCurve2D.Float;
import java.awt.geom.QuadCurve2D;

import javax.jws.WebParam.Mode;
import javax.swing.JPanel;

import com.copernic.ui.drawables.BlocUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;


public class ArchitecturePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ArchitecturePanel().setVisible(true) ;
	}

	
	/**
	 * Create the panel.
	 */
	public ArchitecturePanel() {
		super() ;
		addMouseListener(ma);
		addMouseMotionListener(mma);
	}
	
	enum modes {
		MODE_NEED_TRANSLATE_GLOBAL,
		MODE_TRANSLATE_GLOBAL,
	}
	modes currentMode = modes.MODE_NEED_TRANSLATE_GLOBAL ;
	
	int translateU = 0 ;
	int translateV = 0 ;
	int translateUAddedTemp = 0 ;
	int translateVAddedTemp = 0 ;
	
	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		
		arg0.translate(translateU+translateUAddedTemp, translateV+translateVAddedTemp) ;
		
		QuadCurve2D.Float quadCourbe = new QuadCurve2D.Float( // Créer une courbe quadratique
				20, 20, // Segment debut point
				40, 20, // Point de contrôle
				40, 40); // Point de terminaison du segment
		
		CubicCurve2D.Float cubicCurve = new CubicCurve2D.Float( // Créer une courbe cubique
		100, 20, // Segment debut point
		140, 20, // Point de contrôle for debut
		100, 40, // Point de contrôle for fin
		140, 40); // Point de terminaison du segment

//				cubicCurve = new CubicCurve2D.Double( // Créer une courbe cubique
//				debutC.x, debutC.y, // Segment debut point
//				controleEtoiles.x, controleEtoiles.y, // Point de contrôle for debut
//				finControle.x, finControle.y, // Point de contrôle for fin
//				finC.x, finC.y); // Point de terminaison du segment
		Graphics2D g2D = (Graphics2D)arg0; // Obtenir un contexte de périphérique 
		
		g2D.setPaint(Color.blue);
	    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.draw(quadCourbe);
		g2D.draw(cubicCurve);
//		AffineTransform at = new AffineTransform() ;
//		at.translate(0, 0) ;
//		g2D.setTransform(at) ;
//		g2D.draw(quadCourbe);
//		g2D.draw(cubicCurve);
		
		BlocUI b = new BlocUI() ;
		b.drawme(g2D) ;

	}


	int globalTranslateCoordFirstPressX = 0 ;
	int globalTranslateCoordFirstPressY = 0 ;
	MouseAdapter ma = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			int coordEventX = e.getX() ;
			int coordEventY = e.getY() ;
			switch (currentMode) {
			case MODE_NEED_TRANSLATE_GLOBAL:
			{
				globalTranslateCoordFirstPressX = coordEventX ;
				globalTranslateCoordFirstPressY = coordEventY ;
				translateUAddedTemp = 0 ;
				translateVAddedTemp = 0 ;
				currentMode = modes.MODE_TRANSLATE_GLOBAL ;
				cursorHand() ;
				repaint() ;
				break;
			}
			default:
				break;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			int coordEventX = e.getX() ;
			int coordEventY = e.getY() ;
			switch (currentMode) {
			case MODE_TRANSLATE_GLOBAL:
			{
				translateU = translateU + translateUAddedTemp ;
				translateV = translateV + translateVAddedTemp ;
				translateUAddedTemp = 0 ;
				translateVAddedTemp = 0 ;
				currentMode = modes.MODE_NEED_TRANSLATE_GLOBAL ;
				cursorArrow() ;
				break;
			}
			default:
				break;
			}
		}
	} ;
	MouseMotionAdapter mma = new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			int coordEventX = e.getX() ;
			int coordEventY = e.getY() ;
			switch (currentMode) {
			case MODE_TRANSLATE_GLOBAL:
			{
				translateUAddedTemp = (coordEventX-globalTranslateCoordFirstPressX) ;
				translateVAddedTemp = (coordEventY-globalTranslateCoordFirstPressY) ;
				currentMode = modes.MODE_TRANSLATE_GLOBAL ;
				repaint() ;
				break;
			}
			default:
				break;
			}
		};
		@Override
		public void mouseMoved(MouseEvent e) {
			int coordEventX = e.getX() ;
			int coordEventY = e.getY() ;
			switch (currentMode) {
			case MODE_NEED_TRANSLATE_GLOBAL:
			{
				
				repaint() ;
				break;
			}
			default:
				break;
			}
		}
	};
	
	void cursorHand() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	void cursorArrow() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}


	public void modeGlobalMove() {
		currentMode = modes.MODE_NEED_TRANSLATE_GLOBAL ;
	}
}
