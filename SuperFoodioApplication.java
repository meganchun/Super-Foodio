/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file will create an instance of the Homepage class
 * 
 * Note:
 * - All levels are passable but may require some patients
 * - If you get stuck in a block try spamming the jump button
 * 
 * Additional Features:
 * 
 * - Get player image to Face the Proper Direction as they move
 * - Fix the movement of the character as it is still not perfect or accurate
 * - Add a Menubar - with a number of options (New Game, Quit, etc.)
 * - Add a separate Opening Screen before the game starts
 * - Title screen – A title screen is displayed before the game is started.  
 * - Add Different Board Themes - user can select from different themes
 * - Add more Characters - user can select their character
 * - Add Music
 * - Add Sound Effects 
 * - Add New Levels
 * 
 * - Add a Highscore with a player’s initials - save this information to and external text file (ADVANCED)
 * - Add a Highscore Table - saves the Top 5 scores to an external text file (ADVANCED)
 */

public class SuperFoodioApplication {

	//main method
	public static void main(String[] args) {
		
		//create a new instance of the homepage
		new Homepage();
		
		//play the background music by calling the playMusic class
		Character.playMusic("audio/backgroundMusic.wav");
	
	}

}
