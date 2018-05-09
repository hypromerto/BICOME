package bicome.logic.feature;
/**
 * The base of a Feature that holds they type and the original (unaffected by EnvCods) multipliers for every possible phenotype of said type.
 * Each feature base has three multiplier maps that map String keys (which come from Attribute subclasses) to Double values (multipliers to be multiplied with said Attributes' values) : one map for a dominant phenotype, one map for a recessive phenotype, and one map for the situation where the phenotype doesn't exist
 * IMPORTANT: ALL SUBCLASSES OF THIS CLASS SHOULD BE IMPLEMENTED WITH THE SINGLETON PATTERN.
 * @author Ege Balcioglu
 * @version 2.5.2018
 */
import java.util.HashMap;
import bicome.logic.genotype.Genotype;

public abstract class FeatureBase
{
   // properties
   protected HashMap< String, Double > dominantMultipliers;
   protected HashMap< String, Double > recessiveMultipliers;
   protected HashMap< String, Double > noneMultipliers;
   
   /**
    * Sole constructor that sets the multipliers of this base
    */
   protected FeatureBase()
   {
      dominantMultipliers = new HashMap<>();
      recessiveMultipliers = new HashMap<>();
      noneMultipliers = new HashMap<>();
      setMultipliers();
   }
   
   // methods 
   protected abstract void setMultipliers();
   
   /**
    * Returns the multiplier map that corresponds to the feature's phenotype. The package private access modifier is intentional.
    * @param genotype the genotype whose phenotype will be considered which map to return
    * @return dominantMultipliers if the genotype is one of the dominant genotypes,
    * recessiveMultipliers if the genotype is recessive
    * noneMultipliers if the genotype is none.
    */
   final HashMap< String, Double > getMultipliers( Genotype genotype )
   {
      if ( genotype == Genotype.RECESSIVE_HOMOZYGOTE )
      {
         return recessiveMultipliers;
      }
      
      if ( genotype == Genotype.NONE )
      {
         return noneMultipliers;
      }
      
      return dominantMultipliers; 
   }
   
   /**
    * returns a String representation of this object
    * @return the simple class name of this object's class
    */
   @Override
   public String toString()
   {
      return this.getClass().getSimpleName();
   }

   /**
    * Returns a name for the type of a feature of this base, based on the genotype
    * @return the same String as Feature.toString()
    */
   public abstract String getFeatureName( Genotype g );
}