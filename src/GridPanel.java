import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

    public static GUIManager gui = GUIManager.INSTANCE;
    private int cellWidth, cellHeight;

    public GridPanel() {
        addMouseListener(new GridListener(this));
    }

    /**
     * Draws and paints all the cells of the game grid.
     * <p>
     * Iterates through the 2D array of Cell objects that represent the grid and
     * colours the cell black if it is alive and white if it is dead.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDimensions();

        for (int i = 0; i < gui.getGrid().length; i++) {
            for (int j = 0; j < gui.getGrid()[i].length; j++) {
                Cell currCell = gui.getGrid()[i][j];
                g.setColor(Color.BLACK);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                if (currCell.isAlive()) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth-1, cellHeight-1);
            }
        }

    }

    /**
     * Sets the dimensions of the graphical grid.
     * <p>
     * Cell width is calculated by dividing the grid width by the number of columns.
     * Cell height is calculated by dividing the grid height by the number of rows.
     */
    public void setDimensions() {
        cellWidth = getWidth() / gui.column;
        cellHeight = getHeight() / gui.row;
    }

    /**
     * @return Double value representing the height of an individual cell in the
     *         grid.
     */
    public int getCellHeight() {
        return this.cellHeight;
    }

    /**
     * @return Double value representing the width of an individual cell in the
     *         grid.
     */
    public int getCellWidth() {
        return this.cellWidth;
    }
}
