package environment;
/**
 * @author Ege Balcýoðlu
 * @version 24.4.2018
 */
import genotype.Genotype;
import java.util.HashMap;
import feature.*;
public final class HotTemperature extends EnvironmentCondition
{
   public HotTemperature()
   {
      super();
   }
   
   protected void setMultipliers()
   {
      multipliers.put( new Fur( Genotype.NONE ), 1.55 );
   }
}