package Tiles;

public class WalkableTile implements Tile {


    private boolean isOccupied = false;

    public boolean isOccupied() {
	return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
	this.isOccupied = isOccupied;
    }

    @Override
    public String toString() {
	return " ";
    }
}
