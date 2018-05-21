import java.awt.Graphics;

public class AITank extends Tank //AI Tank is a specific type of Tank
{
	double turretAngle; //Angle of turret in radians
	int xLoc; //X Location of AITank
	int yLoc; //Y Location of AITank
	
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
		
	}
	
	void draw(Graphics g, ImageLibrary l)
	{
//		//Draws a red tank if prompted
//		if(type.equals(TankType.RED)){
//			g.drawImage(l.redTank, xLoc, yLoc, null);
//		}
//		//Draws a blue tank if prompted
//		if(type.equals(TankType.BLUE)){
//			g.drawImage(l.blueTank, xLoc, yLoc, null);
//		}
//		//Draws a black tank if prompted
//		if(type.equals(TankType.BLACK)){
//			g.drawImage(l.blackTank, xLoc, yLoc, null);
//		}
	
	}
	
	
	
}
