package world;
/**
 * @author Mert Aslan
 * @version 27.04.2018
 */

import java.util.ArrayList;
import environment.Environment;

public class World 
{
   
   private final double REPR_THRESHOLD = 1.3;
   
   private Tile[][] tiles;
   private Environment environment;
   private int round;
   
   public World()
   {
      tiles  = new Tile[50][50];
      environment = new Environment();
      round = 0;
      
      for ( int i = 0; i < tiles.length; i++ )
      {
         for( int j = 0; j < tiles.length; j++ )
         {
            tiles[i][j] = new Tile(i,j);
         }
         
      }
   }
   
   public boolean placeOrganism( int row, int col, Organism o )
   {
      // stub
      return false;
   }
   
   
   
   public void nextTurn()
   {
      int totalCell;
      ArrayList<Organism> neighbors;
      Organism[][] offsprings;
      Boolean[][] emptySpaces;
      
      
      emptySpaces = new Boolean[9][9];
      offsprings = new Organism[50][50];
      neighbors = new ArrayList<Organism>();
      totalCell = 0;
      
      if ( !isGameOver() )
      {
         for ( int i = 0; i < tiles.length; i++ )
         {
            for ( int j = 0; j < tiles.length; j++ )
            {
               if ( !tiles[i][j].isEmpty() )
               {
                  for ( int k = 0; k < 9; k++) 
                  {
                     int row = i + (k % 3) - 1;
                     int col = j + (k / 3) - 1;
                     
                     if (row >= 0 && row <= tiles.length && col >= 0 && col <= tiles.length && !(row == i && col == j))
                     {
                        totalCell++;
                        
                        if ( !tiles[row][col].isEmpty() ) 
                        {
                           neighbors.add(  tiles[row][col].getOrganism() );
                        }
                        else
                        {
                           tiles[row][col].setSelected( true ); //needs a better implementation or a way to find row col indexes with respect to center organism
                        }
                     }
                     
                  }
                  
                  if ( !( neighbors.size() < 2 || neighbors.size() > 3 ) )
                  {
                     //if  suitable amount of neighbors, reproduce after calculating reproduction chance
                     
                     if( totalCell > neighbors.size() ) //if there are spaces left for offspring
                     {
                        if ( neighbors.size() == 2 && 2 * Math.random() > REPR_THRESHOLD )
                        {
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                           int toDo;
                           
                           toDo = (int) Math.round( Math.random() );
                           
                           if ( tiles[i][j].getOrganism().canReproduce() && neighbors.get( toDo ).canReproduce() )
                           {
                              //randomly select a location from emptySpaces array and fill its corresponding index in
                              //offsprings multi array
                              
                              
                              
                              offsprings[i][j] = tiles[i][j].getOrganism().reproduce(neighbors.get( toDo ) );
                              
                           }  
                        }
                        else if ( neighbors.size() == 3 &&  3 * Math.random() > REPR_THRESHOLD )
                        {
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                           
                        }
                        
                        
                     }
                     
                  }
                  else
                  {
                     //die without reproducing
                     //stub
                  }
                  
                  
               }
               
            }
         }
         
         for ( int i = 0; i < offsprings.length; i++ )
         {
            for ( int j = 0; j < offsprings.length; i++ )
            {
               if ( !tiles[i][j].isEmpty() )
               {
                  if ( tiles[i][j].getOrganism().canReproduce() ) //incrementing the organisms with cooldown active
                     tiles[i][j].getOrganism().increaseCooldown();
                  
                  tiles[i][j].getOrganism().age();
               }
               
               if ( offsprings[i][j] instanceof Organism )
               {
                  tiles[i][j].placeOrganism(offsprings[i][j]); //porting our offsprings back to original organisms array
                  
                  offsprings[i][j] = null; //flushing offsprings
               }
            }
         }
         round++;
         
         
      }
   }
   
   
   public boolean isGameOver()
   {
      // stub
      return false;
   }
   
   public Environment getEnvironment()
   {
      return environment;
   }
}
