package bicome.logic.world;
/**
 * @author Mert Aslan
 * @version 28.04.2018
 */

import java.util.ArrayList;
import bicome.logic.environment.Environment;

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
      int             totalNeighbourCell;
      int             selectedTiles;
      ArrayList<Tile> aliveNeighbours;
      ArrayList<Tile> emptyNeighbours;
      Organism[][]    offsprings;
      
      
      offsprings         = new Organism[50][50];
      aliveNeighbours    = new ArrayList<Tile>();
      emptyNeighbours    = new ArrayList<Tile>();
      totalNeighbourCell = 0;
      selectedTiles      = 0;
      
      
      if ( !isGameOver() )
      {
         for ( int i = 0; i < tiles.length; i++ )
         {
            for ( int j = 0; j < tiles.length; j++ )
            {
               if ( !tiles[i][j].isEmpty() && !isGameOver() ) 
               {
                  for ( int k = 0; k < 9; k++) 
                  {
                     int row = i + (k % 3) - 1;
                     int col = j + (k / 3) - 1;
                     
                     if (row >= 0 && row <= tiles.length && col >= 0 && col <= tiles.length && !(row == i && col == j) )
                     { // Looking for neighbours
                        totalNeighbourCell++;
                        
                        if ( !tiles[row][col].isEmpty() ) 
                        {
                           aliveNeighbours.add(  tiles[row][col] );
                        }
                        else if ( tiles[i][j].getSelected() )
                        {
                         selectedTiles++;
                        }
                        else
                        {
                           emptyNeighbours.add( tiles[row][col] );
                           tiles[row][col].setSelected( true );
                        }
                     }
                     
                  }
                  
                  if ( !( aliveNeighbours.size() < 2 || aliveNeighbours.size() > 3 ) )
                  {
                     //if  suitable amount of alive neighbors, reproduce after calculating reproduction chance
                     
                     if( totalNeighbourCell > aliveNeighbours.size() + selectedTiles ) //if there are spaces left for offspring
                     {                                                                 //also considering the selected tiles
                    	 
                    	 
                        if ( aliveNeighbours.size() == 2 && 2 * Math.random() > REPR_THRESHOLD )//Reproduction chance can be changed
                        {
                           // stub... read below!!
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                         
                         
                           int mateSelectTwo;
                           
                           mateSelectTwo = (int) Math.round( Math.random() ); //Choosing a partner from two alive neighbours
                           
                           if ( !aliveNeighbours.get( mateSelectTwo ).getOrganism().canReproduce() )
                           {
                        	   if ( mateSelectTwo == 1)
                        		   mateSelectTwo == 0;
                        	   else if( mateSelectTwo == 0)
                        		   mateSelectTwo == 1;
                           }
                           
                           if ( tiles[i][j].getOrganism().canReproduce() )
                           {    
                           Tile offspring;
                           
                           offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) ); //Selecting a tile for the offspring
                           
                              offsprings[offspring.getRow()][offspring.getCol()] = 
                            		  tiles[i][j].getOrganism().reproduce( aliveNeighbours.get( mateSelectTwo ).getOrganism() );
                              
                           }  
                        }
                        else if ( aliveNeighbours.size() == 3 &&  3 * Math.random() > REPR_THRESHOLD )
                        {
                           // stub... read below!!!
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                        	
                        	int mateSelectThree;
                        	
                        	mateSelectThree = (int) (Math.random() * 3); //Choosing a partner from three alive neighbour
                        	
                        	//if statement to check if it can reproduce
                        	
                        	if ()
                           
                        }
                        
                        
                     }
                     
                  }
                  else
                  {
                     //die without reproducing
                   
                   tiles[i][j].killOrganism();
                  }
                  
                  
               }
               // stub... flushings of some variables will be done here
               //end of for loops, therefore end of checks for a single organism
               
            }
         }
         
         for ( int i = 0; i < offsprings.length; i++ )
         {
            for ( int j = 0; j < offsprings.length; i++ )
            {
               if ( !tiles[i][j].isEmpty() )
               {
                  if ( !tiles[i][j].getOrganism().canReproduce() ) //incrementing the organisms with cooldown active
                     tiles[i][j].getOrganism().increaseCooldown();
                  
                  tiles[i][j].getOrganism().age();
                  tiles[i][j].setSelected( false ); //setting the new-borns to unselected state so that 
                                                    //they will be counted as a neighbour next round
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
