package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class HighPredatorDensity extends EnvironmentalCondition
{
   public HighPredatorDensity()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Muscles.getInstance(), Genotype.NONE ), 0.29 );
   }
}