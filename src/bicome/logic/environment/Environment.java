package bicome.logic.environment;
/**
 * @author Ege Balcioglu, Ismail Ilter Sezan
 * @version 27.4.2018
 */
import java.net.URL;
import java.util.*;
import bicome.logic.feature.*;
import javafx.scene.image.Image;

import javax.annotation.Resources;

public class Environment
{
   private static final int MAXIMUM_NUMBER_OF_CONDITIONS = 3;
   // TreeSet utilized for faster calls of add() method
   private TreeSet<EnvironmentalCondition> environmentalConditions;
   private boolean conditionsSet;
   private String name;
   
   /**
    * Sole constructor that sets random conditions to this environment
    */
   public Environment()
   {
      environmentalConditions = new TreeSet<EnvironmentalCondition>( new Comparator<EnvironmentalCondition>() {
         // an adequate comparator for easy traversal/iteration through this TreeSet
         public int compare( EnvironmentalCondition e1, EnvironmentalCondition e2 )
         {
            return e1.getClass().getName().compareTo( e2.getClass().getName() );
         }
      } );
      setRandomConditions();
      setName();
   }
   
   /**
    * Sets random conditions to this environment
    */
   private void setRandomConditions()
   {
      // the list below should be filled in more whenever more EnvironmentalCondition subclasses are written
      ArrayList<EnvironmentalCondition> possibleEnvironmentalConditions
         = new ArrayList<EnvironmentalCondition>(){{
         add( new ColdTemperature() );
         add( new Humid() ); 
         add( new HighPredatorDensity() );
         add( new LowPreyDensity() );
         add( new Windy() );
      }};
      
      for ( int i = 0; i < MAXIMUM_NUMBER_OF_CONDITIONS; i++ )
      {
         EnvironmentalCondition newEnvironmentalCondition;
         newEnvironmentalCondition = possibleEnvironmentalConditions
            .remove( (int) ( Math.random()
                               * possibleEnvironmentalConditions.size() ) );
         if ( newEnvironmentalCondition.equals( new ColdTemperature() ) )
         {
            System.out.println( "Deciding if hot or cold" );
            if ( (int) ( Math.random() * 2 ) == 1 )
               newEnvironmentalCondition = new HotTemperature();
         }
         environmentalConditions.add( newEnvironmentalCondition );
      }
      conditionsSet = true;
   }
   
   /**
    * Sets a name for this environment according to the random conditions set
    */
   private void setName()
   {
      assert conditionsSet; // be sure to make this statement the first line of this method
      
      name = "";
      
      // equals method will be used while checking containment. If the class names are same equals return true.
      if ( environmentalConditions.contains( new HighPredatorDensity() ) )
      {
         name = name + "Unsafe ";
      }
      
      if ( environmentalConditions.contains( new Windy() ) )
      {
         name = name + "Windy ";
      }
      
      if ( environmentalConditions.contains( new HotTemperature() ) && !environmentalConditions.contains( new Humid() ) )
      {
         name = name + "Desert ";
      }
      else if ( environmentalConditions.contains( new ColdTemperature() ) && !environmentalConditions.contains( new Humid() ) )
      {
         name = name + "Pole ";
      }
      else if ( environmentalConditions.contains( new HotTemperature() ) && environmentalConditions.contains( new Humid() ) )
      {
         name = name + "Tropical Rainforest ";
      }
      else if ( environmentalConditions.contains( new ColdTemperature() ) && environmentalConditions.contains( new Humid() )  )
      {
         name = name + "Continental ";
      }
      
       if ( environmentalConditions.contains( new LowPreyDensity() )  )
      {
         name = name + "and Scarce "; 
      }
      
         name = name + "Environment ";
   }

   public Image getImage() {

      if ( this.name.equals("Unsafe Windy Desert Environment ") || this.name.equals("Unsafe Windy Desert and Scarce Environment "))
         return new Image ("Resources/Images/desert&wind&predator.jpg");
      else if ( this.name.equals("Unsafe Windy Pole Environment ") || this.name.equals("Unsafe Windy Pole and Scarce Environment "))
         return new Image ("Resources/Images/pole&wind&predator.jpg");
      else if ( this.name.equals("Unsafe Windy Tropical Rainforest Environment ") || this.name.equals("Unsafe Windy Rainforest and Scarce Environment "))
         return new Image ("Resources/Images/rainforest&wind&predator.jpg");
      else if ( this.name.equals("Unsafe Windy Continental Environment ") || this.name.equals("Unsafe Windy Continental and Scarce Environment "))
         return new Image ("Resources/Images/condinental&wind&predator.jpg");

      else if ( this.name.equals("Unsafe Desert Environment ") || this.name.equals("Unsafe Desert and Scarce Environment "))
         return new Image ("Resources/Images/desert&predator.jpg");
      else if ( this.name.equals("Unsafe Pole Environment ") || this.name.equals("Unsafe Pole and Scarce Environment "))
          return new Image ("Resources/Images/pole&predator.jpg");
      else if ( this.name.equals("Unsafe Tropical Rainforest Environment ") || this.name.equals("Unsafe Tropical Rainforest and Scarce Environment "))
          return new Image ("Resources/Images/rainforest&predator.jpg");
      else if ( this.name.equals("Unsafe Continental Environment ") || this.name.equals("Unsafe Continental and Scarce Environment "))
         return new Image ("Resources/Images/continental&predator.jpg");

      else if ( this.name.equals("Windy Desert Environment ") || this.name.equals("Windy Desert and Scarce Environment "))
         return new Image ("Resources/Images/desert&wind.jpg");
      else if ( this.name.equals("Windy Pole Environment ") || this.name.equals("Windy Pole and Scarce Environment "))
         return new Image ("Resources/Images/pole&wind.jpg");
      else if ( this.name.equals("Windy Tropical Rainforest Environment ") || this.name.equals("Windy Tropical Rainforest and Scarce Environment "))
         return new Image ("Resources/Images/rainforest&wind.jpg");
      else if ( this.name.equals("Windy Continental Environment ") || this.name.equals("Windy Continental and Scarce Environment "))
         return new Image ("Resources/Images/continental&wind.jpg");

      else if ( this.name.equals("Desert Environment ") || this.name.equals("Desert and Scarce Environment "))
         return new Image ("Resources/Images/desert&wind.jpg");
      else if ( this.name.equals("Pole Environment ") || this.name.equals("Pole and Scarce Environment "))
         return new Image ("Resources/Images/pole.jpg");
      else if ( this.name.equals("Tropical Rainforest Environment ") || this.name.equals("Tropical Rainforest and Scarce Environment "))
         return new Image ("Resources/Images/rainforest.jpg");
      else if ( this.name.equals("Continental Environment ") || this.name.equals("Continental and Scarce Environment "))
         return new Image ("Resources/Images/continental.jpg");
      throw new AssertionError();
   }
   
   /**
    * Filters a single Feature.
    * @param featureOfOrganism the feature whose multipliers will be altered.
    */
   public void filter( Feature featureOfOrganism )
   {
      EnvironmentalCondition envCod;
      for ( Iterator<EnvironmentalCondition> iterator = environmentalConditions.iterator(); iterator.hasNext(); )
      {
         envCod = iterator.next();
         // check if the feature is affected by said EnvironmentalCondition - god that is a long name
         if ( envCod.getMultipliers().containsKey( featureOfOrganism ) )
         {
            // multiply every multiplier value inside each feature with the corresponding multiplier of the envCod
            featureOfOrganism.multiply( envCod.getMultipliers().get( featureOfOrganism ) );
         }
      }
   }
   
   /**
    * Returns the name of this environment according to its conditions
    * @return the name of this environment
    * @deprecated use toString method now, it does exactly the same thing
    */
   @Deprecated
   public String getName()
   {
      return name;
   }
   
   /**
    * Returns the name of this environment according to its conditions
    * @return the name of this environment
    */
   @Override
   public String toString()
   {
      return name;
   }
   
   /**
    * Returns the conditions of environment as a String.
    * @return the conditions of environment as a String.
    * The conditions are seperated by commas
    */
   public String getConditions()
   {
      StringBuffer result;
      result = new StringBuffer( "" );
      for ( EnvironmentalCondition envCod : environmentalConditions )
      {
         result.append( envCod );
         result.append( ", " );
      }
      result.delete( result.length() - 2, result.length() );
      return result.toString();
   }
   
   /**
    * Returns the conditions of environment as a String.
    * @return the conditions of environment as a String.
    * The conditions are seperated by newline characters.
    */
   public String getConditionsForGUI()
   {
      StringBuffer result;
      result = new StringBuffer( "" );
      for ( EnvironmentalCondition envCod : environmentalConditions )
      {
         result.append( envCod );
         result.append( "\n" );
      }
      result.delete( result.length() - 1, result.length() );
      return result.toString();
   }
}
