package cluedo;

public class Position {

    private int posX;
    private int posY;

    public Position(int posX, int posY) {
	this.setPosX(posX);
	this.setPosY(posY);
    }

    // Getters and setters
    public int getPosX() {
	return posX;
    }

    public void setPosX(int posX) {
	this.posX = posX;
    }

    public int getPosY() {
	return posY;
    }

    public void setPosY(int posY) {
	this.posY = posY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Position)) {
	    return false;
	}
	Position other = (Position) obj;

	if (posX != other.posX) {
	    return false;
	}
	if (posY != other.posY) {
	    return false;
	}
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + posX;
	result = prime * result + posY;
	return result;
    }

}
