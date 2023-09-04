/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file will create ImageIcons of all the various objects, walls or images needed for the game
 * 
 * Major Skills: n/a
 * 
 *  Areas of Concern:
 * - none
 * 
 *External Sources:
 * - none
 * 
 */ 

import javax.swing.Icon;
import javax.swing.ImageIcon;

// Public class
public class Icons {

	// Create icons for game map
	// Create the wall
	public static final ImageIcon WALL = new ImageIcon("images/wall.png");
	
	public static final ImageIcon GRASS = new ImageIcon("images/grass.png");
	
	// Create a coin
	public static final ImageIcon COIN = new ImageIcon("images/"+ChooseCharacterFrame.getCharacter()+"Coin.png");
	
	// Create the bacteria
	public static final ImageIcon BACTERIA = new ImageIcon("images/bacteria.png");
	
	// Create the portal
	public static final ImageIcon PORTAL1 = new ImageIcon("images/portal.png");
	
	// Create the portal
	public static final ImageIcon PORTAL2 = new ImageIcon("images/portal.png");
	
	// Create the icon to next level
	public static final ImageIcon KIRBY = new ImageIcon("images/kirby.png");
	
	// Create the stars
	public static final ImageIcon STAR = new ImageIcon("images/star.png");
	public static final ImageIcon STARFILLED = new ImageIcon("images/starFilled.png");
	
	// Create icon for level choices
	public static final ImageIcon LEVEL2 = new ImageIcon("images/twoIcon.png");
	public static final ImageIcon LEVEL3 = new ImageIcon("images/threeIcon.png");
	public static final ImageIcon LEVEL4 = new ImageIcon("images/fourIcon.png");

	// Create the two different direction of the character
	public static ImageIcon CHARACTERRIGHT = new ImageIcon("images/"+ChooseCharacterFrame.getCharacter()+"Right.png");
	public static ImageIcon CHARACTERLEFT = new ImageIcon("images/"+ChooseCharacterFrame.getCharacter()+"Left.png");
	

}