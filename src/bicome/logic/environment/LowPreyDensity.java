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
   public LowPreyDensity()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Feature( FoodStorageOrgan.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.27 );
   }
}