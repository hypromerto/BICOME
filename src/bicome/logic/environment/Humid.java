package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class Humid extends EnvironmentalCondition
{
   public Humid()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Fur.getInstance(), Genotype.NONE ), 1.45 );
   }
}