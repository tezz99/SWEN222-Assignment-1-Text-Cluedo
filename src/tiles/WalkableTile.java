package tiles;

public class WalkableTile implements Tile {


    private boolean isOccupied = false;

    /**
     * Returns true if this tile is occupied.
     * @return
     */
    public boolean isOccupied() {
	return isOccupied;
    }

    /**
     * Set this tile as occupied or not.
     * @param isOccupied
     */
    public void setOccupied(boolean isOccupied) {
	this.isOccupied = isOccupied;
    }

    @Override
    public String toString() {
	return " ";
    }
}
