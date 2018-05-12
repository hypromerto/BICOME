import bicome.dialogs.ExitDialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./Resources/Views/StartStage.fxml"));
        primaryStage.setTitle("B.I.C.O.M.E");
        primaryStage.setScene(new Scene(root, 920, 690));
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1000);

        primaryStage.setOnCloseRequest(e -> {
            Optional<ButtonType> result = new ExitDialog().showAndWait();
            if(!result.isPresent() || result.get().equals(ButtonType.CANCEL))
                e.consume();
        });

        primaryStage.show();
    }
    
    public static void main(String[] args) {
       launch(args);
    }
}
