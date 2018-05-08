package bicome.logic.world;
/**
 * @author Mert Aslan
 * @version 28.04.2018
 */

import java.util.ArrayList;
import bicome.logic.environment.Environment;
import bicome.logic.feature.*;

public class World 
{
   private static final int    SIZE = 30;            //Size of the simulation grid
   private static final double REPR_THRESHOLD = 1.3; //Reproduction chance, DUE TO CHANGE
   private static final int    TWO_NEIGHBOUR = 2;    //Number of neighbours surrounding the center organism
   private static final int    THREE_NEIGHBOUR = 3;  //Number of neighbours surrounding the center organism
   
   private Tile[][]    tiles;          //The simulation multi-array
   private Tile[][]    offspringTiles; //The multi-array that is used to hold the new-borns
   private Environment environment;    //The environment of the world
   private int         round;          //Round count of the game
   
   public World()
   {
      tiles          = new Tile[SIZE][SIZE];
      offspringTiles = new Tile[SIZE][SIZE];
      environment    = new Environment();
      round          = 0;
      
      for ( int i = 0; i < tiles.length; i++ )
      {
         for( int j = 0; j < tiles.length; j++ )
         {
            tiles[i][j] = new Tile(i,j);
            offspringTiles[i][j] = new Tile(i,j);
         }
         
      }
   }
   
   /**
    * Places an organism in the given row and column. This is used to place the initial organisms
    * the user clicks on when he first starts the simulation.
    * 
    * @param row         Row to place the initial organism
    * @param col         Column to place the initial organism
    * @param features    The features that are selected in the selection stage
    */
   public void placeInitialOrganism( int row, int col, FeatureList features )
   {
      // stub, will get the coordinates and the organism from the game manager, or it may get the
   // variables needed to "construct" an organism, each way is fine...
    
   tiles[row][col].placeOrganism( new Organism( features, environment ) );
      
   }
   
   /**
    * Simulates the next round.
    */
   
   public void nextTurn()
   {
      int             totalNeighbourCell;
      int             selectedTiles;
      ArrayList<Tile> aliveNeighbours;
      ArrayList<Tile> emptyNeighbours;
      
      
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
               if ( !tiles[i][j].isEmpty() && !isGameOver() ) //mid round game over check
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
                  
                  if ( !( aliveNeighbours.size() < TWO_NEIGHBOUR || aliveNeighbours.size() > THREE_NEIGHBOUR ) )
                  {
                     //if  suitable amount of alive neighbors, reproduce after calculating reproduction chance
                     
                     if( totalNeighbourCell > aliveNeighbours.size() + selectedTiles ) //if there are spaces left for offspring
                     {                                                                 //also considering the selected tiles
                        
                        
                        if ( aliveNeighbours.size() == TWO_NEIGHBOUR && TWO_NEIGHBOUR * Math.random() > REPR_THRESHOLD )//Reproduction chance can be changed
                        {
                           // stub... read below!!
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                           
                           
                           int mateSelectTwo;
                           
                           mateSelectTwo = (int) Math.round( Math.random() ); //Choosing a partner from two alive neighbours
                           
                           
                           /*
                           if ( !aliveNeighbours.get( mateSelectTwo ).getOrganism().canReproduce() ) //DOESN'T WORK IF BOTH CAN'T REPRODUCE!
                           {                                                                         //FIX!!
                              if ( mateSelectTwo == 1)
                                 mateSelectTwo = 0;
                              else if ( mateSelectTwo == 0)
                                 mateSelectTwo = 1;
                           } */
                           
                           if ( tiles[i][j].getOrganism().canReproduce() && aliveNeighbours.get( mateSelectTwo ).getOrganism().canReproduce() )
                           {    
                              Tile offspring;
                              
                              offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) ); //Selecting a tile for the offspring
                              
                              offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism( tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                get( mateSelectTwo ).getOrganism() ) );

                              
                           }  
                        }
                        else if ( aliveNeighbours.size() == THREE_NEIGHBOUR && THREE_NEIGHBOUR * Math.random() > REPR_THRESHOLD )
                        {
                           // stub... read below!!!
                           // IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
                           
                           int mateSelectThree;
                           
                           mateSelectThree = (int) (Math.random() * 3); //Choosing a partner from three alive neighbour
        
                           if ( tiles[i][j].getOrganism().canReproduce() && aliveNeighbours.get( mateSelectThree ).getOrganism().canReproduce() )
                           {
                            Tile offspring;
                            
                            offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) );
                            
                            offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism(tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                              get( mateSelectThree ).getOrganism() ) );
                              
                           }     
                        }
                        
                        
                     }
                     
                  }
                  else
                  {
                     //die without reproducing, number of neighbours required isn't met!
                     
                     tiles[i][j].killOrganism();
                  }
                  
                  
               }
               // stub... flushings of some variables will be done here
               //end of for loops, therefore end of checks for a single organism
               
               selectedTiles = 0;
               totalNeighbourCell = 0;
               aliveNeighbours.clear();
               emptyNeighbours.clear();
               
            }
         }
         
         for ( int i = 0; i < offspringTiles.length; i++ )
         {
            for ( int j = 0; j < offspringTiles.length; j++ )
            {
               if ( !tiles[i][j].isEmpty() )
               {
                  if ( !tiles[i][j].getOrganism().canReproduce() ) //incrementing the organisms with cooldown active
                     tiles[i][j].getOrganism().increaseCooldown();
                  
                  tiles[i][j].getOrganism().age();
                  tiles[i][j].setSelected( false ); //setting the new-borns to unselected state so that 
                  //they will be counted as a neighbour next round
               }
               
               if ( !offspringTiles[i][j].isEmpty() )
               {
                // PROBABLY DOESN'T WORK AS THEY BOTH HAVE THE REFERENCE TO THE SAME OBJECT, SO KILL ORGANISM ALSO KILLS THE ORGANISM
                // IN TILES MULTI ARRAY need a good way to copy
                
                  tiles[i][j].placeOrganism(offspringTiles[i][j].getOrganism() ); //porting our offsprings back to original organisms array
                  
                  offspringTiles[i][j].killOrganism(); //flushing offsprings 
               }
            }
         }
         
         round++;     
      }
   }
   
   /**
    * Gets the simulation grid.
    * 
    * @return  tiles      the simulation grid
    */
   public Tile[][] getGrid()
   {
    return tiles;
   }
   
   /**
    * Checks if the game is over.
    * 
    * @return   true if game is over, false otherwise
    */  
   public boolean isGameOver()
   {
      // stub
      return false;
   }
   
   /**
    * Gets the environment of the world
    * 
    * @return  environment    the environment of the world
    */
   public Environment getEnvironment()
   {
      return environment;
   }
}
