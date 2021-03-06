package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
The deck class will read in the cards from a .txt file
Create the card objects
Store them in an array list
*/

/**
 * Deck class for Top Trumps game
 * @author John Desbois
 * <br><br>
 * 
 * Constructor:<br>
 * 	Deck()<br><br>
 * 
 * Public methods:<br>
 * 	public ArrayList<Card> getDesk()<br>
 * 	public Card getCard(int)<br>
 *  public String toString()<br>
 *  public void shuffleDeck()<br>
 *  public ArrayList<PlayerHand> deal()<br>
 *  public ArrayList<PlayerHand> deal(int)<br>
 *  public void importDeck() throws Exception
 * 
 */
public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<PlayerHand> hands = new ArrayList<PlayerHand>();

/**
 * Constructor <br>
 * No parameters just runs the importDeck() method
 */

    public Deck() {
        //Creates deck by using the import deck function
        try {
            importDeck();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
 * Constructor <br>
 * @param String file name of the deck to import
 */
    public Deck(String deckFileName) {
        //Creates deck by using the import deck function
        try {
            importDeck(deckFileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //getters/setters
    /**
     * Getter method to return deck variable 
     * @return ArrayList<Card> of Card objects that were imported from deck text file
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    //Returns the card using the toString() method of the card object
    /**
     * Getter method to return a Card object at a specific index in the ArrayList.
     * @param int index of Card object to be returned
     * @return Card object at specified index
     */
    public Card getCard(int i) {
        return deck.get(i);
    }
    /*
    This is the toString() method which iterates over the deck, adds each card to the String on a new line
    Returns the completed String as a whole.
    */
    /**
     * toString method which iterates over the deck, adds each card in String format to a new line.
     * @return String with every card in deck object printed on a new line.
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < deck.size(); i++) {
            output += deck.get(i) + "\n";
        }    
        return output;    
    }
    /**
     * Method that is able to shuffle deck. This is achieved via the built in shuffle method for Java Collections.
     */
    //Method to shuffle deck 
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    /*
    Method to take call card objects in deck and deal them to 5 players (can be modified for specific number of players later)
    Instaniates 5 player hand objects, adds these playerhand objects to a Hands arraylist
    While the deck is not equal to 0, method iterates over player hands, taking 0th index item, removing it from deck and placing it into a player hand. 
    */
    /**
     * Deal method which creates 5 PlayerHand objects, iterates over the deck and passes a card to each hand one at a time.
     * Each PlayerHand is put into an ArrayList to be returned.
     * @return ArrayList<PlayerHand> of all 5 PlayerHand objects with hands of Card objects. 
     */
    public ArrayList<PlayerHand> deal() {
        //Create 5 Player hand objects (will later be modified to accept int and create only those about of hands)
        PlayerHand hand1 = new PlayerHand();
        PlayerHand hand2 = new PlayerHand();
        PlayerHand hand3 = new PlayerHand();
        PlayerHand hand4 = new PlayerHand();
        PlayerHand hand5 = new PlayerHand();
        //Adds the PlayerHand objects to the Hands ArrayList
        hands.add(hand1);hands.add(hand2);hands.add(hand3);hands.add(hand4);hands.add(hand5);
        //Will perform until deck reaches 0
        while (deck.size() != 0) {
            //iterates over the hands arraylist adding top card from deck to each hand, until deck is empty
            for (int i=0; i<hands.size(); i++) {
                hands.get(i).add(deck.remove(0));
            }
        }
        return hands;
    }
    
    /*
    Method to take all card objects in deck and deal them to n players.
    Instaniates n player hand objects, adds these playerhand objects to a Hands arraylist
    While the deck is not equal to 0, method iterates over player hands, taking 0th index item, removing it from deck and placing it into a player hand. 
    */
    /**
     * Overloaded deal method that accepts an integer referring to the number of players or playerhands needed.
     * @param int referring to the number of players or the number of hands required.
     * @return ArrayList<PlayerHand> of n amount of hands.
     */
    public ArrayList<PlayerHand> deal(int n) {
        //Creates n amount of PlayerHand object and adds them to hands ArrayList
        for (int i=0; i<n; i++) {
            PlayerHand hand = new PlayerHand();
            hands.add(hand);
        }
        //Will perform until deck reaches 0
        while (deck.size() != 0) {
            //iterates over the hands arraylist adding top card from deck to each hand, until deck is empty
            for (int i=0; i<hands.size(); i++) {
                //Checks if the deck has reached 0 during the num players loop
                if(deck.size() == 0) {
                    continue;
                }
                hands.get(i).add(deck.remove(0));
            }
        }
        return hands;
    }
    /**
     * Method to import the deck from a text file. 
     * File name is currently hardcoded, and file must be in the same directory as the JAR file to be read.
     * 
     * @throws Exception due to use of FileReader object
     */
    public void importDeck() throws Exception {
        JSONParser parser = new JSONParser();
        String deckFileName = "";

        try {
            Object object = parser.parse(new FileReader("TopTrumps.json"));
            JSONObject jsonObject = (JSONObject)object;
            deckFileName = (String) jsonObject.get("deckFile");
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        //Creates file object
        File file = new File(deckFileName);
        //Retrieves file absolute path
        String path = file.getAbsolutePath();
        //Creates file reader and loads in file
        FileReader fr = new FileReader(path);
        //Passes file contents to scanner
        Scanner myScanner = new Scanner(fr);
        //Creates attributes array to be passed to Card Constructor
        String[] attributes = new String[5];

        /*
        These lines strip the first line from the file
        Split the first line into an array
        Iterates over the split array and puts the attribute headers into the attribues array
        */
        String firstLine = myScanner.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        for (int i=1; i < firstLineSplit.length; i++) {
            attributes[i-1] = firstLineSplit[i];
        }
        
        /*
        Iterates over rest of file in scanner line by line
        Takes a line and splits its contents
        Iterates over line from position 1, to avoid taking in the string, and puts values into values array
        Then creates a card object for each line in file, passing Description name, attributes headers and values to constructor
        */
        while (myScanner.hasNext()) {
            int[] values = new int[5];
            String[] split = myScanner.nextLine().split(" ");
            for (int i=1; i < split.length; i++) {
                int value = Integer.parseInt(split[i]);
                values[i-1] = value;
            }
            deck.add(new Card(split[0], attributes, values));
        }
        myScanner.close();
    }
    public void importDeck(String deckFileName) throws Exception {

        //Creates file object
        File file = new File(deckFileName);
        //Retrieves file absolute path
        String path = file.getAbsolutePath();
        //Creates file reader and loads in file
        FileReader fr = new FileReader(path);
        //Passes file contents to scanner
        Scanner myScanner = new Scanner(fr);
        //Creates attributes array to be passed to Card Constructor
        String[] attributes = new String[5];

        /*
        These lines strip the first line from the file
        Split the first line into an array
        Iterates over the split array and puts the attribute headers into the attribues array
        */
        String firstLine = myScanner.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        for (int i=1; i < firstLineSplit.length; i++) {
            attributes[i-1] = firstLineSplit[i];
        }
        
        /*
        Iterates over rest of file in scanner line by line
        Takes a line and splits its contents
        Iterates over line from position 1, to avoid taking in the string, and puts values into values array
        Then creates a card object for each line in file, passing Description name, attributes headers and values to constructor
        */
        while (myScanner.hasNext()) {
            int[] values = new int[5];
            String[] split = myScanner.nextLine().split(" ");
            for (int i=1; i < split.length; i++) {
                int value = Integer.parseInt(split[i]);
                values[i-1] = value;
            }
            deck.add(new Card(split[0], attributes, values));
        }
        myScanner.close();
    }
}
