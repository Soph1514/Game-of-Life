import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridListener implements MouseListener {

    GUIManager gui = GUIManager.INSTANCE;
    GridPanel panel;

    public GridListener(GridPanel panel) {
        this.panel = panel;
    }

    /**
     * Executed when the user mouse clicks on a cell in the grid.
     * The cell which was clicked changes state and hence colour (becomes black if
     * it was dead, white if it was alive).
     * If the user is in the "play" mode, the continous simulation is stopped on the
     * click.
     * 
     * @param e MouseEvent(the click)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX();
        int yCoord = e.getY();

        int colInGrid = xCoord / panel.getCellWidth();
        int rowInGrid = yCoord / panel.getCellHeight();
        if (colInGrid < gui.column && rowInGrid < gui.row) {

            if (gui.getGrid()[rowInGrid][colInGrid].isAlive()) {
                gui.getGrid()[rowInGrid][colInGrid].setAlive(false);
            }

            else if (!gui.getGrid()[rowInGrid][colInGrid].isAlive()) {
                gui.getGrid()[rowInGrid][colInGrid].setAlive(true);
            }
            gui.setPlay(false);
            panel.repaint(); // refreshes the grid
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
