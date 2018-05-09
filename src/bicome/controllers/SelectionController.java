package bicome.controllers;

import bicome.dialogs.FeatureDialog;
import bicome.logic.environment.Environment;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureBase;
import bicome.logic.feature.FeatureList;
import bicome.logic.genotype.Genotype;
import bicome.logic.world.World;
import bicome.utils.FeaturePopulator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SelectionController implements Initializable
{
    @FXML
    private StackPane rootPane;

    @FXML
    JFXListView<Feature> selectedFeaturesListView;

    @FXML
    JFXListView<FeatureBase> unSelectedFeaturesListView;

    private ObservableList<Feature> selectedList;
    private ObservableList<FeatureBase> unSelectedList;
    private Environment environment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set the listView to the expanded mode (Looks cooler ;) )
        selectedFeaturesListView.setExpanded(true);
        unSelectedFeaturesListView.setExpanded(true);

        //Initialize the observables
        selectedList = FXCollections.observableArrayList();
        unSelectedList = FXCollections.observableArrayList();

        //Add all features to unselected list
        FeaturePopulator.populateFeatures(unSelectedList);

        //Link the observables
        selectedFeaturesListView.setItems(selectedList);
        unSelectedFeaturesListView.setItems(unSelectedList);
        environment = new Environment();
    }

    @FXML
    protected void onFeatureSelected(MouseEvent event)
    {
        JFXListView<FeatureBase> listView = (JFXListView<FeatureBase>) event.getSource();

        FeatureBase selected = listView.getSelectionModel().getSelectedItem();
        Optional<Genotype> selectedGene = FeatureDialog.showDialog(selected);
        Feature feature;

        //If gene is selected add it to the selectedList and remove from unSelectedList
        if(selectedGene.isPresent()) {
            feature = new Feature(selected, selectedGene.get());
            unSelectedList.remove(selected);
            selectedList.add(feature);
        }
        else {
            System.out.println("The featureBase " + selected.toString() + "counldn't add beacuse genotype haven't selected.");
        }
    }

    @FXML
    protected void onFeatureUnSelected(MouseEvent event)
    {
        JFXListView<Feature> listView = (JFXListView<Feature>) event.getSource();

        Feature selected = listView.getSelectionModel().getSelectedItem();
        FeatureBase base = selected.getBase();

        selectedList.remove(selected);
        unSelectedList.add(base);
    }

    @FXML
    protected void onBackAction(ActionEvent event)
    {
        //Some messy code but there is no other way for javafx :(
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Resources/Views/StartStage.fxml"));
            //Use currentScene's width and height in order to not change the size of the window while navigating
            currentStage.setScene( new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        }
        catch (IOException e) {
            //Show error dialog if exception occurs
            JFXDialog dialog = new JFXDialog();
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("Error: " + e.getMessage() + ". Please try again."));
            JFXButton cancel = new JFXButton("OK") {{
               setOnAction( event1 -> dialog.close());
            }};
            layout.setActions(cancel);
            dialog.setContent(layout);
            /**dialog.setOnDialogClosed( jfxDialogEvent -> {
                onBackAction(event);
            });*/ //I am not sure about this

            dialog.show(rootPane);
        }
    }

    @FXML
    protected void onNextAction(ActionEvent event) {
        //Set unselected features to NONE
        FeaturePopulator.completeFeatures(selectedList);
        FeatureList list = new FeatureList(selectedList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/Views/GameStage.fxml"));
        try {
            BorderPane borderPane = loader.load();
            GameController controller = loader.getController();
            controller.setManager( environment, list);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(borderPane, rootPane.getWidth(), rootPane.getHeight()));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("An error occured while loading the GameStage: " + e.getMessage());
        }
    }
}
