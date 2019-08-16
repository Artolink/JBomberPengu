package view;

import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *  The class needed for loading FXML file and his controller.
 */
public class FxmlFileLoader extends Page {

    private String pageName;

    //private String fileName;
    //private String filePath;

    private String path;

    private Scene scene;
    private FXMLLoader loader;


    /**
     * 
     * @param filePath - the folder where the FXML file is
     * @param fileName - the name of the file whitout extension
     */
    public FxmlFileLoader(final String filePath, final String fileName) {
        this(filePath + File.separator + fileName + ".fxml");
    }

    /**
     * 
     * @param path - this will be passed directly to the loader
     */
    public FxmlFileLoader(final String path) {
        super();
        this.path = path;
        this.pageName = nameExtrapolator(path);
        loadFile();
    }


    @Override
    public final String getPageName() {
        return pageName;
    }

    @Override
    public final Scene getScene() {
        return scene;
    }

    /**
     * 
     * @return Returns the controller associated with the Page.
     */
    public PageController getPageController() {
        return loader.getController();
    }

    /**
     * 
     * @return Returns the FXML path
     */
    public String getFxmlPath() {
        return path;
    }


    /**
     * Creates the Scene from the FXML file. Called from FxmlFileLoader.
     */
    private void loadFile() {
        try {
            loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource(path));
            final Parent root = loader.load();
            scene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String nameExtrapolator(final String path) {
        final char[] charArray = path.toCharArray();

        int i = 0;
        int lastFileSeparatorPosition = 0;
        int extensionStartPosition = 0;
        for (final char c: charArray) {
            if (c == '\\' /*File.separator.toCharArray()[0]*/) {
                lastFileSeparatorPosition = i;
                }
            if (c == '.') {
                extensionStartPosition = i;
                break;
            }
            i++;
        }

        if (lastFileSeparatorPosition + 1 < extensionStartPosition) {
            String s = "";
            for (i = lastFileSeparatorPosition + 1; i < extensionStartPosition; i++) {
                s += charArray[i];
            }
            return s;
        } else {
            return path; 
        }
    }


}
