package cluedo;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final String name;
    private Player occupant;
    private List<Weapon> weapons = new ArrayList<Weapon>();


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

    /**
     * Returns the name of the room
     * @return
     */
    public String getRoomName() {
	return this.name;
    }

    /**
     * Returns the list of weapons in the room. List will be null if there arent any.
     * @return
     */
    public List<Weapon> getWeapons() {
	return this.weapons;
    }

    /**
     * Adds weapon to the list of weapons in the room.
     * @param weapon
     */
    public void addWeapon(Weapon weapon) {
	this.weapons.add(weapon);
    }

}
