package environment;
/**
 * @author Ege Balcýoðlu
 * @version 24.4.2018
 */
import java.util.HashMap;
import feature.Feature;
public abstract class EnvironmentCondition
{
   protected HashMap< Feature, Double > multipliers;
   
   public EnvironmentCondition()
   {
      multipliers = new HashMap<>();
      setMultipliers();
   }
   
   public HashMap< Feature, Double > getMultipliers()
   {
      return multipliers;
   }
   
   protected abstract void setMultipliers();
}