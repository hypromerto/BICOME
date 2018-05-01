package src.Java.Logic.feature;
/**
 * Determines "hump" size, humps are those things that camels have on their back
 * Dominant: Small Hump
 * Recessive: Big Hump
 * None: No Hump
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
import genotype.Genotype;
import attribute.*;
import java.util.HashMap;
public final class Hump extends Feature
{
   public Hump( Genotype genotype )
   {
      super( genotype );
   }
   
   protected void setMultipliers()
   {
      // TBA
      this.dominantMultipliers.put( "water_stockpiling", 1.4 );
      this.recessiveMultipliers.put( "water_stockpiling", 1.8 );
      this.dominantMultipliers.put( "nutrition_stockpiling", 1.1 );
      this.recessiveMultipliers.put( "nutrition_stockpiling", 1.4 );
      // this.noneMultipliers.put();
   }
}