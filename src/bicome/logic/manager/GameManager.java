package bicome.logic.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bicome.controllers.*;
import bicome.logic.*;
import bicome.logic.feature.*;
import bicome.logic.world.*;

/* A class that manages the game
 * @author İsmail İlter Sezan
 */
public class GameManager
{
   
   //properties
   final int UP_TIME = 10;
   Timer turnTimer;
   int durationOfTurns;
   int timePassed;
   int yearsPassed;
   int numOfTurns;
   World world;
   GameController controller;
   FeatureList features;
   
   //constructor
   public GameManager( World world , GameController controller )
   {
      timePassed = 0;
      yearsPassed = 0;
      numOfTurns = 0;
      durationOfTurns = 50; //in millisecond
      turnTimer = new Timer( UP_TIME , new GameManager.TimeListener() );
      this.world = world;
      this.controller = controller;
      
      turnTimer.start();
   }
   
   /**
    * Lab04b, a TimeListener class
    * @implements ActionListener
    * @author ISMAIL ILTER SEZAN
    */
   private class TimeListener implements ActionListener
   {
      /* This method performs the action
       */
      public void actionPerformed( ActionEvent e )
      {
         //after initialising play next turn
         if ( timePassed % durationOfTurns == 0 && world.nextTurn() )
         {
            controller.drawGrid();
            numOfTurns++;
         }
         
         // increment time
         timePassed = timePassed +  UP_TIME;
      }
   }
   
   /* This method returns number of turns
    */
   public int getNumOfTurns()
   {
      return numOfTurns;
   }
   
   /* This method changes the duration of each turn
    * Make sure duration is a multiply of 10 milliseconds.
    */
   public int setDurationOfTurns( int n )
   {
      durationOfTurns = n;
      return durationOfTurns;
   }
   
   /* This method gives the duration of each turn
    */
   public int getDurationOfTurns()
   {
      return durationOfTurns;
   }
   
   /* This method gives the years passed in the simulation
    */
   public int getYearsPassed()
   {
      return numOfTurns;
   }
}
