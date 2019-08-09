package view;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FxmlFileLoader extends Page {

    private String fileName;
    private String fileAddress;

    private Scene scene;
    private FXMLLoader loader;


    /**
     * 
     * @param fileAddress - the folder where the FXML file is
     * @param fileName - the name of the file
     */
    FxmlFileLoader(String fileAddress, String fileName){
    this.fileAddress = fileAddress;
    this.fileName = fileName;
    loadFile();
    }


    @Override
    public String getPageName() {
        return fileName;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * 
     * @return Returns the controller associated with the Page.
     */
    public Object getController() {
        return loader.getController();
    }

    /**
     * 
     * @return Returns the FXML file name
     */
    public String getFxmlAddress() {
        return fileAddress;
    }


    /**
     * Creates the Scene from the FXML file. Called from FxmlFileLoader.
     */
    private void loadFile() {
        try {
            loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource(fileAddress + File.separator + fileName + ".fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            //System.out.println("\"" + fileAddress + File.separator + fileName + ".fxml" + "\" loaded correctly");    //TODO debug
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("ERROR: Failed loading \"" + fileName + "\"");   //TODO debug
        }
    }

}
