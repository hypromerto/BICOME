package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class HotTemperature extends EnvironmentalCondition
{
   public HotTemperature()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Fur.getInstance(), Genotype.NONE ), 1.55 );
      multipliers.put( new Feature( Fur.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 0.81 );
      multipliers.put( new Feature( Fur.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 0.28 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.40 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.05 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.NONE ), 0.70 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.80 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.14 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.NONE ), 0.45 );
   }
}