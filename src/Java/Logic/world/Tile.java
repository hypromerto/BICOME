package world;

/**
 * 
 * @author  Mert Aslan
 * @version 27.04.2018
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
      this.col = col;
      o = null;
   }
   
   public void placeOrganism( Organism o )
   {
      this.o = o;
   }
   
   public Organism getOrganism()
   {
      return o;
   }
   
   public boolean isEmpty()
   {
      return o == null;
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
