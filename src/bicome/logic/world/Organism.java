package bicome.logic.world;
/**
 * @author Mert Aslan, Ege Balcioglu
 * @version 27.4.2018
 */
import java.util.*;
import bicome.logic.feature.*;
import bicome.logic.attribute.*;
import bicome.logic.environment.*;
import bicome.logic.genotype.Genotype;
public class Organism 
{
   private int cooldown;
   private boolean pregnant;
   private int age;
   // the exact type of List is not specified to ease up optimization.
   private FeatureList features;
   private Environment habitat;
   private Attribute[] attributes;
   
   public Organism( FeatureList features, Environment worldEnvironment )
   {
      // stub
      cooldown = 0;
      age = 0;
      pregnant = false;
      this.features = features;
      attributes = new Attribute[ 5 ];
      setAttributesFromEnvironment( worldEnvironment );
      habitat = worldEnvironment;
      calculateSurvivalChance();
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
      
      offspring.setReproductionCooldown( true );
      
      return offspring;
      
   }

public FeatureList getFeatures()
{
   return features;
}
   
   private void calculateSurvivalChance()
   {
      // stub
   }
   
   public void setAttributesFromEnvironment( Environment env )
   {
      // set proper multipliers in Feature classes
      for ( int i = 0; i < features.size(); i++ )
      {
         env.filter( features.get(i) );
      }
      
      // apply multipliers to Attributes
      for ( Feature f : features )
      {
         for ( int i = 0; i < attributes.length; i++ )
         {
            attributes[i].calculate( f.getMultipliers()
                                       .getOrDefault( attributes[i]
                                                        .getType(), 1.0 ) );
         }
      }
   }
}
