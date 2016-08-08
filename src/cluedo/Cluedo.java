package cluedo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import cards.Card;
import cards.CharacterCard;
import cards.Deck;
import cards.RoomCard;
import cards.WeaponCard;
import tiles.RoomTile;
import tiles.WalkableTile;

/**
 * Represents a game of cluedo.
 * @author tezz99
 *
 */
public class Cluedo {

    public final String characterNames[] = {"Miss Scarlett", "Colonel Mustard", "Mrs. White", "The Reverend Green", "Mrs. Peacock", "Professor Plum"};
    public final String weaponNames[] = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner" };
    public final String roomNames[] = {"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};

    public final Triple solutionEnvelope;

    private Board gameBoard;
    private List<Player> players = new ArrayList<>();
    private List<Player> eliminatedplayers = new ArrayList<>();
    private List<Card> publicCards;
    private Room rooms[] = new Room[9];
    private List<Weapon> weapons = new ArrayList<>();

    private int numberOfPlayers;
    private Player winner;


    /**
     * Create a new game of cluedo.
     * @param numberOfPlayers
     */
    public Cluedo(int numberOfPlayers) {

	this.numberOfPlayers = numberOfPlayers;

	//Set up players according to the number playing.
	for (int i = 0; i < this.numberOfPlayers; i++) {
	    Player current = new Player (this.characterNames[i]);
	    current.setToken(i + 1);
	    this.players.add(current);
	}

	//Set up/create rooms and add to rooms array.
	for (int i = 0; i < this.rooms.length; i++) {
	    this.rooms[i] = new Room (this.roomNames[i]);
	}

	distributeWeapons(); //Distribute weapons to rooms randomly.
	setSecretPassages(); //Set up secret passages in applicable rooms.

	this.gameBoard = new Board(this, this.players, this.rooms); //Create a new board.

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

	if (p.wasTransferred()) {
	    options.add("Suggest");
	    p.setWasTransferred(false); //Suggest option offered. Now can set this to no longer transferred.
	}

	options.add("Accuse");

	System.out.println("Please select one option from the choices below (1 - " + options.size() + "): ");

	//Print/display the options.
	for (int i = 0; i < options.size(); i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + options.get(i));
	}
	System.out.println();

	return options.get(getPlayerChoice(options.size()));
    }


    /**
     * Player has chosen to move. Move the player according to roll and players choice.
     * @param player
     * @param roll
     */
    public void processMove(Player player, int roll) {

	System.out.println(" ** " + player.getName() + " chose to Move and rolled a " + roll + "" + " ** ");
	System.out.println();

	Position startPosition = player.getPosition();
	Position moveTo = this.getMove(player, roll); //Get the position the player would like to move to.

	//Player cannot move so return.
	if (moveTo == null) {
	    return; 
	}

	player.setPosition(moveTo); //Set the new position of the player

	//If player has left a room, set their room.
	if (this.gameBoard.isRoomTile(startPosition)) {
	    player.getCurrentRoom().removeOccupant(player);
	    player.setHolderTile(null);
	    player.setCurrentRoom(null);
	}

	//If player has entered a room, set their room and add the player as a new occupant.
	if (this.gameBoard.isRoomTile(moveTo)) {
	    player.setCurrentRoom(this.gameBoard.getRoom(moveTo));
	    player.getCurrentRoom().addOccupant(player);
	}


	//If player was on a walkable tile before moving, set it as unoccupied now that the player has moved.
	if (this.gameBoard.getTile(startPosition) instanceof WalkableTile) {
	    System.out.println("set " + this.posToCoordinates(startPosition) + " to false");
	    this.gameBoard.getWalkableTile(startPosition).setOccupied(false);
	}

	//If player just moved onto a walkable tile, then set it as occupied.
	if (this.gameBoard.getTile(moveTo) instanceof WalkableTile) {
	    this.gameBoard.getWalkableTile(moveTo).setOccupied(true);
	}

	//Player just entered a room. They can make a suggestion. 
	if (player.isInRoom() && (startPosition != moveTo)) {
	    this.processSuggestion(player);
	}
    }


    /**
     * Gets the coordinates that the player wants to move to and converts it into a position.
     * @return
     */
    public Position getMove(Player p, int diceRoll) {

	List<Position> possiblePositions = this.getPossiblePositions(p.getPosition(), diceRoll);

	if (possiblePositions.isEmpty()) {
	    System.out.println(p.getName() + " is stuck. No moves avaliable. You can forefeit your turn or make an accusation:");

	    System.out.println("1. Forefeit Turn");
	    System.out.println("2. Make an accusation");

	    int choice = this.getPlayerChoice(2);

	    if (choice == 1) {
		this.processAccusation(p);
	    } 

	    return null; //Player chose to forefiet turn.
	}

	//sort list by coordinate
	Collections.sort(possiblePositions, new Comparator<Position>() {
	    @Override
	    public int compare(Position p1, Position p2) {
		if (p1.getPosX() - p2.getPosX() != 0) {
		    return p1.getPosX() - p2.getPosX();
		} else {
		    return p1.getPosY() - p2.getPosY();
		}
	    }
	});

	//Display options avaliable to a player.
	int optionNum = 0;
	System.out.println("Where would you like to move to? Select from the options below.");
	for (int i = 0; i < possiblePositions.size(); i++){
	    optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.posToCoordinates(possiblePositions.get(i)));
	}

	//Add option to move to another room via passage if avaliable.
	if (p.isInRoom()) {
	    Room currentRoom = p.getCurrentRoom();
	    optionNum++;
	    if (currentRoom.hasSecretPassage()) {
		possiblePositions.add(currentRoom.getSecretPassage().getEntrances().get(0));
		System.out.println(optionNum + ". " + currentRoom.getSecretPassage().getRoomName() + " (Secret Passage)");
	    }
	}

	int choice = this.getPlayerChoice(possiblePositions.size());
	return possiblePositions.get(choice); 
    }


    /**
     * Finds all the possible positions that the player can move given the dice roll and returns it as an array list.
     * @param p
     * @param diceRoll
     * @return
     */
    public List<Position> getPossiblePositions(Position position, int diceRoll) {

	Set<Position> possiblePositions = new HashSet<>();
	Queue<MoveInfo> queue = new ArrayDeque <>();

	Room startingRoom = null;

	List<Position> surroundingPositions = new ArrayList<>();

	//Take note of the room player starts in so we dont offer that option again.
	if (this.gameBoard.isRoomTile(position)) {
	    startingRoom = this.gameBoard.getRoom(position);

	    //Add all of the surrounding positions from the different doors.
	    for (int i = 0; i < startingRoom.getEntrances().size(); i++) {
		surroundingPositions.addAll(this.getSurroundingPositions(startingRoom.getEntrances().get(i)));
	    }

	} else {
	    surroundingPositions.addAll(this.getSurroundingPositions(position));
	}

	//Add all valid surrounding positions the the queue.
	for (int i = 0; i < surroundingPositions.size(); i++) {
	    Set<Position> visited = new HashSet<>(); 
	    visited.add(position); //Add the starting position to the visited list.
	    queue.add(new MoveInfo(surroundingPositions.get(i), diceRoll - 1, visited));
	}

	while (!queue.isEmpty()) {
	    MoveInfo moveInfo = queue.poll();
	    Position currentPosition = moveInfo.getPosition();


	    if (!possiblePositions.contains(currentPosition)) {

		//If the current Position is an entrance tile, add this room if it is not already in the list of possible positions.
		if (this.gameBoard.isEntranceTile(currentPosition)) {
		    RoomTile tile = (RoomTile)this.gameBoard.getTile(currentPosition);
		    boolean found = false;

		    for (Position p : possiblePositions) {
			if (this.gameBoard.isEntranceTile(p)) {
			    RoomTile currentTile = (RoomTile)this.gameBoard.getTile(p);
			    if (tile.getRoomName().equals(currentTile.getRoomName())){
				found = true;
			    }
			}
		    }

		    //This room is not already added to the possiblePositions list. add it as long as its not the room player started in.
		    if (!found) {
			if (startingRoom == null) {
			    possiblePositions.add(currentPosition);
			} else if (startingRoom.getRoomName().equals(tile.getRoom().getRoomName())) {
			    //Dont add this room since player is already in it and cant enter back straight away.
			} else {
			    possiblePositions.add(currentPosition);
			}

		    }

		    //If there are zero moves left in this move and this position has not already been visited and this tile isnt occupied.
		}  else if ((moveInfo.getMovesLeft() == 0) && (!moveInfo.getVisited().contains(currentPosition)) && (!this.gameBoard.getWalkableTile(currentPosition).isOccupied())){
		    possiblePositions.add(currentPosition);
		}

		//Not a valid position. There a are still moves left.
		if (moveInfo.getMovesLeft() > 0) {

		    surroundingPositions = this.getSurroundingPositions(moveInfo.getPosition());
		    moveInfo.getVisited().add(moveInfo.getPosition()); //Add this position is visted.

		    //Add all of the valid surrounding positions to the queue.
		    for (int i = 0; i < surroundingPositions.size(); i++) {

			//Add position to queue if it hasnt already been visited
			if (!moveInfo.getVisited().contains(surroundingPositions.get(i))) {
			    queue.add(new MoveInfo(surroundingPositions.get(i), moveInfo.getMovesLeft() - 1, moveInfo.getVisited()));
			}

		    }
		}
	    }
	}

	//Convert set back to list and return it.
	List<Position> possiblePositionsList = new ArrayList<>();
	possiblePositionsList.addAll(possiblePositions);
	return possiblePositionsList;
    }

    /**
     * Returns a list of walkable surrounding positions given a position. Also includes room entrances if passed true.
     * @param position
     * @return
     */
    public List<Position> getSurroundingPositions(Position position) {

	List<Position> surroundingPositions = new ArrayList<> ();

	int posX = position.getPosX();
	int posY = position.getPosY();

	//If its possible to move to the right, add to surroundingPositions.
	if (posX + 1 < this.gameBoard.width) {
	    Position possiblePosition = new Position(posX + 1, posY);
	    if (this.gameBoard.isValidTile(possiblePosition)) {
		//System.out.println("Adding X: " + possiblePosition.getPosX() + " Y: " + possiblePosition.getPosY());
		surroundingPositions.add(possiblePosition);
	    }
	}

	//If its possible to move to the left, add to surroundingPositions.
	if (posX - 1 >= 0) {
	    Position possiblePosition = new Position(posX - 1, posY);

	    if (this.gameBoard.isValidTile(possiblePosition)) {
		//System.out.println("Adding X: " + possiblePosition.getPosX() + " Y: " + possiblePosition.getPosY());
		surroundingPositions.add(possiblePosition);
	    }
	}

	//If its possible to move down, add to surroundingPositions.
	if (posY + 1 < this.gameBoard.height) {
	    Position possiblePosition = new Position(posX, posY + 1);
	    if (this.gameBoard.isValidTile(possiblePosition)) {
		//System.out.println("Adding X: " + possiblePosition.getPosX() + " Y: " + possiblePosition.getPosY());
		surroundingPositions.add(possiblePosition);
	    }
	}

	//If its possible to move up, add to surroundingPositions.
	if (posY - 1 >= 0) {
	    Position possiblePosition = new Position(posX, posY - 1);
	    if (this.gameBoard.isValidTile(possiblePosition)) {
		//System.out.println("Adding X: " + possiblePosition.getPosX() + " Y: " + possiblePosition.getPosY());
		surroundingPositions.add(possiblePosition);
	    }
	}

	return surroundingPositions;
    }


    /**
     * Player has chosen to suggest. Offer suggestion options.
     * @param p
     */
    public void processSuggestion(Player p) {

	this.displayBoard();

	System.out.println();
	System.out.println("** " + p.getName() + ": MAKE A SUGGESTION **");
	System.out.println();

	//Room choice. PRE-DECIDED.
	Room roomChoice = p.getCurrentRoom();
	System.out.println("Suggeting Room: " + p.getCurrentRoom().getRoomName());
	System.out.println();


	//Choose character to accuse.
	System.out.println("Select the CHARACTER (Murderer) from the options below:");
	for (int i = 0; i < this.characterNames.length; i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.characterNames[i]);
	}
	int characterChoiceNum = getPlayerChoice(this.characterNames.length); //Get the choice from the user/player
	Player characterChoice = this.getPlayer(this.characterNames[characterChoiceNum]);

	if (characterChoice == null) characterChoice = new Player(this.characterNames[characterChoiceNum]); //If this player/character is not part the game, create a new player
	System.out.println();
	System.out.println("You chose the following character as the murderer: " + this.characterNames[characterChoiceNum]);
	System.out.println();

	//Choose Muder weapon.
	System.out.println("Select the MURDER WEAPON from the options below:");
	for (int i = 0; i < this.weaponNames.length; i++) {
	    int optionNum = i + 1;
	    System.out.println(optionNum + ". " + this.weaponNames[i]);
	}

	int weaponChoiceNum = getPlayerChoice(this.weaponNames.length); //Get the choice from the user/player
	Weapon weaponChoice = this.getWeapon(this.weaponNames[weaponChoiceNum]);
	System.out.println();
	System.out.println("You chose the following MURDER WEAPON: " + this.weaponNames[weaponChoiceNum]);
	System.out.println();

	this.moveWeapon(weaponChoice, roomChoice); //Move the wepon being suggested to this room.
	if (characterChoice != null) this.movePlayer(characterChoice, roomChoice); //Move the player (if not null) being suggested to this room.

	boolean suggestionCorrect = this.checkSuggestion(p.getName(), characterChoice, weaponChoice, roomChoice);//check suggestion

	if (suggestionCorrect) {
	    System.out.println("No one has any of the Suggestion cards. Would you like to make an accusation?"); 

	    //Suggeestion was correct, provide options.
	    System.out.println("1. Yes");
	    System.out.println("2. No");

	    int choice = this.getPlayerChoice(2);

	    //If player wants to make a suggestion, process it.
	    if (choice == 0) {
		this.processAccusation(p);
	    } 
	} 
    }

    /**
     * Checks if any of the players (or public cards) can refute currentPlayer's suggestion. 
     * Returns true, if no one can refute any of the suggestions and false, if a player (or public cards) holds one of the suggested cards.
     * @param currentPlayer
     * @param characterChoice
     * @param weaponChoice
     * @param roomChoice
     * @return
     */
    public boolean checkSuggestion(String currentPlayer, Player characterChoice, Weapon weaponChoice, Room roomChoice) {

	//Check if any of the suggested items are in the public cards.
	for (int i = 0; i < this.publicCards.size(); i++) {
	    if (this.publicCards.get(i).getName().equals(characterChoice.getName())) {
		System.out.println("Suggestion Incorrect: " + characterChoice.getName() + " is a public card.");
		return false;
	    }
	    if (this.publicCards.get(i).getName().equals(weaponChoice.getName())){
		System.out.println("Suggestion Incorrect: " + weaponChoice.getName() + " is a public card.");
		return false;
	    }
	    if (this.publicCards.get(i).getName().equals(roomChoice.getRoomName())){
		System.out.println("Suggestion Incorrect: " + roomChoice.getRoomName() + " is a public card.");
		return false;
	    }
	}

	//Find current players position in the "player array".
	int position = -1;
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getName().equals(currentPlayer)) {
		position = i;
		break;
	    }
	}
	assert position != -1;

	//Checks each players card for a matching card in a clock-wise fashion.
	for (int i = position + 1; i < this.players.size(); i++) {
	    List<Card> playerHand = this.players.get(i).getHand();

	    if (playerHand.contains(new CharacterCard(characterChoice.getName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + characterChoice.getName() + " card on hand.");
		return false;
	    }

	    if (playerHand.contains(new WeaponCard(weaponChoice.getName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + weaponChoice.getName() + " card on hand.");
		return false;
	    }

	    if (playerHand.contains(new RoomCard (roomChoice.getRoomName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + roomChoice.getRoomName() + " card on hand.");
		return false;
	    }
	}
	//Wrap around with the rest of the players
	for (int i = 0; i < position; i++) {
	    List<Card> playerHand = this.players.get(i).getHand();

	    if (playerHand.contains(new CharacterCard(characterChoice.getName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + characterChoice.getName() + " card on hand.");
		return false;
	    }

	    if (playerHand.contains(new WeaponCard(weaponChoice.getName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + weaponChoice.getName() + " card on hand.");
		return false;
	    }

	    if (playerHand.contains(new RoomCard (roomChoice.getRoomName()))) {
		System.out.println("Suggestion Incorrect: " + this.players.get(i).getName() + " has a " + roomChoice.getRoomName() + " card on hand.");
		return false;
	    }
	}

	return true; //No one has any of the suggested cards, so accusation is correct.
    }



    /**
     * Move given player to the given room.
     * @param characterChoice player to be moved
     * @param roomChoice room to move the player to
     */
    public void movePlayer(Player characterChoice, Room roomChoice) {

	//If player/character is not playing or player is eliminated, we cannot move them so do nothing.
	if(!this.players.contains(characterChoice) || this.eliminatedplayers.contains(characterChoice)) {
	    return;
	}

	Position startPosition = characterChoice.getPosition();
	Position moveTo = roomChoice.getEntrances().get(0); //Get the position the player would like to move to.
	assert moveTo != null;

	//Player is already in the room.
	if (roomChoice.equals(this.gameBoard.getRoom(startPosition))) {
	    return;
	}

	characterChoice.setPosition(moveTo); //Set the new position of the player

	//If player has left a room, set their room.
	if (this.gameBoard.isRoomTile(startPosition)) {
	    characterChoice.getCurrentRoom().removeOccupant(characterChoice);
	    characterChoice.setHolderTile(null);
	    characterChoice.setCurrentRoom(null);
	}

	//If player has entered a room, set their room and add the player as a new occupant.
	if (this.gameBoard.isRoomTile(moveTo)) {
	    characterChoice.setCurrentRoom(this.gameBoard.getRoom(moveTo));
	    characterChoice.getCurrentRoom().addOccupant(characterChoice);
	}


	//If player was on a walkable tile before moving, set it as unoccupied now that the player has moved.
	if (this.gameBoard.getTile(startPosition) instanceof WalkableTile) {
	    this.gameBoard.getWalkableTile(startPosition).setOccupied(false);
	}

	characterChoice.setWasTransferred(true); //Set as true so player can have the choice of a suggestion on their next turn.

    }


    /**
     * Processes accusation. Gives given player the options to make accusation. Determines if winner or is eliminated.
     */
    public void processAccusation(Player p) {
	System.out.println(p.getName() + ": MAKING AN ACCUSATION");

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
	    System.out.println("ACCUSATION INCORRECT: " + p.getName() + " has been eliminated.");
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
		Weapon currentWeapon = new Weapon(this.weaponNames[distributedWeapons], currentRoom);
		this.weapons.add(currentWeapon);
		currentRoom.addWeapon(currentWeapon);
		distributedWeapons++;
	    }
	}
    }

    /**
     * Returns the weapon given the name of the weapon.
     * @param name
     */
    public Weapon getWeapon(String name) {

	for (Weapon weapon : this.weapons) {
	    if (weapon.getName().equals(name)) {
		return weapon;
	    }
	}

	throw new Error ("ERROR: Weapon not found!");

    }

    /**
     * Moves given weapon to the given room.
     */
    public void moveWeapon(Weapon weapon, Room room) {

	//Weapon is already in the room so dont need to do anything.
	if (weapon.getRoom().equals(room)) {
	    return;
	}

	//Remove from current room.
	weapon.getRoom().removeWeapon(weapon);

	//set new room of weapon
	weapon.setRoom(room);

	//Add weapon to the new room.
	room.addWeapon(weapon);

    }


    /**
     * Set up the secret passages for the diagonal rooms.
     */
    public void setSecretPassages() {
	this.getRoom("Kitchen").setSecretPassage(this.getRoom("Study")); //Set passage from kitchen to study.
	this.getRoom("Study").setSecretPassage(this.getRoom("Kitchen")); //Set passage from study to kitchen
	this.getRoom("Lounge").setSecretPassage(this.getRoom("Conservatory")); //Set passage from Lounge to Conservatory
	this.getRoom("Conservatory").setSecretPassage(this.getRoom("Lounge")); //Set passage from Conservatory to Lounge
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
	return (winner != null) || (this.eliminatedplayers.size() == this.players.size());
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
	System.out.println("------------------ " + p.getName().toUpperCase() + "'S TURN (PLAYER " + p.getToken() + ")" + "------------------");

	int playersRemaining = this.players.size() - this.eliminatedplayers.size();
	System.out.println("Players remaining: " + playersRemaining + " ("+ this.eliminatedplayers.size() + " eliminated).");
	System.out.println();

	if (p.isInRoom()) {
	    System.out.println("Current Room: " + p.getCurrentRoom().getRoomName());

	    //Print the list of weapons in the room if there are any.
	    if (!p.getCurrentRoom().getWeapons().isEmpty()) {
		System.out.print("Weapon(s) found in this Room: ");
		for (int i = 0; i < p.getCurrentRoom().getWeapons().size() - 1; i++) {
		    System.out.print(p.getCurrentRoom().getWeapons().get(i).getName() + ", ");
		}
		System.out.print(p.getCurrentRoom().getWeapons().get(p.getCurrentRoom().getWeapons().size() - 1).getName());
	    }

	} else {
	    System.out.println("Current Position: " + this.posToCoordinates(p.getPosition()));
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


    //Returns the player given their name.
    public Player getPlayer (String name){
	for (Player p : this.players) {
	    if (p.getName().equals(name)) {
		return p;
	    }
	}

	return null;
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
     * Returns the Room object given its name.
     * @param name
     * @return
     */
    public Room getRoom(String name) {
	for (Room r : this.rooms) {
	    if (r.getRoomName().equals(name)) {
		return r;
	    }
	}
	return null;
    }


    /**
     * Converts position given to coordinaates or room name.
     * @param pos
     * @return
     */
    public String posToCoordinates(Position pos) {

	//If the position is a room tile, return room name.
	if (this.gameBoard.isEntranceTile(pos)) {
	    return this.gameBoard.getRoom(pos).getRoomName();
	}

	String str = "" + (char)(pos.getPosX()+65)+ "-" + pos.getPosY();

	return str;
    }

    /**
     * Used for getting the player to make a choice from a list starting from 1 to max. Essentially a helper method.
     * @param min
     * @param max
     * @return
     */
    public int getPlayerChoice(int max) {
	@SuppressWarnings("resource")
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
