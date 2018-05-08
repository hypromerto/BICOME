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
      multipliers.put( new Feature( Muscles.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.65 );
      multipliers.put( new Feature( Wing.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 1.36 );
      multipliers.put( new Feature( Wing.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 1.25 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.RECESSIVE_HOMOZYGOTE ), 0.60 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.DOMINANT_HOMOZYGOTE ), 0.67 );
      multipliers.put( new Feature( Hump.getInstance(), Genotype.NONE ), 1.33 );
   }
}