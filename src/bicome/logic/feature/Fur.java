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
      // TBA
      // this.dominantMultipliers.put( , 0.25 );
      // this.recessiveMultipliers.put();
      // this.noneMultipliers.put();
      // this.dominantMultipliers.put();
      // this.recessiveMultipliers.put();
      // this.noneMultipliers.put();
   }
}
