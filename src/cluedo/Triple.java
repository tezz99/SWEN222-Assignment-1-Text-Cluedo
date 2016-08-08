package cluedo;

import cards.Card;
import cards.CharacterCard;
import cards.RoomCard;
import cards.WeaponCard;

/**
 * A triple holds one of each of the three different types of cards in a game of cluedo. 
 * Used to represent a the items in the solution envelope.
 * @author tezz9
 *
 */
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

    /**
     * Returns the name of the character in the suggersion of accusation.
     * @return
     */
    public String getCharacterName() {
	return this.character.getName();
    }

    /**
     * Returns the name of the weapon in the suggersion of accusation.
     * @return
     */
    public String getWeaponName() {
	return this.weapon.getName();
    }

    /**
     * Returns the name of the room in the suggersion of accusation.
     * @return
     */
    public String getRoomName() {
	return this.room.getName();
    }


    public String toString() {
	return "Character: " + this.character.toString() + ", Weapon: " + this.weapon.toString() + ", Room: " + this.room.toString();
    }

}
