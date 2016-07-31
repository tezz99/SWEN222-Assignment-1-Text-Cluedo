import java.util.InputMismatchException;
import java.util.Scanner;

import Cluedo.Cluedo;

public class CluedoClient {

    /**
     * Setup and run a new game of cluedo.
     */
    public CluedoClient() {
	System.out.println("Welcome to Text Cluedo!");
	int numberOfPlayers = this.getNumberOfPlayers();
	System.out.println("Game set up for " + numberOfPlayers + " players.");

	Cluedo game = new Cluedo(numberOfPlayers); //Create a new game of cluedo.

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
	    System.out.println("Enter the number of players (3-6): ");
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
	scan.close();
	System.out.println("---------------------------------------------");
	return players;
    }

    public static void main(String[] args) {
	new CluedoClient();
    }

}
