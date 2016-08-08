import java.util.InputMismatchException;
import java.util.Scanner;

import cluedo.Cluedo;
import cluedo.Dice;
import cluedo.Player;

public class CluedoClient {

    private final String divider = "----------------------------------- * NEXT ROUND * -----------------------------------";

    /**
     * Setup and run a new game of cluedo.
     */
    public CluedoClient() {
	System.out.println("Welcome to Text Cluedo!");
	int numberOfPlayers = this.getNumberOfPlayers();
	System.out.println("Game set up for " + numberOfPlayers + " players.");
	System.out.println(this.divider);

	Cluedo game = new Cluedo(numberOfPlayers); //Create a new game of cluedo.

	Dice dice = new Dice();

	while (!game.isGameOver()) {

	    for (Player p : game.getPlayers()) {

		//If player is not eliminated and game is not over, allow player to make move.
		if (!game.getEliminatedPlayers().contains(p) && !game.isGameOver()) {

		    game.displayBoard(); 
		    System.out.println();

		    game.displayPlayerStatus(p); //Displays player's current position, hand.

		    String selectedOption = game.offerOptions(p); //Ask the player what they want to do and store their choice.

		    if (selectedOption.equals("Move")) {
			game.processMove(p, dice.roll()); //Player has chosen to move.
		    } else if (selectedOption.equals("Accuse")) {
			game.processAccusation(p); //Process the accusation. Ask for accusation and check if correct.
		    }
		}

		if (game.isGameOver()) {
		    break;
		}

		if (!game.getEliminatedPlayers().contains(p)){
		    System.out.println(this.divider);
		}
	    }

	}
	System.out.println();
	System.out.println("----------------------------------- *** GAMEOVER *** -----------------------------------");

	if (game.getWinner() != null){
	    System.out.println(game.getWinner().getName() + " WINS!");
	} else {
	    System.out.println("** NO ONE WINS! MURDERER GOT AWAY! **");
	}

    }


    /**
     * Gets the number of players playing the game from the user.
     * @return
     */
    public int getNumberOfPlayers() {

	Scanner scan = new Scanner(System.in);
	int players = 0;

	//Keep asking until valid input provided.
	while (players < 3 || players > 6) {
	    System.out.print("Enter the number of players (3-6): ");
	    try {
		players = scan.nextInt();
		if (players >= 3 && players <= 6) {
		    break;
		}
	    } catch (InputMismatchException e) {
		players = 0;
		scan.nextLine(); //Skip over invalid inout.
	    }
	    System.out.println("Invalid input");
	}
	return players;
    }

    public static void main(String[] args) {
	new CluedoClient();
    }

}
