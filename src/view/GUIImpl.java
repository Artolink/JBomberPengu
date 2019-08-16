package view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private static Pair<Double, Double> preferredSizes;
    private static Pair<Double, Double> modifiedSizes;

    private static Rectangle2D primaryScreenBounds;
    private static Rectangle2D actualFrame;

    private static boolean initialized = false;

    private boolean fullscreen = false;

    private static Stage stage;

    private Page page;

    //Instance of translation class
    private static Optional<ApplicationStrings> applicationStrings = Optional.empty();

    private static Controller controller;

    /**
     * Application entry point.
     */
    public void launch() {
        if (initialized) {
            launch(GUIImpl.class);
        }
    }

    /*
     * called automatically from Application. Used for configuration purposes.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        GUIImpl.stage = primaryStage;

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        setUpStage();


        //Translator setup
        if (getTranslator().getSelectedLanguage() == null) {
            //setting main language to the first found
            getTranslator().setLanguage(getTranslator().getAvailableLanguages().get(0));
            System.out.println(getTranslator().getSelectedLanguage() + " selected as default language.");
        }

        //Load main menu FXML
        loadPage(PageNames.MAINMENU);

        GUIImpl.stage.show();
    }

    /**
     * Needed to reach the translator class.
     * @return if not initilized returns a new ApplicationStrings, else it returns the instance already created
     */
    protected final ApplicationStrings getTranslator() {
        if (!applicationStrings.isPresent()) {
            applicationStrings = Optional.ofNullable(getController().getTranslator()/*new ApplicationStrings()*/);
        }
        return applicationStrings.get();
    }

    /**
     * 
     * @return The scene currently displayed.
     */
    protected final Scene getCurrentScene() {
        return GUIImpl.stage.getScene();
    }

    /**
     * To be called when a new Scene has to be loaded.
     * @param scene - the Scene you want to load.
     */
    protected final void switchScene(final Scene scene) {
        if (scene != null) {
            GUIImpl.stage.setScene(scene);

            /*
            GUIImpl.stage.setX(actualFrame.getMinX());
            GUIImpl.stage.setY(actualFrame.getMinY());
            GUIImpl.stage.setWidth(actualFrame.getWidth());
            GUIImpl.stage.setHeight(actualFrame.getHeight());
            GUIImpl.stage.setFullScreen(fullscreen);
            */

            GUIImpl.stage.sizeToScene();
            preferredSizes = new Pair<>(scene.getWidth(), scene.getHeight());
            modifiedSizes = getStageSizes();
        }
    }

    @Override
    public final void setFullscreenMode(final boolean fullscreen) {
        GUIImpl.stage.setFullScreen(fullscreen);
        //this.fullscreen = fullscreen;
    }

    @Override
    public final boolean getFullscreenState() {
        //return GUIImpl.stage.isFullScreen();
        return this.fullscreen;
    }

    @Override
    public final Pair<Double, Double> getStageSizes() {
        return new Pair<>(GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
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
        // TODO Auto-generated method stub
        GUIImpl.controller = controller;
        initialized = true;
    }

    /**
     * 
     * @return the controller that controls this
     */
    protected final Controller getController() {
        return GUIImpl.controller;
    }

    @Override
    public final PageController getActivePageController() {
        // TODO Auto-generated method stub
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
                page = (Page) new FxmlFileLoader("view" + File.separator + "settings" + File.separator + "SettingsMenu.fxml");
                break;
            case MAPEDITOR:
                page = (Page) new FxmlFileLoader("view" + File.separator + "mapEditor" + File.separator + "MapEditor.fxml");
                break;
            case LANGUAGEDITOR:
                page = (Page) new FxmlFileLoader("view" + File.separator + "multiLang" + File.separator + "MultilangView.fxml");
                break;
            default:
                System.out.println("404 Page not found");
                break;
        }
        
        switchScene(page.getScene());
    }

    /**
     * @return The currently loaded page
     */
    protected final Page getCurrentPage() {
        return page;
    }

    @Override
    public final void closeGame() {
        System.out.println("Closing game...");
        System.exit(0);
    }

    // Protected methods -------------------------------------------------------------------------------------------



    // Private methods -------------------------------------------------------------------------------------------

    /**
     * sets stage icon, name ad his dimensions.
     */
    private void setUpStage() {
        //load the app icon
        try {
            GUIImpl.stage.getIcons().add(new Image(ICON_FILE));
        } catch (Exception e) {
            System.out.println("ERROR: Image \"" + ICON_FILE + "\" not found");
        }
        //set the app title
        GUIImpl.stage.setTitle("jbomberpengu");
        GUIImpl.stage.setMaxHeight(primaryScreenBounds.getHeight());
        GUIImpl.stage.setMaxWidth(primaryScreenBounds.getWidth());

        //GUIImpl.stage.centerOnScreen();
        //GUIImpl.stage.initStyle(StageStyle.UNDECORATED);

        GUIImpl.stage.sizeToScene();

        GUIImpl.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });

        GUIImpl.stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });
    }

}
