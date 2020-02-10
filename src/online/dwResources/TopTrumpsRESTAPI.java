package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;
import commandline.GameState;
import commandline.Player;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	GameState model;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		model = new GameState();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}

	
	/**
	 * Method to check if human player turn or AI turn
	 * @return int 1 if player turn, two if AI turn
	 * @throws IOException
	 */
	@GET
	@Path("/checkTurn")

	public String checkTurn() throws IOException {
		String result;
		if(model.getActivePlayer().getName().equals(
				model.getHumanPlayer().getName())) {
			result = "1";
		}
		// if AI player is active 	
		else {
			result = "2";
		}
	
		return result;

	}
	
	/**
	 * Method to make all players draw new cards
	 * @return Message "New cards drawn"
	 * @throws IOException
	 */
	@GET
	@Path("/drawCards")

	public String drawCards() throws IOException{

		// remove this when in actual game
		model.drawNewCard();
		
		return "New cards drawn";

	}
	
	/**
	 * Method to return details of player card as a JSON object
	 * @return String JSON Object containing player card details
	 * @throws IOException
	 */
	@GET
	@Path("/showPlayerCard")

	public String showPlayerCard() throws IOException {

		// model.drawNewCard();

		String cardStr = oWriter.writeValueAsString(model.getHumanPlayer().getCard());

		System.out.println(cardStr);
	
		return cardStr;

	}
	
	/**
	 * Method to set human player category
	 * @param Category
	 * @return Category
	 */
	@GET
	@Path("/selectCategory")
	
	public String selectCategory(@QueryParam("Category") String Category) {
		
		int catInt = Integer.parseInt(Category);
		
		this.model.setCurrentAttribute(catInt - 1);
		
		return Category;
		
	}
	
	/**
	 * Method to set AI player category
	 * @return Selected category
	 */
	@GET
	@Path("/AISelectCategory")
	
	public String selectCategoryAI() {
		
		int catInt = model.getActivePlayer().getHighestAttribute();

		System.out.println("integer input to model: " + catInt);
		
		this.model.setCurrentAttribute(catInt - 1);
		
		String catStr = Integer.toString(catInt);
		
		return catStr;
	}
	
	/**
	 * Method to check result (win or draw)
	 * @return 1 if win, 2 if draw
	 */
	@GET
	@Path("/getResult")
	
	public String getResult() {
		
		return "" + this.model.getResult();
	}
	
	/**
	 * Method to get the name of the active player
	 * @return active player name
	 */
	@GET
	@Path("/getActivePlayer")
	
	public String getActivePlayer() {
		return this.model.getActivePlayer().name;
	}
	
	/**
	 * Method to return contents of all players cards as  JSON object
	 * @return String JSON Object containing all active card details
	 * @throws IOException
	 */
	@GET
	@Path("/showAllCards")

	public String showAllCards() throws IOException {
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for(Player p : model.getPlayers()) {
			cards.add(p.getCard());
		}

		String cardsStr = oWriter.writeValueAsString(cards);
		
		System.out.println(cardsStr);
		
		return cardsStr;

	}
	
	@GET
	@Path("/showPlayer")

	public String showPlayer() throws IOException {

		String playerStr = oWriter.writeValueAsString(model.getHumanPlayer());
	
		return playerStr;

	}
	
	@GET
	@Path("/showPlayers")
	
	public String showPlayers() throws IOException {
		
		ArrayList<Player> players = this.model.getPlayers();
		
		String playersStr = oWriter.writeValueAsString(players);
		System.out.println(playersStr);
		
		return playersStr;
	}
	
	@GET
	@Path("/checkEliminations")
	
	public String checkEliminations() throws IOException{
		ArrayList<Player> usersEliminated = model.userEliminated();
		
			if(usersEliminated.size() > 0) {
			
				String elimStr = oWriter.writeValueAsString(usersEliminated);
				System.out.println(elimStr);
				return elimStr;
			
			}
			
			return "0";
	}
	
	/*
	 * To delete?
	 */
	@GET
	@Path("/communalPileSize")
	
	public String communalPileSize() {
		
		return "" + this.model.getCommunalPileSize();
	}
	
	@GET
	@Path("/eliminatedPlayers")
	
	public String eliminatedPlayers() throws IOException {
		ArrayList<Player> usersEliminated = model.userEliminated();
		
		String elimStr = oWriter.writeValueAsString(usersEliminated);
		
		return elimStr;		
	}
	
	@GET
	@Path("/checkHumanPlayer")
	
	public String checkHumanPlayer() throws IOException {
		
		Player humanPlayer = model.getHumanPlayer();
		
		if(humanPlayer != null) {
			return "1";
		}
		else {
			return "0";
		}
			
	}
	
	
}
