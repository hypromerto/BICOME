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
      multipliers.put( new Fur( Genotype.NONE ), 1.55 );
   }
}