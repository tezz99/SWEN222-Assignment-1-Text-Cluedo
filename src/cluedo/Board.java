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
    private Room[] rooms;

    private final int[][] startingPositions = {{9,0}, {15,0}, {24,6}, {24,19}, {7, 24}, {0, 17}}; //Starting positions of characters starting from scarlett

    public Board(List<Player> players, Room[] rooms) {

	this.players = players;
	this.rooms = rooms;

	gameBoard = new Tile[width][height]; //Create a gameboard array that holds tiles.

	//read the layout.txt file into the board array 
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

	    this.setStartPositions(); //set each players starting positions in player objects.
	    this.setDoorTiles(); //Mark certain room tiles as entrances/doors into a particular room.
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
	System.out.println();


	//Print array.
	for (int row = 0; row < 25; row++) {
	    int y = row + 1;

	    if (y < 11) { System.out.print(row + "   ");} else {System.out.print(row + "  ");}; //to correctly print y cootrdinates

	    for (int col = 0; col < 25; col++) {
		assert this.gameBoard[col][row] != null;
		System.out.print(this.gameBoard[col][row].toString() + " ");
	    }
	    System.out.println();

	}
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
	    RoomTile tile = ((RoomTile)this.gameBoard[4][6]);
	    tile.setAsDoor();
	    tile.setRoomName("Kitchen");

	    Room room = this.getRoom("Kitchen");
	    assert room != null;
	    tile.setRoom(room);


	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//Set ball room entrances
	//Left ball room door
	if (this.gameBoard[8][5] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[8][5]);
	    tile.setAsDoor();
	    tile.setRoomName("Ball Room");

	    Room room = this.getRoom("Ball Room");
	    assert room != null;
	    tile.setRoom(room);

	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//Bottom ball room door 1
	if (this.gameBoard[9][7] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[9][7]);
	    tile.setAsDoor();
	    tile.setRoomName("Ball Room");

	    Room room = this.getRoom("Ball Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//Bottom ball room door 2
	if (this.gameBoard[15][7] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[15][7]);
	    tile.setAsDoor();
	    tile.setRoomName("Ball Room");

	    Room room = this.getRoom("Ball Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//right ball room door
	if (this.gameBoard[16][5] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[16][5]);
	    tile.setAsDoor();
	    tile.setRoomName("Ball Room");

	    Room room = this.getRoom("Ball Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}


	//set conservatory entrance
	if (this.gameBoard[19][4] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[19][4]);
	    tile.setAsDoor();
	    tile.setRoomName("Conservatory");

	    Room room = this.getRoom("Conservatory");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}


	//set Billiard room entrances
	//left Billiard room door
	if (this.gameBoard[19][9] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[19][9]);
	    tile.setAsDoor();
	    tile.setRoomName("Billard Room");

	    Room room = this.getRoom("Billard Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//bottom Billiard room door
	if (this.gameBoard[23][12] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[23][12]);
	    tile.setAsDoor();
	    tile.setRoomName("Billard Room");

	    Room room = this.getRoom("Billard Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//set library room entrances
	//top entrance
	if (this.gameBoard[21][14] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[21][14]);
	    tile.setAsDoor();
	    tile.setRoomName("Library");

	    Room room = this.getRoom("Library");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//left entrance
	if (this.gameBoard[18][16] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[18][16]);
	    tile.setAsDoor();
	    tile.setRoomName("Library");

	    Room room = this.getRoom("Library");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}


	//Set study entrance
	if (this.gameBoard[18][21] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[18][21]);
	    tile.setAsDoor();
	    tile.setRoomName("Study");

	    Room room = this.getRoom("Study");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//set hall entrances.
	//right hall entrance
	if (this.gameBoard[15][20] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[15][20]);
	    tile.setAsDoor();
	    tile.setRoomName("Hall");

	    Room room = this.getRoom("Hall");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//top three hall entrances.
	if (this.gameBoard[11][18] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[11][18]);
	    tile.setAsDoor();
	    tile.setRoomName("Hall");

	    Room room = this.getRoom("Hall");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	if (this.gameBoard[12][18] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[12][18]);
	    tile.setAsDoor();
	    tile.setRoomName("Hall");

	    Room room = this.getRoom("Hall");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	if (this.gameBoard[13][18] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[13][18]);
	    tile.setAsDoor();
	    tile.setRoomName("Hall");

	    Room room = this.getRoom("Hall");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//Set lounge entrance
	if (this.gameBoard[6][19] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[6][19]);
	    tile.setAsDoor();
	    tile.setRoomName("Lounge");

	    Room room = this.getRoom("Lounge");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//Set dining room entrances
	//bottom dining room entrance
	if (this.gameBoard[6][15] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[6][15]);
	    tile.setAsDoor();
	    tile.setRoomName("Dining Room");

	    Room room = this.getRoom("Dining Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//right dining entrance
	if (this.gameBoard[7][12] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[7][12]);
	    tile.setAsDoor();
	    tile.setRoomName("Dining Room");

	    Room room = this.getRoom("Dining Room");
	    assert room != null;
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}
    }

}
