package view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.language.ApplicationStrings;
import model.utils.Pair;

public class GUIImpl extends Application implements GUI {

    private static final Pair<String, String> MAIN_MENU_FILE = new Pair<>("view" + File.separator + "mainMenu", "MainMenu");
    private static final String ICON_FILE = "view" + File.separator + "penguin.png";

    private static Pair<Double, Double> preferredSizes;
    private static Pair<Double, Double> modifiedSizes;

    private static boolean initialized = false;

    private static Stage stage;

    private static Map<String, Page> pages = new HashMap<>();

    //Instance of translation class
    private static Optional<ApplicationStrings> applicationStrings = Optional.empty();

    /*
     * Application entry point. do not touch.
     */
    public void launch() {
        launch(GUIImpl.class);
    }

    /*
     * called automatically from Application. Used for configuration purposes.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

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


        System.out.println(getDimensionsMultipliers().toString());

        GUIImpl.stage.show();
    }

    public ApplicationStrings getTranslator() {
        if (!applicationStrings.isPresent()) {
            applicationStrings = Optional.ofNullable(new ApplicationStrings());
        }
        return applicationStrings.get();

    }

    @Override
    public void switchScene(Scene scene) {
        if (scene != null) {
            GUIImpl.stage.setScene(scene);
            GUIImpl.stage.sizeToScene();
            
            preferredSizes = new Pair<>(scene.getWidth(), scene.getHeight());
            modifiedSizes = getSizes();
        }
    }

    @Override
    public void switchScene(Scene scene, Double width, Double height) {
        switchScene(scene);
        GUIImpl.stage.setHeight(height);
        GUIImpl.stage.setWidth(width);
    }

    @Override
    public void switchScene(Scene scene, Double width, Double height, boolean fullscreen) {
        switchScene(scene, height, width);
        GUIImpl.stage.setFullScreen(fullscreen);
    }

    @Override
    public Pair<Double, Double> getSizes() {
        return new Pair<>(GUIImpl.stage.getWidth(), GUIImpl.stage.getHeight());
    }

    @Override
    public Pair<Double, Double> getDimensionsMultipliers() {
        if (preferredSizes.getX() == 0 || preferredSizes.getY() == 0) {
            return new Pair<Double, Double>(1d, 1d);
        }
        System.out.println(preferredSizes.getX() + " " + preferredSizes.getY());
        System.out.println(modifiedSizes.getX() + " " + modifiedSizes.getY());
        System.out.println(modifiedSizes.getX() / preferredSizes.getX() + " " + modifiedSizes.getY() / preferredSizes.getY());
        return new Pair<Double, Double>(modifiedSizes.getX() / preferredSizes.getX(), modifiedSizes.getY() / preferredSizes.getY());
    }

    @Override
    public void setController(/*Controller controller*/) {
        // TODO Auto-generated method stub
        if (!initialized) {
            //this.controller = controller;
            initialized = true;
        }
    }

    @Override
    public Page addPage(Page page) {
        pages.put(page.getPageName(), page);
        return page;
    }

    @Override
    public Page getPage(String name) {
        return pages.get(name);
    }

    @Override
    public Map<String, Page> getPages() {
        return pages;
    }

    @Override
    public void loadPage(String name) {
        switchScene(pages.get(name).getScene());
    }

    @Override
    public void closeGame() {
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

        //preferredSizes = getSizes();
        //modifiedSizes = new Pair<Double, Double>(preferredSizes.getX(), preferredSizes.getY());

        GUIImpl.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getSizes();
            System.out.println(getDimensionsMultipliers().toString());
        });

        GUIImpl.stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            modifiedSizes = getSizes();
        });
    }

}