package view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.language.ApplicationStrings;
import model.utils.Pair;

/**
 *  The Application entry point, contains the Page control mechanism.
 */
public class GUIImpl extends Application implements GUI {

    private static final Pair<String, String> MAIN_MENU_FILE = new Pair<>("view" + File.separator + "mainMenu", "MainMenu");
    private static final String ICON_FILE = "view" + File.separator + "penguin.png";

    private static Pair<Double, Double> preferredSizes;
    private static Pair<Double, Double> modifiedSizes;

    private static boolean initialized = false;

    private static Stage stage;

    private static Map<String, Page> pages = new HashMap<>();

    private static Page currentPage;

    //Instance of translation class
    private static Optional<ApplicationStrings> applicationStrings = Optional.empty();

    /**
     * Application entry point.
     */
    public void launch() {
        launch(GUIImpl.class);
    }

    /*
     * called automatically from Application. Used for configuration purposes.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        GUIImpl.stage = primaryStage;

        setUpStage();


        //Translator setup
        if (getTranslator().getSelectedLanguage() == null) {
            //setting main language to the first found
            getTranslator().setLanguage(getTranslator().getAvailableLanguages().get(0));
            System.out.println(getTranslator().getSelectedLanguage() + " selected as default language.");
        }

        //Load main menu FXML
        addPage(new FxmlFileLoader(MAIN_MENU_FILE.getX(), MAIN_MENU_FILE.getY()));
        loadPage(MAIN_MENU_FILE.getY());

        GUIImpl.stage.show();
    }

    @Override
    public final ApplicationStrings getTranslator() {
        if (!applicationStrings.isPresent()) {
            applicationStrings = Optional.ofNullable(new ApplicationStrings());
        }
        return applicationStrings.get();

    }

    @Override
    public final Scene getCurrentScene() {
        return GUIImpl.stage.getScene();
    }

    @Override
    public final void switchScene(final Scene scene) {
        if (scene != null) {
            GUIImpl.stage.setScene(scene);
            GUIImpl.stage.sizeToScene();

            preferredSizes = new Pair<>(scene.getWidth(), scene.getHeight());
            modifiedSizes = getSizes();
        }
    }

    @Override
    public final void switchScene(final Scene scene, final Double width, final Double height) {
        switchScene(scene);
        GUIImpl.stage.setHeight(height);
        GUIImpl.stage.setWidth(width);
    }

    @Override
    public final void switchScene(final Scene scene, final Double width, final Double height, final boolean fullscreen) {
        switchScene(scene, height, width);
        GUIImpl.stage.setFullScreen(fullscreen);
    }

    @Override
    public Pair<Double, Double> getSizes() {
        return new Pair<>(GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
    }

    @Override
    public final Pair<Double, Double> getDimensionsMultipliers() {
        if (preferredSizes.getX() == 0 || preferredSizes.getY() == 0) {
            return new Pair<Double, Double>(1d, 1d);
        }
        return new Pair<Double, Double>(modifiedSizes.getX() / preferredSizes.getX(), modifiedSizes.getY() / preferredSizes.getY());
    }

    @Override
    public final void setController(/*Controller controller*/) {
        // TODO Auto-generated method stub
        if (!initialized) {
            //this.controller = controller;
            initialized = true;
        }
    }

    @Override
    public final Page addPage(final Page page) {
        pages.put(page.getPageName(), page);
        return page;
    }

    @Override
    public final Page getCurrentPage() {
        return currentPage;
    }

    @Override
    public final Page getPage(final String name) {
        return pages.get(name);
    }

    @Override
    public final Map<String, Page> getPages() {
        return pages;
    }

    @Override
    public final void loadPage(final String name) {
        currentPage = pages.get(name);
        switchScene(currentPage.getScene());
    }

    @Override
    public final void closeGame() {
        System.out.println("Closing game...");
        System.exit(0);
    }

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

        //setting some arbitrary dimensions
        //GUIImpl.stage.setMinHeight(MIN_HEIGHT);
        //GUIImpl.stage.setMinWidth(MIN_WIDHT);
        GUIImpl.stage.sizeToScene();

        GUIImpl.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getSizes();
        });

        GUIImpl.stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getSizes();
        });
    }

}
