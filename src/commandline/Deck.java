package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

/*
The deck class will read in the cards from a .txt file
Create the card objects
Store them in an array list
*/
public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
    
    }
    //getters/setters
    public ArrayList getDeck() {
        return deck;
    }

    public void importDeck() throws Exception {
        //Creates file object
        File file = new File("StarCitizenDeck.txt");
        //Retrieves file absolute path
        String path = file.getAbsolutePath();
        //Creates file reader and loads in file
        FileReader fr = new FileReader(path);
        //Passes file contents to scanner
        Scanner myScanner = new Scanner(fr);
        //Creates attributes array to be passed to Card Constructor
        String[] attributes = new String[5];
        /*
        These lines sctrip the first line from the file
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
        Takes a line and splits it content
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
        System.out.println(deck);
        myScanner.close();

    }


    //Method to shuffle deck 
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }
}