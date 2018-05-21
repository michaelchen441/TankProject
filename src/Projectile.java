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
		TankType type;
		int numWallHits;
		
		
	public Projectile(int x, int y, double a, TankType inType, Wall[][] inWalls)

	{
		xLoc = x;
		yLoc = y;
		angle = a;
		walls = inWalls;
		type = inType;
		angle = a;
		if(type.equals(TankType.GREEN)){
			speed = 7;
		}
		
		active = true;
		numWallHits = 0;
	
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
		
		//if(wallDetected() == false) {
		xLoc+=speed*Math.cos(angle);
		//System.out.println(angle);
		yLoc+=speed*Math.sin(angle);
		//System.out.println(angle);
//		}
//		else{
//			rebound();
//		}
	}



//	private boolean wallDetected() {
//		//the wall detection will use the angle to find what direction. it can be any of the following: north, east, south, west, northeast, northwest, southeast, southwest
//		//based on the direction, it will check a certain side of the wall. in the case of a duel direction, it will check two sides
//		
//		for(int r = 0; r<walls.length; r++) {
//			for(int c = 0; c<walls[r].length; c++) {
//				if(angle<)
//					if()
//			}
//		}
//		return false;
//	}



	private void rebound() {
		
	}



	
}
