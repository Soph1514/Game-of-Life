import javax.swing.JTextField;

public class MyTextField extends JTextField {

    /**
     * creates a JTextField that is set to invisible
     * 
     * @param contents the text to be displayed
     */
    public MyTextField(String contents) {
        super(contents);
        this.setVisible(false);
    }
}
