package cluedo;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

/**
 * Represents a player in the game.
 * @author tezz99
 *
 */
public class Player {

    List<Card> hand = new ArrayList<>();

    private Integer xPos;
    private Integer yPos;

    private String name;

    public Player(String name) {
	this.name = name;
    }

    /**
     * Returns the name of the player.
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Adds the given card to the players hand.
     * @param c
     */
    public void addToHand(Card c) {
	this.hand.add(c);
    }

    /**
     * Returns the players hand/
     * @return
     */
    public List<Card> getHand() {
	return this.hand;
    }

    /*
    public boolean hasCard(Card c) {
	return this.hand.contains(c);
    }
     */

    @Override
    public String toString() {
	return this.name;
    }

}
