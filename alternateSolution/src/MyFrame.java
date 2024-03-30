import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MyFrame extends JFrame {
    /**
     * Initialises the main window frame of the game.
     */
    public MyFrame() {
        super("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(false);
    }
}