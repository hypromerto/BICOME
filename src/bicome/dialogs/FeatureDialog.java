package bicome.dialogs;

import bicome.logic.feature.FeatureBase;
import bicome.logic.genotype.Genotype;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class FeatureDialog
{
    private static final ButtonType HOMOZYGOT_DOMI = new ButtonType("Ho-D");
    private static final ButtonType HETEROZYGOT_DOMI = new ButtonType("He-D");
    private static final ButtonType HOMOZYGOT_RECCESS = new ButtonType("Ho-R");

    /**
     * A method to return genotype for a FeatureBase
     * @param base base of the feature that has been choosen
     * @return genotype that selected in dialog, otherwise Optional.empty()
     * @see Optional
     */
    public static Optional<Genotype> showDialog(FeatureBase base)
    {
        Genotype genotype;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION) {{
            setTitle("Choose genotype for " + base.toString());
            setHeaderText("Choose the genotype for your feature");

            getButtonTypes()
                    .setAll(HOMOZYGOT_DOMI,
                            HETEROZYGOT_DOMI,
                            HOMOZYGOT_RECCESS,
                            ButtonType.CANCEL);
        }};

        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent()) {
            genotype = null;
        }
        else if(result.get() == HOMOZYGOT_DOMI) {
            genotype = Genotype.DOMINANT_HOMOZYGOTE;
        }
        else if(result.get() == HETEROZYGOT_DOMI) {
            genotype = Genotype.DOMINANT_HETEROZYGOTE;
        }
        else { //if(result.get() == HOMOZYGOT_RECCESS)
            genotype = Genotype.RECESSIVE_HOMOZYGOTE;
        }

        //return result;
        return Optional.ofNullable(genotype);
    }
}
