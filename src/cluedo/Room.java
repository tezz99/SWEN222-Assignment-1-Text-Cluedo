package cluedo;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final String name;
    private List<Player> occupants = new ArrayList<>();
    private Room secretPassage = null;

    private List<Weapon> weapons = new ArrayList<>();


    public Room(String name) {
	this.name = name;
    }

    /**
     * Returns true if the room is occupied by a person.
     * @return
     */
    public boolean isOccupied() {
	return !occupants.isEmpty();
    }


    /**
     * Sets the given player as an occupant of the room.
     * @param occupant
     */
    public void addOccupant(Player p) {
	this.occupants.add(p);
    }

    /**
     * Returns the occupant of the room
     * @return
     */
    public void removeOccupant(Player p) {
	this.occupants.remove(p);
    }

    public boolean hasOccupant(Player p) {
	return this.occupants.contains(p);
    }

    /**
     * Returns true if this room has a secret passage to another room.
     * @return
     */
    public boolean hasSecretPassage() {
	return this.secretPassage != null;
    }

    /**
     * @return the secretPassage
     */
    public Room getSecretPassage() {
	return secretPassage;
    }

    /**
     * Set room given as a secret passage
     */
    public void setSecretPassage(Room room) {
	this.secretPassage = room;
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
