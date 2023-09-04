/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 26, 2023
 * 
 * This program will determine the highscores from the list of inputed scores and display the top five scores to the user
 * 
 * Major Skills:
 * - None primative data types
 * - File Scanner
 * - Sorting
 * - For loops
 * 
 *  Areas of Concern:
 * - none
 * 
 * External Sources:
 * - https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/ (check how many lines are in a file)
 * - https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java (sorting hashmaps)
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HighScoresFrame  extends JFrame implements ActionListener{
	
	//background image
	public JLabel background = new JLabel(new ImageIcon("images/ScoresBackground.png"));
	
	//Map of the the user's name and their scores 
	public static Map<String, Integer> scoresList;
	//Linked hash map of the organized scores
	public static LinkedHashMap<String, Integer> highscores;
	//Array list of the scores
	public static ArrayList<Integer> list;
	
	//create an exit button
	Icon home = new ImageIcon("images/home.png");
	JButton homeBtn = new JButton(home);
	
	//Constructor method
	public HighScoresFrame() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1000,800); //size of window
		setTitle("Highscores");
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1000, 800);
		
		//home button
		homeBtn.setBounds(29, 29, 51, 51);
		homeBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		homeBtn.addActionListener(this);
		background.add(homeBtn);
		
		//initialize the x and y coordinates of the names
		int x = 200;
		int y = 370;

		//call the getHighscores method to fill the arrays
		getHighscores();
		
		//initialize the counter for the number of names
		int i = 1;
		
		//for each name in the organized hash map
		for (String name : highscores.keySet()) {
			
			//if there are five or less names printed
			if (i <= 5) {
				
				//create a JLabel for the name
				JLabel nameLabel = new JLabel(name);
				nameLabel.setBounds(x, y, 200, 36);
				nameLabel.setForeground(new Color(33,158,228));
				nameLabel.setFont(new Font("Courier New", Font.BOLD, 30));
				background.add(nameLabel); //add to the window
				
				y += 60;
				
			}
			//if there are already 5 names printed, don't display anymore
			else {
				//break the loop
				break;
			}
			//increment counter
			i++;
			
		}

		//initialize the x and y coordinates of the scores
		x = 700;
		y = 370;
		
		//initialize the counter for the number of scores
		i = 1;
		
		//for each score in the organized hash map
		for (int score : highscores.values()) {

			//if there are five or less scores printed
			if (i <= 5) {
			//create a JLabel for the scores
			JLabel scoreLabel = new JLabel(Integer.toString(score));
			scoreLabel.setBounds(x, y, 120, 36);
			scoreLabel.setForeground(new Color(33,158,228));
			scoreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
			background.add(scoreLabel); //add to the window
			
			//increment the y-coordinate 60 units down
			y += 60;
			}
			//if there are already 5 scores printed, don't display anymore
			else {
				//break the loop
				break;
			}
			//increment counter
			i++;
		}
		//set the window to visible
		setVisible(true);
		
	}
	//fill the arrays, lists and maps with data from the external file
	public static void fillArray() {

		//initialize the number of lines
	    int lines = 0;
	    
		//check how many scores are in the highscores.txt file
		File file = new File("data/highscores.txt");
		
		// https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/
		//create a new line number reader for the txt file
	    try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

	    	//while the line is not empty
	    	while (lnr.readLine() != null) ;

	    	//the number of lines becomes the line number
	    	lines = lnr.getLineNumber();

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		try {
			
			//use the scanner object to access the file
			Scanner inputFile = new Scanner(new File("data/highscores.txt"));
			
			//set a delimiter which will stop scanning the data for one 
			inputFile.useDelimiter(",|\r\n");
			
			//for loop that will iterate through all the indexes in the scores array
			for(int player = 0; player < lines; player++) {
				
				//create variables of each data needed for the scores
				String name = inputFile.next();
				int score = inputFile.nextInt();
				
				//add the data to the corresponding person
				scoresList.put(name,score);
			}
			//close the scanner
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			//if the file is not found output an error message
			System.out.println("File error");
		}
	}
	

	public static void getHighscores() {
		
		//create a new instance of a HashMap called scoresList
		scoresList = new HashMap<String, Integer>();
		//create a new instance of a LinkedHashMap called highscores
		highscores = new LinkedHashMap<>();
		//create a new instance of an ArrayList called list
		list = new ArrayList<>();
		
		//call the fillArray method which will get the data from the file
		fillArray();
		
		//https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java
		//enhanced for loop to get the scores of the users
		for (Map.Entry<String, Integer> entry : scoresList.entrySet()) {
			//add the score to the the ArrayList
			list.add(entry.getValue());
	       }
		
		//sort the list of scores from highest to lowest
		Collections.sort(list, Collections.reverseOrder()); 
		
		//for each score in the list
		for (int score : list) {
			
			//for each entry in the scoresList
            for (Map.Entry<String, Integer> entry : scoresList.entrySet()) {
            	
            	//if the value (score) in the Map is equal to the score in the list
        		if (entry.getValue().equals(score)) {
        			//put the user's name to the corresponding score in the high scores LinkedHashMap
                	highscores.put(entry.getKey(), score);
            	}
            }
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//play a clicking button noise
		Character.playMusic("audio/buttonSound.wav");
		
		//if home button is clicked
		if (e.getSource() == homeBtn) {
			//create a new instance of the Homepage class
			new Homepage();
			setVisible(false);
		}
	}
	
}
