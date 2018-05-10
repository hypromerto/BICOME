package bicome.controllers;

import bicome.logic.environment.Environment;
import bicome.logic.environment.EnvironmentalCondition;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureList;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import bicome.logic.world.Tile;
import bicome.logic.world.World;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageOfAnimal;
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
        speedSlider.setValue(25);
        speedLabel.setText("Speed: " + (int) speedSlider.getValue());
        updateTime(0);
    }


    public static class MyNode extends Rectangle {
        private static final int SIZE = 15;
        private final int x, y;
        private final Tile tile;

        public MyNode( Tile tile, int x, int y) {
            super( SIZE, SIZE);
            setFill(tile.getColor());
            this.tile = tile;
            this.x = x;
            this.y = y;

            /*try {
                //Set the background color of the button
                setStyle("-fx-background-color: #" + tile.getColor().toString().substring(2, 8));
            }
            catch (NullPointerException e) {
                System.out.println("color is null");
                setStyle("-fx-background-color: #ffffff");
            }*/
        }

        public int getRow()
        {
            return x;
        }

        public int getCol()
        {
            return y;
        }

        public Tile getTile() {
            return tile;
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Resources/Views/StartStage.fxml"));
            currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load startStage");
        }

    }

    @FXML
    private void pauseGame(ActionEvent event){
        gameManager.pause();
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
        for(Node node : grid.getChildren()) {
            if(node instanceof MyNode) {
                if(node.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {
                    MyNode currentNode = (MyNode) node;

                    //Set the name of the animal and the image
                }
            }
        }
    }

    @FXML
    private void changeSpeed(ActionEvent event){
        JFXSlider slider = (JFXSlider) event.getSource();
        int value = (int) Math.floor((slider.getValue() - 1) / 25);
        if(value == 0) { // 0 -> 1x
            //1x -> 100 miliseconds
            gameManager.setDurationOfTurns(200);
        }
        else if(value == 1) {
            gameManager.setDurationOfTurns(100);
        }
        else {
            gameManager.setDurationOfTurns(50);
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
        imageOfAnimal.setImage(image);
    }

    public void updateGameStage( long time)
    {
        updateTime(time);
        //updateSurvivalRate(rateOfSurvival);

        World world = gameManager.getWorld();
        Tile[][] tiles = world.getGrid();

        for(Tile[] arr : tiles) {
            for(Tile tile : arr) {
                System.out.print(tile.getOrganism() != null ? 1 : 0);
            }
            System.out.println();
        }


        for(int i = 0; i < 30; ++i) {
            for(int j = 0; j  < 30; ++j) {
                MyNode node = new MyNode(tiles[i][j], i, j);
                grid.add(node , j, i);
            }
        }
    }

    public void init()
    {
        environmentConditionsLabel.setText(gameManager.getWorld().getEnvironment().getConditionsForGUI());
        //Initialize the game grid
        Tile[][] tiles = gameManager.getWorld().getGrid();
        for(int i = 0; i < 30; ++i) {
            for(int j = 0; j  < 30; ++j) {
                grid.add(new MyNode(tiles[i][j], i, j), j, i);
            }
        }

        gameManager.start();
    }

    public void setManager(Environment env, FeatureList list)
    {
        World world = new World(list, env);
        gameManager = new GameManager(world, this);
    }

    public void finishGame()
    {
        //Call this when game is over
    }
}
