package bicome.logic.feature;
/**
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
   
   protected FeatureBase()
   {
      dominantMultipliers = new HashMap<>();
      recessiveMultipliers = new HashMap<>();
      noneMultipliers = new HashMap<>();
      setMultipliers();
   }
   
   // methods 
   protected abstract void setMultipliers();
   
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
   
   public String toString()
   {
      return this.getClass().getSimpleName();
   }

   public abstract String getFeatureName( Genotype g );
}