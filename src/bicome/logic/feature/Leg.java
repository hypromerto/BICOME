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
   
   public static FeatureBase getInstance()
   {
      if ( instance == null )
         instance = new Leg();
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
