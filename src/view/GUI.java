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
    enum PageNames {
        MAINMENU, GAME, GAMENDED, MAPEDITOR, SETTINGS, LANGUAGEDITOR, HOWTOPLAY;  //
    }

    /**
     *Set the fullscreen state.
     * @param fullscreen - true if you want to set fullscreen on (reset to default every time you change scene)
     */
    void setFullscreenMode(boolean fullscreen);

    /**
     * Checks the fullscreen state.
     * @return the fullscreen state as boolean
     */
    boolean isFullscreen();

    /**
     * Get stage sizes.
     * @return Pair (width, height)
     */
    Pair<Double, Double> getStageSizes();

    /**
     * gets the multiplier created to compensate user change the windows size.
     * @return the values needed for window scaling in the form x = width, y = height
     */
    Pair<Double, Double> getDimensionsMultipliers();

    /**
     * To be called before launch(), sets the controller for communication purposes.
     * @param controller - the controller that decides how the gui is going to react
     */
    void setController(Controller controller);

    /**
     * 
     * @return the controller associated to the current page
     */
    PageController getActivePageController();

    /**
     * loads the page whit matching name on the stage.
     * @param pageName - the name of the page you want to load
     */
    void loadPage(PageNames pageName);

    /**
     * gets the maximum screen sizes.
     * @return A Pair representing screen dimensions
     */
    Pair<Double, Double> getMaxScreenDimensions();

    /**
     * terminates the window.
     */
    void stop();

}
