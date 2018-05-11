package bicome.utils;
/**
 * @author Onur Sahin
 * @version 11/05/2018
 * This class is created for the same navigation method between pages
 */

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PageNavigator
{
    /**
     * navigate from oldSceneRoot to newSceneRoot
     * @param oldSceneRoot the root of the current page
     * @param newSceneRoot the root of the new page
     */
    public static void navigate(Pane oldSceneRoot, Pane newSceneRoot)
    {
        Scene currentScene = oldSceneRoot.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight()) );
        if(!currentStage.isShowing())
            currentStage.show();
    }
}
