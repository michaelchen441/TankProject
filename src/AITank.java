import java.awt.Graphics;

public class AITank extends Tank
{
	int turretAngle;
	int xLoc;
	int yLoc;
	
	public AITank(TankType inType, int x, int y, Wall[][] walls)
	{
		super(walls);
		alive = true;
		type = inType;

	}

	
	
	void move()
	{
		
	}
	
	void aim()
	{
		
	}
	
	void fire()
	{
		//create projectile with input: type, 
		
	}
	
	void draw(Graphics g, ImageLibrary l)
	{
		if(type.equals(TankType.RED)){
			g.drawImage(l.redTank, xLoc, yLoc, null);
		}
		if(type.equals(TankType.BLUE)){
			g.drawImage(l.blueTank, xLoc, yLoc, null);
		}
		if(type.equals(TankType.BLACK)){
			g.drawImage(l.blackTank, xLoc, yLoc, null);
		}
	}
	
	
}
