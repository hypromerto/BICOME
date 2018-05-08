package bicome.logic.world;

/**
 * 
 * @author  Mert Aslan
 * @version 28.04.2018
 */
import javafx.scene.paint.Color;
public class Tile 
{
   private Color    color;    //Color of the tile
   private int      row;      //Row of the tile
   private int      col;      //Column of the tile
   private boolean  selected; //Selected state of the tile
   private Organism o;        //Organism that resides the tile
   
   public Tile( int row, int col )
   {
      color    = Color.TRANSPARENT;
      this.row = row;
      selected = false;
      this.col = col;
      o        = null;
   }
   
   /**
    * Places an organism into the tile. Also sets the tile's color according to the organism's color.
    * 
    * @param o  the organism to be placed into the tile
    */
   public void placeOrganism( Organism o )
   {
      this.o = o;
      
      color = this.getOrganism().getColor();
   }
   
   /**
    * Kills the tile in the tile, effectively setting the reference of the organism to null.
    * Also sets the color of the tile to transparent.
    */
   public void killOrganism()
   {
      o = null;
      
      color = Color.TRANSPARENT;
   }
   
   /**
    * Gets the organism that is currently in the tile.
    * 
    * @return  o        the organism in the tile, null if there is none
    */
   public Organism getOrganism()
   {
      return o;
   }  
   
   /**
    * Gets the color of the tile.
    * 
    * @return  color    the current color of the tile.
    */
   public Color getColor()
   {
      return color;
   }
   
   /**
    * Checks if there is an organism in the tile.
    * 
    * @return   true if there is no organism in the tile, false otherwise
    */
   public boolean isEmpty()
   {
      return o == null;
   }   
   
   /**
    * Gets the row of the tile
    * 
    * @return  row    the row of the tile
    */
   public int getRow()
   {
      return row;
   }
   
   /**
    * Gets the column of the tile
    * 
    * @return  col    the column of the tile
    */
   public int getCol()
   {
      return col;
   }   
   
   /**
    * Sets the selected state of the tile. This is used in order to determine whether there
    * will be new organisms that will be added to a tile at the end of a round. This solves the collision
    * problem that may arise in reproduction.
    * 
    * @param state     true if there will be new organisms in this tile the next round, false otherwise
    */
   public void setSelected( boolean state )
   {
      selected = state;
   }   
   
   /**
    * Gets the selected state of the tile
    * 
    * @return  true if the tile is selected, false otherwise
    */
   public boolean getSelected()
   {
      return selected;
   }   
   
   public void emptyTile()
   {
	   
   }
   
}
