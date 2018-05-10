package bicome.logic.world;
/**
 * @author  Mert Aslan
 * @version 28.04.2018
 */

import java.util.ArrayList;
import bicome.logic.environment.Environment;
import bicome.logic.feature.*;

public class World 
{
   private static final int    SIZE            = 30;   //Size of the simulation grid
   private static final double REPR_THRESHOLD  = 1.3;  //Reproduction chance, DUE TO CHANGE
   private static final int    ONE_NEIGHBOUR   = 1;    //Number of neighbours surrounding the center organism
   private static final int    TWO_NEIGHBOUR   = 2;    //Number of neighbours surrounding the center organism
   private static final int    THREE_NEIGHBOUR = 3;    //Number of neighbours surrounding the center organism
   private static final int    ROUND_LIMIT     = 50;   //The total amount of possible rounds of the game
   
   private Tile[][]    tiles;          //The simulation multi-array
   private Tile[][]    offspringTiles; //The multi-array that is used to hold the new-borns
   private Environment environment;    //The environment of the world
   private int         round;          //Round count of the game
   
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
      
      placeInitialOrganisms( features );
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
                   
                   //if ( !( aliveNeighbours.size() < TWO_NEIGHBOUR || aliveNeighbours.size() > THREE_NEIGHBOUR ) )
                    if ( aliveNeighbours.size() >= 1 && aliveNeighbours.size() <= 3 )
                     {
                         //if  suitable amount of alive neighbors, reproduce after calculating reproduction chance
                         
                         if( totalNeighbourCell > aliveNeighbours.size() + selectedTiles ) //if there are spaces left for offspring
                         {                                                                 //also considering the selected tiles
                            
                              // System.out.println("Row: " + i + " Col : " + j + " Empty neighbours: " + emptyNeighbours.size());
//                            System.out.println("Row: " + i + " Col : " + j + " Alive neighbours: " + aliveNeighbours.size());
//                            System.out.println("Row: " + i + " Col : " + j + " Selected tiles " + selectedTiles);
                          
                         if ( aliveNeighbours.size() == ONE_NEIGHBOUR && Math.random() > REPR_THRESHOLD)
                         {
                          Tile offspring;
                          
                                offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) ); //Selecting a tile for the offspring
                                
                                offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism( tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                get( 0 ).getOrganism() ) );
                         }

                            if ( aliveNeighbours.size() == TWO_NEIGHBOUR && TWO_NEIGHBOUR * Math.random() > REPR_THRESHOLD )//Reproduction chance can be changed
                            {  
                               int mateSelectTwo;
                               
                               //mateSelectTwo = (int) Math.round( Math.random() ); //Choosing a partner from two alive neighbours
                                
                               mateSelectTwo = (int) ( Math.random() * aliveNeighbours.size() );
                               
//                               System.out.println("Row: " + i + " Col : " + j + " Mate Location: " + "Row: " +
//                               aliveNeighbours.get( mateSelectTwo).getRow() + " Col: "  + aliveNeighbours.get( mateSelectTwo).getCol());

                               //if ( tiles[i][j].getOrganism().canReproduce() && aliveNeighbours.get( mateSelectTwo ).getOrganism().canReproduce() )
                               //{    
                                  Tile offspring;
                                  
                                  offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) ); //Selecting a tile for the offspring
                                  
                                  offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism( tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                  get( mateSelectTwo ).getOrganism() ) );
                                  
//                                  System.out.println("Row: " + i + " Col : " + j + " Offspring Location: " + "Row: " +
//                                          offspring.getRow() + " Col: "  + offspring.getCol() );
                                  
                                  tiles[offspring.getRow()][offspring.getCol()].setSelected( true);
                                  
//                                  System.out.println(offspringTiles[offspring.getRow()][offspring.getCol()].getOrganism());
                                  
                               //}  
                               
                            }
                            else if ( aliveNeighbours.size() == THREE_NEIGHBOUR && THREE_NEIGHBOUR * Math.random() > REPR_THRESHOLD )
                            {  
                               int mateSelectThree;
                               
                               mateSelectThree = (int) (Math.random() * 3); //Choosing a partner from three alive neighbour
            
                               //if ( tiles[i][j].getOrganism().canReproduce() && aliveNeighbours.get( mateSelectThree ).getOrganism().canReproduce() )
                               //{
                                Tile offspring;
                                
                                offspring = emptyNeighbours.get( (int) (Math.random() * emptyNeighbours.size() ) );
                                
                                offspringTiles[offspring.getRow()][offspring.getCol()].placeOrganism(tiles[i][j].getOrganism().reproduce( aliveNeighbours.
                                get( mateSelectThree ).getOrganism() ) );    
                                
                               //}     
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
               // stub... flushings of some variables will be done here
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
                 // if ( !tiles[i][j].getOrganism().canReproduce() ) //incrementing the organisms with cooldown active
                   //  tiles[i][j].getOrganism().increaseCooldown();
                  
                  tiles[i][j].getOrganism().age();
                                    
                  
               }
               
               if ( !offspringTiles[i][j].isEmpty() )
               {
                
               // System.out.println("This is offspring tiles");
                //System.out.println( offspringTiles[i][j].getOrganism());
                
                
                  tiles[i][j].placeOrganism(offspringTiles[i][j].getOrganism() ); //porting our offsprings back to original organisms array
                  
//                  System.out.println();
                  
//                  System.out.println("Tiles after placing");
//                  System.out.println( tiles[i][j].getOrganism());
                 
    
                  
                  offspringTiles[i][j].killOrganism(); //flushing offsprings
                  
                  if ( !tiles[i][j].isEmpty())
                  {
//                      System.out.println( "After porting: Row: " + tiles[i][j].getRow() + " Col: " + tiles[i][j].getCol() );

                  }
                  
                  //System.out.println();
                  
                  //System.out.println( tiles[i][j].getOrganism());
               }
               
               tiles[i][j].setSelected( false ); //setting the new-borns to unselected state so that 
               //they will be counted as a neighbour next round
            }
         }
         
         round++;     
         
         System.out.println( "Round: " + round);
         
         return true;
      }
      else
       return false;
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
    * @return  environment    the environment of the world
    */
   public Environment getEnvironment()
   {
      return environment;
   }
   
   public int getRound()
   {
    return round;
   }

   public int getSize() { return SIZE; }
}
