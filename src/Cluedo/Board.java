package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import Tiles.RoomTile;
import Tiles.SolutionTile;
import Tiles.Tile;
import Tiles.WalkableTile;
import Tiles.WallTile;

public class Board {

    private final int width = 25;
    private final int height = 25;
    private List<Player> players;
    private Tile[][] gameBoard;

    private final int[][] startingPositions = {{9,0}, {15,0}, {24,6}, {24,19}, {7, 24}, {0, 17}}; //Starting positions of characters starting from scarlett

    public Board(List<Player> players) {

	this.players = players;
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
		}

		xPos++;

		if (xPos == 25) {
		    xPos = 0;
		    yPos++;
		}
	    }

	    scan.close();

	    this.setStartPositions();
	    this.setDoorTiles(); //Mark certain room tiles as entrances/doors into a particular room.

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }


    /**
     * Sets the starting positions of all players playing.
     */
    public void setStartPositions() {
	for (int i = 0; i < this.players.size(); i++) {
	    this.players.get(i).setPosition(new Position(this.startingPositions[i][0], this.startingPositions[i][1]));
	}
    }

    /**
     * Sets certain room tiles as doors. These door tiles mark entrances into a room.
     */
    public void setDoorTiles() {

	//Set kitchen entrance
	if (this.gameBoard[4][6] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[4][6]).setAsDoor();
	    ((RoomTile)this.gameBoard[4][6]).setRoomName("Kitchen");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//Set ball room entrances
	//Left ball room door
	if (this.gameBoard[8][5] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[8][5]).setAsDoor();
	    ((RoomTile)this.gameBoard[8][5]).setRoomName("Ball Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//Bottom ball room door 1
	if (this.gameBoard[9][7] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[9][7]).setAsDoor();
	    ((RoomTile)this.gameBoard[9][7]).setRoomName("Ball Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//Bottom ball room door 2
	if (this.gameBoard[15][7] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[15][7]).setAsDoor();
	    ((RoomTile)this.gameBoard[15][7]).setRoomName("Ball Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//right ball room door
	if (this.gameBoard[16][5] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[16][5]).setAsDoor();
	    ((RoomTile)this.gameBoard[16][5]).setRoomName("Ball Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}


	//set conservatory entrance
	if (this.gameBoard[19][4] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[19][4]).setAsDoor();
	    ((RoomTile)this.gameBoard[19][4]).setRoomName("Conservatory");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//set Billiard room entrances
	//left Billiard room door
	if (this.gameBoard[19][9] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[19][9]).setAsDoor();
	    ((RoomTile)this.gameBoard[19][9]).setRoomName("Billard Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//bottom Billiard room door
	if (this.gameBoard[23][12] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[23][12]).setAsDoor();
	    ((RoomTile)this.gameBoard[23][12]).setRoomName("Billard Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//set library room entrances
	//top entrance
	if (this.gameBoard[21][14] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[21][14]).setAsDoor();
	    ((RoomTile)this.gameBoard[21][14]).setRoomName("Library");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//left entrance
	if (this.gameBoard[18][16] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[18][16]).setAsDoor();
	    ((RoomTile)this.gameBoard[18][16]).setRoomName("Library");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}


	//Set study entrance
	if (this.gameBoard[18][21] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[18][21]).setAsDoor();
	    ((RoomTile)this.gameBoard[18][21]).setRoomName("Study");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//set all entrances.
	//right hall entrance
	if (this.gameBoard[15][20] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[15][20]).setAsDoor();
	    ((RoomTile)this.gameBoard[15][20]).setRoomName("Hall");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//top three hall entrances.
	if (this.gameBoard[11][18] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[11][18]).setAsDoor();
	    ((RoomTile)this.gameBoard[11][18]).setRoomName("Hall");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	if (this.gameBoard[12][20] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[12][18]).setAsDoor();
	    ((RoomTile)this.gameBoard[12][18]).setRoomName("Hall");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	if (this.gameBoard[13][20] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[13][18]).setAsDoor();
	    ((RoomTile)this.gameBoard[13][18]).setRoomName("Hall");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//Set lounge entrance
	if (this.gameBoard[6][19] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[6][19]).setAsDoor();
	    ((RoomTile)this.gameBoard[6][19]).setRoomName("Lounge");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//Set dining room entrances
	//bottom dining room entrance
	if (this.gameBoard[6][15] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[6][15]).setAsDoor();
	    ((RoomTile)this.gameBoard[6][15]).setRoomName("Dining Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

	//right dining entrance
	if (this.gameBoard[7][12] instanceof RoomTile) {
	    ((RoomTile)this.gameBoard[7][12]).setAsDoor();
	    ((RoomTile)this.gameBoard[7][12]).setRoomName("Dining Room");
	} else {
	    throw new Error("Incorrent entrance coordinates.");
	}

    }


    /**
     * Displays the board (current state of the game) on the console.
     */
    public void displayBoard() {
	System.out.println();

	/*
	//Display the X coordinates at the top.
	System.out.print("   ");
	for(int i = 0; i < this.gameBoard.length; i++){
	    System.out.print(" " + (char)(i + 65));
	}
	 */


	//Print board.
	for (int row = 0; row < 25; row++) {
	    for (int col = 0; col < 25; col++) {
		System.out.print(this.gameBoard[col][row].toString() + " ");

	    }
	    System.out.println();

	}




    }

}
