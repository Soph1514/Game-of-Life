import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyButton extends JButton {

    /**
     * 
     * @param contents
     */
    public MyButton(String contents) {
        super(contents);
        this.setVisible(false);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        };
        this.addActionListener(listener);
    }

    /**
     * the method that is called when this button is pressed
     * <p>
     * to be overriden
     */
    public void execute() {
    }
}