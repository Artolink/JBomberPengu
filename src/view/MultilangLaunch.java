package view;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main GUI entry point.
 */
public class MultilangLaunch extends Application {

    private static final String MAIN_FILE = "view" + File.separator + "MultilangView.fxml";

    /**
     * Launches the graphical interface.
     */
    public void launch() {
        launch(MultilangLaunch.class);
    }

    /**
     * Start the window.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Scene mainMenuScene;
        try {
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource(MAIN_FILE));
            final Parent root = loader.load();
            mainMenuScene = new Scene(root);
        } catch (Exception e) {
            System.out.println("ERROR: Failed loading \"" + MAIN_FILE + "\"");
            throw e;
        }

        primaryStage.setTitle("Translation");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}
