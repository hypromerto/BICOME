package bicome.controllers;

import bicome.utils.FeaturePopulator;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import bicome.logic.feature.FeatureBase;

public class SelectionController implements Initializable
{
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
        FeaturePopulator.PopulateFeatures(unSelectedFeaturesListView.getItems());
    }
}
