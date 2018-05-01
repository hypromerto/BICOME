package Java.Logic.world;

/**
 * 
 * @author  Mert Aslan
 * @version 28.04.2018
 */
public class Tile 
{
   private int row;
   private int col;
   private boolean selected;
   private Organism o;
   
   public Tile( int row, int col )
   {
      this.row = row;
      selected = false;
      this.col = col;
      o = null;
   }
   
   public void placeOrganism( Organism o )
   {
      this.o = o;
   }
   
   public void killOrganism()
   {
      o = null;
   }
   
   public Organism getOrganism()
   {
      return o;
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
