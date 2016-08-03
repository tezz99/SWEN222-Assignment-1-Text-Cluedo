package cluedo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cards.Card;
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
    private List<Card> publicCards;
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

	distributeWeapons(); //Distribute weapons to rooms randomly.


	this.gameBoard = new Board(this.players, this.rooms); //Create a new board.


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
	this.publicCards = deck.deal(this.players); //Deal the remaining cards to the players and take note of undealt cards.

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

	System.out.println("Solution: " + solution.toString());

	return solution;
    }


    /**
     * Offer player valid options and return the option selected by player.
     * @param p
     * @return
     */
    public String offerOptions(Player p) {

	ArrayList<String> options = new ArrayList<>();

	//Add the relevant options avaliable to the player.
	options.add("Move");
	if (p.isInRoom()) {
	    options.add("Suggest");
	}
	options.add("Accuse");

	System.out.println("Please select one option from the choices below (1 - " + options.size() + "): ");

	//Print/display the options.
	for (int i = 0; i < options.size(); i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + options.get(i));
	}
	System.out.println();

	return options.get(getPlayerChoice(options.size())); //NEED TO CHANGE. Temporary to make it compile.

    }


    /**
     * Player has chosen to move. Move the player according to roll and players choice.
     * @param p
     * @param roll
     */
    public void processMove(Player p, int roll) {

	int movesLeft = roll;

	System.out.println(p.getName() + " chose to Move and rolled a " + roll + ".");

	Position moveTo = null;

	//while (moveTo == null || isValidMove(p, moveTo)) {
	for (int i = 0; i < roll; i++) {
	    moveTo = this.getDirection(p); //Get the position the player would like to move to.
	    p.setPosition(moveTo); //Set the new position of the player
	    this.displayBoard(); //Show board again to show new position of the player.
	    movesLeft--;

	    //If player enters a room, 
	    if (p.isInRoom()) {
		break;
	    }
	    System.out.println(movesLeft + " Moves remaining in your turn. ");
	}

	//Player just entered a room. They can make a suggestion. 
	if (p.isInRoom()) {
	    this.processSuggestion(p);
	}
    }


    /**
     * Gets the coordinates that the player wants to move to and converts it into a position.
     * @return
     */
    public Position getCoordinates(Player p, int diceRoll) {

	List<Position> possiblePositions = getPossiblePositions(p , diceRoll);



	return null; // neeed to change
    }


    /**
     * Finds all the possible positions that the player can move given the dice roll and returns it as an array list.
     * @param p
     * @param diceRoll
     * @return
     */
    public ArrayList<Position> getPossiblePositions(Player p, int diceRoll) {

	return null; //need to change.

    }


    /**
     * Ask user for the direction they would like to move.
     * @return
     */
    public Position getDirection(Player p) {

	List<String> validDirections = new ArrayList<>();

	//Determine possible directions the player can go.
	Position currentPosition = p.getPosition();
	if (currentPosition != null && (currentPosition.getPosY() - 1) >= 0) {
	    validDirections.add("Up");
	}
	if (currentPosition != null && (currentPosition.getPosY() + 1) <= 24) {
	    validDirections.add("Down");
	}
	if (currentPosition != null && (currentPosition.getPosX() - 1) >= 0) {
	    validDirections.add("Left");
	}
	if (currentPosition != null && (currentPosition.getPosY() + 1) <= 24) {
	    validDirections.add("Right");
	}

	if (validDirections.isEmpty()) {
	    throw new Error("No valid directions found! WIll need to change/fix this.");
	}

	//Print direction options.
	for (int i = 0; i < validDirections.size(); i++) {
	    int count = i + 1;
	    System.out.println(count + ". " + validDirections.get(i));
	}

	System.out.print("Please enter the direction you would like to move: ");
	int direction = this.getPlayerChoice(validDirections.size());

	//Return the new position of the player according to their choice.
	if (validDirections.get(direction).equals("Up")){
	    return new Position(p.getPosition().getPosX(), p.getPosition().getPosY() - 1);
	} else if (validDirections.get(direction).equals("Down")){
	    return new Position(p.getPosition().getPosX(), p.getPosition().getPosY() + 1);
	} else if (validDirections.get(direction).equals("Left")) {
	    return new Position(p.getPosition().getPosX() - 1, p.getPosition().getPosY());
	}

	return new Position(p.getPosition().getPosX() + 1, p.getPosition().getPosY()); //Return the new postion that moves they player one tile to the right.
    }

    public boolean isValidMove(Player p, Position moveTo) {
	assert p != null; //Ensure p is not null.
	assert moveTo != null; //moveTo variable should not be null.

	return false;
    }


    /**
     * Player has chosen to suggest. Offer suggestion options.
     * @param p
     */
    public void processSuggestion(Player p) {
	System.out.println(p.getName() + " chose to make a suggestion.");
    }


    /**
     * Processes accusation. Gives given player the options to make accusation. Determines if winner or is eliminated.
     */
    public void processAccusation(Player p) {
	System.out.println(p.getName() + " chose to make an accusation.");

	//Choose character to accuse.
	System.out.println("Select the CHARACTER (Murderer) from the options below:");
	for (int i = 0; i < this.characterNames.length; i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.characterNames[i]);
	}
	int characterChoice = getPlayerChoice(this.characterNames.length); //Get the choice from the user/player
	System.out.println();
	System.out.println("You chose the following character as the murderer: " + this.characterNames[characterChoice]);
	System.out.println();


	//Choose Muder weapon.
	System.out.println("Select the MURDER WEAPON from the options below:");
	for (int i = 0; i < this.weaponNames.length; i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.weaponNames[i]);
	}
	int weaponChoice = getPlayerChoice(this.weaponNames.length); //Get the choice from the user/player
	System.out.println();
	System.out.println("You chose the following MURDER WEAPON: " + this.weaponNames[weaponChoice]);
	System.out.println();


	//Choose Room to accuse
	System.out.println("Select the Room in which the murder occured from the options below:");
	for (int i = 0; i < this.roomNames.length; i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.roomNames[i]);
	}
	int roomChoice = getPlayerChoice(this.roomNames.length); //Get the choice from the user/player
	System.out.println();
	System.out.println("You chose the following room: " + this.roomNames[roomChoice]);

	//If player wins announce winner.
	if (this.checkEnvelope(this.characterNames[characterChoice], this.weaponNames[weaponChoice], this.roomNames[roomChoice])) {
	    System.out.println();
	    System.out.println("ACCUSATION CORRECT!");
	    this.winner = p;
	} else {
	    this.eliminatedplayers.add(p);
	}
    }

    /**
     * Distributes weapons to rooms randomly.
     */
    public void distributeWeapons() {
	int distributedWeapons  = 0;
	Random rand = new Random();

	while (distributedWeapons < this.weaponNames.length) {
	    Room currentRoom = this.rooms[rand.nextInt(this.roomNames.length)];

	    //If the current room does not already have a weapon, then add it.
	    if (currentRoom.getWeapons().isEmpty()) {
		//System.out.println("Adding weapon: " + this.weaponNames[distributedWeapons] + " to " + currentRoom.getRoomName());
		currentRoom.addWeapon(new Weapon(this.weaponNames[distributedWeapons], currentRoom));
		distributedWeapons++;
	    }
	}

	//System.out.println("Distributed weapons: " + distributedWeapons);
    }


    /**
     * Returns true if the provided information matches the solution envelope. Used to check a suggestion or accusation.
     * @return
     */
    public boolean checkEnvelope (String character, String weapon, String room) {
	String solutionCharacter = this.solutionEnvelope.getCharacterName();
	String solutionWeapon = this.solutionEnvelope.getWeaponName();
	String solutionRoom = this.solutionEnvelope.getRoomName();
	return (solutionCharacter.equals(character) && solutionWeapon.equals(weapon) && solutionRoom.equals(room));
    }


    /**
     * Checks if the game is finished yet or not.
     */
    public boolean isGameOver() {
	return (winner != null);
    }

    /**
     * Returns winner
     * @return
     */
    public Player getWinner() {
	return this.winner;
    }

    /**
     * Displays the gameboard onto console
     */
    public void displayBoard() {
	this.gameBoard.displayBoard();
    }


    /**
     * Displays information about the position of the player. And if in a room, then displays the weapon in room
     */
    public void displayPlayerStatus(Player p) {
	System.out.println("------------ " + p.getName().toUpperCase() + "'S TURN ------------" );

	int playersRemaining = this.players.size() - this.eliminatedplayers.size();
	System.out.println("Players remaining: " + playersRemaining + " ("+ this.eliminatedplayers.size() + " eliminated).");
	System.out.println();

	if (p.isInRoom()) {
	    System.out.println("Current Room: " + p.getCurrentRoom().getRoomName());

	    //Print the list of weapons in the room if there are any.
	    if (!p.getCurrentRoom().getWeapons().isEmpty()) {
		System.out.println("Weapon(s) found in this Room: ");
		for (int i = 0; i < p.getCurrentRoom().getWeapons().size() - 1; i++) {
		    System.out.print(p.getCurrentRoom().getWeapons().get(i) + ", ");
		}
		System.out.print(p.getCurrentRoom().getWeapons().get(p.getCurrentRoom().getWeapons().size() - 1));

	    }

	} else {
	    System.out.println("Current Position: ADD COORDINATES HERE");
	}

	this.displayPublicCards(); //Display any public cards if there are any.

	//Display the players hand.
	System.out.println();
	System.out.print("Cards On Hand: ");
	for (int i = 0; i < p.getHand().size() - 1; i++) {
	    System.out.print(p.getHand().get(i) + ", ");
	}
	System.out.print(p.getHand().get(p.getHand().size() - 1));
	System.out.println();
	System.out.println();
    }

    /**
     * Print public cards if there are any.
     */
    public void displayPublicCards() {
	if (this.publicCards.size() > 0) {
	    System.out.println();
	    System.out.print("Public Cards (Visible to everyone): ");
	    for (int i = 0; i < this.publicCards.size() - 1; i++) {
		Card c = this.publicCards.get(i);
		System.out.print(c.toString() + ", ");
	    }
	    System.out.print(this.publicCards.get(this.publicCards.size() - 1));
	}
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


    /**
     * Used for getting the player to make a choice from a list starting from 1 to max. Essentially a helper method.
     * @param min
     * @param max
     * @return
     */
    public int getPlayerChoice(int max) {
	Scanner scan = new Scanner(System.in);
	int selectedOption = 0;

	//Keep asking until valid input provided.
	while (selectedOption <= 0 || selectedOption > max) {
	    System.out.print("Enter the number corresponding to your selection: ");
	    try {
		selectedOption = scan.nextInt();
		if (selectedOption > 0  && selectedOption <= max) {
		    break;
		}
	    } catch (InputMismatchException e) {
		selectedOption = 0;
		scan.nextLine(); //Skip over invalid inout.
	    }
	    System.out.println("Invalid input.");
	    System.out.println();
	}

	return selectedOption - 1; //NEED TO CHANGE. Temporary to make it compile.
    }

}
