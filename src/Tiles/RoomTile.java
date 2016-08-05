package Tiles;

import cluedo.Room;

public class RoomTile implements Tile{

    private String roomName;
    private Room room; //Link to the room.
    private boolean isDoor = false;


    /**
     * Returns room name.
     * @return
     */
    public String getRoomName() {
	return this.roomName;
    }

    /**
     * Returns the room associated to this tile.
     * @return
     */
    public Room getRoom() {
	return this.room;
    }

    /**
     * Returns true if this tile is a door to the room.
     * @return
     */
    public boolean isDoor() {
	return this.isDoor;
    }

    /**
     * Sets given name as the room's name.
     */
    public void setRoomName(String name) {
	this.roomName = name;
    }

    /**
     * Sets this tile as a door/entry to the room.
     */
    public void setAsDoor() {
	this.isDoor = true;
    }

    /**
     * Set the room that is represented by this room tile.
     * @param room
     */
    public void setRoom(Room room) {
	this.room = room;
    }

    @Override
    public String toString() {
	if (this.isDoor) {
	    return "D";
	}
	return "R";
    }

}
