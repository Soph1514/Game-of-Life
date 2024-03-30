import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class VanishingField extends MyTextField {

    /**
     * Creates an invisible JTextField.
     * <p>
     * When gains keyboard's focus (the user clinks on the tesxt field) will stop
     * displaying text, then when loses focus will display again.
     * 
     * @param contents the text to be displayed
     */
    public VanishingField(String contents) {
        super(contents);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                setText("");
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                if (getText().isEmpty())
                    setText(contents);
            }
        });
    }
}
