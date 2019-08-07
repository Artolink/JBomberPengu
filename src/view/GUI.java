package view;

import java.util.Map;

import javafx.scene.Scene;
import model.language.ApplicationStrings;
import model.utils.Pair;

public interface GUI {

    /**
     * Needed to reach the translator class.
     * @return if not initilized returns a new ApplicationStrings, else it returns the instance already created
     */
    public ApplicationStrings getTranslator();

    /**
     * To be called when a new Scene has to be loaded.
     * @param scene - the Scene you want to load.
     */
    public void switchScene(Scene scene);

    /**
     * To be called when a new Scene has to be loaded and are
     * required some fixed dimensions.
     * @param scene - the Scene you want to load.
     * @param width - your stage width
     * @param height - your stage height
     */
    public void switchScene(Scene scene, Double width, Double height);

    /**
     * To be called when a new Scene has to be loaded and are
     * required some fixed dimensions and you need to specify
     * the fullscreen visualization.
     * @param scene - the Scene you want to load.
     * @param width - your stage width
     * @param height - your stage height
     * @param fullscreen - true if you want to set fullscreen on (reset to default every time you change scene)
     */
    public void switchScene(Scene scene, Double width, Double height, boolean fullscreen);

    /**
     * Get stage sizes.
     * @return Pair (width, height)
     */
    public Pair<Double, Double> getSizes();

    /**
     * 
     * @return the values needed for window scaling in the form x = width, y = height
     */
    public Pair<Double, Double> getDimensionsMultipliers();

    /**
     * To be called only one time during startup, sets the controller for communication purposes.
     */
    public void setController();

    /**
     * add a new Page.
     * @param c - the page you want to add
     * @return the page you just added
     */
    public Page addPage(Page c);

    /**
     * Gets the page whit matching name.
     * @param name - the name of the page you want
     * @return the matching page
     * @exception exception can throw something if there is no match    //TODO
     */
    public Page getPage(String name);

    /**
     * Gets all the pages added. 
     * @return Map of pages
     */
    public Map<String, Page> getPages();

    /**
     * loads the page whit matching name on the stage.
     * @param name - the name of the page you want to load
     */
    public void loadPage(String name);

    /**
     * Closes the game, equivalent to System.exit(0)
     */
    public void closeGame();


}
