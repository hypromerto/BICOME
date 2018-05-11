package bicome.controllers;

import bicome.database.Report;
import bicome.dialogs.ReturnHomeDialog;
import bicome.logic.environment.Environment;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureList;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import bicome.logic.world.Tile;
import bicome.logic.world.World;
import bicome.utils.PageNavigator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Please add javadoc Cerag
 * Please only send compilable source next time because I have to test our app
 * I have commented all lines that prevents complitation
 */
public class GameController implements Initializable{


    @FXML
    private BorderPane rootPane;

    @FXML
    private ImageView animalPicture;
    @FXML
    private ImageView environmentImage;
    @FXML
    private ImageView backgroundPicture;
    @FXML
    private Label animalName;
    @FXML
    private Label environmentName;

    @FXML
    private Label timeData;

    @FXML
    private Label survivalRate;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton pauseButton;

    @FXML
    private JFXButton speedButton;

    @FXML
    private Label environmentConditionsLabel;

    @FXML
    private JFXListView animalList;

    @FXML
    private Label speedLabel;

    @FXML
    private JFXSlider speedSlider;

    @FXML
    private GridPane grid;

    public ObservableList<Feature> featuresList;

    private GameManager gameManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //This is the function that will called during creation of the page
        featuresList = FXCollections.observableArrayList();
        animalList.setItems(featuresList);
        // 25 -> 1x
        updateTime(0);
        //environmentImage.setImage( gameManager.getWorld().getEnvironment().getImage());

        speedSlider.valueProperty().addListener( e -> {
           try {
            int value = (int) Math.floor((speedSlider.getValue() - 1) / 25) + 1;
            speedLabel.setText("Speed: " + value + "x");
            gameManager.setDurationOfTurns(GameManager.INITIAL_DURATION_OF_TURNS / value);
           }
           catch ( ArithmeticException ex )
           {
              speedSlider.setValue( 1 );
              gameManager.setDurationOfTurns( GameManager.INITIAL_DURATION_OF_TURNS );
           }
        });
        speedSlider.setValue(25);
    }

    public static class MyNode extends Rectangle {
        //private static final int SIZE = 15;
        private final Tile tile;

        public MyNode(Tile tile, double w, double h) {
            super(w, h);
            setFill(tile.getColor());
            this.tile = tile;
        }

        public Tile getTile() {
            return tile;
        }
    }
    
    @FXML
    private void goHome(ActionEvent event) {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        ReturnHomeDialog dialog = new ReturnHomeDialog();
        Optional<ButtonType> result = dialog.showAndWait();
        if(!result.isPresent() || result.get().equals(ButtonType.CANCEL))
            return;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resources/Views/StartStage.fxml"));
            currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load startStage");
        }

    }

    @FXML
    private void pauseAndPlayGame(ActionEvent event){
        Button button = (Button) event.getSource();
        ImageView imageView = (ImageView) button.getGraphic();
        if(button.getText().equals("playing")) {
            gameManager.pause();
            button.setText("paused");
            imageView.setImage(new Image("/Resources/Images/playWhite.png"));
        }
        else {
            gameManager.start();
            button.setText("playing");
            imageView.setImage(new Image("/Resources/Images/pauseWhite.png"));
        }
    }

    private void setAnimalList(Tile tile){
        Optional<Organism> organism = Optional.ofNullable(tile.getOrganism());

        organism.ifPresent( o -> {
            animalList.getItems().addAll(o.getFeatures());
        });
    }

    @FXML
    protected void onGridClicked(MouseEvent event)
    {
        System.out.println(grid.getRowConstraints().size());
        int size = grid.getRowConstraints().size();
        Tile[][] tiles = gameManager.getWorld().getGrid();
        int row = (int) Math.floor(event.getY() / (grid.getHeight() / size));
        int col = (int) Math.floor(event.getX() / (grid.getWidth() / size));
        System.out.println(row + "\n" + col);
        try {
            Organism selectedOrganism = tiles[row][col].getOrganism();
            animalList.getItems().clear();
            animalList.getItems().addAll(selectedOrganism.getFeatures());
        }
        catch (NullPointerException e) {
            System.out.println("The cell is null");
        }
    }

    @FXML
    private void updateTime( long time) {
        timeData.setText( time + " years" );
    }

    @FXML
    private void updateSurvivalRate( int rateOfSurvival ) {
        survivalRate.setText( rateOfSurvival + "%" );
    }

    @FXML
    private void setAnimalPic( Image image) {
        animalPicture.setImage(image);
    }

    public void updateGameStage( long time)
    {
        updateTime(time);
        //updateSurvivalRate(rateOfSurvival);

        drawGrid();
    }

    public void init()
    {
        environmentConditionsLabel.setText(gameManager.getWorld().getEnvironment().getConditionsForGUI());
        environmentName.setText(gameManager.getWorld().getEnvironment().toString());
        System.out.println(gameManager.getWorld().getEnvironment().getConditionsForGUI());
        //Initialize the game grid

        gameManager.start();

        //Resizes rectangles
        rootPane.widthProperty().addListener(observable -> drawGrid());
        rootPane.heightProperty().addListener(observable -> drawGrid());
        Platform.runLater(() -> drawGrid());
    }

    public void setManager(Environment env, FeatureList list)
    {
        World world = new World(list, env);
        gameManager = new GameManager(world, this);
    }

    public void finishGame()
    {
        Scene currentScene = rootPane.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        Environment environment = gameManager.getWorld().getEnvironment();
        FeatureList list = gameManager.getWorld().getFirstOrganism().getFeatures();
        /*Report report = new Report(environment, list);
        report.connect();
        //report.createReportTable();
        //report.createAnimalTable(list);
        //report.createEnvironmentTable(environment); */
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/Views/ReflectionStage.fxml"));
            AnchorPane root = loader.load();
            ReflectionController controller = loader.getController();
            controller.setGameManager(gameManager);
            controller.init();
            currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawGrid()
    {
        World world = gameManager.getWorld();
        Tile[][] tiles = world.getGrid();
        int size = world.getSize();

        grid.getChildren().retainAll(grid.getChildren().get(0)); //retainAll is for the grid lines
        double width = grid.getWidth() / size;
        double height = grid.getHeight() / size;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j  < size; ++j) {
                MyNode node = new MyNode(tiles[i][j], width, height);
                grid.add(node , i, j);
            }
        }
    }

    private void onResize()
    {
        drawGrid();
    }
}
