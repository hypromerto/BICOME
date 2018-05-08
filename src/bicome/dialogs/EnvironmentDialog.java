package bicome.dialogs;

import bicome.logic.environment.Environment;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EnvironmentDialog extends JFXDialog
{
    private Environment environment;

    public EnvironmentDialog( StackPane dialogContainer, Environment env )
    {
        super();
        environment = env;
        setTransitionType(DialogTransition.CENTER);
        setDialogContainer(dialogContainer);
        setContent(getLayout());
    }

    private JFXDialogLayout getLayout()
    {
        JFXButton ok = new JFXButton("OK"){{
            setStyle("-fx-background-radius: 25; -fx-background-color: TODO");
            setWidth(150);
            setHeight(50);
            setOnAction( event -> close() );
        }};
        JFXDialogLayout layout = new JFXDialogLayout(){{

           setBody(
                   new VBox(new ImageView(/*This will return the image of the Environment*/),
                            new Label(environment.toString()),
                            new Label(/*This will have the properties of the environment*/))
           );
           setActions(ok);
        }};

        return layout;
    }
}
