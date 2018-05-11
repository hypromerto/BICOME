package bicome.dialogs;

import bicome.logic.environment.Environment;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
        setContent(getLayout( env.getImage()));
    }

    private JFXDialogLayout getLayout( Image img)
    {

        ImageView imageView = new ImageView(img);
        Label envNameLabel = new Label("Name: " + environment.toString()) {{
            setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
            //setFont(FontWeight.BOLD);
            setTextFill(Paint.valueOf("#000000"));
            //setTextFill(Paint.valueOf("#ffffff"));
        }};
        Label envConditionsLabel = new Label("Conditions:\n" + environment.getConditionsForGUI()) {{
            setFont(defaultFont);
            setTextFill(Paint.valueOf("#000000"));
        }};
        JFXDialogLayout layout = new JFXDialogLayout(){{
            setStyle("-fx-background-color: #66bb6a");
            setHeading( imageView);
                    //envNameLabel);
            setBody(new VBox( 5, envNameLabel, envConditionsLabel) {{
                                //Set margin of the dialog
                                for(Node node : this.getChildren()) {
                                    setMargin(node, defaultInsets);
                                }
                    }}
            );
        }};
        //VBox vbox = new VBox(int 5, imageView, envNameLabel, envConditionsLabel);

        return layout;
    }

    private static final Insets defaultInsets = new Insets(10);
    private static final Font defaultFont = new Font("Segoe UI", 14);
}
