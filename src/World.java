import java.util.ArrayList;

public class World 
{
	
	private final double REPR_THRESHOLD = 1.3;
	
	private Organism[][] organisms;
	private Environment environment;
	private int round;
	
	public World()
	{
		organisms  = new Organism[50][50];
		environment = new Environment();
		round = 0;
	}
	
	public Boolean placeOrganism( int row, int col, Organism o )
	{
       	
	}
	
	public void nextTurn()
	{
		int totalCell;
		ArrayList<Organism> neighbors;
		Organism[][] offsprings;
		Boolean[][] emptySpaces;
		
		
		emptySpaces = new Boolean[9][9];
		offsprings = new Organism[50][50];
		neighbors = new ArrayList<Organism>();
		totalCell = 0;
		
		if ( !isGameOver() )
		{
			for ( int i = 0; i < organisms.length; i++ )
			{
				for ( int j = 0; j < organisms.length; j++ )
				{
					if ( organisms[i][j] instanceof Organism )
					{
						for ( int k = 0; k < 9; k++) 
						{
							int row = i + (k % 3) - 1;
							int col = j + (k / 3) - 1;
							
							if (row >= 0 && row <= organisms.length && col >= 0 && col <= organisms.length && !(row == i && col == j))
							{
								totalCell++;
								
								if ( organisms[row][col] instanceof Organism ) 
								{
									neighbors.add( organisms[row][col ] );
								}
								else
								{
									emptySpaces[row][col] = true; //needs a better implementation or a way to find row col indexes with respect to center organism
								}
							}
							
						}
						
						if ( !( neighbors.size() < 2 || neighbors.size() > 3 ) )
						{
							//if  suitable amount of neighbors, reproduce after calculating reproduction chance
							
							if( totalCell > neighbors.size() ) //if there are spaces left for offspring
							{
								if ( neighbors.size() == 2 && 2 * Math.random() > REPR_THRESHOLD )
								{
									// IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
									int toDo;
									
									toDo = (int) Math.round( Math.random() );
									
											if ( organisms[i][j].canReproduce() && neighbors.get( toDo ).canReproduce() )
											{
												//randomly select a location from emptySpaces array and fill its corresponding index in
												//offsprings multi array
												
												
												
												offsprings[i][j] = organisms[i][j].reproduce(neighbors.get( toDo ) );
												
											}		
								}
								else if ( neighbors.size() == 3 &&  3 * Math.random() > REPR_THRESHOLD )
								{
									// IMPORTANT: AN IF STATEMENT HERE TO CHECK IF ORGANISMS SURVIVAL RATE IS ENOUGH TO SURVIVE!!!!
									
								}
									
							
							}
							
						}
						else
						{
							//die without reproducing
						}
						
						
					}
					
				}
			}
			
			for ( int i = 0; i < offsprings.length; i++ )
			{
				for ( int j = 0; j < offsprings.length; i++ )
				{
					if ( organisms[i][j] instanceof Organism )
					{
						if ( !organisms[i][j].canReproduce() ) //incrementing the organisms with cooldown active
							organisms[i][j].increaseCooldown();
						
						organisms[i][j].age();
					}
					
					if ( offsprings[i][j] instanceof Organism )
					{
						organisms[i][j] = offsprings[i][j]; //porting our offsprings back to original organisms array
						
						offsprings[i][j] = null; //flushing offsprings
					}
				}
			}
			round++;
			
			
		}
	}
	
	
	public Boolean isGameOver()
	{
		
	}
}
