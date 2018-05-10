package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public abstract class Attribute
{
   // properties
   /**
    * The initial value of an Attribute 
    */
   protected static final double INITIAL = 87.5;
   private double value;
   // type Strings will be used as Keys in the Maps of Feature class.
   protected String type;
   
   // constructors
   /**
    * Sole constructor.
    */
   public Attribute()
   {
      this.value = INITIAL;
      setType();
   }
   
   // methods
   /**
    * returns the value of this Attribute
    * @return the value of this Attribute
    */
   public Double getValue()
   {
      return new Double( value );
   }
   
   /**
    * multiplies the value of this Attribute with the given value
    * @param multiplier the number to be multiplied with the inner value
    */
   public void calculate( Double multiplier )
   {
      value *= multiplier;
   }
   
   /**
    * Sets the name-ID of this Attribute 
    */
   protected abstract void setType();
   
   /**
    * Returns the type of this Attribute
    * @return the name-ID of this Attribute
    */
   public String getType()
   {
      return type;
   }
}