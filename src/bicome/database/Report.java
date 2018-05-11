package bicome.database;

import bicome.logic.environment.Environment;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureList;
import bicome.logic.manager.GameManager;
import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Olcay Akman, Hakki Burak Okumus
 * @version 08.05.2018
 */
public class Report
{

    //properties
    // gamemanager.getworld.getfirstorganism.getfeatures
    // gamemanger.getworld.getenvironment
    GameManager gameManager;
    FileReader reader;
    FileWriter writer;
    BufferedReader bufferedReader;
    SimpleDateFormat dateFormatter;
    Date date;

    //constructors
    public Report(GameManager gameManager)
    {
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.gameManager = gameManager;
        try
        {
            reader = new FileReader("data.txt");
            writer = new FileWriter("data.txt");
            bufferedReader = new BufferedReader(reader);
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    //methods
    /**
     * Creates the main report table which will be shown on the history menu
     * writes it into data.txt
     */
    public void createReport()
    {
        String text = "";
        String line = null;

        try
        {
            if (bufferedReader.readLine() == null)
            {
                writer.write("Game 1 ");
            } else
            {
                int counter = 1;
                while ((line = bufferedReader.readLine()) != null)
                {
                    text += line + "\n";
                    counter++;
                }
                writer.write(text);
                writer.write("Game " + counter + " ");
            }
            date = new Date();
            writer.write("Date: " + dateFormatter.format(date) + " ");
            for (String s : getFeatures(gameManager.getWorld().getFirstOrganism().getFeatures()))
            {
                writer.write(s + " ");
            }
            for (String s : getEnvironmentalFactorConditions(gameManager.getWorld().getEnvironment()))
            {
                writer.write(s + " ");
            }

            writer.write(gameManager.getYearsPassed() + " years passed, ");
            writer.write("survival rate: " + gameManager.getWorld().getFinalSurvivalRate() + " ");
            if (gameManager.gameIsWon())
            {
                writer.write("Result: won");
            } else
            {
                writer.write("Result: lost");
            }
            writer.flush();

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static String returnReport()
    {
        String result = "";
        String line = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @param featureList list that contains the features
     * @return features of the current animal
     */
    private ArrayList<String> getFeatures(FeatureList featureList)
    {
        ArrayList<String> features = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            features.add("");
        }

        for (Feature feature : featureList)
        {
            if (feature.toString().toLowerCase().equals("foodstorageorgan"))
            {
                String foodStorageOrgan = feature.toString();
                features.add(0, foodStorageOrgan);
            } else if (feature.toString().toLowerCase().equals("fur"))
            {
                String fur = feature.toString();
                features.add(1, fur);
            } else if (feature.toString().toLowerCase().equals("hump"))
            {
                String hump = feature.toString();
                features.add(2, hump);
            } else if (feature.toString().toLowerCase().equals("leg"))
            {
                String leg = feature.toString();
                features.add(3, leg);
            } else if (feature.toString().toLowerCase().equals("muscles"))
            {
                String muscles = feature.toString();
                features.add(4, muscles);
            } else if (feature.toString().toLowerCase().equals("wing"))
            {
                String wing = feature.toString();
                features.add(5, wing);
            }

        }
        return features;
    }

    private ArrayList<String> getEnvironmentalFactorConditions(Environment environment)
    {
        String[] factorConditions = environment.getConditions().split(", ");
        return new ArrayList<>(Arrays.asList(factorConditions));
    }

    private ArrayList<String> getEnvironmentalFactors(Environment environment)
    {
        String[] factors = environment.toString().split(" ");
        return new ArrayList<>(Arrays.asList(factors));
    }

}
