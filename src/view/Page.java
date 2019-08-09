package view;

import javafx.scene.Scene;

public abstract class Page extends GUIImpl {

    /**
     * Gets the page name.
     * @return  The name of this page.
     */
    public abstract String getPageName();

    /**
     * Gets the page scene.
     * @return The scene of this page.
     */
    public abstract Scene getScene();

    /**
     * Loads this Page on the stage.
     */
    public void load() {
        loadPage(this.getPageName());
    }

}
