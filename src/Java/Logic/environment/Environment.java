package environment;
/**
 * @author Ege Balcioglu, Ismail Ilter Sezan
 * @version 27.4.2018
 */
import java.util.*;
import feature.*;
public class Environment 
{
   public static final EnvironmentalCondition[] possibleEnvironmentalConditions
      = { /* stub */ };
   // TreeSet utilized for faster calls of add() method
   private TreeSet<EnvironmentalCondition> environmentalConditions;
   private boolean conditionsSet;
   private String name;
   
   public Environment()
   {
      environmentalConditions = new TreeSet<EnvironmentalCondition>
         ( new Comparator<EnvironmentalCondition>() {
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
      // stub, will be filled in more when more EnvironmentalCondition subclasses are written
      conditionsSet = false;
   }
   
   /**
    * Will set a name for this environment according to the random conditions set
    */
   private void setName()
   {
      // stub, will be filled in more when more EnvironmentalCondition subclasses are written
      assert conditionsSet; // be sure to make this statement the first line of this method
      
      boolean andCheck;
      andCheck = false;
      
      // equals method will be used while checking containment. If the class names are same equals return true. 
      if ( environmentalConditions.contains( new Windy() ) )
      {
         name = "Windy ";
         andCheck = true;
      }
      
      if ( andCheck && ( environmentalConditions.contains( new HotTemperature() )  ||
          environmentalConditions.contains( new ColdTemperature() ) ) )
      {
         name = name + "and ";
      }
      
      if ( environmentalConditions.contains( new HotTemperature() ) )
      {
         name = name + "Hot ";
      }
      else if ( environmentalConditions.contains( new ColdTemperature() ) )
      {
         name = name + "Cold ";
      }
      
      name = name + "Environment";
   }
   
   /**
    * Filters a single Feature.
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
   
   public String getName()
   {
      return name;
   }
}
