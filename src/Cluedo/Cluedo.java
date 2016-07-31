package cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.CharacterCard;
import cards.Deck;
import cards.RoomCard;
import cards.WeaponCard;

/**
 * Represents a game of cluedo.
 * @author tezz99
 *
 */
public class Cluedo {

    public final String characterNames[] = {"Miss Scarlett", "Colonel Mustard", "Mrs. White", "The Reverend Green", "Mrs. Peacock", "Professor Plum"};
    public final String weaponNames[] = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner" };
    public final String roomNames[] = {"Kitchen", "Ball Room", "Conservatory", "Billard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};

    public final Triple solutionEnvelope;

    private Board gameBoard;
    private List<Player> players = new ArrayList<>();
    private List<Player> eliminatedplayers = new ArrayList<>();
    private Room rooms[] = new Room[9];
    private Player winner;

    private int numberOfPlayers;


    /**
     * Create a new game of cluedo.
     * @param numberOfPlayers
     */
    public Cluedo(int numberOfPlayers) {

	this.numberOfPlayers = numberOfPlayers;

	//Set up players according to the number playing.
	for (int i = 0; i < this.numberOfPlayers; i++) {
	    this.players.add(new Player (this.characterNames[i]));
	}

	//Set up/create rooms and add to rooms array.
	for (int i = 0; i < this.rooms.length; i++) {
	    this.rooms[i] = new Room (this.roomNames[i]);
	}

	this.gameBoard = new Board(this.players); //Create a new board.


	//Create a list of cards for each card type.
	List<CharacterCard> characterCards = new ArrayList<>();
	List<WeaponCard> weaponCards = new ArrayList<>();
	List<RoomCard> roomCards = new ArrayList<>();

	//Add all character cards to respective list.
	for (String s : this.characterNames) {
	    characterCards.add(new CharacterCard(s));
	}

	//Add all weapon cards to respective list.
	for (String s : this.weaponNames) {
	    weaponCards.add(new WeaponCard(s));
	}

	//Add all room cards to respective list.
	for (String s : this.roomNames) {
	    roomCards.add(new RoomCard(s));
	}

	this.solutionEnvelope = createSolution(characterCards, weaponCards, roomCards); //Create and store the solution of this game as a triple.

	Deck deck = new Deck(characterCards, weaponCards, roomCards); //Creates a new deck that contains the cards remaining after solution has been removed.
	deck.deal(this.players); //Deal the remaining cards to the plays.

    }


    /**
     * Creates a solution for the game. Picks random character, weapon and room cards and removes them from their lists.
     * @return
     */
    public Triple createSolution(List<CharacterCard> characterCards, List<WeaponCard> weaponCards, List<RoomCard> roomCards) {
	Random rand = new Random();

	//Select solution cards.
	CharacterCard characterCard = characterCards.get(rand.nextInt(characterCards.size())); 
	WeaponCard weaponCard = weaponCards.get(rand.nextInt(weaponCards.size())); 
	RoomCard roomCard = roomCards.get(rand.nextInt(roomCards.size())); 

	Triple solution = new Triple (characterCard, weaponCard, roomCard); //Create solution triple

	//Remove the solution cards from their respective cards.
	characterCards.remove(characterCard);
	weaponCards.remove(weaponCard);
	roomCards.remove(roomCard);

	//System.out.println("Solution: " + solution.toString());

	return solution;
    }

    public void displayBoard() {
	this.gameBoard.displayBoard();
    }

    /**
     * Checks if the game is finished yet or not.
     */
    public boolean isGameOver() {
	return (winner != null);
    }

    /**
     * Returns the list of players currently playing the game.
     * @return
     */
    public List<Player> getPlayers() {
	return this.players;
    }

    /**
     * Returns the list of players that have been eliminated from the game.
     * @return
     */
    public List<Player> getEliminatedPlayers() {
	return this.eliminatedplayers;
    }

}
