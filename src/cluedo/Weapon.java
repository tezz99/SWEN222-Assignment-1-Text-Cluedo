package cluedo;

public class Weapon {

    private final String name; //Name of the weapon.
    private Room room; //The room that this weapon is in

    public Weapon (String name, Room room) {
	this.name = name;
	this.room = room;
    }


}
