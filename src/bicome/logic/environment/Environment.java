package bicome.logic.environment;
/**
 * @author Ege Balcioglu, Ismail Ilter Sezan
 * @version 27.4.2018
 */
import java.util.*;
import bicome.logic.feature.*;
public class Environment 
{
   private static final int MAXIMUM_NUMBER_OF_CONDITIONS = 3;
   // TreeSet utilized for faster calls of add() method
   private TreeSet<EnvironmentalCondition> environmentalConditions;
   private boolean conditionsSet;
   private String name;
   
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
    * Will set random conditions to this environment
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
         if ( newEnvironmentalCondition == new ColdTemperature() )
         {
            if ( (int) ( Math.random() * 2 ) == 1 )
               newEnvironmentalCondition = new HotTemperature();
         }
         environmentalConditions.add( newEnvironmentalCondition );
      }
      conditionsSet = true;
   }
   
   /**
    * Will set a name for this environment according to the random conditions set
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
}
