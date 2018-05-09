package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.HashMap;
import bicome.logic.feature.*;
public final class Windy extends EnvironmentalCondition
{
   /**
    * Sole constructor.
    */
   public Windy()
   {
      super();
   }
   
   @Override
   protected void setMultipliers()
   {
      multipliers.put( new Feature( Wing.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 0.25 );
      multipliers.put( new Feature( Wing.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 0.55 );
      multipliers.put( new Feature( Leg.getInstance(), Genotype.NONE ), 1.67 );
      multipliers.put( new Feature( Leg.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 0.90 );
   }
}