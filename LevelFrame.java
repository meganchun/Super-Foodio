/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file will display the game level to the user based on which level they inputed
 * It will display their character, "coins"/food and a background based on their lastest input
 * 
 * Major Skills:
 * - Arrays
 * - File Scanner
 * 
 *  Areas of Concern:
 * - bottom row of walls does not display in the panel (does not effect program, visual concern)
 * 
 * External Sources:
 * - none
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class LevelFrame extends JFrame implements KeyListener,ActionListener {
	
	//create the image background
	static ImageIcon backgroundImg;
	
	//JLabel array that will create a 20 x 25 board
	public static JLabel[][] boardArray = new JLabel[20][25];;
	
	//create a image panel 
	public static ImagePanel levelPanel;
	
	//variable that will hold the value of the level number
	public static int levelNum;
	
	//create an instance of Character class
	//pass image from Icon class and keys used to move him
	public static Character character = new Character(Icons.CHARACTERRIGHT, new String[] {"a", "d", " "});
	
	//create a home button
	Icon home = new ImageIcon("images/home.png");
	JButton homeBtn = new JButton(home);
	
	//create an exit button
	Icon exit = new ImageIcon("images/exit.png");
	JButton exitBtn = new JButton(exit);
	
	//create an reset button
	Icon reset = new ImageIcon("images/restartBtn.png");
	JButton resetBtn = new JButton(reset);
	
	//label and icon to display the user's score
	public static JLabel scoreLabel;
	public static JLabel scoreIcon = new JLabel(Icons.COIN);
	
	//image of the stars
	public static ImageIcon starImage1 = new ImageIcon("images/darkStar.png");
	public static ImageIcon starImage2 = new ImageIcon("images/darkStar.png");
	public static ImageIcon starImage3 = new ImageIcon("images/darkStar.png");
	
	//JLabel of the stars
	public static JLabel star1;
	public static JLabel star2;
	public static JLabel star3;
	
	//array that will hold the coordinate of portal 1 and portal 2
	public static int[] portal1;
	public static int[] portal2;
	
	//string of the file path to the high scores file
	String filePath = "data/highscores.txt";
	
	//Boolean array that will control which levels are unlocked for the user
	public static boolean[] levelsUnlocked = {true,false,false,false};
		
	//constructor method that has a parameter indicating the level
	public LevelFrame(int level) {
		
		Character.score = 0;
		Character.star = 0;
		
		//set the level number to the level parameter
		levelNum = level;
		
		// length of row (horizontal)	// length of columns (vertical)
		setSize(25*boardArray[0].length, 25*boardArray.length);
		setTitle("Level "+ levelNum);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//set the images in the array based on the level txt file
		loadLevel(level);
		
		//create a panel of the level based on the level of the user
		createLvlPanel();
		
		//call the setupKeyBinders method that will indicate the movements
		setupKeyBinders();
		
		//add a key listener to the panel
		levelPanel.addKeyListener(this);
		
		//set the window to visible
		setVisible(true);
		
	}
	
	//Read level input and load correct map
	private void loadLevel(int level) {
	
		
		portal1 = new int[2];
		portal2 = new int[2];
		
		try {
			// Create Scanner object to get the file
			Scanner inputFile = new Scanner(new File("data/level" + level + ".txt"));
			
			//for each row in the boardArray
			for (int row = 0; row < boardArray.length; row++) {
				
				//create a character array that will save each character in each row
				char[] lineArray = inputFile.next().toCharArray();
	
				//for each column in a row
				for (int col = 0; col < lineArray.length; col++) {
					
					//add the image to the board based on the character of the txt file
					if (lineArray[col] == 'G') {
						boardArray[row][col] = new JLabel(Icons.GRASS);
					}
					else if (lineArray[col] == 'B') {
						boardArray[row][col] = new JLabel(Icons.WALL);
					}
					else if (lineArray[col] == 'C') {
						boardArray[row][col] = new JLabel(Icons.COIN);
					}
					else if (lineArray[col] == 'S') {
						boardArray[row][col] = new JLabel(Icons.STAR);
					}
					else if (lineArray[col] == 'E') {
						boardArray[row][col] = new JLabel(Icons.BACTERIA);
					}
					else if (lineArray[col] == 'K') {
						boardArray[row][col] = new JLabel(Icons.KIRBY);
					}
					else if (lineArray[col] == 'P') {
						boardArray[row][col] = new JLabel(Icons.PORTAL1);
						portal1[0] = row;
						portal1[1] = col;
					}
					else if (lineArray[col] == 'p') {
						boardArray[row][col] = new JLabel(Icons.PORTAL2);
						portal2[0] = row;
						portal2[1] = col;
					}
					else {
						boardArray[row][col] = new JLabel();
					}
					
				}
			} inputFile.close(); //close the scanner 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

	//createLvlPanel will create and add the images into the window
	private void createLvlPanel() {
		
		//background image based on the theme the user chose 
		backgroundImg = new ImageIcon("images/"+ChooseTheme.getTheme()+".png");
		
		//create a panel with the background image
		levelPanel = new ImagePanel(backgroundImg.getImage());
		
		//add the panel to the JFrame
		add(levelPanel);
		levelPanel.setLayout(null);
	
		//set the width and length to the size of the level
		levelPanel.setBounds(0, 0, 25*boardArray[0].length, 25*boardArray.length);
		
		//iterate through each row in the array board
		for (int row = 0; row < boardArray.length; row++) {
			
			//iterate through each column in the array board
			for (int col = 0; col < boardArray[0].length; col++) {
				
				// Add labels to the panel
				// Set bounds for each of the squares/tiles
				if (boardArray[row][col].getIcon() == Icons.WALL) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.GRASS) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.COIN) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.STAR) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.KIRBY) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.BACTERIA) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
				}
				else if (boardArray[row][col].getIcon() == Icons.PORTAL1) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
					
				}
				else if (boardArray[row][col].getIcon() == Icons.PORTAL2) {
					boardArray[row][col].setBounds(col*25, row*25, 25, 25);
					levelPanel.add(boardArray[row][col]);
					
				}
			}
		}
		
		//set the character's initial position
		character.setBounds(25, 425, 25, 25);
		//add the character to the frame
		levelPanel.add(character);
		
		//score icon
		scoreIcon.setBounds(310, 30, 25, 25);
		levelPanel.add(scoreIcon);
		
		//score text
		scoreLabel = new JLabel("0");
		scoreLabel.setBounds(290, 30, 20, 30);
		scoreLabel.setForeground(new Color(33,158,228));
		scoreLabel.setFont(new Font("Courier New", Font.BOLD, 15));
		levelPanel.add(scoreLabel);
		
		//first star
		star1 = new JLabel(starImage1);
		star1.setBounds(530, 40, 15, 15);
		levelPanel.add(star1);
		
		//second star
		star2 = new JLabel(starImage2);
		star2.setBounds(550, 40, 15, 15);
		levelPanel.add(star2);
		
		//third star
		star3 = new JLabel(starImage3);
		star3.setBounds(570, 40, 15, 15);
		levelPanel.add(star3);
		
		//exit button
		exitBtn.setBounds(30, 30, 25, 25);
		exitBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		exitBtn.addActionListener(this);
		levelPanel.add(exitBtn);
		
		//reset button
		resetBtn.setBounds(60, 30, 25, 25);
		resetBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		resetBtn.addActionListener(this);
		levelPanel.add(resetBtn);
		
	}
	
	//the setupKeyBinders method will assign keyboard actions to map actions
	private void setupKeyBinders() {
		
		//create an ActionMap
		ActionMap actionMap;
		
		//create an InpuMap
		InputMap inputMap;
		
		//get and assign the InputMap of the level panel 
		inputMap = levelPanel.getInputMap();
		
		//get and assign the ActionMap of the level panel 
		actionMap = levelPanel.getActionMap();
		
		//if the user enters 'a', set the action to left
		inputMap.put(KeyStroke.getKeyStroke(character.getKey()[0].toCharArray()[0]), "left");
		actionMap.put("left", new KeyAction("left"));
		
		//if the user enters 'd, set the action to right
		inputMap.put(KeyStroke.getKeyStroke(character.getKey()[1].toCharArray()[0]), "right");
		actionMap.put("right", new KeyAction("right"));
		
		//if the user enters a space, set the action to jump
		inputMap.put(KeyStroke.getKeyStroke(character.getKey()[2].toCharArray()[0]), "jump");
		actionMap.put("jump", new KeyAction("jump"));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//if the key is released, stop moving the character
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			character.setdX(0);
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exitBtn) {
		

			//set the window to not visible
			setVisible(false);
			
			//create a new instance of the Homepage class
			new Homepage();
			
		}
		//if the reset button is clicked
		if (e.getSource() == resetBtn) {
			
			Character.star = 0;
			
			new LevelFrame(levelNum);
			dispose();
		}
	}
}