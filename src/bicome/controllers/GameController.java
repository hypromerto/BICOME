package bicome.controllers;

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

import javax.swing.*;

public class GameController {

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


    /*boolean paused;
    Timer timer;
    int elapsedTime;
    this.timer = new Timer(100, new game());
    */  //What is this?
    boolean paused = false;

    @FXML
    void goHome(ActionEvent event){
        //directs to start page
    }

    @FXML
    void pauseGame(ActionEvent event){
        paused = true;
    }

    @FXML
    void changeSpeed(ActionEvent event){
        //??!?!?!?!?
    }

    @FXML
    void viewListOfAnimal( ObservableList<String> listOfAnimal){
        animalList.setItems(listOfAnimal);
        // ???
    }

    @FXML
    void viewListOfEnviro( ObservableList<String> listOfEnvironment){
        environmentList.setItems(listOfEnvironment);
        // ???
    }

    @FXML
    void updateTime( double time) {
        timeData.setText("" + time + " years");
    }

    @FXML
    void updateSurvivalRate( int rateOfSurvival ) {
        survivalRate.setText("" + rateOfSurvival + " %");
}

    @FXML
    void insertBackgroundPic( Image image) {
        backgroundPicture.setImage(image);
    }

    @FXML
    void insertAnimalPic( Image image) {
        imageOfAnimal.setImage(image);
    }

}
