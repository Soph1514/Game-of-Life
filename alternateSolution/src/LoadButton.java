public class LoadButton extends MyButton {

    private static final String CONTENTS = "Load";

    GUIManager gui = GUIManager.INSTANCE;

    public LoadButton() {
        super(CONTENTS);
    }

    /**
     * Executed when the user clicks on 'Load' button
     * Initial option window and user is presented with an opportunity to load an
     * existing game file.
     */
    @Override
    public void execute() {
        gui.showCreateLoadButtons(false);
        gui.showFileSubmitDisplay(true);
    }
}
