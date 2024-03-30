public class RunningThread extends Thread {
    
    GUIManager gui = GUIManager.INSTANCE;
    
    /**
     * This method is run when the thread is started.
     * <p>
     * An updated grid is generated and displayed on the GUI then pauses for a time dicted by the game speed.
     */
    public void run() {
        while(true) {
            try {
                Thread.sleep(gui.getRunDelay());
                gui.displayNextTurn();
            } catch(InterruptedException e) {
                break;
            }
        }
    }
}
