import java.awt.Graphics;

public abstract class Tank
{
	TankType type;
	int height;
	int width;
	int speed;
	int xLoc;
	int yLoc;
	
	public Tank() {
		height = 28;
		width = 56;
	}
	
	
	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g);
	
	
}
