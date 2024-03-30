import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class SaveButton extends MyButton {

    private static final String CONTENTS = "Save";

    GUIManager gui = GUIManager.INSTANCE;

    public SaveButton() {
        super(CONTENTS);
    }

    /**
     * Executed when the user clicks on 'Save' button after typing a file name they
     * want to save the current grid state in.
     * The button is visible both in "pause" and "play" mode.
     */
    @Override
    public void execute() {
        try {
            String fileName = gui.northPanel.saveField.getText();
            if (fileName.length() > 4) {
                if (!fileName.substring(fileName.length() - 4).equals(".gol")) {
                    fileName += ".gol";
                }
            } else {
                fileName += ".gol";
            }

            // Check if inputted file name is one of the "Examples"
            // Add other example files to this list -->
            List<String> reservedFileNames = Arrays.asList("block.gol", "glider.gol", "oscillator.gol", "spaceship.gol",
                    "centinal.gol", "Tile.gol");
            if (reservedFileNames.contains(fileName)) {
                JOptionPane.showMessageDialog(gui.frame,
                        "This file name is reserved for the examples. Please choose another one.", "File Name Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                gui.game.saveGrid(gui.x, gui.y, gui.z, gui.row, gui.column, fileName);
                gui.northPanel.saveField.setText(null); // clear the field
            }

            gui.northPanel.saveField.setText("File name");
            gui.northPanel.dropDownMenu.addItem(fileName.substring(0, fileName.length() - 4));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}