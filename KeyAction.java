/*
 * Megan Chun
 * 
 * ICS3U1-05 (Ms. Biswas)
 * 
 * May 28, 2023
 * 
 * This file is responsible for handling movement based on their user input to call on different methods
 * 
 * Major Skills:
 * - if/if else statements
 * - methods
 * 
 *  Areas of Concern:
 * - none
 * 
 * External Sources:
 * - none
 * 
 */
import javax.swing.text.*;
import java.awt.event.ActionEvent;


public class KeyAction extends TextAction {
	private String key;
	
	//Constructor method
	public KeyAction(String key) {
		super(key);
		this.key = key;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		//create a character that is the character created in the LevelFrame class
		Character character = LevelFrame.character;
		
		//determine which key will do what actions
		//if the user enters 'd', 
		if (e.getActionCommand().equals(character.getKey()[0]) && 
		LevelFrame.boardArray[character.getRow()][character.getCol()-1].getIcon() !=
		Icons.WALL)
			//call the moveLeft method
			character.moveLeft();
		
		//else if the user enters 'd'
		else if (e.getActionCommand().equals(character.getKey()[1]) && 
		LevelFrame.boardArray[character.getRow()][character.getCol()+1].getIcon() !=
		Icons.WALL)
			//call the moveRight method
			character.moveRight();
		
		//else if the user enters a space and has nothing above it
		else if (e.getActionCommand().equals(character.getKey()[2]) && 
		LevelFrame.boardArray[character.getRow()-1][character.getCol()].getIcon() !=
		Icons.WALL)
			//if the character is not already jumping
			if (!character.isJumping()) {
				character.setJumping(true);
				character.jump();
			}
	}
}