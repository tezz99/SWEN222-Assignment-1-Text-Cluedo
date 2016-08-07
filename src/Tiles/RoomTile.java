package Tiles;

import cluedo.Position;
import cluedo.Room;

public class RoomTile implements Tile{

    private String roomName;
    private Room room; //Link to the room.
    private boolean isDoor = false;
    private boolean innerTile = false;;
    private boolean isPlayerHolder = false;
    private boolean occupied = false;
    private Position position;

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
	assert room != null : "Room should not equal null!";
	return this.room;
    }

    /**
     * Set the room that is represented by this room tile.
     * @param room
     */
    public void setRoom(Room room) {
	this.room = room;
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

    public boolean isinnerTile() {
	return this.innerTile;
    }

    public void setInnerTile(boolean isClear) {
	this.innerTile = isClear;
    }

    public boolean isPlayerHolder() {
	return this.isPlayerHolder;
    }

    public void setPlayerHolder(boolean holder) {
	this.isPlayerHolder = holder;
    }

    public boolean isOccupied() {
	return occupied;
    }

    public void setOccupied(boolean occupied) {
	this.occupied = occupied;
    }

    public void setPosition(Position pos) {
	this.position = pos;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (innerTile ? 1231 : 1237);
	result = prime * result + (isDoor ? 1231 : 1237);
	result = prime * result + (isPlayerHolder ? 1231 : 1237);
	result = prime * result + (occupied ? 1231 : 1237);
	result = prime * result
		+ ((position == null) ? 0 : position.hashCode());
	result = prime * result + ((room == null) ? 0 : room.hashCode());
	result = prime * result
		+ ((roomName == null) ? 0 : roomName.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof RoomTile)) {
	    return false;
	}
	RoomTile other = (RoomTile) obj;
	if (innerTile != other.innerTile) {
	    return false;
	}
	if (isDoor != other.isDoor) {
	    return false;
	}
	if (isPlayerHolder != other.isPlayerHolder) {
	    return false;
	}
	if (occupied != other.occupied) {
	    return false;
	}
	if (position == null) {
	    if (other.position != null) {
		return false;
	    }
	} else if (!position.equals(other.position)) {
	    return false;
	}
	if (room == null) {
	    if (other.room != null) {
		return false;
	    }
	} else if (!room.equals(other.room)) {
	    return false;
	}
	if (roomName == null) {
	    if (other.roomName != null) {
		return false;
	    }
	} else if (!roomName.equals(other.roomName)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	if (this.isDoor) {
	    return "D";
	}

	if (this.innerTile) {
	    return " ";
	}

	return "R";
    }



}
