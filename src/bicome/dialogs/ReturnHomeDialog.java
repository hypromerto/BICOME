package bicome.dialogs;
/**
 * @author Onur Sahin
 * @version 11/05/2018
 *
 * Simple class for returning home dialogs
 */

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReturnHomeDialog extends Alert
{

    public ReturnHomeDialog()
    {
        super(AlertType.CONFIRMATION);
        setHeaderText("Are you sure to abort the game?");
    }
}
