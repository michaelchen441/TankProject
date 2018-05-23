import java.awt.Graphics;

public class AITank extends Tank //AI Tank is a specific type of Tank
{

	int numMoveTries = 0;//Number of times the tank has tried to move
	int tankSlowMultiplier = 3;//ex. 1 is fastest, 3 is 1/3 speed
	
	public AITank(TankType inType, int x, int y, Wall[][] walls)
	{
		super(walls); // Calls superclass contructor which takes in all walls in arena
		alive = true;
		type = inType;
		xLoc = x*50; //Each cell is 50 pixels in wide
		yLoc = y*50; //Each cell is 50 pixels in length

	}

	
	//Need to figure out mechanism by which AI Tank Moves
	void move()
	{
		//Everytime a tank moves, the number of times it has tried increments 
		numMoveTries++;
		
		if(numMoveTries%tankSlowMultiplier == 0){ //checks if its time to move based on the slow multipler
			//write move code in here
			
			
			
		}
	}
	//Need to figure out mechanism by which AI Tank Aims
	void aim()
	{
		
	}
	//Need to figure out mechanism by which AI Tank Fires
	void fire()
	{
		//create projectile with input: type, 
		Projectile p = new Projectile(xLoc+25 , yLoc+25, Math.atan2(-(targetY - turretCenterY), targetX - turretCenterX),type, surroundingWalls);
		
	}

	
	
}
