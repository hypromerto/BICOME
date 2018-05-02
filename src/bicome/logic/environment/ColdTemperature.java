package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class ColdTemperature extends EnvironmentalCondition
{
   public ColdTemperature()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Fur.getInstance(), Genotype.NONE ), 0.25 );
      multipliers.put( new Feature( Fur.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.80 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.40 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.05 );
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.NONE ), 0.70 );
   }
}