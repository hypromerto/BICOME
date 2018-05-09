package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class LowPreyDensity extends EnvironmentalCondition
{
   /**
    * Sole constructor.
    */
   public LowPreyDensity()
   {
      super();
   }
   
   @Override
   protected void setMultipliers()
   {
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.27 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.97 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.NONE ), 0.73 );
      multipliers.put( new Feature( Wing.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.36 );
      multipliers.put( new Feature( Wing.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.67 );
   }
}