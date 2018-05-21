import java.awt.Color;
import java.awt.Graphics;

public class Projectile
{
		double xLoc;
		double yLoc;
		double speed;
		TankType color;
		double angle;
		boolean active;
		Wall[][] walls;
		
	public Projectile(int x, int y, double a, double s, Wall[][] inWalls)
	{
		active = true;
		xLoc = x;
		yLoc = y;
		angle = a;
		speed = s;
		walls = inWalls;
		
	}
	
	
	
	void draw(Graphics g, ImageLibrary l){
//		double rotationRequired = Math.toRadians (angle);
//		double locationX = l.projectile.getWidth() / 2;
//		double locationY = l.projectile.getHeight() / 2;
//		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
//		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//		g.drawImage(op.filter(l.projectile, null), xLoc, yLoc, null);	
		g.setColor(Color.BLACK);
		g.fillRect((int)(xLoc), (int)(yLoc), 15, 15);
		}
	
	
	
	void move() {
		
		if(!detectedWall()) {
		xLoc+=speed*Math.cos(angle);
		//System.out.println(angle);
		yLoc+=speed*Math.sin(angle);
		//System.out.println(angle);
		}
	}



	private boolean detectedWall() {
		
		return false;
	}
}
