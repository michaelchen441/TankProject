import java.awt.Color;
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
	
	
	public void move(int incx, int incy){
		xLoc += incx;
		yLoc -= incy;
	}
	
	public void aim(){
		
	}
	
	public void fire()
	{
		
		//create projectile with input: type
	}
	
	void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(xLoc, yLoc, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xLoc, yLoc, width, height);
		
	}
}


