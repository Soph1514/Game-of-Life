public class BackButton extends MyButton {

    private static final String CONTENTS = "Home";

    GUIManager gui = GUIManager.INSTANCE;

    public BackButton() {
        super(CONTENTS);
    }

    @Override
    public void execute() {
        gui.setPlay(false);
        gui.showManualSubmitDisplay(false);
        gui.showFileSubmitDisplay(false);
        if (gui.gridPanel != null) {
            gui.showGridPanel(false);
        }
        gui.showCreateLoadButtons(true);
    }
}
