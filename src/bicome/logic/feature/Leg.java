package bicome.logic.feature;
/**
 * Determines the leg length
 * Dominant: Long Leg
 * Recessive: Short Leg
 * None: No Leg
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import bicome.logic.genotype.Genotype;
import bicome.logic.attribute.*;
import java.util.HashMap;
public final class Leg extends FeatureBase
{
   private static Leg instance;
   
   private Leg()
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
         instance = new Leg();
      return instance;
   }
   
   @Override
   protected void setMultipliers()
   {
      this.dominantMultipliers.put( "speed", 1.90 );
      this.recessiveMultipliers.put( "speed", 1.56 );
      this.noneMultipliers.put( "speed", 0.19 );
      this.recessiveMultipliers.put( "power", 1.04 );
      this.dominantMultipliers.put( "nutrition_stockpiling", 0.45 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 0.50 );
      this.noneMultipliers.put( "nutrition_stockpiling", 1.60 );
   }
      
   @Override
   protected String getFeatureName( Genotype g )
   {
      StringBuffer result = new StringBuffer( "" );
      if ( g == Genotype.NONE )
         result.append( "No " );
      else if ( g == Genotype.RECESSIVE_HOMOZYGOTE )
         result.append( "Short " );
      else
         result.append( "Long " );
      result.append( "Leg" );
      return result.toString();
   }
}
