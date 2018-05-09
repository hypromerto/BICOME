package bicome.utils;
/**
 * Class for featureBase adding featureBases to a list
 * @author Onur Sahin
 */

import bicome.logic.feature.*;
import bicome.logic.genotype.Genotype;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;

public class FeaturePopulator
{
    //Initialize the list to be added
    private static final ArrayList<FeatureBase> features = new ArrayList<FeatureBase>() {{
       add(FoodStorageOrgan.getInstance());
       add(Fur.getInstance());
       add(Hump.getInstance());
       add(Leg.getInstance());
       add(Muscles.getInstance());
       add(Wing.getInstance());
    }};

    private static final ArrayList<Feature> featureList = new ArrayList<Feature>() {{
        for(FeatureBase fb : features) {
            add(new Feature(fb, Genotype.NONE));
        }
    }};

    /**
     * Adds features to the list
     * @param list list to be added these features
     * @throws NullPointerException if list is null
     */
    public static void populateFeatures(List<FeatureBase> list)
    {
        if(list == null)
            throw new NullPointerException("List was null");

        list.addAll(features);
    }

    public static void completeFeatures(List<Feature> list)
    {
        if(list == null)
            throw new NullPointerException("List was null");

        List<Feature> result =  new ArrayList<>(featureList);

        //We could do this with two for loops because it would work when we add other features too however it would be a lot slower for high number of features
        for(Feature f : list) {
            FeatureBase base = f.getBase();
            int index;
            if(base.equals(FoodStorageOrgan.getInstance())) {
                index = 0;
            }
            else if(base.equals(Fur.getInstance())) {
                index = 1;
            }
            else if(base.equals(Hump.getInstance())) {
                index = 2;
            }
            else if(base.equals(Leg.getInstance())) {
                index = 3;
            }
            else if(base.equals(Muscles.getInstance())) {
                index = 4;
            }
            else { //base.equals(Wing.getInstace()
                index = 5;
            }

            list.set(index, f);
        }
    }
}
