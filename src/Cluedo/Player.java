package Cluedo;

import java.util.ArrayList;
import java.util.List;

import Cards.Card;

/**
 * Represents a player in the game.
 * @author tezz99
 *
 */
public class Player {

    List<Card> hand = new ArrayList<>();

    private String name;

    public Player(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void addToHand(Card c) {
	this.hand.add(c);
    }

    public List<Card> getHand() {
	return this.hand;
    }

}
