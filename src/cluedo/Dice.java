package cluedo;

import java.util.Random;

/**
 * Represents a dice.
 * @author Pritesh R. Patel
 *
 */
public class Dice {

    /**
     * Simulates the roll of a dice and returns a random number between 1 and 6.
     * @return
     */
    public int roll() {
	Random rand = new Random();
	int roll = rand.nextInt(6) + 1;
	System.out.println(roll);
	return roll;
    }
}
