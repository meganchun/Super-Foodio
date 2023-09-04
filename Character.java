/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * Control the character's movements, actions and results
 * 
 * Major Skills:
 * - if statements
 * - file input
 * - methods
 * 
 * Areas of Concern:
 * - some movements are not fully accurate or effective
 * 
 * External Sources:
 * - https://www.youtube.com/watch?v=wJO_cq5XeSA (audio files)
 * - https://www.digitalocean.com/community/tutorials/java-append-to-file (append files)
 * 
 */


import java.awt.Image;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.StringCharacterIterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

// Public class
public class Character extends JLabel implements ActionListener {
	
	// This deals with the character facing different directions
	private ImageIcon[] iconArray;
	// This deals with the character facing one direction (static)
	private ImageIcon icon;
	private String[] key;
	// Controls how many units the character moves
	private int dX, dY;
	// How long character should jump for
	private Timer jumpTimer = new Timer(25, this);
	// When character is jumping
	private int jumpCounter;
	// Determines when character is jumping
	private boolean jumping = false;
	// How long character should fall for
	private Timer fallTimer = new Timer(25, this);
	// When character is falling
	private int fallCounter;
	// Determines when character is falling
	private boolean falling = false;
	
	public static int totalScore;
	public static int score; //variable to control the score
	public static int star; //checks how many stars have been collected
	
	//sound file
	public static String btnSound = "audio/coinSound.wav";

	// Constructor for ImageIcon character (static image)
	public Character(ImageIcon icon, String[] key) {
		super();
		this.icon = icon; // Sets icon field
		this.key = key; // Sets key field
	}
		
	
	// Constructor for ImageIcon[] array character (dynamic image)
	public Character(ImageIcon[] iconArray, String[] key) {
		super();
		setIconArray(iconArray);
		this.key = key;
	}

	
	public ImageIcon[] getIconArray() {
		return iconArray;
	}
	
	//GET AND SET METHODS
	public static int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void setIconArray(ImageIcon[] iconArray) {
		this.iconArray = iconArray;
	}


	public ImageIcon getIcon() {
		return icon;
	}


	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}


	public String[] getKey() {
		return key;
	}


	public void setKey(String[] key) {
		this.key = key;
	}


	public int getdX() {
		return dX;
	}


	public void setdX(int dX) {
		this.dX = dX;
	}


	public int getdY() {
		return dY;
	}


	public void setdY(int dY) {
		this.dY = dY;
	}


	public Timer getJumpTimer() {
		return jumpTimer;
	}


	public void setJumpTimer(Timer jumpTimer) {
		this.jumpTimer = jumpTimer;
	}


	public int getJumpCounter() {
		return jumpCounter;
	}


	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}


	public boolean isJumping() {
		return jumping;
	}


	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}


	public Timer getFallTimer() {
		return fallTimer;
	}


	public void setFallTime(Timer fallTimer) {
		this.fallTimer = fallTimer;
	}


	public int getFallCounter() {
		return fallCounter;
	}


	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}


	public boolean isFalling() {
		return falling;
	}


	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	// Utility methods
	// -------------------------------------------
	
	//make character jump
	public void jump() {
		
		if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL ||
			LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GRASS) {
			jumping = true;
			jumpCounter = 0;
			jumpTimer.start();
			playMusic("audio/jump.wav");
		}
			
	}
	//make character fall
	public void fall() {
		
		//When the character should NOT fall
		//if the character is on a wall
		if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL) { 
			
			//if the character is moving right and the block down and too the right is a wall
			if (LevelFrame.boardArray[getRow()+1][getCol()+1].getIcon() == Icons.WALL && dX > 0)  {		
			}
			//if the character is moving left and the block down and too the left is a wall
			if (LevelFrame.boardArray[getRow()+1][getCol()-1].getIcon() == Icons.WALL && dX < 0)  {	
			}
		}
		//else it should fall
		else {
			falling = true;
			fallCounter = 0;
			fallTimer.start();	
		}
		
	}
	
	//moveRight method will move the character right and check if it hit any objects
	public void moveRight() {
		
		//set the icon of the character to face right
		setIcon(Icons.CHARACTERRIGHT);
		
		// x-coordinate to change 5 units at a times
		dX = 5;
		
		// Add change to the x change
		// Use dimensions of the character ie 25, 25
		setBounds(getX() + dX, getY(), 25, 25);
		
		collectCoin(); //call the collectCoin method to see if it is able to collect a coin
		fall(); //call the fall method to see if it is able to fall
		collectStar(); //call the collectStar method to see if it is able to collect a star
		goToNextLevel(); //call the restartGame method to see if it reached Kirby
		enterPortal(); //call the enterPortal method to see if it entered a portal
		restartGame(); //call the restartGame method to see if it hit a bacteria
		
	}
	//moveLeft method will move the character left and check if it hit any objects
	public void moveLeft() {
		
		//set the icon of the character to face left
		setIcon(Icons.CHARACTERLEFT);
		
		// x-coordinate to change 5 units at a times
		dX = -5;
		
		// Add change to the x change
		setBounds(getX() + dX, getY(), 25, 25);
		
		
		collectCoin(); //call the collectCoin method to see if it is able to collect a coin
		fall(); //call the fall method to see if it is able to fall
		collectStar(); //call the collectStar method to see if it is able to collect a star
		goToNextLevel(); //call the restartGame method to see if it reached Kirby
		enterPortal(); //call the enterPortal method to see if it entered a portal
		restartGame(); //call the restartGame method to see if it hit a bacteria
		
	}
	
	//get row position of character
	public int getRow() {
		// Divides current height of character by
		// height of game board to determine y value
		return (int)getY()/25;
	}
	
	//get column position of character
	public int getCol() {
		return (int)getX()/25;
	}
	
	//restartGame method will check if the character hit the bacteria
	public void restartGame() {
		
		//if the user is playing level 2,3,4
		if (LevelFrame.levelNum > 1) {
			//check if the user hit the bacteria
			if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.BACTERIA) {
				playMusic("audio/loseSound.wav");
				//set the previous window to not visible
				NextLevelFrame.playNextLevel.setVisible(false);
				//open a new window of the level
				NextLevelFrame.playNextLevel = new LevelFrame(LevelFrame.levelNum);
				
				//set the players score to zero
				score = 0;
				//set the stars they collected to zero
				star = 0;

			}
		}
		//if the user is playing level 1
		else if (LevelFrame.levelNum == 1) {
			//check if the user hit the bacteria
			if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.BACTERIA) {
				playMusic("audio/loseSound.wav");
				//set the previous window to not visible
				ChooseLevel.playLevel.setVisible(false);				
				//open a new window of the level
				ChooseLevel.playLevel = new LevelFrame(1);
				
				//set the players score to zero
				score = 0;
				//set the stars they collected to zero
				star = 0;
			}
		}
	
	}
	
	//enterPortal method will move the player to the other portal
	public void enterPortal() {
		
		//if the user is on the portal
		if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.PORTAL1){
			//move the character to the location of the second portal
			LevelFrame.character.setBounds(LevelFrame.portal2[1]*25, LevelFrame.portal2[0]*25, 25, 25);
		}

	}
	
	//collectCoin method collect the "food" for the character
	public void collectCoin() {
		
		//get the location of the character
		int row = getRow();
		int col = getCol();
	
		//if the user is on a coin
		if (LevelFrame.boardArray[row][col].getIcon() == Icons.COIN) {
			//play the collecting coin music
			playMusic(btnSound);
			// remove the coin from the board when the character
			// collects it
			LevelFrame.boardArray[row][col].setIcon(null);
			//adds to the score
			score++;
			LevelFrame.scoreLabel.setText(Integer.toString(score));
		}
		
	}

	//the collectStar method will check if the character can collect a star
	public void collectStar() {
		
		//get the location of the character
		int row = getRow();
		int col = getCol();
		
		//if the character is on a star
		if (LevelFrame.boardArray[row][col].getIcon() == Icons.STAR) {
			
			//play a collecting sound
			
			playMusic(btnSound);
			
			// remove the coin from the board when the character
			// collects it
			LevelFrame.boardArray[row][col].setIcon(null);
			
			//increment the star variable
			star++;

			//if user collects 1 star, fill one star in the bar 
			if (star == 1) {
				LevelFrame.star1.setIcon(Icons.STARFILLED);
			}
			//if user collects 2 star, fill two star in the bar 
			if (star == 2) {
				LevelFrame.star2.setIcon(Icons.STARFILLED);
			}
			//if user collects 3 star, fill three star in the bar 
			if (star == 3) {
				LevelFrame.star3.setIcon(Icons.STARFILLED);
			}
		}
		
	}
	
	//see if the user has completed the level
	public void goToNextLevel() {
		
		//if the player reaches Kirby and they have collected all three stars
		if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.KIRBY && star >= 3) {
			
			//add the score of the current level to the total score
			totalScore += score;
			
			//if the level number is less than 4 
			if (LevelFrame.levelNum < 4) {
				LevelFrame.levelsUnlocked[LevelFrame.levelNum] = true; //unlock the next level
			}
		
			//https://www.digitalocean.com/community/tutorials/java-append-to-file
			//add the players score to the high score file
			FileWriter fr = null;
			try {
				fr = new FileWriter("data/highscores.txt", true);
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			BufferedWriter br = new BufferedWriter(fr);
			try {
				br.write("\r\n"+Name.getName(0)+","+totalScore);
				br.close();
				fr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//play the victory sound
			playMusic("audio/victory.wav");
			
			//
			new NextLevelFrame();
		}
	}
	
	//playMusic method will play music based on the file path
	public static void playMusic(String filepath) {
		
		//https://www.youtube.com/watch?v=wJO_cq5XeSA
		try {
			//create a file
			File musicPath = new File(filepath);
			
			//if the file exists
			if (musicPath.exists()) {
				
				//create an audio input stream
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				//create a clip of the audio
				Clip clip = AudioSystem.getClip();
				//open the audio input stream
				clip.open(audioInput);
				
				//if the file is the coin sound
				if (filepath.equals("audio/coinSound.wav")) {
					FloatControl gainControl = 
						    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					//lower the sound by 30 decibel
					gainControl.setValue(-30.0f);
				}
				//if the file is the victory sound
				if (filepath.equals("audio/victory.wav")) {
					FloatControl gainControl = 
							(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					//lower the sound by 10 decibel
					gainControl.setValue(-10.0f);
				}
				//if the file is the jump sound
				if (filepath.equals("audio/jump.wav")) {
					FloatControl gainControl = 
							(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					//lower the sound by 10 decibel
					gainControl.setValue(-20.0f);
				}
				//if the file is the background music
				if (filepath.equals("audio/backgroundMusic.wav")) {
					//loop the audio clip
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				//start to the audio
				clip.start();
			}
			//if the file is not found, print an error message
			else {
				System.out.println("can't find file");
			}
		}
		
		catch (Exception e) 
		{
			System.out.println("error");
		}
	}
	
	//actioonPerformed method
	public void actionPerformed(ActionEvent e) {
		
		//If the character is jumping and hitting a platform above
		if (jumping && dY < 0 && LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.WALL) {
			collectCoin();
			collectStar();
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
		}
		
		//if it is jumping and going downwards
		if (jumping && dY > 0) {
			
			//check if it can collect stars, coins, go to the next level, enter a portal or hit a bacteria
			collectCoin();
			collectStar();
			goToNextLevel(); 
			enterPortal(); 
			restartGame(); 
			
			//if the character is moving left and the block below and to the left is a platform
			if (dX < 0 && LevelFrame.boardArray[getRow()+1][getCol()-1].getIcon() == Icons.WALL) {
				//not jumping anymore
				jumping = false;
				dY = 0;
				jumpTimer.stop();
			}
			//else if the character is moving right and the block below and to the right is a platform
			else if (dX > 0 && LevelFrame.boardArray[getRow()+1][getCol()+1].getIcon() == Icons.WALL) {
				//not jumping anymore
				jumping = false;
				dY = 0;
				jumpTimer.stop();
			}
		}
		
		//If the character is falling
		if (falling && dY > 0) {
			
			//check if it can collect stars, coins, go to the next level, enter a portal or hit a bacteria
			collectCoin();
			collectStar();
			goToNextLevel(); 
			enterPortal(); 
			restartGame(); 
			
			//check if the platform below is a wall or grass
			if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL || 
				LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GRASS) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			
			//if the character is moving right and the platform down and to the right is a wall
			if (dX > 0 && (LevelFrame.boardArray[getRow()+1][getCol()+1].getIcon() == Icons.WALL || 
					LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL)) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
				
			}
			
			//if the character is moving left and the platform down and to the left is a wall
			else if (dX < 0 && LevelFrame.boardArray[getRow()+1][getCol()-1].getIcon() == Icons.WALL) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			
		}
		
		// When the character is jumping
		if (jumping) {
			
			if ((LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL 
					|| LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GRASS)
				&& dY > 0) {
				dY = 0;
			}	
			//increment the jump counter
			jumpCounter++;
			if (jumpCounter <= 10) {
				dY = -5;
			}
			else if (jumpCounter <= 18) // going up
				dY = 5;
			else {
				jumping = false;
				dY = 0;
				jumpTimer.stop();
				fall();
			}
			
			//check if it can collect stars, coins, go to the next level, enter a portal or hit a bacteria
			collectCoin();
			collectStar();
			goToNextLevel(); 
			enterPortal(); 
			restartGame(); 

		}
		
		// Reset everything so character stops jumping
		else if (falling) {
			
			//check if it can collect stars, coins, go to the next level, enter a portal or hit a bacteria
			collectCoin();
			collectStar();
			goToNextLevel(); 
			enterPortal(); 
			restartGame(); 
			
			//increment fall counter	
			fallCounter++;
			
			//set the fall speed
			dY = 5;
			
			if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL ||
					LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GRASS) {
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			
			//check if the platform below is a wall or grass
			if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL || 
				LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.GRASS) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			//if the character is moving right and the platform down and to the right is a wall
			if (dX > 0 && (LevelFrame.boardArray[getRow()+1][getCol()+1].getIcon() == Icons.WALL || 
					LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL)) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
				
			}
			//if the character is moving left and the platform down and to the left is a wall
			else if (dX < 0 && LevelFrame.boardArray[getRow()+1][getCol()-1].getIcon() == Icons.WALL) {
				//stop falling
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			
			if (fallCounter >= 20) { // stop falling after reaching GRASS level
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
			
			setBounds(getX(), getY() + dY, 25, 25); // update character's position

			return; // exit method since character is falling
		}
		
		//update the character's position
		setBounds(getX() + dX, getY() + dY, 25, 25);
			
	}
	
	//
	
}


