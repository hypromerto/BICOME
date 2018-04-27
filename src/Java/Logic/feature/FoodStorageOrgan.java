package feature;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import genotype.Genotype;
import attribute.*;
import java.util.HashMap;
public final class FoodStorageOrgan extends Feature
{
   public FoodStorageOrgan( Genotype genotype )
   {
      super( genotype );
   }
   
   protected void setMultipliers()
   {
      this.dominantMultipliers.put( "nutrition_stockpiling", 1.7 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 1.2 );
      this.noneMultipliers.put( "nutrition_stockpiling", 0.9 );
      this.dominantMultipliers.put( "speed", 0.90 );
      this.recessiveMultipliers.put( "speed", 0.95 );
      this.dominantMultipliers.put( "flight_ease", 1.05 );
      this.recessiveMultipliers.put( "flight_ease", 1.02 );
   }
}
