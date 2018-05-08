package bicome.logic.world;
/**
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
   private int cooldown;
   private double survivalChance;
   private boolean pregnant;
   private int age;
   private FeatureList features;
   private Environment habitat;
   private Attribute[] attributes;
   private Color color;
   
   public Organism( FeatureList features, Environment worldEnvironment )
   {
      // stub
      cooldown = 0;
      age = 0;
      pregnant = false;
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
   
   public void increaseCooldown()
   {
      if ( cooldown % 3 == 0 && cooldown != 0 )
      {
         cooldown = 0;
         setReproductionCooldown( false );
      }
      else
         cooldown++;
   }
   
   public void age()
   {
      age++;
      survivalChance--;
   }
   
   public void setReproductionCooldown( boolean state )
   {
      pregnant = state;
   }
   
   public boolean canReproduce()
   {
      return !pregnant;      
   }
   
   public Organism reproduce( Organism other )
   {
      Organism offspring;
      FeatureList newFeatures;
      
      this.setReproductionCooldown( true );
      other.setReproductionCooldown( true );
      
      if ( this.features == null || other.features == null )
      {
         newFeatures = null;
      }
      
      else
      {
         newFeatures = new FeatureList();
         for ( int i = 0; i < features.size(); i++ )
            newFeatures.add( new Feature( FeatureList.getBase( i ), 
                                         Genotype.cross( this.getFeatures().get( i ).getGenotype(), 
                                                        other.getFeatures().get( i ).getGenotype() ) ) );
      }
      
      offspring = new Organism( newFeatures, habitat );
      
      //offspring.setReproductionCooldown( true );  //This might be not needed, may even be bad for the algorithm
      //On a second thought i am sure that this line would break the game by making it a lot harder as if it is
      //not hard enough.
      
      return offspring;
      
   }
   
   public FeatureList getFeatures()
   {
      return features;
   }
   
   private void calculateSurvivalChance()
   {
      // the per cent survival chance of an organism is the geometric mean of atrribute values ( max 100%, min 0% )
      survivalChance = 1;
      for ( Attribute a : attributes )
      {
         survivalChance *= a.getValue();
      }
      survivalChance = Math.pow( survivalChance, 1.0 / attributes.length );
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
         result.append( a.getType() );
         result.append( ": " ); 
         result.append( a.getValue() );
         result.append( "\n" );
      }
      result.append( "Survival Chance: " );
      result.append( survivalChance ); 
      result.append( "%\n" );
      result.append( "This organism can" );
      if ( pregnant )
         result.append( "not" );
      result.append( " reproduce right now.\n" );
      result.append( "Features:\n" );
      result.append( features );
      return result.toString();
   }
}
