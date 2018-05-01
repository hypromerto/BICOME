package src.Java.Logic.feature;
/**
 * Determines muscle amount & size
 * Dominant: Heavy Muscles
 * Recessive: Light Muscles -> has no effect on anything
 * None: Almost No Muscles
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import genotype.Genotype;
import attribute.*;
import java.util.HashMap;
public final class Muscles extends Feature
{
   public Muscles( Genotype genotype )
   {
      super( genotype );
   }
   
   protected void setMultipliers()
   {
      this.dominantMultipliers.put( "water_stockpiling", 0.78 );
      this.dominantMultipliers.put( "power", 1.90 );
      this.noneMultipliers.put( "power", 0.50 );
      this.dominantMultipliers.put( "speed", 0.70 );
      this.noneMultipliers.put( "speed", 1.45 );
   }
}
