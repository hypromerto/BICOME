package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public final class Power extends Attribute
{
   /**
    * Sole constructor.
    */
   public Power()
   {
      super();
   }
   
   @Override
   protected void setType()
   {
      type = "power";
   }
}