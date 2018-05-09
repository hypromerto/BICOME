package bicome.logic.feature;
/** 
 * A list that contains Features. In a single run-time, every instance of this class will have the same order of bases
 * For example, if the first FeatureList ever contains a Feature with the base Fur at the second index, every other FeatureList will be guaranteed to have a Fur at the second index.
 * @author Ege Balcioglu
 * @version 3.5.2018
 */
import bicome.logic.genotype.Genotype;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.RandomAccess;
import java.util.Iterator;

public class FeatureList extends AbstractList<Feature> implements RandomAccess
{
   // properties
   private static final int INITIAL_CAPACITY = 6;
   private static Hashtable<FeatureBase, Integer> assignedIndices;
   private static Hashtable<Integer, FeatureBase> basesOfIndices;
   private Hashtable<FeatureBase, Feature> features;
   private ArrayList<Feature> featureList;
   
   // constructors
   /**
    * Standard constructor that creates an empty FeatureList with a certain initial capacity
    */
   public FeatureList()
   {
      assignedIndices = new Hashtable<>( INITIAL_CAPACITY );
      basesOfIndices = new Hashtable<>( INITIAL_CAPACITY );
      features = new Hashtable<>( INITIAL_CAPACITY );
      featureList = new ArrayList<>( INITIAL_CAPACITY );
   }
   
   /**
    * Constructs a FeatureList from an unordered collection of Features.
    * Also acts as a copy constructor
    */
   public FeatureList( Collection<Feature> features )
   {
      this();
      for ( Feature f : features )
      {
         this.add( f );
      }
   }
   
   @Override
   public Feature get( int index )
   {
      if ( !featureList.isEmpty() && index < featureList.size() && featureList.get( index ) != null )
         return featureList.get( index );
      return new Feature( getBase( index ), Genotype.NONE );
   }
   
   /**
    * Returns the size of this list, which is the same for every instance of this class in a single runtime
    * @return number of feature bases that are associated with an index
    */
   @Override
   public int size()
   {
      return assignedIndices.size();
   }
   
   public Feature get( FeatureBase base )
   {
      return features.getOrDefault( base, new Feature( base, Genotype.NONE ) );
   }
   
   /**
    * Adds the given feature to this list if the feature is eligible
    * @param f the feature to be added
    */
   @Override
   public boolean add( Feature f )
   {
      featureList.ensureCapacity( this.size() );
      Feature oldFeature;
      
      oldFeature = features.putIfAbsent( f.getBase(), f );
      
      if ( oldFeature == null )
      {
         if ( assignedIndices.putIfAbsent( f.getBase(), this.size() ) == null )
         {
            assert basesOfIndices.putIfAbsent( this.size() - 1, f.getBase() ) == null;
         }
         featureList.add( f );
         return true;
      }
      
      if ( oldFeature != null && !oldFeature.deepEquals( f ) )
      {
         featureList.set( this.indexOf( f.getBase() ), f );
         return true;
      }
      
      if ( oldFeature != null && oldFeature.deepEquals( f ) )
         return false;
      
      return true;
   }
                      
   public int indexOf( FeatureBase base )
   {
      return assignedIndices.getOrDefault( base, -1 );
   }
   
   public boolean contains( Feature f )
   {      
      if ( f.deepEquals( features.get( f.getBase() ) ) )
         return true;
      return false;
   }
   
   public int indexOf( Feature f )
   {
      if ( this.contains( f ) )
         return indexOf( f.getBase() );
      return -1;
   }
   
   @Override
   public Feature remove( int index )
   {
      return this.remove( this.get( index ) );
   }
   
   public Feature remove( Feature f )
   {
      return this.remove( f.getBase() );
   }
   
   public Feature remove( FeatureBase base )
   {
      int removalIndex = this.indexOf( base );
      featureList.set( removalIndex, null );
      return features.remove( base );
   }
   
   public static FeatureBase getBase( int index )
   {
      return basesOfIndices.get( index );
   }
   
   public String toString()
   {
      StringBuffer result;
      result = new StringBuffer( "" );
      for ( int i = 0; i < this.size(); i++ )
      {
         result.append( this.get( i ) );
         result.append( "\n" );
      }
      return result.toString();
   }
}