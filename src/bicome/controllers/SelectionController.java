package bicome.controllers;

import bicome.dialogs.FeatureDialog;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureBase;
import bicome.logic.genotype.Genotype;
import bicome.utils.FeaturePopulator;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;
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
}
