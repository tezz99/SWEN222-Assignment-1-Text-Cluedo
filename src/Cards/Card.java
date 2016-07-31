package Cards;

public abstract class Card {

    private final String name; //Name of card.

    public Card (String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
    }

    @Override
    public String toString() {
	return this.getName();
    }
}
