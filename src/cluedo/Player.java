package cluedo;

import java.util.ArrayList;
import java.util.List;

import Tiles.RoomTile;
import cards.Card;

/**
 * Represents a player in the game.
 * 
 * @author tezz99
 *
 */
public class Player {

    List<Card> hand = new ArrayList<>();
    private Position currentPosition;
    private String name;
    private int token;
    private Room currentRoom = null;
    private RoomTile holderTile = null;

    public Player(String name) {
	this.name = name;
    }

    /**
     * Returns the name of the player.
     * 
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Adds the given card to the players hand.
     * 
     * @param c
     */
    public void addToHand(Card c) {
	this.hand.add(c);
    }

    /**
     * Returns the players hand/
     * 
     * @return
     */
    public List<Card> getHand() {
	return this.hand;
    }


    // GETTERS AND SETTERS
    public Position getPosition() {
	return this.currentPosition;
    }

    public void setPosition(Position pos) {
	this.currentPosition = pos;
    }

    public Room getCurrentRoom() {
	return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
	this.currentRoom = currentRoom;
    }

    public RoomTile getHolderTile() {
	return holderTile;
    }

    public void setHolderTile(RoomTile holderTile) {
	this.holderTile = holderTile;
    }


    /**
     * Returns true if player is currently in a room.
     * 
     * @return
     */
    public boolean isInRoom() {
	return this.currentRoom != null;
    }

    public String getToken() {
	return "" + this.token;
    }

    public void setToken(int i) {
	this.token = i;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
	result = prime * result
		+ ((currentRoom == null) ? 0 : currentRoom.hashCode());
	result = prime * result + ((hand == null) ? 0 : hand.hashCode());
	result = prime * result
		+ ((holderTile == null) ? 0 : holderTile.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + token;
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
	if (!(obj instanceof Player)) {
	    return false;
	}
	Player other = (Player) obj;
	if (currentPosition == null) {
	    if (other.currentPosition != null) {
		return false;
	    }
	} else if (!currentPosition.equals(other.currentPosition)) {
	    return false;
	}
	if (currentRoom == null) {
	    if (other.currentRoom != null) {
		return false;
	    }
	} else if (!currentRoom.equals(other.currentRoom)) {
	    return false;
	}
	if (hand == null) {
	    if (other.hand != null) {
		return false;
	    }
	} else if (!hand.equals(other.hand)) {
	    return false;
	}
	if (holderTile == null) {
	    if (other.holderTile != null) {
		return false;
	    }
	} else if (!holderTile.equals(other.holderTile)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (token != other.token) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return this.name;
    }


}
