package bicome.dialogs;

import bicome.logic.feature.FeatureBase;
import bicome.logic.genotype.Genotype;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FeatureDialog
{
    private static final ToggleGroup group = new ToggleGroup();
    private static final JFXRadioButton dominantHomoButton = new JFXRadioButton("Homozygot dominant") {{
        setPadding(new Insets(10));
        setToggleGroup(group);
        setSelected(true);
    }};
    private static final JFXRadioButton dominantHeteroButton = new JFXRadioButton("Heterozygot dominant") {{
        setPadding(new Insets(10));
        setToggleGroup(group);
    }};
    private static final JFXRadioButton reccesiveHomoButton = new JFXRadioButton("Homozygot reccesive") {{
        setPadding(new Insets(10));
        setToggleGroup(group);
    }};

    public static Genotype showDialog(FeatureBase base, StackPane dialogContainer)
    {
        Genotype result;
        JFXDialog dialog = new JFXDialog();
        dialog.setDialogContainer(dialogContainer);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Choose the genotype of the feature: " + base.getName()));

        layout.setBody(new VBox() {{
            getChildren().add(dominantHomoButton);
            getChildren().add(dominantHeteroButton);
            getChildren().add(reccesiveHomoButton);
        }});

        JFXButton okButton = new JFXButton("pick");
        okButton.setOnAction( event -> {
            //To Do...
        } );

        layout.setActions(okButton);

        //return result;
        return Genotype.NONE;
    }
}
