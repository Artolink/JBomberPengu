package view;

import java.io.File;
import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.language.ApplicationStrings;
import model.utils.Pair;
import view.sounds.SoundsAssociator;

/**
 *  The Application entry point, contains the Page control mechanism.
 */
public class GUIImpl extends Application implements GUI {

    private static final String ICON_FILE = "view" + File.separator + "penguin.png";

    private Pair<Double, Double> preferredSizes;
    private Pair<Double, Double> modifiedSizes;

    private Rectangle2D primaryScreenBounds;
    //private static Rectangle2D actualFrame;

    private boolean initialized = false;

    //private boolean fullscreen = false;

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

        StObjCont.setStage(primaryStage);

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set up the stage whit some settings
        setUpStage();

        //Load main menu FXML as default
        loadPage(PageNames.MAINMENU);

        //start sound
        StObjCont.setSoundsAssociator(new SoundsAssociator());
        if(isSoundEnabled()) {
            getSounds().getStartGameSound().play();
        }

        StObjCont.getStage().show();
    }

    @Override
    public final void stop() {
        Platform.exit();
    }

    @Override
    public final void setFullscreenMode(final boolean fullscreen) {
        StObjCont.getStage().setFullScreen(fullscreen);
        //this.fullscreen = fullscreen;
    }

    @Override
    public final boolean isFullscreen() {
        return StObjCont.getStage().isFullScreen();
        //return this.fullscreen;
    }

    @Override
    public final Pair<Double, Double> getStageSizes() {
        return new Pair<>(StObjCont.getStage().getWidth(), StObjCont.getStage().getHeight());
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
        StObjCont.setController(controller);
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
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "mainMenu", "MainMenu"));
                break;
            case GAME:
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "game", "Game"));
                //start match sound
                if(isSoundEnabled()) {
                    getSounds().stopSounds();
                    getSounds().getStartMatchSound().play();
                }
                break;
            case GAMENDED:
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "game", "GameEnded"));
                break;
            case SETTINGS:
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "settings", "SettingsMenu"));
                break;
            case MAPEDITOR:
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "mapEditor", "MapEditor"));
                break;
            case LANGUAGEDITOR:
                StObjCont.setPage((Page) new FxmlFileLoader("view" + File.separator + "multiLang", "MultilangView"));
                break;
            default:
                System.out.println("404 Page not found");
                break;
        }

        switchScene(StObjCont.getPage().getScene());
    }

    @Override
    public final Pair<Double, Double> getMaxScreenDimensions() {
        return new Pair<Double, Double>(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    }

    // Protected methods -------------------------------------------------------------------------------------------

    /**
     * 
     * @return The scene currently displayed.
     */
    protected final Scene getCurrentScene() {
        return StObjCont.getStage().getScene();
    }

    /**
     * 
     * @return the controller that controls this
     */
    protected final Controller getController() {
        return StObjCont.getController();
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
        return StObjCont.getPage();
    }

    /**
     * @return the SoundsAssociator
     */
    protected SoundsAssociator getSounds() {
        return StObjCont.getSoundsAssociator();
    }

    /**
     * 
     * @param music
     */
    protected void setSound(Boolean music) {
        StObjCont.setMusic(music);
        if(!music) {
            getSounds().stopSounds();
        }
    }

    /**
     * 
     * @return
     */
    protected Boolean isSoundEnabled() {
        return StObjCont.isMusicEnabled();
    }

    // Private methods -------------------------------------------------------------------------------------------

    /**
     * To be called when a new Scene has to be loaded.
     * @param scene - the Scene you want to load.
     */
    private void switchScene(final Scene scene) {
        if (scene != null) {
            StObjCont.getStage().setScene(scene);

            /*
            GUIImpl.stage.setX(actualFrame.getMinX());
            GUIImpl.stage.setY(actualFrame.getMinY());
            GUIImpl.stage.setWidth(actualFrame.getWidth());
            GUIImpl.stage.setHeight(actualFrame.getHeight());
            GUIImpl.stage.setFullScreen(fullscreen);
            */

            StObjCont.getStage().sizeToScene();
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
            StObjCont.getStage().getIcons().add(new Image(ICON_FILE));
        } catch (Exception e) {
            System.out.println("ERROR: Image \"" + ICON_FILE + "\" not found");
        }
        //set the app title
        StObjCont.getStage().setTitle("jbomberpengu");
        StObjCont.getStage().setMaxWidth(getMaxScreenDimensions().getX());
        StObjCont.getStage().setMaxHeight(getMaxScreenDimensions().getY());

        //this.stage.centerOnScreen();
        //this.stage.initStyle(StageStyle.UNDECORATED);

        StObjCont.getStage().sizeToScene();

        StObjCont.getStage().widthProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            //actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });

        StObjCont.getStage().heightProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getStageSizes();
            //actualFrame = new Rectangle2D(GUIImpl.stage.getX(), GUIImpl.stage.getX(), GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
        });

        StObjCont.getStage().setOnCloseRequest((new EventHandler<WindowEvent>(){

            @Override
            public void handle(final WindowEvent arg0) {
                arg0.consume();
                getController().actionPerformedCloseBtn();
            }
        }));
    }


    /**
     * All the objects common to all of this class instance.
     */
    private static class StObjCont {

        private static Stage stage;
        private static Page page;
        private static Controller controller;
        private static SoundsAssociator sounds;
        private static Boolean music = true;

        public static void setStage(final Stage stage) {
            StObjCont.stage = stage;
        }

        public static Stage getStage() {
            return StObjCont.stage;
        }

        public static void setPage(final Page page) {
            StObjCont.page = page;
        }

        public static Page getPage() {
            return StObjCont.page;
        }

        public static void setController(final Controller controller) {
            StObjCont.controller = controller;
        }

        public static Controller getController() {
            return StObjCont.controller;
        }

        public static void setSoundsAssociator(final SoundsAssociator sounds) {
            StObjCont.sounds = sounds;
        }

        public static SoundsAssociator getSoundsAssociator() {
            return StObjCont.sounds;
        }
 
        public static void setMusic(final Boolean music) {
            StObjCont.music = music;
        }

        public static Boolean isMusicEnabled() {
            return StObjCont.music;
        }
    }

}



/*
*/
