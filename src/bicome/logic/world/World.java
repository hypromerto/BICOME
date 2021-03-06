package bicome.logic.world;
/**
 * Represents a world. Has a Tile grid which is updated every turn. Has methods to accurately calculate it's next
 * turn of existence and carries out whole reproduction process of the organisms that reside in the grid. Organism's
 * are affected by survival and reproduction chance in this class. 
 * 
 * @author  Mert Aslan
 * @version 28.04.2018
 */

import java.util.ArrayList;
import bicome.logic.environment.Environment;
import bicome.logic.feature.*;

public class World 
{
   private static final int    SIZE            = 30;   //Size of the simulation grid
   private static final double REPR_THRESHOLD  = 1.1;  //Reproduction chance, DUE TO CHANGE
   private static final int    ONE_NEIGHBOUR   = 1;    //Number of neighbours surrounding the center organism
   private static final int    TWO_NEIGHBOUR   = 2;    //Number of neighbours surrounding the center organism
   private static final int    THREE_NEIGHBOUR = 3;    //Number of neighbours surrounding the center organism
   private static final int    ROUND_LIMIT     = 100;   //The total amount of possible rounds of the game
   
   private Tile[][]    tiles;             //The simulation multi-array
   private Tile[][]    offspringTiles;    //The multi-array that is used to hold the new-borns
   private Environment environment;       //The environment of the world
   private int         round;             //Round count of the game
   private Organism    firstOrganism;     //One of the first Organisms that were brought into this world
   private double      finalSurvivalRate; //The average final survival chance of the last organisms
   private Organism    sampleOrganism;    //An average organism that is a general sample of the final organisms
   
   public World(FeatureList features, Environment environment)
   {
      tiles               = new Tile[SIZE][SIZE];
      offspringTiles      = new Tile[SIZE][SIZE];
      this.environment    = environment;
      round               = 0;
      
      for ( int i = 0; i < tiles.length; i++ )
      {
         for( int j = 0; j < tiles.length; j++ )
         {
            tiles[i][j] = new Tile(i,j);
            offspringTiles[i][j] = new Tile(i,j);
         }
         
      }
      finalSurvivalRate = 0;
      placeInitialOrganisms( features );
      firstOrganism = new Organism( features, environment );
   }
   
   /**
    * Places an organism into a random spot in the grid and two others into random places that are neighbour
    * to the first placed organism. This creates the starting state of the simulation.
    * 
    * @param features     the features user selected at the selection stage of the game
    */
   private void placeInitialOrganisms( FeatureList features )
   {
      
      int             row;
      int             col;
      Tile            firstNeighbour;
      Tile            secondNeighbour;
      ArrayList<Tile> initialNeighbours;
      
      initialNeighbours = new ArrayList<Tile>();
      row               = (int) ( Math.random() * SIZE );
      col               = (int) ( Math.random() * SIZE );  
      
      tiles[row][col].placeOrganism( new Organism( features, environment ) ); 
      
      
      for( int k = 0; k < 9; k++) //Detecting the neighbours of the initial organism
      {
         int neighbourRow = row + (k % 3) - 1;
         int neighbourCol = col + (k / 3) - 1;
         
         //If they suit the condition of being a valid neighbour, which is being in the bounds
         //of the grid, then add that tile to the initialNeighbours ArrayList.
         if (neighbourRow >= 0 && neighbourRow < tiles.length && neighbourCol >= 0 &&   
             neighbourCol < tiles.length && !(neighbourRow == row && neighbourCol == col) ) 
         {
            initialNeighbours.add( tiles[neighbourRow][neighbourCol] );
         }
      }
      
      //Randomly selecting the first neighbour of the initial organism, and then removing it from the
      //ArrayList so that it can no longer be selected as a possible neighbour to the initial organism
      firstNeighbour = initialNeighbours.get( (int) ( Math.random() * initialNeighbours.size() ) );
      
      initialNeighbours.remove( firstNeighbour );
      
      //Placing the first neighbour to our grid
      tiles[firstNeighbour.getRow()][firstNeighbour.getCol()].placeOrganism( new Organism( features, environment ) );
      
      //Randomly selecting the second neighbour
      secondNeighbour = initialNeighbours.get( (int) ( Math.random() * initialNeighbours.size() ) );
      
      initialNeighbours.remove( secondNeighbour );
      
      tiles[secondNeighbour.getRow()][secondNeighbour.getCol()].placeOrganism( new Organism( features, environment ) );
      
      initialNeighbours.clear(); //Just to look clean and tidy
   }
   
   /**
    * Simulates the next round.
    * @return true if the game did not end yet, false otherwise
    */ 
   public boolean nextTurn()
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
               if ( !tiles[i][j].isEmpty() )
               {
                  int percentage;  //Used to determine if an organism lives or dies depending on it's survival chance
                  
                  percentage = (int) ( Math.random() * 100 );
                  
                  for ( int k = 0; k < 9; k++) //Traversing the neighbours of a single organism
                  {
                     int row = i + (k % 3) - 1;
                     int col = j + (k / 3) - 1;
                     
                     // Looking for valid neighbours, valid referring to actually being inside the boundaries of the grid
                     if (row >= 0 && row < tiles.length && col >= 0 && col < tiles.length && !(row == i && col == j) )
                     { 
                        totalNeighbourCell++;
                        
                        if ( !tiles[row][col].isEmpty() ) //If an organism resides in the tile
                        {
                           aliveNeighbours.add(  tiles[row][col] );
                        }
                        else if ( tiles[row][col].getSelected() ) //The tiles that are due to have new organisms in them 
                        {                                     //at the end of the round
                           selectedTiles++;
                        }
                        else  //If none of the above, then it is an empty tile
                        {
                           emptyNeighbours.add( tiles[row][col] );
                           // tiles[row][col].setSelected( true );
                        }
                     }
                     
                  }
                  
                  if ( percentage < tiles[i][j].getOrganism().getSurvivalChance() ) //Crucial decision for game rules here, might change
                  {
                     
                     if ( aliveNeighbours.size() >= ONE_NEIGHBOUR && aliveNeighbours.size() <= THREE_NEIGHBOUR )
                     {
                        //if  suitable amount of alive neighbors, reproduce after calculating reproduction chance
                        
                        if( totalNeighbourCell > aliveNeighbours.size() + selectedTiles ) //if there are spaces left for offspring
                        {                                                                 //also considering the selected tiles
                           
                        
                              
                           if ( aliveNeighbours.size() == TWO_NEIGHBOUR && TWO_NEIGHBOUR * Math.random() > REPR_THRESHOLD )//Reproduction chance can be changed
                           {  
                           Tile offspring;
                              int mateSelect;
                              
                              
                              mateSelect = (int) ( Math.random() * aliveNeighbours.size() ); 
                                                            
                              offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) ); //Selecting a tile for the offspring
                              
                              offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism( 
                                tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                                                                           get( mateSelect ).getOrganism() ) );
                              
                              
                              tiles[offspring.getRow()][offspring.getCol()].setSelected( true);
                              
                           }
                           else if ( aliveNeighbours.size() == THREE_NEIGHBOUR && THREE_NEIGHBOUR * Math.random() > REPR_THRESHOLD )
                           {  
                           Tile offspring;
                              int mateSelectThree;
                              
                              mateSelectThree = (int) (Math.random() * aliveNeighbours.size() ); //Choosing a partner from three alive neighbour                             
                              
                              offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) );
                              
                              offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism(
                                tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                                                                           get( mateSelectThree ).getOrganism() ) );       
                           }              
                        }                     
                     }
                     else
                     {
                        //die without reproducing, number of neighbours required isn't met!
                        
                        tiles[i][j].killOrganism();
                     }
                     
                  }
                  
                  else
                  {
                     //die without reproducing, survival chance is not enough!
                     
                     tiles[i][j].killOrganism();
                  }
                      
               }
               //end of for loops, therefore end of checks for a single organism
               
               selectedTiles       = 0;
               totalNeighbourCell  = 0;
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
                  tiles[i][j].getOrganism().age();      
               }
               
               if ( !offspringTiles[i][j].isEmpty() )
               {
                  
                  tiles[i][j].placeOrganism(offspringTiles[i][j].getOrganism() ); //porting our offsprings back to original organisms array
                  
                  
                  offspringTiles[i][j].killOrganism(); //flushing offsprings
               }
               
               tiles[i][j].setSelected( false ); //setting the new-borns to unselected state so that 
               //they will be counted as a neighbour next round
            }
         }
         
         round++;     
         return true;
      }
      else
      {
         setSampleOrganismAndSurvivalRate();
         return false;
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
      
      if ( round >= ROUND_LIMIT )
         return true;
      
      for ( int i = 0; i < SIZE; i++ )
      {
         for ( int j = 0; j < SIZE; j++ )
         {
            if ( !tiles[i][j].isEmpty() )
               return false;
         }
      }
      
      return true;  //No organisms left
   }
   
   /**
    * Gets the environment of the world
    * 
    * @return the environment of the world
    */
   public Environment getEnvironment()
   {
      return environment;
   }
   
   /**
    * Gets the current round number
    * @return the current turn number
    */
   public int getRound()
   {
      return round;
   }
   
   /**
    * Gets the size of the grid of the game
    * 
    * @return the size of the grid of the game
    */
   public int getSize() 
   {
      return SIZE; 
   }
   
   /**
    * Gets a copy of the first Organisms that were brought into this world
    * 
    * @return a copy of the first Organisms that were brought into this world
    */
   public Organism getFirstOrganism()
   {
      return firstOrganism;
   }
   
   /**
    * Sets the sample organism and the final survival rate of the organisms.
    */
   private void setSampleOrganismAndSurvivalRate()
   {
      boolean foundSample;
      double survivalSum;
      int organismCount;
      
      foundSample = false;
      survivalSum = 0.0;
      organismCount = 0;
      
      for ( int i = 0; i < tiles.length; i++ )
      {
         for ( int j = 0; j < tiles[ i ].length; j++ )
         {
            if ( !tiles[i][j].isEmpty() )
            {
               if ( !foundSample )
               {
                  sampleOrganism = tiles[i][j].getOrganism();
                  foundSample = true;
               }
               survivalSum += tiles[i][j].getOrganism().getSurvivalChance();
               organismCount++;
            }
         }
      }
      if ( !foundSample )
         sampleOrganism = null;
      else
      {
         finalSurvivalRate = survivalSum / organismCount;
      }
   }
   
   /**
    * Gets a sample organism from the final state of the world's grid.
    * 
    * @return a sample organism from the final state of the world's grid.
    */
   public Organism getSampleOrganism()
   {
      return sampleOrganism;
   }
   
   /**
    * Gets the final survival rate of the organisms.
    * 
    * @return finalSurvivalRate - the final survival rate of the organisms
    */
   public double getFinalSurvivalRate()
   {
      return finalSurvivalRate;
   }
   
   /**
    * Gets the size of the grid.
    * 
    * @return SIZE - the pre-determined size of the grid.
    */
   public static int getSIZE() 
   {
      return SIZE;
   }
}