package src.Java.Logic.environment;
/**
 * @author Ege Balc�o�lu
 * @version 24.4.2018
 */
import genotype.Genotype;
import java.util.HashMap;
import feature.*;
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