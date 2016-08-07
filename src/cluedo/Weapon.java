package cluedo;

public class Weapon {

    private final String name; //Name of the weapon.
    private Room room; //The room that this weapon is in

    public Weapon (String name, Room room) {
	this.name = name;
	this.setRoom(room);
    }

    public Room getRoom() {
	return room;
    }

    public void setRoom(Room room) {
	this.room = room;
    }

    /**
     * Returns the name of the weapon
     * @return
     */
    public String getName() {
	return this.name;
    }

}
