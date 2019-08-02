package view;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main GUI entry point.
 */
public class MainGUI extends Application {

    private static final String MAIN_MENU_FILE = "view" + File.separator + "MainMenu.fxml";
    private static final String ICON_FILE = "view" + File.separator + "penguin.png";

    private static final int  MIN_HEIGHT = 350;
    private static final int  MIN_WIDHT = 370;

    private Stage stage;

    //Stage window;
    private Scene mainMenuScene; //, settingsMenuScene;

    //MainMenuController
    private static MainMenuController mainMenuController;

    /** Launches the graphical interface.
     * 
     */
    public void launch() {
        launch(MainGUI.class);
    }

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        //Load MainMenu.fxml into mainMenuScene and synchronize his controller
        try {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource(MAIN_MENU_FILE));
            Parent root = loader.load();
            mainMenuScene = new Scene(root);
            mainMenuController = loader.getController();
            mainMenuController.setMainGUI(this);
        } catch (Exception e) {
            System.out.println("ERROR: Failed loading \"" + MAIN_MENU_FILE + "\"");
            throw e;
        }

        //load the app icon
        try {
            this.stage.getIcons().add(new Image(ICON_FILE));
        } catch (Exception e) {
            System.out.println("ERROR: Image \"" + ICON_FILE + "\" not found");
        }

        this.stage.setTitle("jbomberpengu");
        this.stage.setScene(mainMenuScene);

        this.stage.setMinHeight(MIN_HEIGHT);
        this.stage.setMinWidth(MIN_WIDHT);

        this.stage.show();
    }

    /**Switches to the Game Scene.
     * 
     */
    public void loadGame() {
        System.out.println("Game started");
        /*
        GameGUI game = new GameGUI();
        game.setMainGUI(this);
        this.stage.setScene(game.start());
        */
    }

    /**Switches to the main menu.
     * 
     */
    public void loadMainMenu() {
        System.out.println("Game ended");
        this.stage.setScene(mainMenuScene);
    }

}
