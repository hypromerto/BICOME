package bicome.logic.world;
/**
 * A single, hermaphrodite, fictional organism that has Attributes and Features, and is living in a particular Environment 
 * @author Mert Aslan, Ege Balcioglu
 * @version 27.4.2018
 */
import javafx.scene.paint.Color;
import java.util.*;
import bicome.logic.feature.*;
import bicome.logic.attribute.*;
import bicome.logic.environment.*;
import bicome.logic.genotype.Genotype;

public class Organism 
{
   private double survivalChance;
   private int age;
   private FeatureList features;
   private Environment habitat;
   private Attribute[] attributes;
   private Color color;
   
   /**
    * Sole constructor.
    * @param features the list of Features of this Organism
    * @param worldEnvironment the habitat of this organism's population
    */
   public Organism( FeatureList features, Environment worldEnvironment )
   {
      age = 0;
      this.features = features;
      attributes = new Attribute[ 5 ];
      attributes[ 0 ] = new Power();
      attributes[ 1 ] = new Speed();
      attributes[ 2 ] = new FlightEase();
      attributes[ 3 ] = new WaterStockpiling();
      attributes[ 4 ] = new NutritionStockpiling();
      setAttributesFromEnvironment( worldEnvironment );
      habitat = worldEnvironment;
      setColor();
      
      calculateSurvivalChance(); //This has to be done in the end of the constructor
   }
   
   /**
    * Makes the organism age by one year, also decreasing its survival chance accordingly
    */
   public void age()
   {
      age++;
      survivalChance--;
   }
   
   /**
    * Makes this Organism mate with a partner and returns the offspring.
    * @param other this organism's mating partner
    * @return the offspring. Each reproduction act only produces one offspring.
    */
   public Organism reproduce( Organism other )
   {
      Organism offspring;
      FeatureList newFeatures;
      
      if ( this.features == null || other.features == null )
      {
         newFeatures = null;
      }
      
      else
      {
         newFeatures = new FeatureList();
         for ( int i = 0; i < features.size(); i++ )
         {
            newFeatures.add( new Feature( FeatureList.getBase( i ), 
                                         Genotype.cross( this.getFeatures().get( i ).getGenotype(), 
                                                        other.getFeatures().get( i ).getGenotype() ) ) );
         }
      }
      
      offspring = new Organism( newFeatures, habitat );
      
      return offspring;
   }
   
   /**
    * returns the list of features this organism possesses.
    * @return this organism's FeatureList
    */
   public FeatureList getFeatures()
   {
      return features;
   }
   
   /**
    * calculates the per cent survival chance of this organism
    */
   private void calculateSurvivalChance()
   {
      // the per cent survival chance of an organism is the geometric mean of atrribute values ( max 100%, min 0% )
      survivalChance = 1;
      for ( Attribute a : attributes )
      {
         survivalChance *= a.getValue();
      }
      survivalChance = Math.pow( survivalChance, 1.0 / attributes.length );
      
      if ( survivalChance < 0.0 )
      {
         survivalChance = 0.0;
      }
      
      else if ( survivalChance > 100.0 )
      {
         survivalChance = 100.0;
      }
   }
   
   public void setAttributesFromEnvironment( Environment env )
   {
      if ( env != null )
      {
         // set proper multipliers in Feature classes
         for ( int i = 0; i < features.size(); i++ )
         {
            env.filter( features.get( i ) );
         }
      }
      
      // apply multipliers to Attributes
      for ( Feature f : features )
      {
         for ( int i = 0; i < attributes.length; i++ )
         {
            attributes[i].calculate( f.getMultipliers().getOrDefault( attributes[i].getType(), 1.0 ) );
         }
      }
   }
   
   private void setColor()
   {
      final double SCALING_FACTOR = 1.0 / features.size();
      final double OPACITY = 1.0;
      double r, g, b;
      r = 0.0;
      g = 0.0;
      b = 0.0;
      for ( int i = 0; i < features.size(); i++ )
      {
         if ( features.get( i ).getGenotype() == Genotype.NONE )
            b += SCALING_FACTOR;
         else if ( features.get( i ).getGenotype() == Genotype.RECESSIVE_HOMOZYGOTE )
            g += SCALING_FACTOR;
         else
            r += SCALING_FACTOR;
      }
      color = Color.color( r, g, b, OPACITY );
   }
   
   public Color getColor()
   {
      return color;
   }
   
   public double getSurvivalChance()
   {
      return survivalChance;
   }
   
   public String toString()
   {
      StringBuffer result;
      result = new StringBuffer( "" );
      for ( Attribute a : attributes )
      {
         result.append( a.getName() );
         result.append( ": " ); 
         result.append( a.getValue() );
         result.append( "\n" );
      }
      result.append( "Age: ");
      result.append( age );
      result.append( "\n" );
      result.append( "Survival Chance: " );
      result.append( survivalChance ); 
      result.append( "%\n" );
      result.append( "Features:\n" );
      result.append( features );
      return result.toString();
   }
}
