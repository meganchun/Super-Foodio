/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This program will allow the user to select which character they would like for the game and store their
 * decision in a string
 * 
 * Major Skills:
 * - if statements
 * 
 * Areas of Concern:
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

public class ChooseCharacterFrame extends JFrame implements ActionListener {

	//background image
	public JLabel background = new JLabel(new ImageIcon("images/universalbackground.png"));
	
	//title label
	public JLabel chooseTitle = new JLabel(new ImageIcon("images/chooseTitle.png"));
	
	//create icons of the different characters
	public Icon friesIcon = new ImageIcon("images/friesIcon.png");
	public Icon toastIcon = new ImageIcon("images/toastIcon.png");
	public Icon burgerIcon = new ImageIcon("images/burgerIcon.png");
	
	//create buttons of the different character icons
	public JButton fries = new JButton(friesIcon);
	public JButton toast = new JButton(toastIcon);
	public JButton burger = new JButton(burgerIcon);
	
	//string of the user's character choice
	public static String characterChoice;
	
	//Constructor method
	public ChooseCharacterFrame() {
		
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
				
		
		background.add(chooseTitle);
		chooseTitle.setBounds(196, 141, 600, 131);
			
		//Fries button
		fries.setBounds(190, 373, 104, 199);
		fries.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		fries.addActionListener(this);
		background.add(fries);
	
		//fries JLabel
		JLabel friesLabel = new JLabel("FRIES");
		friesLabel.setBounds(198,600,100,36);
		friesLabel.setForeground(new Color(33,158,228));
		friesLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		background.add(friesLabel);
		
		//Toast button
		toast.setBounds(438, 373, 114, 206);
		toast.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		toast.addActionListener(this);
		background.add(toast);
		
		//toast JLabel
		JLabel toastLabel = new JLabel("TOAST");
		toastLabel.setBounds(439,600,105,36);
		toastLabel.setForeground(new Color(33,158,228));
		toastLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		background.add(toastLabel);
		
		//Burger button
		burger.setBounds(695, 373, 106, 197);
		burger.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		burger.addActionListener(this);
		background.add(burger);
		
		//toast JLabel
		JLabel burgerLabel = new JLabel("BURGER");
		burgerLabel.setBounds(685,600,135,36);
		burgerLabel.setForeground(new Color(33,158,228));
		burgerLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		background.add(burgerLabel);

		//set the window to visible
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//play a button clicked sound
		Character.playMusic("audio/buttonSound.wav");

		//if the user chooses fries
		if (e.getSource() == fries) {
			
			//set the user's choice to fries
			characterChoice = "fries";
			
			//create a new instance of the ChooseTheme class
			new ChooseTheme();
			setVisible(false);
		}
		//if the user chooses toast
		if (e.getSource() == toast) {
			
			//set the user's choice to toast
			characterChoice = "toast";
			
			//create a new instance of the ChooseTheme class
			new ChooseTheme();
			setVisible(false);
		}
		//if the user chooses burger
		if (e.getSource() == burger) {
			
			//set the user's choice to burger
			characterChoice = "burger";
			
			//create a new instance of the ChooseTheme class
			new ChooseTheme();
			setVisible(false);
		}
	}
	
	//return the character which the user chose
	public static String getCharacter() {
		return characterChoice;
	}
	
}
