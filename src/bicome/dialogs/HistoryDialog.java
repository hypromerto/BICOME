package bicome.dialogs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Paint;

public class HistoryDialog extends JFXDialog
{
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
}
