package bicome.controllers;

import bicome.animations.ScaleValue;
import bicome.animations.ViewAnimations;
import bicome.database.Report;
import bicome.logic.environment.Environment;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import com.jfoenix.controls.*;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
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
{
    @FXML
    private Label anchorPane;

    @FXML
    private ImageView enviroImage;

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
    private JFXButton saveButton;

    @FXML
    private JFXButton replayButton;

    @FXML
    private JFXListView firstAnimalListView;

    @FXML
    private JFXListView avarageAnimalListView;

    @FXML
    private Label winLoseLabel;


    private GameManager gameManager;

    public void init() //Don't call this before all privates are set
    {
        animalNameLabel.setText(gameManager.getWorld().getFirstOrganism().toString());
        timeLabel.setText("" + gameManager.getYearsPassed());
        //survivalRateLabel("" + gameManager.getWorld().getSurvivalRate());
        environmentNameLabel.setText(gameManager.getWorld().getEnvironment().toString());
        environmentConditionsLabel.setText(gameManager.getWorld().getEnvironment().getConditionsForGUI());
        firstAnimalListView.getItems().setAll(gameManager.getWorld().getFirstOrganism().getFeatures());
        avarageAnimalListView.getItems().setAll(gameManager.getWorld().getSampleOrganism());
        //avarageAnimalListView.getItems().setAll(gameManager.getAverageOrganism().getFeatures());
        enviroImage.setImage( gameManager.getWorld().getEnvironment().getImage());
        //animalImage.setImage( new Image("Resources/Images/AnimalPicture"));
        winLoseLabel.setText(gameManager.gameIsWon() ? "You Win!" : "You Lose!");
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
    protected void onMouseEntered(MouseEvent event)
    {
        ViewAnimations.scaleControl((Control) event.getSource(), ScaleValue.BIGGER);
    }

    @FXML
    protected void onMouseExited(MouseEvent event)
    {
        ViewAnimations.scaleControl((Control) event.getSource(), ScaleValue.MEDIUM);
    }

    @FXML
    protected void onSaveAction(ActionEvent event)
    {
        Report report = new Report(gameManager);
        report.createReport();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
