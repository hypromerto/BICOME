package environment;
/**
 * @author Ege Balcýoðlu, Ýlter Sezan
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
      });
      setRandomConditions();
      setName();
   }
   
   /**
    * Will set random conditions to this environment
    */
   private void setRandomConditions()
   {
      // stub
      conditionsSet = false;
   }
   
   /**
    * Will set a name for this environment according to the random conditions set
    */
   private void setName()
   {
      // stub
      assert conditionsSet; // be sure to make this statement the first line of this method
   }
   
   public void filter( Feature... featuresOfOrganism )
   {
      EnvironmentalCondition envCod;
      for ( Iterator<EnvironmentalCondition> iterator = environmentalConditions.iterator(); iterator.hasNext(); )
      {
         envCod = iterator.next();
         for ( int f = 0; f < featuresOfOrganism.length; f++ )
         {
            // check if the feature is affected by said EnvironmentalCondition - god that is a long name
            if ( envCod.getMultipliers().containsKey( featuresOfOrganism[f] ) )
            {
               // multiply every multiplier value inside each feature with the corresponding multiplier of the envCod
               featuresOfOrganism[f].multiply( envCod.getMultipliers().get( featuresOfOrganism[f] ) );
            }
         }
      }
   }
   
   public String getName()
   {
      return name;
   }
}
