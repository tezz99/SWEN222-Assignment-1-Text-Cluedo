package Tiles;

public class RoomTile implements Tile{

    private String roomName;
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

    @Override
    public String toString() {
	if (this.door) {
	    return "D";
	}
	return "R";
    }

}
