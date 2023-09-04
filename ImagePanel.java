/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file will create a ImagePanel object that will allow a panel to have a background
 * 
 * Major Skills: n/a
 * 
 *  Areas of Concern:
 * - none
 * 
 *External Sources:
 * - http://www.java2s.com/Tutorials/Java/Swing_How_to/Basic/Add_Background_image_to_JPanel.htm (panel with image)
 * 
 */ 


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//The ImagePanel class will create a panel with an image background
class ImagePanel extends JPanel {

	//create an image
	private Image img;

	//constructor method with an image parameter
	public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}