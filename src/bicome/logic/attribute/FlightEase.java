package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public final class FlightEase extends Attribute
{
   /**
    * Sole constructor.
    */
   public FlightEase()
   {
      super();
   }
   
   @Override
   protected void setType()
   {
      type = "flight_ease";
   }
}