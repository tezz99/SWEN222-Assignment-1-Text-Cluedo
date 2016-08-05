package cluedo;

import java.util.List;

public class MoveInfo {
    private int movesLeft;
    private Position position;
    List<Position> visited;


    public MoveInfo(Position position, int movesLeft, List<Position> visited) {
	this.movesLeft = movesLeft;
	this.position = position;
	this.visited = visited;
    }

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
    public List<Position> getVisited() {
	return visited;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(List<Position> visited) {
	this.visited = visited;
    }


}
