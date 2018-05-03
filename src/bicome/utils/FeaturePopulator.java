package bicome.utils;
/**
 * Class for featureBase adding featureBases to a list
 * @author Onur Şahin
 */

import bicome.logic.feature.*;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;

public class FeaturePopulator
{
    private static final ArrayList<FeatureBase> features = new ArrayList<FeatureBase>() {{
       add(FoodStorageOrgan.getInstance());
       add(Fur.getInstance());
       add(Hump.getInstance());
       add(Leg.getInstance());
       add(Muscles.getInstance());
       add(Wing.getInstance());
    }};

    /**
     * Adds features to the list
     * @param list list to be added these features
     * @throws NullPointerException if list is null
     */
    public static  void PopulateFeatures(List<FeatureBase> list)
    {
        if(list == null)
            throw new NullPointerException("list was null");

        list.addAll(features);
    }
}
