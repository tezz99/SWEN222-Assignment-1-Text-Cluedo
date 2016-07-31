package Cluedo;

import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;

public class Triple {

    private CharacterCard character;
    private WeaponCard weapon;
    private RoomCard room;

    public Triple(CharacterCard character, WeaponCard weapon, RoomCard room) {
	this.character = character;
	this.weapon = weapon;
	this.room = room;
    }

    /**
     * Checks if a specified card is within this triple.
     * @return
     */
    public boolean contains (Card c) {
	if (c instanceof Card) {
	    if ((this.character.getName().equals(c.getName())) || (this.weapon.getName().equals(c.getName())) || (this.room.getName().equals(c.getName()))  ) {
		System.out.println("Returning true.");
		return true;
	    }
	}
	return false;
    }


    public String toString() {
	return "Character: " + this.character.toString() + ", Weapon: " + this.weapon.toString() + ", Room: " + this.room.toString();
    }

}
