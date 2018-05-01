package src.Java.Logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import genotype.Genotype;
import java.util.HashMap;
import feature.*;
public final class ColdTemperature extends EnvironmentalCondition
{
   public ColdTemperature()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Fur( Genotype.NONE ), 0.25 );
      multipliers.put( new Fur( Genotype.RECESSIVE_HOMOZYGOTE ), 1.80 );
      multipliers.put( new FoodStorageOrgan( Genotype.DOMINANT_HOMOZYGOTE ), 1.40 );
      multipliers.put( new FoodStorageOrgan( Genotype.RECESSIVE_HOMOZYGOTE ), 1.05 );
      multipliers.put( new FoodStorageOrgan( Genotype.NONE ), 0.70 );
   }
}