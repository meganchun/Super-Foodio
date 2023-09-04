/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This program will allow the user to select which theme they would like for the background of the game
 * 
 * Major Skills:
 * - if statements
 * 
 *  Areas of Concern:
 * - none
 * 
 * External Sources:
 * - none
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChooseTheme extends JFrame implements ActionListener {

	public JLabel background = new JLabel(new ImageIcon("images/themeBackground.png"));
	
	//create icons of the different characters
	public Icon dayIcon = new ImageIcon("images/dayIcon.png");
	public Icon sunsetIcon = new ImageIcon("images/sunsetIcon.png");
	public Icon nightIcon = new ImageIcon("images/nightIcon.png");
	
	//create buttons of the different character icons
	public JButton day = new JButton(dayIcon);
	public JButton sunset = new JButton(sunsetIcon);
	public JButton night = new JButton(nightIcon);
	
	public static String themeChoice;
	
	public ChooseTheme() {
		
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
		
		//day theme button
		day.setBounds(100, 334, 245, 314);
		day.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		day.addActionListener(this);
		background.add(day);
	
		//sunset theme button
		sunset.setBounds(400, 334, 245, 314);
		sunset.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		sunset.addActionListener(this);
		background.add(sunset);
	
		//night theme button
		night.setBounds(700, 334, 245, 314);
		night.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		night.addActionListener(this);
		background.add(night);
		
		//set the window to visible
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Character.playMusic("audio/buttonSound.wav");

		//if the day theme button is clicked
		if (e.getSource() == day) {
			themeChoice = "day";
			setVisible(false);
			new ChooseLevel();
		}
		//if the sunset theme button is clicked
		if (e.getSource() == sunset) {
			themeChoice = "sunset";
			setVisible(false);
			new ChooseLevel();
		}
		//if the night theme button is clicked
		if (e.getSource() == night) {
			themeChoice = "night";
			setVisible(false);
			new ChooseLevel();
		}
	}
	//the getTheme method will return the theme the user chose
	public static String getTheme() {
		return themeChoice;
	}
	

	
}
