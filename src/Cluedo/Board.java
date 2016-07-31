package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Tiles.RoomTile;
import Tiles.SolutionTile;
import Tiles.Tile;
import Tiles.WalkableTile;
import Tiles.WallTile;

public class Board {

    private final int width = 25;
    private final int height = 25;
    private Tile[][] gameBoard;

    private final int[][] startingPositions = {{9,0}, {15,0}, {24,6}, {24,19}, {7, 24}, {0, 17}}; //Starting positions of characters starting from scarlett

    public Board() {
	gameBoard = new Tile[width][height];

	try {
	    Scanner scan = new Scanner(new File("src/layout.txt"));

	    int xPos = 0;
	    int yPos = 0;

	    while (scan.hasNext()) {
		if (scan.hasNextInt()) {
		    int next = scan.nextInt();

		    if (next == 1) {
			this.gameBoard[xPos][yPos] = new WallTile();
		    } else if (next == 0) {
			this.gameBoard[xPos][yPos] = new WalkableTile();
		    } else {
			throw new Error("Error loading layout file.");
		    }
		} else {
		    String room = scan.next();
		    assert room.length() == 1;
		    char c = room.charAt(0);

		    if (c == 'R' || c == 'D') {
			this.gameBoard[xPos][yPos] = new RoomTile();
		    } else if (c == 'X') {
			this.gameBoard[xPos][yPos] = new SolutionTile();
		    } else {
			throw new Error("Error loading layout file...");
		    }

		    xPos++;

		    if (xPos ==  25) {
			xPos = 0;
			yPos++;
		    }
		}
	    }

	    scan.close();

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }





    /**
     * Displays the board (current state of the game) on the console.
     */
    public void displayBoard() {
	System.out.println();


	//Display the X coordinates at the top.
	System.out.print("   ");
	for(int i = 0; i < this.gameBoard.length; i++){
	    System.out.print(" " + (char)(i + 65));
	}


    }

}
