import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Tank
{
	TankType type;
	int height;
	int width;
	int speed;
	int xLoc;
	int yLoc;
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();
	
	public Tank() {
		height = 28;
		width = 56;
	}
	
	
	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g);
	
	
}
