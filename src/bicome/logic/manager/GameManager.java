package bicome.logic.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bicome.controllers.*;
import bicome.logic.*;
import bicome.logic.environment.Environment;
import bicome.logic.feature.*;
import bicome.logic.world.*;

/* A class that manages the game
 * @author İsmail İlter Sezan
 */
public class GameManager
{
   
   //properties
   private final int UP_TIME = 10;
   private Timer turnTimer;
   private int durationOfTurns;
   private int timePassed;
   private int yearsPassed;
   private int numOfTurns;
   private World world;
   private GameController controller;
   private FeatureList features;
   
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
         if ( timePassed % durationOfTurns == 0 && world.nextTurn() && timePassed != 0 )
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

   public void start()
   {
      turnTimer.start();
   }

   public World getWorld()
   {
      return world;
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
