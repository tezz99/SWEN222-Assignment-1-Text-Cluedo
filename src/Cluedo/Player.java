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

    private Position currentPosition;

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


    //GETTERS AND SETTERS
    public Position getPosition() {
	return this.currentPosition;
    }

    public void setPosition(Position pos) {
	this.currentPosition = pos;
    }


    @Override
    public String toString() {
	return this.name;
    }




}
