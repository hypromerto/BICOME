package bicome.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Onur Sahin
 * @version 11/05/2018
 *
 * This class reads past games from the string
 */

public class HistoryReader
{
    public static List<HBox> read(String text)
    {
        ArrayList<HBox> result = new ArrayList<HBox>();
        String[] games = text.split("\n");

        for(String game : games) {
            List<Node> labels = new ArrayList<>();
            String[] args = game.split(" ");
            result.add( new HBox(new Label(args[args.length - 1]),              //Win/lose
                        new Label("survival rate: " + args[args.length - 3],    //survival rate
                        new Label(args[4])) {{          //Date
                setPadding(new Insets(5, 10, 5, 10));
            }}) );
        }

        return result;
    }
}
