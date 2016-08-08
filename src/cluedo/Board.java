package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import tiles.RoomTile;
import tiles.SolutionTile;
import tiles.Tile;
import tiles.WalkableTile;
import tiles.WallTile;

public class Board {

    public final int width = 25;
    public final int height = 25;
    private Cluedo game;
    private List<Player> players;
    private Room[] rooms;
    private Tile[][] gameBoard;

    private final int[][] startingPositions = {{9,0}, {15,0}, {24,6}, {24,19}, {7, 24}, {0, 17}}; //Starting positions of characters, starting from scarlett

    public Board(Cluedo game, List<Player> players, Room[] rooms) {
	this.game = game;
	this.players = players;
	this.rooms = rooms;

	gameBoard = new Tile[width][height]; //Create a gameboard array that holds tiles.

	//read the layout.txt file into the board array 
	try {
	    @SuppressWarnings("resource")
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

		    if (c == 'K' || c == 'k' || c == '*') {
			RoomTile roomTile = new RoomTile();

			if (c == 'k' || c == '*') {
			    roomTile.setInnerTile(true);
			    if (c == '*') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Kitchen"));
			roomTile.setRoomName("Kitchen");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    } 
		    else if (c == 'B' || c == 'b' || c == '-') {
			RoomTile roomTile = new RoomTile();

			if (c == 'b' || c == '-') {
			    roomTile.setInnerTile(true);
			    if (c == '-') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Ball Room"));
			roomTile.setRoomName("Ball Room");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'C' || c == 'c' || c == '.') {
			RoomTile roomTile = new RoomTile();

			if (c == 'c' || c == '.') {
			    roomTile.setInnerTile(true);
			    if (c == '.') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Conservatory"));
			roomTile.setRoomName("Conservatory");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'I' || c == 'i' || c == ';') {
			RoomTile roomTile = new RoomTile();

			if (c == 'i' || c == ';') {
			    roomTile.setInnerTile(true);
			    if (c == ';') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Billiard Room"));
			roomTile.setRoomName("Billiard Room");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'L' || c == 'l' || c == '^') {
			RoomTile roomTile = new RoomTile();

			if (c == 'l' || c == '^') {
			    roomTile.setInnerTile(true);
			    if (c == '^') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Library"));
			roomTile.setRoomName("Library");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'S' || c == 's' || c == '#') {
			RoomTile roomTile = new RoomTile();

			if (c == 's' || c == '#') {
			    roomTile.setInnerTile(true);
			    if (c == '#') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Study"));
			roomTile.setRoomName("Study");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'H' || c == 'h' || c == '@') {
			RoomTile roomTile = new RoomTile();

			if (c == 'h' || c == '@') {
			    roomTile.setInnerTile(true);
			    if (c == '@') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Hall"));
			roomTile.setRoomName("Hall");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'L' || c == 'l' || c == '&') {
			RoomTile roomTile = new RoomTile();

			if (c == 'l' || c == '&') {
			    roomTile.setInnerTile(true);
			    if (c == '&') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Lounge"));
			roomTile.setRoomName("Lounge");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'D' || c == 'd' || c == '%') {
			RoomTile roomTile = new RoomTile();

			if (c == 'd' || c == '%') {
			    roomTile.setInnerTile(true);
			    if (c == '%') {
				roomTile.setPlayerHolder(true);
			    }
			}
			roomTile.setRoom(this.getRoom("Dining Room"));
			roomTile.setRoomName("Dining Room");
			roomTile.setPosition(new Position(xPos, yPos));
			this.gameBoard[xPos][yPos] = roomTile;
		    }
		    else if (c == 'X') {
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


	//Print the board.
	for (int row = 0; row < 25; row++) {
	    int y = row + 1;

	    if (y < 11) { System.out.print(row + "  ");} else {System.out.print(row + " ");}; //to correctly print y cootrdinates

	    for (int col = 0; col < 25; col++) {
		assert this.gameBoard[col][row] != null;

		boolean tokenPrinted = false;

		for (Player p : this.players) {
		    //If player is elimintated, do not display them.
		    if (this.game.getEliminatedPlayers().contains(p)) {
			continue;
		    }

		    //If this room tile has a player on it then display it.
		    if (this.gameBoard[col][row] instanceof RoomTile) {
			RoomTile currentTile = (RoomTile)this.gameBoard[col][row];

			if (currentTile.isPlayerHolder()) {
			    if (currentTile.getRoom().hasOccupant(p) && p.getHolderTile() == null && !currentTile.isOccupied()) {
				p.setHolderTile(currentTile);
				currentTile.setOccupied(true);
				System.out.print("|" + p.getToken());
				tokenPrinted = true;
				break;
			    } else if (p.getHolderTile() != null && currentTile.getRoom().hasOccupant(p) && p.getHolderTile().equals(currentTile)) {
				System.out.print("|" + p.getToken());
				tokenPrinted = true;
				break;
			    }
			}

			//If player is on this walkable tile...
		    } else if (!game.getEliminatedPlayers().contains(p) && p.getPosition().getPosX() == col && p.getPosition().getPosY() == row) {
			System.out.print("|" + p.getToken());
			tokenPrinted = true;
			break;
		    }
		}

		if (!tokenPrinted) {
		    System.out.print("|" + this.gameBoard[col][row].toString());
		}
	    }

	    System.out.print("| " + row);
	    System.out.println();  
	}
	System.out.println(); 
	System.out.println("Room Structure (Clockwise from top left):");
	System.out.println("Kitchen, Ball Room, Conservatory, Billiard Room, Library, Study, Hall, Lounge, Dining Room");
    }


    /**
     * Sets the starting positions of all players playing.
     */
    public void setStartPositions() {
	for (int i = 0; i < this.players.size(); i++) {
	    Position currentPos = new Position(this.startingPositions[i][0], this.startingPositions[i][1]);
	    this.getWalkableTile(currentPos).setOccupied(true);
	    this.players.get(i).setPosition(currentPos);
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
	    room.addExit(new Position(4,7));
	    room.addEntrance(new Position(4,6));
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
	    room.addEntrance(new Position(8,5));
	    room.addExit(new Position(7,5));
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
	    room.addEntrance(new Position(9,7));
	    room.addExit(new Position(9, 8));
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
	    room.addEntrance(new Position(15,7));
	    room.addExit(new Position(15, 8));
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
	    room.addEntrance(new Position(16,5));
	    room.addExit(new Position(17,5));
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
	    room.addEntrance(new Position(19,4));
	    room.addExit(new Position(19,5));
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}


	//set Billiard room entrances
	//left Billiard room door
	if (this.gameBoard[19][9] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[19][9]);
	    tile.setAsDoor();
	    tile.setRoomName("Billiard Room");

	    Room room = this.getRoom("Billiard Room");
	    assert room != null;
	    room.addEntrance(new Position(19,9));
	    room.addExit(new Position(18,9));
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
	}

	//bottom Billiard room door
	if (this.gameBoard[23][12] instanceof RoomTile) {
	    RoomTile tile = ((RoomTile)this.gameBoard[23][12]);
	    tile.setAsDoor();
	    tile.setRoomName("Billiard Room");

	    Room room = this.getRoom("Billiard Room");
	    assert room != null;
	    room.addEntrance(new Position(23,12));
	    room.addExit(new Position(23, 13));
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
	    room.addEntrance(new Position(21,14));
	    room.addExit(new Position(21,13));
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
	    room.addEntrance(new Position(18,16));
	    room.addExit(new Position(17, 16));
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
	    room.addEntrance(new Position(18,21));
	    room.addExit(new Position(18, 20));
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
	    room.addEntrance(new Position(15,20));
	    room.addExit(new Position(16,20));
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
	    room.addEntrance(new Position(11,18));
	    room.addExit(new Position(11, 17));
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
	    room.addEntrance(new Position(12,18));
	    room.addExit(new Position(12,17));
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
	    room.addEntrance(new Position(13,18));
	    room.addExit(new Position(13,17));
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
	    room.addEntrance(new Position(6,19));
	    room.addExit(new Position(6,18));
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
	    room.addEntrance(new Position(6,15));
	    room.addExit(new Position(6, 16));
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
	    room.addEntrance(new Position(7,12));
	    room.addExit(new Position(8,18));
	    tile.setRoom(room);
	} else {
	    throw new Error("Incorrect entrance coordinates.");
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
     * Returns the room given the position. If tile is not part of room, returns null.
     * @param p
     * @return
     */
    public Room getRoom(Position p) {

	//If given position is part of a room.
	if (this.gameBoard[p.getPosX()][p.getPosY()] instanceof RoomTile) {
	    RoomTile tile = (RoomTile)this.gameBoard[p.getPosX()][p.getPosY()];
	    if (tile.isDoor()) {
		return tile.getRoom();
	    }
	}
	return null;
    }

    /**
     * Returns the tile at position p/
     * @param p
     * @return
     */
    public Tile getTile(Position p) {
	return this.gameBoard[p.getPosX()][p.getPosY()];
    }

    public boolean isRoomTile (Position p) {
	return this.gameBoard[p.getPosX()][p.getPosY()] instanceof RoomTile;
    }

    /**
     * Returns if a given position is a walkable or a tile that is an entrance to a room.
     * @param p
     * @return
     */
    public boolean isValidTile(Position p) {
	if (this.gameBoard[p.getPosX()][p.getPosY()] instanceof WalkableTile) {
	    //System.out.println("Tested X: " + p.getPosX() + " and Y: " + p.getPosY() + " and returning true as its a walkable tile");
	    return true;
	}

	return isEntranceTile(p);
    }


    /**
     * Returns true if the tile position given is part of a room and an entrace to the room.
     * @param p
     * @return
     */
    public boolean isEntranceTile(Position p) {

	if (this.gameBoard[p.getPosX()][p.getPosY()] instanceof RoomTile) {
	    RoomTile tile = (RoomTile)this.gameBoard[p.getPosX()][p.getPosY()];
	    //System.out.println("Tested X: " + p.getPosX() + " and Y: " + p.getPosY() + " and returning " + tile.isDoor());
	    return tile.isDoor();
	}

	return false;
    }

    /**
     * Returns the walkable tile at given position.
     * @param p
     * @return
     */
    public WalkableTile getWalkableTile(Position p) {
	int posX = p.getPosX();
	int posY = p.getPosY();

	if (this.gameBoard[posX][posY] instanceof WalkableTile) {
	    return (WalkableTile)this.gameBoard[posX][posY];
	}

	throw new Error("Position given is not a walkable tile");
    }

    /**
     * 
     * @param p position of potential walkable tile.
     * @return true if position given is a walkable tile on the game board.
     */
    public boolean isWalkableTile(Position p) {
	return this.gameBoard[p.getPosX()][p.getPosY()] instanceof WalkableTile;
    }

}
