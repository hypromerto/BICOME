package src.Java.Logic.environment.feature;
/**
 * Determines wingspan
 * Dominant: Big Wing
 * Recessive: Small Wing
 * None: No Wing
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import genotype.Genotype;
import attribute.*;
import java.util.HashMap;
public final class Wing extends Feature
{
   public Wing( Genotype genotype )
   {
      super( genotype );
   }
   
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
}
