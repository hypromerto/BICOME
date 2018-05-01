package src.Java.Logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public abstract class Attribute
{
   // properties
   protected static final double INITIAL = 50.0;
   private double value;
   // type Strings will be used as Keys in the Maps of Feature class.
   protected String type;
   
   // constructors
   public Attribute()
   {
      this.value = INITIAL;
      setType();
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
   
   protected abstract void setType();
   
   public String getType()
   {
      return type;
   }
}