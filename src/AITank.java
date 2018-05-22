import java.awt.Graphics;

public class AITank extends Tank //AI Tank is a specific type of Tank
{

	
	
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
