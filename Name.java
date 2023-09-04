/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * Major Skills:
 * - Java Swing
 * 
 *  Areas of Concern:
 * - if the user enters a name that is already entered, it won't produce an error message
 * 
 * This frame will display a screen to the user that will allow them to enter their name or initial
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
import javax.swing.JTextField;

public class Name extends JFrame implements ActionListener{
		
	//background image
	public static JLabel background = new JLabel(new ImageIcon("images/nameBackground.png"));
	
	//text field for name
	public static JTextField nameField = new JTextField("",479);
	
	//name variable will hold a String of the name the user entered
	public static String name;
	
	//enter button
	public static Icon enter = new ImageIcon("images/enterBtn.png");
	public static JButton enterBtn = new JButton(enter);
	
		
	public Name() {
		
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
		
		//text area to enter their names
		nameField.setBounds(124, 408, 479, 70);
		nameField.addActionListener(this);
		nameField.setEditable(true);
		nameField.setForeground(new Color(33,158,228));
		nameField.setFont(new Font("Courier New", Font.BOLD, 30));
		background.add(nameField);
			
		//enter button
		enterBtn.setBounds(644, 400, 244, 96);
		enterBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn.addActionListener(this);
		background.add(enterBtn);

		//set the window to visible
		setVisible(true);
		
	}
	
	//getName method will return the name that the user entered in the text box
	public static String getName(int x) {
		return name;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		//if the user presses the enter button
		if (e.getSource() == enterBtn) { 
			
			//save the name in the text field to the name variable
			name = nameField.getText();
			
			//play button clicked noise
			Character.playMusic("audio/buttonSound.wav");
			
			//create a new instance of the ChooseCharacterFrame
			new ChooseCharacterFrame();
			setVisible(false);
		}	
	
	}	
}
