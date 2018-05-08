package bicome.controllers;

import bicome.logic.world.Organism;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

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
    private JFXListView environmentList;
    @FXML
    private JFXListView animalList;
    @FXML
    private JFXSlider speedSlider;


    private boolean paused;
    private ObservableList<String> list;
    MyNode[][] simulation;
    Organism[][] organism;
    Organism[][] theGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //This is the function that will called during creation of the page
        theGrid = new Organism[30][30];
        organism = new Organism[30][30];
        list = FXCollections.observableArrayList();
        paused = false;
        //changeTimerTime(50);
        viewListOfAnimal( list);
        viewListOfEnvironment( list);
        updateTime( 0.0);
        //updateSurvivalRate( 50.0);
        //insertBackgroundPic( )  //insertimage
        simulation = new MyNode[30][30];

        //calculate the width, height, x, y of a grid cell!!
    }


    public static class MyNode extends StackPane {

        public MyNode( Organism organism, double x, double y, double width, double height) {

            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(organism.getColor());
            rectangle.setFill(organism.getColor());
            // set position
            setTranslateX( x);
            setTranslateY( y);

            getChildren().addAll( rectangle);
        }
    }

    private void reproduce() {
        int x, y;
        boolean flag;
        // organism constructor'ında color olsun.
        // organism Organism[][] arrayine yerleşsin.
        // organizmayı random bir gridcelle koy ve o celli boya
        flag = true;

        while (flag) {
            x = (int) (Math.random() * 100) % 30;
            y = (int) (Math.random() * 100) % 30;
            if (organism[x][y] != occupied) { //occupied
                flag = false;
                organism[x][y].setOccupied(true);               //setOccupied
                Group root = new Group();

                // initialize simulation
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        // create node
                        MyNode node = new MyNode(organism[i][j], i * gridWidth, j * gridHeight, gridWidth, gridHeight);

                        // add node to group
                        root.getChildren().add(node);

                        // add to simulation grid
                        simulation[i][j] = node;
                    }
                }
            }
        }
    }

        @FXML
    private void goHome(ActionEvent event){
        //directs to start page
    }

    @FXML
    private void pauseGame(ActionEvent event){
        if (paused)
            paused = false;
        else
            paused = true;
    }

    @FXML
    private void infoOfCell(MouseEvent event){
        if (paused) {
            Organism source = (Organism)event.getSource();
            String name = source.getClass().getName();//What is this?                                          //getColor() returns color of offspring
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (organism[i][j] == source) {             //Are you trying to find the current organism? It is nonsense since you have to have the current organism to check if it is current organism in the grid
                        //viewListOfAnimal(organism[i][j].getList());                            //getList() diye bir method olsun ve animal'ın observableListini return etsin?
                        //insertAnimalPic(organism[i][j].image); //insertimage?
                    }
                }
            }
        }
        else if(!animalList.getItems().isEmpty()){  //Don't empty the list if it is already empty
            viewListOfAnimal( FXCollections.observableArrayList());     //listeyi boşaltmak
        }
    }

    @FXML
    private void changeSpeed(ActionEvent event){
        //timer'ın zamanını değiştirecek
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //changeTimerTime(newValue.intValue());                                              //changeTimerTime is a method to change the timer time ??
        });
    }

    @FXML
    private void viewListOfAnimal( ObservableList<String> listOfAnimal){
        animalList.setItems(listOfAnimal);
    }

    @FXML
    private void viewListOfEnvironment(ObservableList<String> listOfEnvironment){
        environmentList.setItems(listOfEnvironment);
    }

    @FXML
    private void updateTime( double time) {
        timeData.setText("" + time + " years");
    }

    @FXML
    private void updateSurvivalRate( int rateOfSurvival ) {
        survivalRate.setText("" + rateOfSurvival + "%");
}

    @FXML
    private void insertBackgroundPic( Image image) {
        backgroundPicture.setImage(image);
    }

    @FXML
    private void insertAnimalPic( Image image) {
        imageOfAnimal.setImage(image);
    }

}
