/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This frame will open a window that tells the user the rules and objectives of the game
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RulesFrame extends JFrame implements ActionListener{
	
	//background image
	JLabel background = new JLabel(new ImageIcon("images/rulesBackground.png"));
	
	//Button to go back to the homepage
	ImageIcon backIcon = new ImageIcon("images/home.png"); //image of the button
	JButton backBtn = new JButton(backIcon); //turn image into a button

	//constructor method
	public RulesFrame() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1000,800); //size of window
		setTitle("Foodio Rules");
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1000, 800);
		
		//quit button
		backBtn.setBounds(30, 30, 51, 51);
		backBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		backBtn.addActionListener(this);
		background.add(backBtn);
		
		//set the window to visible
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//play button clicking noise
		
		Character.playMusic("audio/buttonSound.wav");
		//if the back button is clicked
		if (e.getSource() == backBtn) {
			setVisible(false);
			//create a new instance of the Homepage class
			new Homepage();
		}
	}
	
}
