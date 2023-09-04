/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file will congratulate the user for completing the level and ask if the wish to 
 * continue or to return back home
 * 
 * Major Skills:
 * - if statements
 * - Java Swing
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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NextLevelFrame extends JFrame implements ActionListener{
	
	//background image
	JLabel background = new JLabel(new ImageIcon("images/nextLevelBackground.png"));
	
	//Button to go back to the homepage
	public ImageIcon backIcon = new ImageIcon("images/home.png");
	public JButton backBtn = new JButton(backIcon);

	//create icons of the different characters
	public Icon nextLevel = new ImageIcon("images/nextLevelBtn.png");
	public Icon home = new ImageIcon("images/homeBtn.png");
	
	//create buttons of the different character icons
	public JButton nextLevelBtn = new JButton(nextLevel);
	public JButton homeBtn = new JButton(home);
	
	public static LevelFrame playNextLevel;

	//constructor class
	public NextLevelFrame() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1000,800); //size of window
		setTitle("Completed Level "+LevelFrame.levelNum);
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1000, 800);		
		
		//if the previous level is less than level 4
		if (LevelFrame.levelNum < 4) {
			//next level button
			nextLevelBtn.setBounds(320, 440, 367, 96);
			nextLevelBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
			nextLevelBtn.addActionListener(this);
			background.add(nextLevelBtn);
		}
		
		//home button
		homeBtn.setBounds(368, 556, 265, 96);
		homeBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		homeBtn.addActionListener(this);
		background.add(homeBtn);
		
		//set the window to visible
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//play button clicking sound
		Character.playMusic("audio/buttonSound.wav");
		
		//if the next level button is clicked
		if (e.getSource() == nextLevelBtn) {
			if (LevelFrame.levelNum > 2) {
				playNextLevel.setVisible(false);
			}
			//create a new instance of the LevelFrame of the next level
			playNextLevel = new LevelFrame(LevelFrame.levelNum+1);
			ChooseLevel.playLevel.setVisible(false);
			setVisible(false);
			
		}
		//if the home button is clicked
		if (e.getSource() == homeBtn) {
			//create a new instance of the Homepage class
			new Homepage();
			setVisible(false);
		}
	}
	
}
