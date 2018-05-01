package Java.Logic.feature;

import genotype.Genotype;
import java.util.HashMap;
import java.util.function.*;
import attribute.Attribute;
/**
 * @author Ege Balcioglu
 * @version 23.4.2018
 */
public abstract class Feature
{
   // properties
   private Genotype genotype;
   protected HashMap< String, Double > dominantMultipliers;
   protected HashMap< String, Double > recessiveMultipliers;
   protected HashMap< String, Double > noneMultipliers;
   
   // constructors
   public Feature( Genotype genotype )
   {
      this.genotype = genotype;
      dominantMultipliers = new HashMap<>();
      recessiveMultipliers = new HashMap<>();
      noneMultipliers = new HashMap<>();
      setMultipliers();
   }
   
   // methods
   public Genotype getGenotype()
   {
      return this.genotype;
   }
   
   public final HashMap< String, Double > getMultipliers()
   {
      if ( this.getGenotype() == Genotype.RECESSIVE_HOMOZYGOTE )
      {
         return recessiveMultipliers;
      }
      
      if ( this.getGenotype() == Genotype.NONE )
      {
         return noneMultipliers;
      }
      
      return dominantMultipliers; 
   }
   
   /**
    * Unorthodox equals method to be used implicitly in Organism class's 
    * setAttributesFromEnvironment() method, and Environment's filter() method.
    * It ensures that the multipliers that is returned by the getMultipliers() 
    * method of EnvironmentalCondition class is properly multiplied with the 
    * organism's correct features' multipliers. To do this, this method 
    * implicitly converts the genotype into a phenotype and then compares 
    * the phenotypes of the two features in question.
    * @param other the other object to be compared with this one
    * @return returns true if the other object is a Feature that is the same type
    * and bears the same phenotype as this one.
    * @see world.Organism Organism
    * @see world.World World
    * @see environment.Environment Environment
    * @see environment.EnvironmentalCondition EnvironmentalCondition
    */
   @Override
   public boolean equals( Object other )
   {
      if ( other != null && other instanceof Feature &&
          this.getClass().getName().equals( other.getClass().getName() ) )
      {
         Genotype g1;
         Genotype g2;
         g1 = this.getGenotype();
         g2 = ( (Feature) other ).getGenotype();
         // Implicit conversion of genotypes to phenotypes
         if ( g1.equals( g2 ) )
            return true;
         if ( g1.equals( Genotype.DOMINANT_HOMOZYGOTE ) 
                && g2.equals( Genotype.DOMINANT_HETEROZYGOTE ) )
            return true;
         if ( g1.equals( Genotype.DOMINANT_HETEROZYGOTE )
                && g2.equals( Genotype.DOMINANT_HOMOZYGOTE ) )
            return true;
      }
      return false;
   }
   
   public void multiply( Double externalMultiplier )
   {
      this.getMultipliers().replaceAll( new BiFunction< String, Double, Double >() {
         public Double apply( String attributeType, Double internalMultiplier )
         {
            return internalMultiplier * externalMultiplier;
         }
      } );
   }
   
   protected abstract void setMultipliers();
}