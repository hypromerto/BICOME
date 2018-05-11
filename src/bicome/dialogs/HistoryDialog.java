package bicome.dialogs;

import bicome.logic.environment.Environment;
import bicome.logic.manager.GameManager;
import bicome.logic.world.Organism;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

import java.awt.*;

public class HistoryDialog extends JFXDialog
{
    public HistoryDialog()
    {
        super();
    }

    private final JFXListView historyListView = new JFXListView(){{
        //getItems().addAll(); add the past games
    }};

    private final JFXDialogLayout layout = new JFXDialogLayout(){{
       setHeading(new Label("Past games") {{
           setTextFill(Paint.valueOf("000000"));
       }});
       setBody(new ScrollPane(historyListView) {{
           setStyle("-fx-background-color: #66bb6a");
       }});
    }};

    private class HistoryDialogCell extends ListCell<GameManager>
    {
        @Override
        public void updateItem(GameManager manager, boolean empty)
        {
            super.updateItem(manager, empty);
            if(manager != null)
            {
                //boolean won;
                Environment environment = manager.getWorld().getEnvironment();
                Organism firstOrganism = manager.getWorld().getFirstOrganism();
                //Organism lastOrganism;
                setContent(new HBox(
                        new Label(/*won ? "Win" : "Lose"*/),
                        new Label(environment.toString()),
                        new JFXButton("Expand") {{
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            //setGraphic();
                            setOnAction(e -> {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/Views/ReflectionStage.fxml"));
                                
                            });
                        }}
                ));
            }
        }
    }
}
