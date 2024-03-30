import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Game {

    Cell[][] grid; // Starting grid

    protected int row; // dimensions of the grid
    protected int column; // dimensions of the grid
    protected int x; // lower limit of neighbours
    protected int y; // upper limit of neighbours
    protected int z; // exact number of neighbours when a dead cell becomes alive

    /**
     * Populates the grid, by iterating through the 2D array of Cell objects and
     * initialising each cell.
     * At this stage all the Cells are null.
     */
    public void initialiseCells() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    /**
     * Processes the current state of the grid and calculates the number of alive
     * cell neighbours for each cell
     * by incrementing the attribute neighbourNum for all the cells surrounding an
     * alive cell.
     */
    public void loadCurrentState() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell currentCell = grid[i][j];

                if (currentCell.isAlive()) {
                    // set neighbors

                    // Check by edges

                    // Check if current cell is on top edge
                    if (i == 0) {
                        grid[row - 1][j].incrementNeighbor(); // bottom row, same col
                        // Check if its in a corner

                        // Left (top) corner
                        if (j == 0) {
                            grid[i][column - 1].incrementNeighbor(); // left neighbor
                            grid[i][j + 1].incrementNeighbor(); // right neighbor
                            grid[row - 1][j + 1].incrementNeighbor(); // bottow-right (diag) neighbor
                            grid[row - 1][column - 1].incrementNeighbor(); // top-left (diag) neighbor
                            grid[i + 1][j].incrementNeighbor(); // bottom neighbor
                            grid[i + 1][j + 1].incrementNeighbor(); // bottom-right (diag) neighbor
                            grid[i + 1][column - 1].incrementNeighbor(); // bottom-left(diag) neighbor
                        }

                        // Right (top) corner
                        else if (j == (column - 1)) {
                            grid[i][0].incrementNeighbor(); // right neighbor
                            grid[i][j - 1].incrementNeighbor(); // left neighbor
                            grid[i + 1][j].incrementNeighbor(); // bottom neighbor
                            grid[i + 1][j - 1].incrementNeighbor(); // bottom-left (diag) neighbor
                            grid[i + 1][0].incrementNeighbor(); // bottom-right (diag) neighbor
                            grid[row - 1][j - 1].incrementNeighbor(); // top-left (diag) neighbor
                            grid[row - 1][0].incrementNeighbor(); // top-right (diag) neighbor
                        }

                        // Not a corner
                        else {
                            grid[i][j - 1].incrementNeighbor();
                            grid[i][j + 1].incrementNeighbor();
                            grid[row - 1][j - 1].incrementNeighbor();
                            grid[row - 1][j + 1].incrementNeighbor();
                            grid[i + 1][j].incrementNeighbor();
                            grid[i + 1][j - 1].incrementNeighbor();
                            grid[i + 1][j + 1].incrementNeighbor();
                        }
                    }

                    // Check if current cell is on bottom edge
                    else if (i == (row - 1)) {
                        grid[0][j].incrementNeighbor();

                        // Check if cell is on corners

                        // Left (bottom) corner
                        if (j == 0) {
                            grid[i][j + 1].incrementNeighbor(); // right neighbor
                            grid[i][column - 1].incrementNeighbor(); // left neighbor
                            grid[i - 1][j].incrementNeighbor(); // top neighbor
                            grid[i - 1][j + 1].incrementNeighbor(); // top-right (diag) neighbor
                            grid[i - 1][column - 1].incrementNeighbor(); // top left (diag) neighbor
                            grid[0][j + 1].incrementNeighbor(); // bottom right (diag) neighbor
                            grid[0][column - 1].incrementNeighbor(); // bottom left (diag) neighbor
                        }

                        // Right (bottom) corner
                        else if (j == (column - 1)) {
                            grid[i][j - 1].incrementNeighbor(); // left neighbor
                            grid[i][0].incrementNeighbor(); // right neighbor
                            grid[i - 1][j].incrementNeighbor(); // top neighbor
                            grid[i - 1][j - 1].incrementNeighbor(); // top left (diag) neighbor
                            grid[i - 1][0].incrementNeighbor(); // top right (diag) neighbor
                            grid[0][0].incrementNeighbor(); // bottom right (diag) neighbor
                            grid[0][j - 1].incrementNeighbor(); // bottom left (diag) neighbor
                        }

                        // Not a corner
                        else {
                            grid[i][j - 1].incrementNeighbor();
                            grid[i][j + 1].incrementNeighbor();
                            grid[i - 1][j].incrementNeighbor();
                            grid[i - 1][j - 1].incrementNeighbor();
                            grid[i - 1][j + 1].incrementNeighbor();
                            grid[0][j - 1].incrementNeighbor();
                            grid[0][j + 1].incrementNeighbor();
                        }
                    }

                    // Check if current cell is on left edge (but not in corner)

                    else if (j == 0) {
                        grid[i - 1][j].incrementNeighbor(); // top neighbor
                        grid[i - 1][j + 1].incrementNeighbor(); // top right (diag)
                        grid[i - 1][column - 1].incrementNeighbor(); // top left (diag)
                        grid[i][column - 1].incrementNeighbor(); // left neighbor
                        grid[i][j + 1].incrementNeighbor(); // right neighbor
                        grid[i + 1][j].incrementNeighbor(); // bottom neighbor
                        grid[i + 1][j + 1].incrementNeighbor(); // right bottom neighbor (diag)
                        grid[i + 1][column - 1].incrementNeighbor(); // left bottom neighbor (diag)
                    }

                    // Check if current cell is on right edge (but no in corner)

                    else if (j == (column - 1)) {
                        grid[i - 1][j].incrementNeighbor();// top neighbor
                        grid[i - 1][j - 1].incrementNeighbor(); // top left neighbor (diag)
                        grid[i - 1][0].incrementNeighbor(); // top right neighbor (diag)
                        grid[i][j - 1].incrementNeighbor(); // left neighbor
                        grid[i][0].incrementNeighbor(); // right neighbor
                        grid[i + 1][j - 1].incrementNeighbor(); // bottom left neighbor (diag)
                        grid[i + 1][j].incrementNeighbor(); // bottom neighbor
                        grid[i + 1][0].incrementNeighbor();
                    }

                    // If not on edges
                    else {
                        grid[i - 1][j - 1].incrementNeighbor(); // top left
                        grid[i - 1][j].incrementNeighbor(); // top
                        grid[i - 1][j + 1].incrementNeighbor(); // top right
                        grid[i][j - 1].incrementNeighbor(); // left
                        grid[i][j + 1].incrementNeighbor(); // right
                        grid[i + 1][j - 1].incrementNeighbor(); // bottom left
                        grid[i + 1][j].incrementNeighbor(); // bottom
                        grid[i + 1][j + 1].incrementNeighbor(); // bottom right
                    }

                }
            }
        }
    }

    /**
     * Iterates through the grid and changes cell's state depending on the
     * neighbours and set parameters.
     */
    public void loadNextState() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell currentCell = grid[i][j];
                if (currentCell.isAlive()) {
                    if (currentCell.getNeighborNum() < x || currentCell.getNeighborNum() > y) {
                        currentCell.setAlive(false);
                    }
                } else {
                    if (currentCell.getNeighborNum() == z) {
                        currentCell.setAlive(true);
                    }
                }
            }
        }

    }

    /**
     * Ensures that each cell has no set neighbors from the previous turn.
     * Is run each time before the next state of the grid is loaded.
     */
    public void resetGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setNeighborNum(0);
            }
        }
    }

    /**
     * Saves the current state of the grid to a file into the directory LoadFiles.
     * The first row of the file are the parameters: x,y,z and the grid dimensions
     * separated by a comma to preserve the rules of that particular game.
     * Rows with only dead cells are represented as '!' so save space.
     * 
     * @param x        inputted by the user using a JTextField when creating a game
     * @param y        inputted by the user using a JTextField when creating a game
     * @param z        inputted by the user using a JTextField when creating a game
     * @param row      inputted by the user using a JTextField when creating a game
     * @param col      inputted by the user using a JTextField when creating a game
     * @param fileName inputted by the user using a JTextField during a game
     * @throws IOException
     */
    public void saveGrid(int x, int y, int z, int row, int col, String fileName) throws IOException {

        File file = new File("LoadFiles" + "/" + fileName);
        PrintWriter writer = new PrintWriter(file);
        writer.write(x + "," + y + "," + z + "," + row + "," + col + "\n");
        boolean liveRow = false;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {
            liveRow = false;
            for (int j = 0; j < grid[i].length; j++) {
                Cell currentCell = grid[i][j];
                if (currentCell.isAlive()) {
                    output.append("o");
                    liveRow = true;
                } else {
                    output.append(".");
                }
            }
            if (!liveRow) { // if entire row is dead
                output.replace(0, col, "!"); // '!' signfies entire row is dead
            }
            output.append("\n");// new line
            writer.write(output.toString());
            output.setLength(0); // empties the stringbuilder
        }
        writer.close();
    }

    /**
     * Reads the first line of a file that stores an encoded grid to get the
     * parameters x,y,z and the grid dimensions.
     * 
     * @param fileName the file chosen by the user from the list of files in the
     *                 LoadFiles directory.
     * @return integer array that holds x,y,z,row,column
     * @throws IOException
     */
    public int[] readParameters(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("LoadFiles" + "/" + fileName));
        String firstLine = reader.readLine();
        String[] values = firstLine.split(",");
        int[] parameters = { Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                Integer.parseInt(values[3]), Integer.parseInt(values[4]) };
        reader.close();
        return parameters;
    }

    /**
     * Reads the file with the encoded grid character by character and changes the
     * state of the corresponding cells accordingly.
     * Only checks for alive cells (those encoded with 'o') since the grid is
     * generated with cells set to their default value (dead).
     * 
     * @param fileName the file chosen by the user from the list of files in the
     *                 LoadFiles directory.
     * @throws IOException
     */
    public void loadGrid(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("LoadFiles" + "/" + fileName));
        reader.readLine(); // ignore the first line
        String line = "";
        int i = 0;
        while ((line = reader.readLine()) != null) {
            int j = 0;
            for (int a = 0; a < line.length(); a++) {
                if (line.charAt(a) == 'o') {
                    grid[i][j].setAlive(true);
                }
                j++;
            }
            i++;
        }
        reader.close();
    }
}
