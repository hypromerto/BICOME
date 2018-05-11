package bicome.controllers;

import bicome.animations.ScaleValue;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import bicome.animations.ViewAnimations;
import bicome.dialogs.EnvironmentDialog;
import bicome.dialogs.FeatureDialog;
import bicome.logic.environment.Environment;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureBase;
import bicome.logic.feature.FeatureList;
import bicome.logic.genotype.Genotype;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
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
    AnchorPane anchor;

    @FXML
    JFXListView<FeatureBase> unSelectedFeaturesListView;

    @FXML
    ImageView rightWingDom, leftWingDom;

    @FXML
    ImageView rightWingRec, leftWingRec;

    @FXML
    ImageView furDom, furRec;

    @FXML
    ImageView fso1, fso2, fso3, fso4, fso5;

    @FXML
    ImageView humpDom, humpRec;

    @FXML
    ImageView legsDom, legsRec;

    @FXML
    ImageView rightMuscleDom, leftMuscleDom;

    @FXML
    ImageView rightMuscleRec, leftMuscleRec;

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
        //Add all NONEs to selectedList
        FeaturePopulator.addNoneFeatures(selectedList);

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
            for(Feature f : selectedList) {
                if(f.getBase().equals(feature.getBase())) {
                    selectedList.remove(f);
                    selectedList.add(feature);
                    constructAnimal(feature,true);
                    return; //break may not be allowed
                }
            }
        }
        else {
            System.out.println("The feature base " + selected.toString() + " couldn't be added beacuse its genotype wasn't selected.");
        }
    }

    @FXML
    protected void onFeatureUnSelected(MouseEvent event)
    {
        JFXListView<Feature> listView = (JFXListView<Feature>) event.getSource();

        Feature selected = listView.getSelectionModel().getSelectedItem();
        if(selected == null || selected.getGenotype().equals(Genotype.NONE)) //We have clicked an empty place don't do anything
            return;
        FeatureBase base = selected.getBase();

        //Remove the feature and add the NONE version of it
        selectedList.remove(selected);
        selectedList.add(new Feature(base, Genotype.NONE));
        unSelectedList.add(base);
        constructAnimal(selected,false);
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
        FeatureList list = new FeatureList(selectedList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/Views/GameStage.fxml"));
        try {
            BorderPane borderPane = loader.load();
            GameController controller = loader.getController();
            controller.setManager( environment, list);
            controller.init();
            saveAnimalImage();
            //Yes this is ugly but there is no other way (same with line: 144)
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(borderPane, rootPane.getWidth(), rootPane.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occured while loading the GameStage: " + e.getMessage());
        }
    }

    @FXML
    protected void onEnvironmentAction(ActionEvent event)
    {
        EnvironmentDialog dialog = new EnvironmentDialog(rootPane, environment);
        dialog.show();
    }

    @FXML
    protected void onMouseEntered(MouseEvent event)
    {
        JFXButton node = (JFXButton) event.getSource();
        ViewAnimations.scaleControl(node, ScaleValue.BIGGER);
    }

    @FXML
    protected void onMouseExited(MouseEvent event)
    {
        JFXButton node = (JFXButton) event.getSource();
        ViewAnimations.scaleControl(node, ScaleValue.MEDIUM);
    }
    
    @FXML
    private void constructAnimal(Feature feature, boolean selected) {

        if (feature.toString().equals("Big Wing")) {
            rightWingDom.setVisible(selected);
            leftWingDom.setVisible(selected);
        }
        else if (feature.toString().equals("Small Wing")) {
             rightWingRec.setVisible( selected);
             leftWingRec.setVisible( selected);
        }

        else if (feature.toString().equals("Four Extra Food Storage Organs")) {
            fso2.setVisible(selected);
            fso3.setVisible(selected);
            fso4.setVisible(selected);
            fso5.setVisible(selected);
        }
        else if (feature.toString().equals("One Extra Food Storage Organs")) {
            fso2.setVisible(selected);
        }
        else if (feature.toString().equals("Thick Fur")){
            furRec.setVisible(selected);
        }
        else if (feature.toString().equals("Thin Fur")) {
            furDom.setVisible(selected);
        }
        else if (feature.toString().equals("Long Leg") ){
            legsDom.setVisible(selected);
        }
        else if (feature.toString().equals("Short Leg")) {
            legsRec.setVisible(selected);
        }
        else if (feature.toString().equals("Small Hump")) {
            humpDom.setVisible(selected);
        }
        else if (feature.toString().equals("Big Hump")) {
            humpRec.setVisible(selected);
        }
        else if (feature.toString().equals("Heavy Muscles")){
            rightMuscleDom.setVisible(selected);
            leftMuscleDom.setVisible(selected);
        }
        else if (feature.toString().equals("Light Muscles")) {
            rightMuscleRec.setVisible(selected);
            leftMuscleRec.setVisible(selected);
        }
    }

    @FXML
    public void saveAnimalImage() {
        WritableImage image = anchor.snapshot(new SnapshotParameters(), null);
        File file = new File("Resources/Images/AnimalPicture");
    }
}
