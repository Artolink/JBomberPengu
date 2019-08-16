package view;

import controller.Controller;
import model.utils.Pair;

/**
 * 
 */
public interface GUI {

    /**
     *The pages available.
     */
    public enum PageNames {
        MAINMENU, GAME, GAMENDED, SETTINGS, MAPEDITOR, LANGUAGEDITOR;
    }

    /**
     *Set the fullscreen state.
     * @param fullscreen - true if you want to set fullscreen on (reset to default every time you change scene)
     */
    public void setFullscreenMode(boolean fullscreen);

    /**
     * 
     * @return the fullscreen state as boolean
     */
    public boolean getFullscreenState();

    /**
     * Get stage sizes.
     * @return Pair (width, height)
     */
    public Pair<Double, Double> getStageSizes();

    /**
     * 
     * @return the values needed for window scaling in the form x = width, y = height
     */
    public Pair<Double, Double> getDimensionsMultipliers();

    /**
     * To be called befor launch(), sets the controller for communication purposes.
     */
    public void setController(Controller controller);

    /**
     * 
     * @return the controller associated to the current page
     */
    public PageController getActivePageController();

    /**
     * loads the page whit matching name on the stage.
     * @param pageName - the name of the page you want to load
     */
    public void loadPage(PageNames pageName);

    /**
     * Closes the game, equivalent to System.exit(0).
     */
    public void closeGame();

}
