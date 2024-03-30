import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

public class DropDown extends JComboBox<Object> {
    GUIManager gui = GUIManager.INSTANCE;

    public DropDown(List<String> presetFiles) {
        super(presetFiles.toArray());
        this.setVisible(false);
        this.setEditable(false);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        };
        this.addActionListener(listener);
    }

    // On click
    public void execute() {
        gui.northPanel.loadField.setText(getSelectedItem().toString() + ".gol");
    }
}
