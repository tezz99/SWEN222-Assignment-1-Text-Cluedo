package cluedo;

public class Room {

    private final String name;
    private Player occupant;


    public Room(String name) {
	this.name = name;
    }

    /**
     * Returns true if the room is occupied by a person.
     * @return
     */
    public boolean isOccupied() {
	return occupant != null;
    }

    /**
     * Sets the given player as an occupant of the room.
     * @param occupant
     */
    public void setOccupant(Player occupant) {
	this.occupant = occupant;
    }

    /**
     * Returns the occupant of the room
     * @return
     */
    public Player getOccupant() {
	return this.occupant;
    }

    public String getRoomName() {
	return this.name;
    }

}
