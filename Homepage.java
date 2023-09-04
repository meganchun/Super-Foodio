/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 26, 2023
 * 
 * This file will create the homepage of the program that will give user's the option to start a new game, 
 * see the highscores or quit the game
 * 
 * Major Skills:
 * - Java Swing (JFrame)
 * 
 * Areas of Concern:
 * - none
 * 
 * External Sources:
 * - none
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Homepage extends JFrame implements ActionListener {

	//background image
	public JLabel background = new JLabel(new ImageIcon("images/homePagebackground.png"));
	
	//start button
	public Icon startBtn = new ImageIcon("images/startBtn.png");
	public JButton start = new JButton(startBtn);
	
	//high scores button
	public Icon scoresBtn = new ImageIcon("images/scoresBtn.png");
	public JButton scores = new JButton(scoresBtn);
	
	//rules button
	public Icon rulesBtn = new ImageIcon("images/rulesBtn.png");
	public JButton rules = new JButton(rulesBtn);
	
	//quit button
	public Icon quitBtn = new ImageIcon("images/quitBtn.png");
	public JButton quit = new JButton(quitBtn);
		
	public Homepage() {
		
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
		
		//start button
		start.setBounds(265, 324, 459, 119);
		start.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		start.addActionListener(this);
		background.add(start);
		
		//high scores button
		scores.setBounds(265, 471, 460, 72);
		scores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		scores.addActionListener(this);
		background.add(scores);
		
		//rules button
		rules.setBounds(265, 559, 460, 72);
		rules.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		rules.addActionListener(this);
		background.add(rules);
		
		//quit button
		quit.setBounds(265, 642, 460, 72);
		quit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		quit.addActionListener(this);
		background.add(quit);	
		
		//set the window to visible
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//play a button noise if a button is clicked
		Character.playMusic("audio/buttonSound.wav");
		
		//if the start button is pressed
		if (e.getSource() == start) {
			new Name(); //create an instance of the Name class
			setVisible(false);
		}
		//if the high scores button is pressed
		if (e.getSource() == scores) {
			new HighScoresFrame(); //create an instance of the HighScoresFrame class
			setVisible(false);
		}
		//if the rules button is pressed
		if (e.getSource() == rules) {
			new RulesFrame(); //create an instance of the RulesFrame class
			setVisible(false);
		}
		//if the quit button is pressed
		if (e.getSource() == quit) 
			dispose(); //close the window
		}
		
	}

