import java.awt.Graphics;

public class PlayerTank extends Tank
{
	
	
	
	public PlayerTank(int inX, int inY)
	{
		super();
		type = TankType.GREEN;
		xLoc = inX;
		yLoc = inY;
		
	}
	
	
	void move()
	{
		
	}
	
	void aim()
	{
		
	}
	
	void fire()
	{
		
		//create projectile with input: type
	}
	
	void draw(Graphics g)
	{
		g.drawRect(xLoc, yLoc, width, height);
	}
}
