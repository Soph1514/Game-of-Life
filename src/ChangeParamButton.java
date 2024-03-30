import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class ChangeParamButton extends MyButton {

    GUIManager gui = GUIManager.INSTANCE;

    public ChangeParamButton(String contents) {
        super(contents);
    }

    /**
     * Executed when the user clicks on the 'Submit' button during the "pause" mode
     * in the game.
     * If the user chnaged x,y,z parameters, the values inputted in the text fields
     * are passed to the appropriate attributes.
     * The rules of the game change without altering the previous grid pattern.
     */
    @Override
    public void execute() {
        try {
            gui.setX(Integer.valueOf(gui.northPanel.xField.getText()));
            gui.setY(Integer.valueOf(gui.northPanel.yField.getText()));
            gui.setZ(Integer.valueOf(gui.northPanel.zField.getText()));

            if (gui.x <= 0 || gui.y <= 0 || gui.z <= 0) {
                throw new NumberFormatException();
            }

            if (!(gui.x <= gui.z && gui.z <= gui.y && gui.x < gui.y)) {
                throw new InputMismatchException("Parameters have to be x<=z<=y and x < y");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gui.frame, "Only positive integers are allowed", "Format Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(gui.frame, e.getMessage(), "Parameter Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}