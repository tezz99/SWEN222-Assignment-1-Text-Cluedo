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

	int pos = -1;

	for (int i = 0; i < this.occupants.size(); i++) {
	    if (this.occupants.get(i).getName().equals(p.getName())) {
		pos = i;
	    }
	}

	this.occupants.remove(pos);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((entrances == null) ? 0 : entrances.hashCode());
	result = prime * result + ((exits == null) ? 0 : exits.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((secretPassage == null) ? 0 : secretPassage.hashCode());
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
	if (!(obj instanceof Room)) {
	    return false;
	}
	Room other = (Room) obj;
	if (entrances == null) {
	    if (other.entrances != null) {
		return false;
	    }
	} else if (!entrances.equals(other.entrances)) {
	    return false;
	}
	if (exits == null) {
	    if (other.exits != null) {
		return false;
	    }
	} else if (!exits.equals(other.exits)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (secretPassage == null) {
	    if (other.secretPassage != null) {
		return false;
	    }
	} else if (!secretPassage.equals(other.secretPassage)) {
	    return false;
	}
	return true;
    }

    /**
     * @param p
     * @return true if the room has the given occupant.
     */
    public boolean hasOccupant(Player p) {
	for (int i = 0; i < this.occupants.size(); i++) {
	    if (this.occupants.get(i).getName().equals(p.getName())) {
		return true;
	    }
	}

	return false;
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
