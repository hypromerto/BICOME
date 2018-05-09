package bicome.logic.feature;
/**
 * Determines "hump" size, humps are those things that camels have on their back to store water and fat
 * Dominant: Small Hump
 * Recessive: Big Hump
 * None: No Hump
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import bicome.logic.genotype.Genotype;
import bicome.logic.attribute.*;
import java.util.HashMap;
public final class Hump extends FeatureBase
{
   private static Hump instance;
   
   private Hump()
   {
      super();
   }
   
   /**
    * Gets the instance of this class according to the singleton pattern
    * @return the instance of this class
    */
   public static FeatureBase getInstance()
   {
      if ( instance == null )
         instance = new Hump();
      return instance;
   }
   
   @Override
   protected void setMultipliers()
   {
      // TBA
      this.dominantMultipliers.put( "water_stockpiling", 1.40 );
      this.recessiveMultipliers.put( "water_stockpiling", 1.80 );
      this.dominantMultipliers.put( "nutrition_stockpiling", 1.10 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 1.40 );
      this.noneMultipliers.put( "speed", 1.60 );
   }
   
   @Override
   protected String getFeatureName( Genotype g )
   {
      StringBuffer result = new StringBuffer( "" );
      if ( g == Genotype.NONE )
         result.append( "No " );
      else if ( g == Genotype.RECESSIVE_HOMOZYGOTE )
         result.append( "Big " );
      else
         result.append( "Small " );
      result.append( "Hump" );
      return result.toString();
   }
}