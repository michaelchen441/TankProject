import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PlayerTank extends Tank
{
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();
	double turretAngle;
	
	
	public PlayerTank(int inX, int inY)
	{
		super();
		type = TankType.GREEN;
		xLoc = inX * 50; //multiples so they can be set up with the same grid as walls
		yLoc = inY * 50;
		
	}
	
	
	public void move(int incx, int incy){
		xLoc += incx;
		yLoc -= incy;
	}
	
	public void aim(){
		
	}
	
	public void fire()
	{
		if(stockPile.size() > 0) {
			new Projectile(xLoc,yLoc,TankType.GREEN, turretAngle);
			stockPile.remove(stockPile.size());
		}
		
	}
	
	void draw(Graphics g)
	{
		
		
	}
}


