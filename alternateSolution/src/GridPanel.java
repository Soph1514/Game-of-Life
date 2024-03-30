import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

    public static GUIManager gui = GUIManager.INSTANCE;
    private Double cellWidth, cellHeight;

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
        Graphics2D g2d = (Graphics2D) g;
        setDimensions();

        for (int i = 0; i < gui.getGrid().length; i++) {
            for (int j = 0; j < gui.getGrid()[i].length; j++) {
                Cell currCell = gui.getGrid()[i][j];
                g2d.setColor(Color.BLACK);
                g2d.draw(new Rectangle2D.Double(j * cellWidth, i * cellHeight, cellWidth, cellHeight));
                if (currCell.isAlive()) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g2d.fill(new Rectangle2D.Double(j * cellWidth, i * cellHeight, cellWidth, cellHeight));
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
        cellWidth = getWidth() * 1.0 / gui.column;
        cellHeight = getHeight() * 1.0 / gui.row;
    }

    /**
     * @return Double value representing the height of an individual cell in the
     *         grid.
     */
    public Double getCellHeight() {
        return this.cellHeight;
    }

    /**
     * @return Double value representing the width of an individual cell in the
     *         grid.
     */
    public Double getCellWidth() {
        return this.cellWidth;
    }
}
