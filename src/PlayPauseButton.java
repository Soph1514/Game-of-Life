import java.awt.Dimension;

import javax.swing.ImageIcon;

public class PlayPauseButton extends MyButton {

    ImageIcon playImg;
    ImageIcon pauseImg;

    GUIManager gui = GUIManager.INSTANCE;

    /**
     * Initialises the pause and the play image buttons.
     * The images are first rescaled.
     */
    public PlayPauseButton() {
        super("");
        setPreferredSize(new Dimension(50, 50));
        this.playImg = gui.rescaleImg("play.jpg", 50, 50);
        this.pauseImg = gui.rescaleImg("pause.png", 50, 50);
        this.setIcon(this.playImg);
    }

    /**
     * Executed when the user clicks on either the play button or the pause button.
     * <p>
     * When the play button is clicked, the user is put into "play" mode.
     * When the pause button is clicked, the user is put into "pause" mode.
     */
    @Override
    public void execute() {
        if (gui.getPlay()) {
            gui.setPlay(false);
        } else {
            gui.setPlay(true);
        }
    }

    public void setPlayIcon(boolean play) {
        if (play) {
            this.setIcon(this.pauseImg);
        } else {
            this.setIcon(this.playImg);
        }
    }
}
