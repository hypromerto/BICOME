package bicome.logic.attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public final class NutritionStockpiling extends Attribute
{
   /**
    * Sole constructor.
    */
   public NutritionStockpiling()
   {
      super();
   }
   
   @Override
   protected void setType()
   {
      type = "nutrition_stockpiling";
   }
}