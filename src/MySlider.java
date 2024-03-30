import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MySlider extends JSlider {
    GUIManager gui = GUIManager.INSTANCE;

    public MySlider() {
        super();
        this.setVisible(false);

        majorTickSpacing = 10;
        minorTickSpacing = 5;
        setPaintLabels(true);
        setPaintTicks(true);
        setPaintTrack(true);
        setMaximum(100);
        setMinimum(0);
        setValue(10); // default value
        setPreferredSize(new Dimension(300, 80));

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                execute();
            }

        };
        this.addChangeListener(changeListener);
    }

    public void execute() {
        if (this.getValue() == 0) {
            this.setValue(1);
        }
        gui.setRunDelay(this.getValue());
    }
}
