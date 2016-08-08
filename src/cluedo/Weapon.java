package cluedo;

public class Weapon {

    private final String name;
    private Room room; //The room that this weapon is in

    public Weapon (String name, Room room) {
	this.name = name;
	this.setRoom(room);
    }

    /**
     * Returns the room that this weapon is in.
     * @return
     */
    public Room getRoom() {
	return room;
    }

    /**
     * Set the room that this weapon is in.
     * @param room
     */
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
