package view;

import javafx.scene.Scene;

/**
 * 
 */
public abstract class GuiChild {

    private MainGUI mainGUI;

    /**
     * @param mainGUI the MainGUI who will receive event notification
     */
    public void setMainGUI(final MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    /**
     * @return the MainGUI who will receive event notification
     * @throws NullPointerException if not set to an acceptable value
     */
    public MainGUI getMainGUI() throws NullPointerException {
        try {
            return this.mainGUI;
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    /**Scene creator.
     * @return the requested Scene
     * @throws Exception generic
     */
    public abstract Scene start() throws Exception;

}
