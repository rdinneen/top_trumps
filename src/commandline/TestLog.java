package commandline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestLog {

	FileWriter log;

	/**
	 * Constructor
	 */
	public TestLog(File f) {
		
		try {
			
			log = new FileWriter(f);
		} catch(IOException e) {
			
			System.err.print("FileWriter initialisation error");
		}
	}
	
	/**
	 * Logs the deck state when it's loaded from file
	 * @param String toString of Deck
	 * @return Boolean if write was successful
	 */
	public void logDeck(String s) {
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Loaded Deck: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the deck after its been shuffled
	 * @param String toString of Deck after shuffle
	 * @return Boolean if write was successful
	 */
	public void logShuffledDeck(String s) {
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the Player hands at the start of the game
	 * Note: Expects HumanPlayer as the last entry
	 * @param String toString of playerHands
	 * @return Boolean if write was successful
	 */
	public void logStartingHands(ArrayList<Card> sH) {
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through all AiPlayers and prints their hand
		for(int i = 0; i < sH.size() - 1; i++) {
			
			sb.append("AIPlayer" + (i + 1) + ": " + sH.remove(0).toString());
		}
		
		// Should not be passed null (as this is game start)
		sb.append("HumaPlayer: " + sH.remove(0).toString());
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the communal pile after cards are added or removed
	 * @param String communal pile
	 * @return Boolean if write was successful
	 */
	public void logCommunalPile(ArrayList<Card> cP) {
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through the communal pile and prints each card
		for(int i = 0; i < cP.size(); i++) {
			
			sb.append(cP.remove(0).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Communal Pile Contents: "  + sb);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs cards in play
	 * @param String each players top card 
	 * @return Boolean if write was successful
	 */
	public void logCurrentCards(ArrayList<Card> c) {
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through all active cards and prints them
		for(int i = 0; i < c.size(); i++) {
			
			sb.append(c.remove(0).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the chosen category and each players associated value
	 * @param String Category 
	 * @param int Values
	 * @return Boolean if write was successful
	 */
	public void logCategoryandValues(String s, int[] n) {
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the hands after every round
	 * @param String each player's hand contents
	 * @param Boolean is humanPlayer active
	 * @return Boolean if write was successful
	 */
	public void logHands(ArrayList<Card> cH, boolean b) {
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through all AiPlayers and prints their hand
		for(int i = 0; i < cH.size() - 1; i++) {
			
			sb.append("AIPlayer" + (i + 1) + ": " + cH.remove(0).toString());
		}
		
		// Checks if humanPlayer is null and if not, appends hand
		if(b) {
		
			sb.append("HumaPlayer: " + cH.remove(0).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the game winner
	 * @param String winner
	 * @return Boolean if write was successful
	 */
	public void logWinner(String s) {
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Game Winner: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}