package bicome.dialogs;

import javafx.scene.control.Alert;

/**
 * @author Onur Sahin
 * @version 11/05/2018
 *
 * Simple class for exits
 */

public class ExitDialog extends Alert
{
    public ExitDialog()
    {
        super(AlertType.CONFIRMATION);
        setHeaderText("Are you to quit? Any unfinished game will be lost.");
    }
}
