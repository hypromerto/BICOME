package bicome.logic.environment;
/**
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import java.util.HashMap;
import bicome.logic.feature.Feature;
public abstract class EnvironmentalCondition
{
   protected HashMap< Feature, Double > multipliers;
   
   public EnvironmentalCondition()
   {
      multipliers = new HashMap<>();
      setMultipliers();
   }
   
   public HashMap< Feature, Double > getMultipliers()
   {
      return multipliers;
   }
   
   public boolean equals( Object other )
   {
      String thisClassName;
      String otherClassName;
      if ( other != null && other instanceof EnvironmentalCondition )
      {
         thisClassName = this.getClass().getName();
         otherClassName = other.getClass().getName();
         if ( thisClassName.substring( thisClassName.lastIndexOf( '.' ) )
                .equals(
         otherClassName.substring( otherClassName.lastIndexOf( '.' ) ) ) )
         {
            return true;
         }
      }
      return false;
   }
   
   protected abstract void setMultipliers();
}