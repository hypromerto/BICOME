package attribute;
/**
 * @author Ege Balcýoðlu
 * @version 23.4.2018
 */
public abstract class Attribute
{
   // properties
   protected static final double INITIAL = 50.0;
   private double value;
   private Attribute.Type type;
   
   // constructors
   public Attribute()
   {
      this.value = INITIAL;
   }
   
   // inner classes
   public enum Type
   {
      POWER, SPEED, FLIGHT_EASE, AGE, NUTRITION_STOCKPILING, WATER_STOCKPILING;
   }
   
   // methods
   public Double getValue()
   {
      return new Double( value );
   }
   
   public void calculate( Double multiplier )
   {
      value *= multiplier;
   }
}