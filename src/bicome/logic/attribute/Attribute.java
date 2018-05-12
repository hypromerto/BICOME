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
   protected static final double INITIAL = 75.0;
   private double value;
   // type Strings will be used as Keys in the Maps of Feature class.
   protected String type;
   protected String name;
   
   // constructors
   /**
    * Sole constructor.
    */
   public Attribute()
   {
      this.value = INITIAL;
      setType();
      setName();
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
    * Sets the name of this Attribute to be used in GUI
    */
   protected void setName()
   {
      StringBuffer buff;
      buff = new StringBuffer( this.getType() );
      for ( int i = 1; i < buff.length() - 1; i++ )
      {
         if ( buff.charAt( i ) == '_' )
         {
            buff.setCharAt( i, ' ' );
            buff.setCharAt( i + 1, Character.toUpperCase( buff.charAt( i + 1 ) ) );
         }
      }
      buff.setCharAt( 0, Character.toUpperCase( buff.charAt( 0 ) ) );
      name = buff.toString();
   }
   
   /**
    * Returns the type of this Attribute
    * @return the name-ID of this Attribute
    */
   public String getType()
   {
      return type;
   }
   
   /**
    * Returns the type of this Attribute
    * @return the name-ID of this Attribute
    */
   public String getName()
   {
      return name;
   }
}