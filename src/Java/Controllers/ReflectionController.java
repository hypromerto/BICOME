package Java.Controllers;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.jfoenix.controls.*;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;


public class ReflectionController { //implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton replayButton;
    @FXML
    private Label animalName;
    @FXML
    private Label environmentName;
    @FXML
    private Label timeData;
    @FXML
    private Label survivalRateData;
    @FXML
    private Label speedOfAnimal;
    @FXML
    private Label strengthOfAnimal;
    @FXML
    private Label wingOfAnimal;
    @FXML
    private Label speedOfFit;
    @FXML
    private Label strengthOfFit;
    @FXML
    private Label wingOfFit;
    @FXML
    private Label winLose;
    @FXML
    private JFXListView<String> listOfEnvironment;
    @FXML
    private JFXListView<String> listOfAnimal;
    @FXML
    private JFXListView<String> listOfFit;
    @FXML
    private ImageView animalPic;
    @FXML
    private ImageView environmentPic;


    //@Override
    public void initialize(ResourceBundle resources) { //URL Location,

    }

    @FXML
    void saveAction (ActionEvent event){
    //saves game
    }

    @FXML
    void replayAction (ActionEvent event){
    //directs to the selection stage
    }

    @FXML
    void insertAnimalPic( Image image) {
        animalPic.setImage(image);
    }

    @FXML
    void insertEnvironmentPic( Image image){
        environmentPic.setImage( image);
    }

    @FXML
    void updateListOfAnimal( double speed, double strength, boolean wing){
        speedOfAnimal.setText( "" + speed);
        strengthOfAnimal.setText( "" + strength);
        wingOfAnimal.setText( "" + wing);
    }


    @FXML
    void updateListOfFit( double speed, double strength, boolean wing){
        speedOfFit.setText( "" + speed);
        strengthOfFit.setText( "" + strength);
        wingOfFit.setText( "" + wing);
    }

    @FXML
    void updateGameData( double time, double survivalRate, boolean win){
        timeData.setText( "" + time);
        survivalRateData.setText( "" + survivalRate);
        if (win)
            winLose.setText( "You Win!");
        else
            winLose.setText( "You Lose!");
    }

    @FXML
    void updateListOfEnvironment( ObservableList<String> property){
        //property =FXCollections.observableArrayList;
        listOfEnvironment.setItems( property);
    }

    @FXML
    void updateListOfAnimal(ObservableList<String> features){
        listOfAnimal.setItems( features);
    }

    @FXML
    void updateListOfFit( ObservableList<String> features){
        listOfFit.setItems( features);
    }
}
