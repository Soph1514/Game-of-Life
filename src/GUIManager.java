import java.awt.BorderLayout;
import javax.swing.ImageIcon;

import java.awt.Image;

public enum GUIManager {
    INSTANCE;

    Game game;
    int column;
    int row;
    int x;
    int y;
    int z;
    private int runDelay;
    private boolean play;

    MyFrame frame;
    GridPanel gridPanel;
    NorthPanel northPanel;
    String gliderInfo, blockInfo, oscillatorInfo;

    RunningThread runningThread;

    /**
     * Initialises the game.
     * <p>
     * The user is presented with a GUI frame to choose the game variation and start
     * playing.
     */
    public void construct() {
        game = new Game();
        frame = new MyFrame();
        northPanel = new NorthPanel();
        play = false;
        runDelay = 500;
        frame.add(northPanel, BorderLayout.NORTH);
        showCreateLoadButtons(true);
    }

    /**
     * Adds the graphical representation of the grid to the main frame.
     */
    public void addGridPanel() {
        gridPanel = new GridPanel();
        frame.add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Displays the next state of the grid to the user.
     * <p>
     * Loades the next state of the grid based on the neighbour number of cells and
     * repaints the grid accordingly.
     */
    public void displayNextTurn() {
        this.game.loadCurrentState();
        this.game.loadNextState();
        this.gridPanel.repaint();
        this.game.resetGrid();
    }

    /**
     * Rescales an image based on height and width.
     * 
     * @param filename file name of the image that will be rescaled
     * @param height   desired height of the image
     * @param width    desired width of the image
     * @return the rescaled image
     */
    public ImageIcon rescaleImg(String filename, int height, int width) {
        ImageIcon img = new ImageIcon(filename);
        Image newimg = img.getImage().getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    /**
     * Displays the JTextFields for the user to input x,y,z parameters, grid
     * dimensions and a 'Submit' button when true is passed to it.
     * 
     * @param show a boolean value that determines whether the relevant fields and
     *             the button are visible or hidden.
     */
    public void showManualSubmitDisplay(boolean show) {
        northPanel.showColRowFields(show);
        northPanel.showXYZFields(show);
        northPanel.optionLabel.setVisible(show);
        northPanel.manualSubmitButton.setVisible(show);
        northPanel.backButton.setVisible(show);
        if (show) {
            northPanel.optionLabel.setText("Set dimensions and x,y,z parameters for the game");
        }
    }

    /**
     * Displays the JTextField for the file the user wants to load and a 'Load'
     * button when true is passed to it.
     * 
     * @param show a boolean value that determines whether the relevant field and
     *             the button are visible or hidden.
     */
    public void showFileSubmitDisplay(boolean show) {
        northPanel.showLoadField(show);
        northPanel.fileSubmitButton.setVisible(show);
        northPanel.backButton.setVisible(show);
        if (show) {
            northPanel.optionLabel.setText("choose a file you want to load");
        }
    }

    /**
     * Displays the game grid and paints it based on the state of the cells when
     * true is passed to it.
     * 
     * @param show a boolean value that determines whether the game grid is visible
     *             or hidden.
     */
    public void showGridPanel(boolean show) {
        northPanel.showControls(show);
        northPanel.showSave(show);
        northPanel.backButton.setVisible(show);
        gridPanel.setVisible(show);
        northPanel.changeParamButton.setVisible(false);

        if (!show) {
            northPanel.slider.setVisible(false);
        }
        gridPanel.repaint();
    }

    /**
     * Displays buttons 'Create' and 'Load' for the user to choose their game option
     * when true is passed to it.
     * 
     * @param show boolean value that determines whether 'Create','Load' buttons are
     *             visible or hidden.
     */
    public void showCreateLoadButtons(boolean show) {
        northPanel.showCreateLoadButtons(show);
    }

    /**
     * Returns the time delay of the game when it is in "play" mode (the grid is
     * continously changing).
     * 
     * @return an integer value representing the delay of the simulation in
     *         millisecoonds.
     */
    public int getRunDelay() {
        return this.runDelay;
    }

    /**
     * Converts the value at the slide bar that controls the game speed into
     * appropriate delay in milliseconds and stores it in an attribute.
     * 
     * @param gameSpeed the integer value the user chooses at the slide bar to
     *                  change to speed of the simulation.
     */
    public void setRunDelay(int gameSpeed) {
        this.runDelay = (25000 / ((gameSpeed) + 25) - 140);
    }

    /**
     * Returns the current game grid from the class Game.
     * 
     * @return 2D array of Cell objects.
     */
    public Cell[][] getGrid() {
        return this.game.grid;
    }

    /**
     * Assigns a game grid to the according attribute in the class Game.
     * 
     * @param grid a 2D array of Cell objects.
     */
    public void setGrid(Cell[][] grid) {
        this.game.grid = grid;
    }

    /**
     * If 'true' is passed in, the "play" mode is executed.
     * The grid starts continuosuly changing based on the cell pattern. The speed
     * control slider, a pause button are displayed.
     * <p>
     * If 'false' is pased in, the "pause" mode is executed.
     * The text fields for the user to change x,y,z parameters, a 'Submit' button
     * and the button to get the next state of the grid are displayed.
     * 
     * @param play a boolean value that determines whether or not the "play" mode is
     *             executed.
     */
    public void setPlay(boolean play) {
        if (this.play != play) {
            this.play = play;
            this.northPanel.playPauseButton.setPlayIcon(play);
            if (play) {
                runningThread = new RunningThread();
                this.runningThread.start();
                this.changeParameters(false);
                this.northPanel.slider.setVisible(true);
                this.northPanel.nextButton.setVisible(false);

            } else {
                this.runningThread.interrupt();
                this.changeParameters(true);
                this.northPanel.slider.setVisible(false);
                this.northPanel.nextButton.setVisible(true);
            }
        }
    }

    /**
     * Displays JTextFields for x,y,z paramters and a 'Submit' button to confirm the
     * changes to these values the user just made.
     * 
     * @param show boolean value that determines whether the user is provided with
     *             an opportunity to change x,y,z parameters
     */
    public void changeParameters(boolean show) {
        northPanel.showXYZFields(show);
        northPanel.changeParamButton.setVisible(show);
    }

    /**
     * Returns true if the user is in "play" mode, false if in the "pause" mode.
     * 
     * @return a boolean value representing the mode of the game the user is
     *         currently in
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Sets a value to the column attribute in the GUIManager and Game classes.
     * 
     * @param column integer value representing the number of columns of the game
     *               grid.
     */
    public void setColumn(int column) {
        this.column = column;
        game.column = column;
    }

    /**
     * Sets a value to the row attribute in the GUIManager and Game classes.
     * 
     * @param row integer value representing the number of rows of the game grid.
     */
    public void setRow(int row) {
        this.row = row;
        game.row = row;
    }

    /**
     * Sets a value to the x attribute in the GUIManager and Game classes.
     * 
     * @param x integer value representing the lower limit of neighbours.
     */
    public void setX(int x) {
        this.x = x;
        game.x = x;
    }

    /**
     * Sets a value to the y attribute in the GUIManager and Game classes.
     * 
     * @param y integer value representing the upper limit of neighbours.
     */
    public void setY(int y) {
        this.y = y;
        game.y = y;
    }

    /**
     * Sets a value to the z attribute in the GUIManager and Game classes.
     * 
     * @param z integer value representing the exact number of neighbours for a dead
     *          cell to become alive.
     */
    public void setZ(int z) {
        this.z = z;
        game.z = z;
    }

    /**
     * @return a String value that holds information about the glider example to be
     *         displayed.
     */
    public String getGliderInfo() {
        String gliderInfo = "The glider is the smallest most-common spaceship in Game of Life. "
                + "Every 4 steps and 2 glide reflections, the glider returns to its original shape."
                + "\nA glide reflection is a geometric transformation that consists of a reflection "
                + "across a hyperplane and a translation in a direction parallel to that hyperplane, hence the name 'glider'."
                + "\nInterestingly, John Conway did not like the name and thought 'an ant walking across the plane' was a better alternative.";
        return gliderInfo;
    }

    /**
     * @return a String value that holds information about the block example to be
     *         displayed.
     */
    public String getBlockInfo() {
        String blockInfo = "The block is a 2x2 square and is the smallest static object. Its state does not change in the subsequent generations"
                + "\n since all cells have exactly 3 neighbours sp they are constantly alive. The block is often produced by 2-glider collisions.";
        return blockInfo;
    }

    /**
     * @return a String value that holds information about the oscillator example to
     *         be displayed.
     */
    public String getOscillatorInfo() {
        String oscInfo = "An oscillator is a pattern that repeats itself after a set number of iterations."
                + "\nThe oscillator in this example is called a 'blinker' and it is the only known one cell thick oscillator."
                + "\nThe blinker has a period of 2 but you can find a period 100 oscillator in the file list under the name 'centinal'";
        return oscInfo;
    }

    /**
     * @return a String value that holds information about the centinal example to
     *         be displayed.
     */
    public String getCentinalInfo() {
        String centinalInfo = "Centinal is a shuttle oscillator with a preiod of 100. Bill Gosper disovered it between 1973 and 1987"
                +
                "\nmaking centinal the first oscillator of this period to be discovered.";
        return centinalInfo;
    }

    /**
     * @return a String value that holds information about the spaceship example to
     *         be displayed.
     */
    public String getSpaceshipInfo() {
        String spaceshipInfo = "This is a 'Hammerhead' spaceship."
                + "\nIt was discovered by Hartmut Holzwart around 1990 and it travels orthogonally to the grid (at right angle).";
        return spaceshipInfo;
    }

    /**
     * @return a String value that holds information about the tile example to be
     *         displayed.
     */
    public String getTileInfo() {
        return "The final state of the game evokes the intricate beauty of traditional Portuguese tiles \n known as azulejos.";
    }
}
