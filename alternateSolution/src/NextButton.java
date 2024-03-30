public class NextButton extends MyButton {

    private static final String CONTENTS = "Next";

    GUIManager gui = GUIManager.INSTANCE;

    public NextButton() {
        super(CONTENTS);
    }

    @Override
    public void execute() {
        gui.displayNextTurn();
    }
}