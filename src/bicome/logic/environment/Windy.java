package bicome.logic.environment;
/**
 * @author Ege Balc�o�lu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class Windy extends EnvironmentalCondition
{
   public Windy()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Wing( Genotype.RECESSIVE_HOMOZYGOTE ), 0.25 );
      multipliers.put( new Wing( Genotype.DOMINANT_HOMOZYGOTE ), 0.55 );
   }
}