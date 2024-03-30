import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileSubmitButton extends MyButton {

    GUIManager gui = GUIManager.INSTANCE;

    public FileSubmitButton(String contents) {
        super(contents);
    }

    /**
     * Executed when the user clicks on 'Load' button after selecting a file to be
     * loaded to the game.
     * The game parameters and grid dimensions are extracted from the file and
     * passed to the relevant attributes.
     * The rest of the file is read and the grid is contructed and painted
     * accordingly.
     */
    @Override
    public void execute() {
        String fileName = gui.northPanel.loadField.getText();// getting the file name the user has selected from the
                                                             // text field.
        // adding .gol extension to the name
        if (fileName.length() > 4) {
            if (!fileName.substring(fileName.length() - 4).equals(".gol")) {
                fileName += ".gol";
            }
        } else {
            fileName += ".gol";
        }

        try {
            // printing out additional information if the user chose one of the examples
            switch (fileName) {
                case "glider.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getGliderInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "block.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getBlockInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "oscillator.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getOscillatorInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "centinal.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getCentinalInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "spaceship.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getSpaceshipInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Tile.gol":
                    JOptionPane.showMessageDialog(gui.frame, gui.getTileInfo(), "Pattern Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }

            // getting x,y,z,row, column values from the file
            int[] parameters = gui.game.readParameters(fileName);
            gui.setX(parameters[0]);
            gui.setY(parameters[1]);
            gui.setZ(parameters[2]);
            gui.setRow(parameters[3]);
            gui.setColumn(parameters[4]);

            // creating and initalising the grid
            gui.setGrid(new Cell[gui.row][gui.column]);
            gui.game.initialiseCells();
            gui.game.loadGrid(fileName);
            // displaying the grid to the user with relevant controls
            gui.addGridPanel();
            gui.showFileSubmitDisplay(false);
            gui.showGridPanel(true);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(gui.frame, "File does not exist", "File Name Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
