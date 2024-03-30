import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class ManualSubmitButton extends MyButton {

    GUIManager gui = GUIManager.INSTANCE;

    public ManualSubmitButton(String contents) {
        super(contents);

    }

    /**
     * Executed when the user clicks on 'Submit' button after defining parameteres
     * for a new game.
     * User's input for grid parameters in the text fields is passed to relevant
     * attributes and a new grid is created and displayed to the user.
     */
    @Override
    public void execute() {
        try {
            gui.setColumn(Integer.valueOf(gui.northPanel.colField.getText()));
            gui.setRow(Integer.valueOf(gui.northPanel.rowField.getText()));
            gui.setX(Integer.valueOf(gui.northPanel.xField.getText()));
            gui.setY(Integer.valueOf(gui.northPanel.yField.getText()));
            gui.setZ(Integer.valueOf(gui.northPanel.zField.getText()));

            if (gui.x <= 0 || gui.y <= 0 || gui.z <= 0 || gui.row <= 0 || gui.column <= 0) {
                throw new NumberFormatException();
            }

            if (!(gui.x <= gui.z && gui.z <= gui.y && gui.x < gui.y)) {
                throw new InputMismatchException("Parameters have to be x<=z<=y and x < y");
            }

            if (gui.row < 3 || gui.column < 3) {
                throw new InputMismatchException("The grid has to be at least a 3 by 3");
            }

            gui.setGrid(new Cell[gui.row][gui.column]);
            gui.game.initialiseCells();
            gui.addGridPanel();
            gui.showManualSubmitDisplay(false);
            gui.showGridPanel(true);
        }

        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gui.frame, "Only positive integers are allowed", "Format Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(gui.frame, e.getMessage(), "Parameter Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}