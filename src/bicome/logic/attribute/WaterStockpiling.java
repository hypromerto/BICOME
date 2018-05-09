package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public final class WaterStockpiling extends Attribute
{
   /**
    * Sole constructor.
    */
   public WaterStockpiling()
   {
      super();
   }
   
   @Override
   protected void setType()
   {
      type = "water_stockpiling";
   }
}