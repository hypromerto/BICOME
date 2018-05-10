package bicome.controllers;

import bicome.logic.environment.Environment;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.io.IOException;
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
import javafx.stage.Stage;


public class ReflectionController
{ //implements Initializable {

    @FXML
    private Label animalNameLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label survivalRateLabel;

    @FXML
    private Label environmentNameLabel;

    @FXML
    private Label environmentConditionsLabel;

    @FXML
    private JFXListView firstAnimalListView;

    @FXML
    private JFXListView avarageAnimalListView;

    private Environment environment;
    private Organism firstOrganism;
    private Organism avarageOrganism;
    private GameManager gameManager;


    public void init() //Don't call this before all privates are setted
    {
        //animalNameLabel.setText(firstOrganism.getName());
        timeLabel.setText("" + gameManager.getYearsPassed());
        //survivalRateLabel("" + gameManager.getWorld().getSurvivalRate());
        environmentNameLabel.setText(gameManager.getWorld().getEnvironment().toString());
        environmentConditionsLabel.setText(gameManager.getWorld().getEnvironment().getConditionsForGUI());
        firstAnimalListView.getItems().setAll(firstOrganism.getFeatures());
        avarageAnimalListView.getItems().setAll(avarageOrganism.getFeatures());
    }

    @FXML
    protected void onReplayAction(ActionEvent event)
    {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resources/Views/StartStage.fxml"));
            currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onSaveAction(ActionEvent event)
    {
        //To Do: add to the history
    }

    public void setValues(Environment environment, Organism firstOrganism, Organism avarageOrganism, GameManager manager)
    {
        setEnvironment(environment);
        setFirstOrganism(firstOrganism);
        setAvarageOrganism(avarageOrganism);
        setGameManager(manager);
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setFirstOrganism(Organism firstOrganism) {
        this.firstOrganism = firstOrganism;
    }

    public void setAvarageOrganism(Organism avarageOrganism) {
        this.avarageOrganism = avarageOrganism;
    }
}
