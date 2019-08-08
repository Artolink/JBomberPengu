package view.game;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.utils.Pair;
import view.GUIImpl;


public class GameController extends GUIImpl{

private static final int BLOCK_DIMENSION = 47;
    
    @FXML
    public AnchorPane anchorPane1;
    @FXML
    private Button button;
    @FXML
    private ImageView background;
    @FXML
    private GraphicsContext gc;
    
    Canvas canvas = new Canvas();
    Pair<Integer, Integer> dimensions;
    
    public void initialize() {
        
        button.setText(getTranslator().getValueOf("give up"));
        
        background.fitWidthProperty().bind(anchorPane1.widthProperty());
        background.fitHeightProperty().bind(anchorPane1.heightProperty());
        background.setPreserveRatio(true);
        
        anchorPane1.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        
        canvas.setWidth(anchorPane1.getPrefWidth());
        canvas.setHeight(anchorPane1.getPrefHeight());
        gc = canvas.getGraphicsContext2D();
        anchorPane1.getChildren().add(canvas);
        getTranslator();
    }
    
    public void draw(Image image, int row, int col) {

        gc.drawImage(image, BLOCK_DIMENSION*row+10, BLOCK_DIMENSION*col+10, BLOCK_DIMENSION, BLOCK_DIMENSION);//calcolare scarto
     }
    
    //cambia le dimensioni del pannello, del canvas e della scena
    public void setWindowSize(double width, double height) {
        
        anchorPane1.setPrefWidth(width);
        anchorPane1.setPrefHeight(height);
        canvas.setWidth(width);
        canvas.setHeight(height);
        //getPage("Game").getScene().wid
        //settare grandezza scena
    }
    
    //ridimensiona la finestra in base a computeWindowSize
    public Pair<Double, Double> resizeToMap(Pair<Integer, Integer> dim) {
        
        Pair<Double, Double> pair;
        pair = computeWindowSize(dim.getX(), dim.getY());
        setWindowSize(pair.getX(), pair.getY());
        return pair;
    }
    
    //controller give me the map size
    public void setDimensions(Pair<Integer, Integer> dim) {
        
        this.dimensions = dim;
    }

    //it returns the size of the game area (canvas)
    @Override
    public Pair<Double, Double> getSizes(){
        
        return new Pair<Double, Double>(canvas.getWidth(), canvas.getHeight());
    }
    
    //calcola quanto deve essere grande la finestra o la scena in base a quante righe e colonne ci sono nella mappa
    private Pair<Double, Double> computeWindowSize(Integer row, Integer col){
        
        double computeX = 0;
        double computeY = 0;
        
        computeX = BLOCK_DIMENSION*row;
        computeY = BLOCK_DIMENSION*col;
        return new Pair<Double, Double>(computeX, computeY);//aggiungere e calcolare scarto
    }
    
}
