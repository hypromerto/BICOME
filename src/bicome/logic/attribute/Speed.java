package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public final class Speed extends Attribute
{
   /**
    * Sole constructor.
    */
   public Speed()
   {
      super();
   }
   
   @Override
   protected void setType()
   {
      type = "speed";
   }
}