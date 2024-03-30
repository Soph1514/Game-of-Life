
public class CreateButton extends MyButton {

    private static final String CONTENTS = "Create";

    GUIManager gui = GUIManager.INSTANCE;

    public CreateButton() {
        super(CONTENTS);
    }

    /**
     * Executed when the user clicks on 'Create' button
     * Initial option window and user is presented with an opportunity to create a
     * new game.
     */
    @Override
    public void execute() {
        gui.showCreateLoadButtons(false);
        gui.showManualSubmitDisplay(true);
    }
}
