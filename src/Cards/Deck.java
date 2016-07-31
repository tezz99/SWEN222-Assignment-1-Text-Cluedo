package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cluedo.Player;

/**
 * Represents a deck of cards (weapon, 
 * @author tezz9
 *
 */
public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck(List<CharacterCard> characterCards, List<WeaponCard> weaponCards, List<RoomCard> roomCards) {
	//Add all three lists of cards to deck.
	this.deck.addAll(characterCards);
	this.deck.addAll(weaponCards);
	this.deck.addAll(roomCards);
    }

    /**
     * Deal the cards in the deck to the given list of players.
     * @param players
     */
    public void deal(List<Player> players) {
	Random rand = new Random();

	while (!this.deck.isEmpty()) {
	    for (Player p : players) {

		//If deck is not empty, deal card to the next player and remove it from the deck.
		if (!this.deck.isEmpty()) {
		    Card card = deck.get(rand.nextInt(this.deck.size()));
		    p.addToHand(card);
		    deck.remove(card);
		    assert !deck.contains(card) : "Deck should not contain card anymore.";
		}
	    }
	}

    }


}
