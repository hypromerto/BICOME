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
   /**
    * Sole constructor.
    */
   public Humid()
   {
      super();
   }
   
   @Override
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Fur.getInstance(), Genotype.NONE ), 1.45 );
      multipliers.put( new Feature( Fur.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 0.87 );
      multipliers.put( new Feature( Fur.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 0.94 );
   }
}