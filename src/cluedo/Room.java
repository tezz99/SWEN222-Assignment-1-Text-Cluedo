package cluedo;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final String name;
    private List<Player> occupants = new ArrayList<>();
    private Room secretPassage = null;
    private List<Position> entrances = new ArrayList<>();
    private List<Position> exits = new ArrayList<>(); //The tiles that are just outside the rooms doors (will be used for GUI later).

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

    /**
     * @param p
     * @return true if the room has the given occupant.
     */
    public boolean hasOccupant(Player p) {
	return this.occupants.contains(p);
    }

    /**
     * @return true if this room has a secret passage to another room.
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
     * @return the name of the room
     */
    public String getRoomName() {
	return this.name;
    }

    /**
     * @return the list of weapons in the room. List will be null if there arent any.
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

    /**
     * Remove the given weapon from the list of weapons in the room.
     * @param weapon
     */
    public void removeWeapon(Weapon weapon) {
	this.weapons.remove(weapon);
    }

    /**
     * Returns list of entrances to the room
     * @return
     */
    public List<Position> getEntrances() {
	return this.entrances;
    }

    /**
     * Adds given entrance to the list of entrances to the room.
     * @param entrances
     */
    public void addEntrance(Position e) {
	this.entrances.add(e);
    }

    /**
     * 
     * @return the exits of this room.
     */
    public List<Position> getExits() {
	return exits;
    }

    /**
     * Add an exit to this room.
     * @param exit
     */
    public void addExit(Position exit) {
	this.exits.add(exit);
    }

}
