package bicome.logic.manager;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;

import javafx.application.*;
import bicome.controllers.*;
import bicome.logic.*;
import bicome.logic.environment.Environment;
import bicome.logic.feature.*;
import bicome.logic.world.*;

/**
 * A class that manages the interactions between the World class and the controller classes
 * @author Ismail Ilter Sezan, Ege Balcioglu
 * @version 9.5.2018
 */
public class GameManager
{
   
   //properties
   public static final int INITIAL_DURATION_OF_TURNS = 1000;
   private static final int UP_TIME = 1;
   private Timer turnTimer;
   private int durationOfTurns;
   private int timePassed;
   private int yearsPassed;
   private int numOfTurns;
   private World world;
   private GameController controller;
   private FeatureList features;
   private boolean hasWon;
   
   //constructor
   /**
    * Sole constructor
    * @param world the world of this game
    * @param controller the game controller of this game
    */
   public GameManager( World world, GameController controller )
   {
      timePassed = 0;
      yearsPassed = 0;
      numOfTurns = 0;
      durationOfTurns = INITIAL_DURATION_OF_TURNS; // in millisecond
      this.world = world;
      this.controller = controller;
      turnTimer = new Timer( durationOfTurns * UP_TIME, new TimeListener() );
   }
   
   /**
    * a TimeListener class that listens to the Timer inside this manager
    * @implements ActionListener
    * @author ISMAIL ILTER SEZAN
    */
   private class TimeListener implements ActionListener
   {
      /**
       * This method performs the action
       */
      public void actionPerformed( ActionEvent e )
      {
         if ( /* timePassed % UP_TIME == 0 && */ world.nextTurn() )
         {
//            System.out.println("next turn");
            Platform.runLater( new Runnable() {
               public void run() {
                  //after initialising play next turn
                  controller.updateGameStage( getYearsPassed() );
               }
            } );
            numOfTurns++;
         }
         else
         {
//            System.out.println("game is over");
            turnTimer.stop();
            hasWon = ( world.getSampleOrganism() != null );
            //inform controller the game is over.
            Platform.runLater( new Runnable() {
               public void run() {
                  controller.finishGame();
               }
            } );
         }
         
         // increment time
         //timePassed = timePassed + UP_TIME;
      }
   }

   
   /**
    * This method returns number of turns
    * @return number of turns
    */
   public int getNumOfTurns()
   {
      return numOfTurns;
   }
   
   /**
    * This method changes the duration of each turn
    * @param n the new duration of turns (Make sure said duration is a multiply of 10 milliseconds.)
    * @return the new duration of turns
    */
   public int setDurationOfTurns( int n )
   {
      durationOfTurns = n;
      turnTimer.setDelay( durationOfTurns * UP_TIME );
      return durationOfTurns;
   }

   /**
    * Starts the timer
    */
   public void start()
   {
      turnTimer.start();
   }
   
   /**
    * Pauses the game
    */
   public void pause()
   {
      turnTimer.stop();
   }
   
   /**
    * returns the world inside this manager
    * @return the world of this game
    */
   public World getWorld()
   {
      return world;
   }

   /**
    * This method gives the duration of each turn
    * @return the duration of turns
    */
   public int getDurationOfTurns()
   {
      return durationOfTurns;
   }
   
   /**
    * This method gives the years passed in the simulation
    * @return the years passed in the simulation
    */
   public int getYearsPassed()
   {
      return numOfTurns;
   }
   
   /**
    * Gets a sample organism from the final state of the world's grid.
    * @return a sample organism from the final state of the world's grid.
    */
   public Organism getAvarageOrganism()
   {
      if ( !hasWon )
         return null;
      return world.getSampleOrganism();
   }
   
   /**
    * returns if the game is won or not
    * @return if the game is won or not
    */
   public boolean gameIsWon()
   {
      assert world.isGameOver();
      return hasWon;
   }
}
