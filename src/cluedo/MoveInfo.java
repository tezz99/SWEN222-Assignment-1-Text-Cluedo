package cluedo;

import java.util.HashSet;
import java.util.Set;

/**
 * Used for the path finding implementation. Represents an ongoing move...
 * @author tezz9
 *
 */
public class MoveInfo {
    private int movesLeft;
    private Position position;
    Set<Position> visited;


    public MoveInfo(Position position, int movesLeft, Set<Position> visited) {
	this.movesLeft = movesLeft;
	this.position = position;
	this.visited = new HashSet<>();

	this.visited.addAll(visited);
    }


    /* Getters and Setters */
    public int getMovesLeft() {
	return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
	this.movesLeft = movesLeft;
    }

    public Position getPosition() {
	return position;
    }

    public void setPosition(Position position) {
	this.position = position;
    }

    /**
     * @return the visited
     */
    public Set<Position> getVisited() {
	return this.visited;
    }

}
