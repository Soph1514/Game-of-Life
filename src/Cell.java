// This class represents one single cell within the game grid
public class Cell {

    private int neighborNum;
    private boolean alive;

    /**
     * Returns the number of alive cell neighbours the cell has
     * 
     * @return neighbourNum
     */
    public int getNeighborNum() {
        return neighborNum;
    }

    /**
     * Returns the state of the cell
     * 
     * @return true if the cell is alive, false if the cell is dead.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Assigns the attribute neighbourNum to a particular integer value.
     * 
     * @param neighborNum the number of alive cell neighbours the cell has
     */
    public void setNeighborNum(int neighborNum) {
        this.neighborNum = neighborNum;
    }

    /**
     * Increments the number of alive cell neighbours by 1.
     */
    public void incrementNeighbor() {
        neighborNum++;
    }

    /**
     * Assigns the cell's state to a boolean value
     * 
     * @param alive boolean value representing the cell's state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}