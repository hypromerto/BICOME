package bicome.logic.feature;
/**
 * Determines fur thickness
 * Dominant: Thin Fur
 * Recessive: Thick Fur
 * None: No Fur
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import bicome.logic.genotype.Genotype;
import bicome.logic.attribute.*;
import java.util.HashMap;
public final class Fur extends FeatureBase
{
   private static Fur instance;
   
   private Fur()
   {
      super();
   }
   
   public static FeatureBase getInstance()
   {
      if ( instance == null )
         instance = new Fur();
      return instance;
   }
   
   @Override
   protected void setMultipliers()
   {
      this.dominantMultipliers.put( "speed", 0.90 );
      this.recessiveMultipliers.put( "speed", 0.75 );
      this.noneMultipliers.put( "nutrition_stockpiling", 0.90 );
      this.dominantMultipliers.put( "water_stockpiling", 1.22 );
      this.recessiveMultipliers.put( "water_stockpiling", 1.45 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 1.60 );
      this.noneMultipliers.put( "speed", 1.34 );
   }
}
