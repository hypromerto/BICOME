package bicome.database;

import bicome.logic.environment.Environment;
import bicome.logic.feature.Feature;
import bicome.logic.feature.FeatureList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Olcay Akman, Hakki Burak Okumus
 * @version 08.05.2018
 */
public class Report
{

    //properties
    
    Environment environment;
    FeatureList featureList;

    //constructors
    public Report(Environment environment, FeatureList featureList)
    {
        //TODO
        this.environment = environment;
        this.featureList = featureList;
    }

    //methods
    
    /**
     * Establishes a connection with the embedded server
     */
    public void connect()
    {
        Connection conn = null;
        try
        {
            // db parameters
            String url = "jdbc:derby:database;create=true";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connected to the database.");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Creates the main report table which will be shown on the history menu
     */
    public void createReportTable()
    {
        // SQL connection string
        String url = "jdbc:derby:database;create=true";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS reporttable (\n"
                + " id integer PRIMARY KEY,\n"
                + " name VARCHAR(30) NOT NULL,\n"
                + " date DATE,\n" //new Date() gives the curent date. to be used...
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param featureList features of the animal
     * 
     * creates the table which will show the list of selected features
     * 
     */
    public void createAnimalTable(FeatureList featureList)
    {
        // SQL connection string
        String url = "jdbc:derby:database;create=true";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS animaltable (\n"
                + " id integer PRIMARY KEY,\n"
                + " foodStorageOrgan VARCHAR(100),\n"
                + "     furType VARCHAR(15),\n"
                + "     hump VARCHAR(15),\n"
                + "     leg VARCHAR(15),\n"
                + "     muscles VARCHAR(20),\n"
                + "     wing VARCHAR(15)\n)"
                + ");";

        sql += "\nINSERT INTO animalTable (id, foodStorageOrgan, furType, hump, leg, muscles, wing)\n"
                + "VALUES ( 1, " + getFeatures(featureList).get(0) + ", " + getFeatures(featureList).get(1) + ", "
                + getFeatures(featureList).get(2) + ", " + getFeatures(featureList).get(3) + ", "
                + getFeatures(featureList).get(4) + ", " + getFeatures(featureList).get(5) + ")";

        //fittest animal features to be added...
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param environment parameter which contains the environment variables
     * creates the table which will show the list of environmental conditions
     */
    public void createEnvironmentTable(Environment environment)
    {
        // SQL connection string
        String url = "jdbc:derby:database;create=true";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS environmentTable (\n"
                + " id integer PRIMARY KEY,\n"
                + " environmentalFactor VARCHAR(40) NOT NULL,\n"
                + " condition VARCHAR(3),\n"
                + ");";

        for (int i = 0; i < getEnvironmentalFactors(environment).size(); i++)
        {
            sql += "\nINSERT INTO environmentTable (id, environmentalFactor, condition)\n"
                    + "VALUES ( " + (i + 1) + ", " + getEnvironmentalFactors(environment).get(i) + ", "
                    + getEnvironmentalFactorConditions(environment) + ")";
        }

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
        {
            // create a new table
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param featureList list that contains the features
     * @return features of the current animal
     */
    public ArrayList<String> getFeatures(FeatureList featureList)
    {
        ArrayList<String> features = new ArrayList<>();

        for (Feature feature : featureList)
        {
            if (feature.toString().toLowerCase().equals("foodstorageorgan"))
            {
                String foodStorageOrgan = feature.toString();
                features.add(0, foodStorageOrgan);
            }
            else if (feature.toString().toLowerCase().equals("fur"))
            {
                String fur = feature.toString();
                features.add(1, fur);
            }
            else if (feature.toString().toLowerCase().equals("hump"))
            {
                String hump = feature.toString();
                features.add(2, hump);
            }
            else if (feature.toString().toLowerCase().equals("leg"))
            {
                String leg = feature.toString();
                features.add(3, leg);
            }
            else if (feature.toString().toLowerCase().equals("muscles"))
            {
                String muscles = feature.toString();
                features.add(4, muscles);
            }
            else if (feature.toString().toLowerCase().equals("wing"))
            {
                String wing = feature.toString();
                features.add(5, wing);
            }

        }
        return features;
    }

    public ArrayList<String> getEnvironmentalFactorConditions(Environment environment)
    {
        String[] factorConditions = environment.getConditions().split(", ");
        return new ArrayList<>(Arrays.asList(factorConditions));
    }

    public ArrayList<String> getEnvironmentalFactors(Environment environment)
    {
        String[] factors = environment.toString().split(" ");
        return new ArrayList<>(Arrays.asList(factors));
    }

}