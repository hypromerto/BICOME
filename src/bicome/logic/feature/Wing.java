package bicome.logic.feature;
/**
 * Determines wingspan
 * Dominant: Big Wing
 * Recessive: Small Wing
 * None: No Wing
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import bicome.logic.genotype.Genotype;
import bicome.logic.attribute.*;
import java.util.HashMap;
public final class Wing extends FeatureBase
{
   private static Wing instance;
   
   private Wing()
   {
      super();
   }
   
   public static FeatureBase getInstance()
   {
      if ( instance == null )
         instance = new Wing();
      return instance;
   }
   
   @Override
   protected void setMultipliers()
   {
      this.dominantMultipliers.put( "flight_ease", 1.55 );
      this.recessiveMultipliers.put( "flight_ease", 1.90 );
      this.noneMultipliers.put( "flight_ease", 0.12 );
      this.dominantMultipliers.put( "nutrition_stockpiling", 0.65 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 0.9 );
      this.dominantMultipliers.put( "speed", 0.79 );
      this.noneMultipliers.put( "speed", 1.34 );
   }
      
   @Override
   public String getFeatureName( Genotype g )
   {
      StringBuffer result = new StringBuffer( "" );
      if ( g == Genotype.NONE )
         result.append( "No " );
      else if ( g == Genotype.RECESSIVE_HOMOZYGOTE )
         result.append( "Small " );
      else
         result.append( "Big " );
      result.append( "Wing" );
      return result.toString();
   }
}
