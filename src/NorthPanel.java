import java.util.List;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanel extends JPanel {

    VanishingField xField, yField, zField, rowField, colField, loadField, saveField;
    JLabel optionLabel;
    ManualSubmitButton manualSubmitButton;
    FileSubmitButton fileSubmitButton;
    ChangeParamButton changeParamButton;
    CreateButton createButton;
    LoadButton loadButton;
    NextButton nextButton;
    SaveButton saveButton;
    DropDown dropDownMenu;
    PlayPauseButton playPauseButton;
    MySlider slider;
    BackButton backButton;

    /**
     * Initialises the main panel with all the controls (buttons, text fields) in
     * the game.
     */
    public NorthPanel() {
        this.backButton = new BackButton();
        this.add(backButton);

        this.optionLabel = new JLabel();
        this.add(optionLabel);

        this.xField = new VanishingField("X");
        this.add(xField);
        this.yField = new VanishingField("Y");
        this.add(yField);
        this.zField = new VanishingField("Z");
        this.add(zField);
        this.rowField = new VanishingField("Rows");
        this.add(rowField);
        this.colField = new VanishingField("Columns");
        this.add(colField);
        this.loadField = new VanishingField("File name");
        this.add(loadField);

        this.manualSubmitButton = new ManualSubmitButton("Submit");
        this.add(manualSubmitButton);
        this.changeParamButton = new ChangeParamButton("Submit");
        this.add(changeParamButton);
        this.fileSubmitButton = new FileSubmitButton("Submit");
        this.add(fileSubmitButton);
        this.createButton = new CreateButton();
        this.add(createButton);
        this.loadButton = new LoadButton();
        this.add(loadButton);

        this.nextButton = new NextButton();
        this.add(nextButton);
        this.playPauseButton = new PlayPauseButton();
        this.add(playPauseButton);

        this.dropDownMenu = new DropDown(addFilesToDropDown("LoadFiles"));
        this.add(dropDownMenu);

        this.slider = new MySlider();
        this.add(slider);

        this.saveField = new VanishingField("File name");
        this.add(saveField);
        this.saveButton = new SaveButton();
        this.add(saveButton);

    }

    /**
     * Text fields that accept values for x,y,z parameters are displayed if true is
     * passed and are hidden if false is passed.
     * 
     * @param show a boolean value that determines whether x,y,z JTextFields are
     *             visible or hidden.
     */
    public void showXYZFields(boolean show) {
        zField.setVisible(show);
        xField.setVisible(show);
        yField.setVisible(show);
    }

    /**
     * Text fields that accept values for the grid dimensions are displayed if true
     * is passed and are hidden if false is passed.
     * 
     * @param show a boolean value that determines whether row, column JTextFields
     *             are visible or hidden.
     */
    public void showColRowFields(boolean show) {
        colField.setVisible(show);
        rowField.setVisible(show);
    }

    /**
     * Displays a drop down menu with possible files to load, a 'Load' button and a
     * text field for the file name if true is passed.
     * 
     * @param show a boolean value that determines whether the option for loading a
     *             file was chosen and hence if relevant fields and buttons are
     *             visible.
     */
    public void showLoadField(boolean show) {
        optionLabel.setText("Choose a file that you want to load");
        optionLabel.setVisible(show);
        loadField.setVisible(show);
        dropDownMenu.setVisible(show);
    }

    /**
     * Displays the inital game window for the user to choose between creating a new
     * game or loading an existing file if true is passed.
     * 
     * @param show a boolean value that determines whether a label with a message
     *             and 'Create', 'Load' buttons are visible or hidden.
     */
    public void showCreateLoadButtons(boolean show) {
        optionLabel.setVisible(show);
        createButton.setVisible(show);
        loadButton.setVisible(show);
        if (show) {
            optionLabel.setText("Would you like to create a new game or load an existing one?");
        }
    }

    /**
     * Displays the 'Next' button and the "play/pause" button if true is passed.
     * 
     * @param show a boolean value that determines whether the controls are visible
     *             or hidden.
     */
    public void showControls(boolean show) {
        this.nextButton.setVisible(show);
        this.playPauseButton.setVisible(show);
    }

    /**
     * Displays a JTextFeild where the user enters a file name and a 'Save' button
     * if true is passed.
     * 
     * @param show a boolean value that determines whether the user is provided an
     *             option to save thier current game grid to a file.
     */
    public void showSave(boolean show) {
        this.saveButton.setVisible(show);
        this.saveField.setVisible(show);
    }

    /**
     * Gets all the files from a directory and allocates their names wihout .gol
     * extension to an ArrayList.
     * These files will be added to the drop down menu for the user to choose from
     * when loading a game file.
     * 
     * @param directoryName the name of the directory the files in which will be
     *                      added to the drop down menu.
     * @return a List of type String with all the file names in the passed
     *         directory.
     */
    public List<String> addFilesToDropDown(String directoryName) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File file : files) {
            String name = file.getName();
            name = name.substring(0, name.length() - 4);
            fileList.add(name);
        }
        return fileList;
    }
}
