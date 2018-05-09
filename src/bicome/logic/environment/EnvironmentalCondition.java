package bicome.logic.environment;
/**
 * Abbreviated as EnvCod
 * @author Ege Balcioglu
 * @version 24.4.2018
 */
import java.util.HashMap;
import bicome.logic.feature.Feature;
public abstract class EnvironmentalCondition
{
   /**
    * The map that stores which multipliers belong to which features.
    * The corresponding multipliers of the corresponding features will be multiplied with the attribute multipliers inside the Feature.
    */
   protected HashMap< Feature, Double > multipliers;
   
   /**
    * Constructs an environmental condition with its multipliers
    */
   public EnvironmentalCondition()
   {
      multipliers = new HashMap<>();
      setMultipliers();
   }
   
   /**
    * Gets the multipliers of this condition
    * @return the map that contains the multipliers of this condition
    */
   public HashMap< Feature, Double > getMultipliers()
   {
      return multipliers;
   }
   
   /**
    * checks if another object is the same with this one, false otherwise
    * @param other the object to be compared with this EnvCod
    * @return true if other is an EnvCod and both of 
    * their simple class names are the same
    */
   @Override
   public boolean equals( Object other )
   {
      String thisClassName;
      String otherClassName;
      if ( other != null && other instanceof EnvironmentalCondition )
      {
         thisClassName = this.getClass().getSimpleName();
         otherClassName = other.getClass().getSimpleName();
         if ( thisClassName.substring( thisClassName.lastIndexOf( '.' ) )
                .equals(
         otherClassName.substring( otherClassName.lastIndexOf( '.' ) ) ) )
         {
            return true;
         }
      }
      return false;
   }
   
   /**
    * Returns a String representation of this object
    * @return simply the simple class name
    */
   @Override
   public String toString()
   {
      return this.getClass().getSimpleName();
   }
   
   /**
    * Creates the map of multipliers of this EnvCod
    */
   protected abstract void setMultipliers();
}