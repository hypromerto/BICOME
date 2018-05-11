package bicome.dialogs;

import bicome.controllers.ReflectionController;
import bicome.logic.environment.Environment;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import com.jfoenix.controls.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.io.IOException;

public class HistoryDialog extends JFXDialog
{
    private StackPane pane;

    public HistoryDialog(StackPane pane)
    {
        super();

        this.pane = pane;
    }

    private final JFXDialogLayout layout = new JFXDialogLayout(){{
       setHeading(new Label("Past games") {{
           setTextFill(Paint.valueOf("000000"));
       }});
       setBody(

       );
    }};

    private class HistoryPane extends VBox
    {
        public HistoryPane(String winLose, String survivalRate, String years, String firstAnimalProps, String lastAnimalProps, String EnvConditions)
        {
            super(
                    new Label(winLose),
                    new Label("Survival rate: " + survivalRate),
                    new Label(years + " years"),
                    new Label(lastAnimalProps),
                    new Label(EnvConditions)
                    );
        }
    }
}
