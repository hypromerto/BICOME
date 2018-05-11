package bicome.dialogs;

import bicome.database.Report;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class HistoryDialog extends JFXDialog
{
    private JFXDialogLayout layout = new JFXDialogLayout(){{
       ArrayList<String> games = new ArrayList<String>( Arrays.asList(Report.returnReport().split("\n")) );
       ArrayList<Label> content = new ArrayList<Label>() {{
          games.forEach(text -> add(new Label(text) {{
              setMaxWidth(500);
              setWrapText(true);
              setTextFill(Paint.valueOf("#ffffff"));
          }}));
       }};
        ListView<Label> gamesListView = new ListView<Label>() {{
           getItems().addAll(content);
        }};
        setStyle("-fx-background-color: #4CAF50");
        setHeading(new Label("Past games"));
        setBody(gamesListView);
        getStylesheets().add("/Resources/Styles/ListViewStyle.css");
    }};

    public HistoryDialog()
    {
        super();
        setContent(layout);
    }
}
