package bicome.controllers;

import bicome.logic.feature.FeatureBase;
import bicome.utils.FeaturePopulator;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionController implements Initializable
{
    @FXML
    private StackPane rootPane;

    @FXML
    JFXListView<FeatureBase> selectedFeaturesListView;

    @FXML
    JFXListView<FeatureBase> unSelectedFeaturesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set the listView to the expanded mode (Looks cooler ;) )
        selectedFeaturesListView.setExpanded(true);
        unSelectedFeaturesListView.setExpanded(true);
        //Add all features to unselected list
        FeaturePopulator.populateFeatures(unSelectedFeaturesListView.getItems());
    }

    @FXML
    protected void onFeatureSelected(MouseEvent event)
    {
        JFXListView<FeatureBase> listView = (JFXListView<FeatureBase>)event.getSource();

        FeatureBase selected = listView.getSelectionModel().getSelectedItem();

        //To Do get selected genotype and create a feature with that genotype then add it to the selectedFeaturesListView
    }
}
