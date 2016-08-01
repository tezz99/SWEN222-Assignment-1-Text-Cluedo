package Tiles;

import cluedo.Room;

public class RoomTile implements Tile{

    private String roomName;
    private Room room; //Link to the room.
    private boolean door = false;


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
	this.door = true;
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
	if (this.door) {
	    return "D";
	}
	return "R";
    }

}
