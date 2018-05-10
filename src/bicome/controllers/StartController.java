package bicome.controllers;

import bicome.animations.ViewAnimations;
import bicome.animations.ScaleValue;
import com.jfoenix.controls.*;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable
{
    @FXML
    private ImageView logoView;

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXButton helpButton;

    @FXML
    private JFXButton playButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To fix the focus issue
        repeatFocus(logoView);
    }

    @FXML
    protected void onHelpClickedHandler(ActionEvent event)
    {
        //Create dialogLayout to display help message
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Label("How to play?"));
        //TO DO: write the help message
        dialogLayout.setBody(new Label("Enter help message here"));
        dialogLayout.setStyle("-fx-background-color: #B2EBF2");

        //cancel button for dialog
        JFXButton cancel = new JFXButton("I get it");
        cancel.setPrefWidth(150);
        cancel.setPrefHeight(40);
        cancel.setButtonType(JFXButton.ButtonType.RAISED);
        cancel.setStyle("-fx-background-color: #34edcf; -fx-background-radius: 25;");
        dialogLayout.setActions(cancel);

        //Set the content to dialogLayout declared before
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.setStyle("-fx-background-color: transparent");

        cancel.setOnAction((action) -> {
            dialog.close();
            repeatFocus(logoView);
        });

        dialog.show();
        repeatFocus(dialog);
    }

    @FXML
    protected void onPlayAction(ActionEvent event)
    {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        //System.out.println(getClass().getResource("/Resources/Views/SelectionStage.fxml"));
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resources/Views/SelectionStage.fxml"));
            currentStage.setScene( new Scene(root,  currentScene.getWidth(), currentScene.getHeight()) );
        }
        catch (IOException e) {
           System.out.println("Couldn't open SelectionStage");
        }
    }

    @FXML
    protected void onMouseEnteredHandler(MouseEvent event)
    {
        Button button = (Button) event.getSource();
        ViewAnimations.scaleControl(button, ScaleValue.BIGGER);
    }

    @FXML
    protected void onMouseExitedHandler(MouseEvent event)
    {
        Button button = (Button) event.getSource();
        ViewAnimations.scaleControl(button, ScaleValue.MEDIUM);
    }

    @FXML
    protected void onLogoAction()
    {
        logoView.setRotate(0);
        RotateTransition transition = new RotateTransition(Duration.millis(300), logoView);
        transition.setToAngle(360);

        transition.play();
    }

    //This fixes gets the focus to the imageView Look https://stackoverflow.com/questions/12744542/requestfocus-in-textfield-doesnt-work
    private void repeatFocus(Node node)
    {
        Platform.runLater( () -> {
            if(!node.isFocused()) {
                logoView.requestFocus();
                repeatFocus(node);
            }
        });
    }

}
