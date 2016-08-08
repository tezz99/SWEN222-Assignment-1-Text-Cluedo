package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cluedo.Player;

/**
 * Represents a deck of cards.
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
     * Deal the cards in the deck to the given list of players and return the remaining list of card that cannot be dealt.
     * @param players
     */
    public List<Card> deal(List<Player> players) {
	Random rand = new Random();

	List<Card> remainingCards = new ArrayList<>(); //Will hold the cards that cannot be dealt due to "uneven" amount cards.

	//While its possible to deal to every player... Deal a card to every player.
	while ((deck.size() % players.size() == 0 || deck.size() > players.size()) && !this.deck.isEmpty()) {
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

	//Add the remaining cards to the remainingCards list.
	for (Card c : deck) {
	    remainingCards.add(c);
	}

	//System.out.println("Remaining cards after dealing: " + remainingCards.size());
	return remainingCards;
    }


}
