package bicome.logic.world;

/**
 * 
 * @author  Mert Aslan
 * @version 28.04.2018
 */
import javafx.scene.paint.Color;
public class Tile 
{
   private Color color;
   private int row;
   private int col;
   private boolean selected;
   private Organism o;
   
   public Tile( int row, int col )
   {
      color = Color.TRANSPARENT;
      this.row = row;
      selected = false;
      this.col = col;
      o = null;
   }
   
   public void placeOrganism( Organism o )
   {
      this.o = o;
      
      color = this.getOrganism().getColor();
   }
   
   public void killOrganism()
   {
      o = null;
      
      color = color.TRANSPARENT;
   }
   
   public Organism getOrganism()
   {
      return o;
   }  
   
   public Color getColor()
   {
      return color;
   }
   
   public boolean isEmpty()
   {
      return o == null;
   }   
   
   public int getRow()
   {
      return row;
   }
   
   public int getCol()
   {
      return col;
   }   
   
   public void setSelected( boolean state )
   {
      selected = state;
   }   
   
   public boolean getSelected()
   {
      return selected;
   }   
   
}
