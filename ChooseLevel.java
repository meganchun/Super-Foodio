/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 *May 28, 2023
 * 
 * This program will allow the user to select which level they would like to play, and show which 
 * levels are unlocked to them. If the level is unlocked it will show the number and will play a clicking noise
 * when selected. If it is locked, the level icon will show a lock and will not allow the user to continue 
 * 
 * Major Skills:
 * - if/else statements
 * 
 *  Areas of Concern:
 * - none
 * 
 * External Sources:
 * - none
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChooseLevel extends JFrame implements ActionListener{
	
	//background image
	public JLabel background = new JLabel(new ImageIcon("images/universalBackground.png"));
	
	//title label
	public JLabel levelTitle = new JLabel(new ImageIcon("images/levelTitle.png"));
	
	
	//create icons of the different characters
	public static ImageIcon level1;
	public static ImageIcon level2;
	public static ImageIcon level3;
	public static ImageIcon level4;
	
	//create buttons of the different character icons
	public static JButton one;
	public static JButton two;
	public static JButton three;
	public static JButton four;
	
	public static LevelFrame playLevel;
	
	public ChooseLevel() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1000,800); //size of window
		setTitle("Welcome To Super Foodio"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1000, 800);
		
		//title of the window
		levelTitle.setBounds(346, 131, 350, 141);
		background.add(levelTitle);
		
		//icon of the first level (always unlocked)
		level1 = new ImageIcon("images/oneIcon.png");
		
		//change the icon of the level to display locked or unlocked
		if (LevelFrame.levelsUnlocked[1] == false) { //if it is locked, display a locked icon
			level2 = new ImageIcon("images/locked.png");
		}
		else {
			level2 = new ImageIcon("images/twoIcon.png");
		}
		
		if (LevelFrame.levelsUnlocked[2] == false) { //if it is locked, display a locked icon
			level3 = new ImageIcon("images/locked.png");
		}
		else {
			level3 = new ImageIcon("images/threeIcon.png");
		}
		
		if (LevelFrame.levelsUnlocked[3] == false) { //if it is locked, display a locked icon
			level4 = new ImageIcon("images/locked.png");
		}
		else {
			level4 = new ImageIcon("images/fourIcon.png");
		}
	
		//create a button of the level image
		one = new JButton(level1);
		two = new JButton(level2);
		three = new JButton(level3);
		four = new JButton(level4);
	
		//buttons for level choices
		//level one
		one.setBounds(171, 400, 108, 108);
		one.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		one.addActionListener(this);
		background.add(one);
		
		//level two
		two.setBounds(355, 400, 108, 108);
		two.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		two.addActionListener(this);
		background.add(two);
		
		//level one
		three.setBounds(538, 400, 108, 108);
		three.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		three.addActionListener(this);
		background.add(three);
		
		//level one
		four.setBounds(722, 400, 108, 108);
		four.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		four.addActionListener(this);
		background.add(four);
		
		//set the window to visible
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		//if level one is clicked
		if (e.getSource() == one) {
			if (LevelFrame.levelsUnlocked[0] == true) {
				
				//play a button clicking sound
				Character.playMusic("audio/buttonSound.wav");
				
				//create a new instance of level 1
				playLevel = new LevelFrame(1);
				setVisible(false);
			}
		}
		//if level two is clicked
		if (e.getSource() == two) {
			//if level 2 is unlocked
			if (LevelFrame.levelsUnlocked[1] == true) {
				
				//play a button clicking sound
				Character.playMusic("audio/buttonSound.wav");
				
				//create a new instance of level 2
				playLevel = new LevelFrame(2);
				setVisible(false);
			}
		}
		//if level three is clicked
		if (e.getSource() == three) {
			//if level 3 is unlocked
			if (LevelFrame.levelsUnlocked[2] == true) {
				
				//play a button clicking sound
				Character.playMusic("audio/buttonSound.wav");

				//create a new instance of level 3
				playLevel = new LevelFrame(3);
				setVisible(false);
			}
		}
		//if level four is clicked
		if (e.getSource() == four) {
			//if level 4 is unlocked
			if (LevelFrame.levelsUnlocked[3] == true) {
				
				//play a button clicking sound
				Character.playMusic("audio/buttonSound.wav");
				
				//create a new instance of level 4
				playLevel = new LevelFrame(4);
				setVisible(false);
			}
		}
	}
	
}
