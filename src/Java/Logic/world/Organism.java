package world;
/**
 * @author Mert Aslan, Ege Balcioglu
 * @version 27.4.2018
 */
import java.util.*;
import feature.*;
import attribute.*;
import environment.*;
import genotype.Genotype;
public class Organism 
{
   private int cooldown;
   private boolean pregnant;
   private int age;
   // the exact type of List is not specified to ease up optimization.
   private List<Feature> features;
   private Attribute[] attributes;
   
   public Organism( List<Feature> features, Environment worldEnvironment )
   {
      // stub
      cooldown = 0;
      age = 0;
      pregnant = false;
      this.features = features;
      attributes = new Attribute[ 5 ];
      setAttributesFromEnvironment( worldEnvironment );
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
   
   public Organism reproduce( Organism o )
   {
      Organism offspring;
      
      this.setReproductionCooldown( true );
      o.setReproductionCooldown( true );
      
      offspring = new Organism( null, null/* stub */ );
      
      // stub
      
      return offspring;
      
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
