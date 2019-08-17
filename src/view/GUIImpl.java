package view;

import java.io.File;
import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.language.ApplicationStrings;
import model.utils.Pair;

/**
 *  The Application entry point, contains the Page control mechanism.
 */
public class GUIImpl extends Application implements GUI {

    private static final String ICON_FILE = "view" + File.separator + "penguin.png";

    private Pair<Double, Double> preferredSizes;
    private Pair<Double, Double> modifiedSizes;

    private Rectangle2D primaryScreenBounds;
    //private static Rectangle2D actualFrame;

    private boolean initialized = true; //TODO change to false

    //private boolean fullscreen = false;

    private Stage stage;

    private Page page;

    private Controller controller;

 // application creation methods ------------------------------------------------------------------------------------------- 

    /**
     * Application entry point.
     */
    public void launch() {
        if (initialized) {
            launch(GUIImpl.class);
        }
    }

 // public methods -------------------------------------------------------------------------------------------

    /*
     * called automatically from Application. Used for configuration purposes.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set up the stage whit some settings
        setUpStage();

        //Load main menu FXML as default
        loadPage(PageNames.MAINMENU);

        this.stage.show();
    }

    @Override
    public final void setFullscreenMode(final boolean fullscreen) {
        this.stage.setFullScreen(fullscreen);
        //this.fullscreen = fullscreen;
    }

    @Override
    public final boolean getFullscreenState() {
        return this.stage.isFullScreen();
        //return this.fullscreen;
    }

    @Override
    public final Pair<Double, Double> getStageSizes() {
        return new Pair<>(this.stage.getWidth(), this.stage.getHeight());
    }

    @Override
    public final Pair<Double, Double> getDimensionsMultipliers() {
        if (preferredSizes == null) {
            preferredSizes = getStageSizes();
        }
        if (modifiedSizes == null) {
            modifiedSizes = getStageSizes();
        }

        if (preferredSizes.getX() == 0 || preferredSizes.getY() == 0) {
            return new Pair<Double, Double>(1d, 1d);
        }
        return new Pair<Double, Double>(modifiedSizes.getX() / preferredSizes.getX(), modifiedSizes.getY() / preferredSizes.getY());
    }

    @Override
    public final void setController(final Controller controller) {
        // TODO Are the type right?
        this.controller = controller;
        initialized = true;
    }

    @Override
    public final PageController getActivePageController() {
        return getCurrentPage().getPageController();
    }

    @Override
    public final void loadPage(final PageNames pageName) {

        switch (pageName) {
            case MAINMENU:
                page = (Page) new FxmlFileLoader("view" + File.separator + "mainMenu", "MainMenu");
                break;
            case GAME:
                page = (Page) new FxmlFileLoader("view" + File.separator + "game", "Game");
                break;
            case GAMENDED:
                page = (Page) new FxmlFileLoader("view" + File.separator + "mainMenu", "GameEnded");
                break;
            case SETTINGS:
                page = (Page) new FxmlFileLoader("view" + File.separator + "settings", "SettingsMenu");
                break;
            //case MAPEDITOR:
                //page = (Page) new FxmlFileLoader("view" + File.separator + "mapEditor", "MapEditor");
                //break;
            case LANGUAGEDITOR:
                page = (Page) new FxmlFileLoader("view" + File.separator + "multiLang", "MultilangView");
                break;
            default:
                System.out.println("404 Page not found");
                break;
        }

        switchScene(page.getScene());
    }

    // Protected methods -------------------------------------------------------------------------------------------

    /**
     * 
     * @return The scene currently displayed.
     */
    protected final Scene getCurrentScene() {
        return this.stage.getScene();
    }

    /**
     * 
     * @return the controller that controls this
     */
    protected final Controller getController() {
        return this.controller;
    }

    /**
     * Needed to reach the translator class.
     * @return ApplicationStrings
     */
    protected final ApplicationStrings getTranslator() {
        return getController().getTranslator();
    }

    /**
     * @return The currently loaded page
     */
    protected final Page getCurrentPage() {
        return page;
    }

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * To be called when a new Scene has to be loaded.
     * @param scene - the Scene you want to load.
     */
    private void switchScene(final Scene scene) {
        if (scene != null) {
            this.stage.setScene(scene);

            /*
            GUIImpl.stage.setX(actualFrame.getMinX());
            GUIImpl.stage.setY(actualFrame.getMinY());
            GUIImpl.stage.setWidth(actualFrame.getWidth());
            GUIImpl.stage.setHeight(actualFrame.getHeight());
            GUIImpl.stage.setFullScreen(fullscreen);
            */

            this.stage.sizeToScene();
            preferredSizes = new Pair<>(scene.getWidth(), scene.getHeight());
            modifiedSizes = getStageSizes();
        }
    }

    /**
     * sets stage icon, name ad his dimensions.
     */
    private void setUpStage() {
        //load the app icon
        try {
            this.stage.getIcons().add(new Image(ICON_FILE));
        } catch (Exception e) {
            System.out.println("ERROR: Image \"" + ICON_FILE + "\" not found");
        }
        //set the app title
        this.stage.setTitle("jbomberpengu");
        this.stage.setMaxHeight(primaryScreenBounds.getHeight());
        this.stage.setMaxWidth(primaryScreenBounds.getWidth());

        //this.stage.centerOnScreen();
        //this.stage.initStyle(StageStyle.UNDECORATED);

        this.stage.sizeToScene();

        this.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            //actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });

        this.stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            //actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });
    }

}



/*
*/
