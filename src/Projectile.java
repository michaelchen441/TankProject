import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Projectile
{
	double xLoc; //location of tip; translate to draw but not for hit detection
	double yLoc; //

	double speed;
	double angle; //Angle of projectile in radians, counterclockwise (0 is positive X axis, PI/2 is point up)
	boolean active; //for playertank to remove from stockpile once not active
	Wall[][] walls;
	TankType type;
	int numWallHits;

	//use to stop projectile early for visualizing
	//int numDraws; 

	public Projectile(int x, int y, double a, TankType inType, Wall[][] inWalls)

	{
		xLoc = x;
		yLoc = y;
		angle = a;
		walls = inWalls;
		type = inType;
		angle = a;
	
		if(type.equals(TankType.GREEN)){
			speed = 3;
		}

		active = true;
		numWallHits = 0;

		//	numDraws = 0;

	}



	void draw(Graphics g, ImageLibrary l){

		//draws black box projectile - use to test where image should be 
		//g.setColor(Color.BLACK);
		//g.fillRect((int)(xLoc), (int)(yLoc), 15, 15);


		{	
			Graphics2D	g2D = (Graphics2D)g;
			AffineTransform	backupAT = g2D.getTransform();
			AffineTransform	theAT = new AffineTransform();

			//projectile image is 20x10
			int projectileDrawX = (int)xLoc-20;
			int projectileDrawY = (int)yLoc-5;
			int	projectileRotateOffsetX = 20;
			int projectileRotateOffsetY = 5;

			theAT.rotate( -angle,	projectileDrawX + projectileRotateOffsetX,
					projectileDrawY + projectileRotateOffsetY);//-angle to adjust to y axis pointing down

			g2D.transform(theAT);
			g.drawImage(l.projectile, projectileDrawX, projectileDrawY, null);

			g2D.setTransform(backupAT);

			//	numDraws++;
		}

	}



	void move() {
		//		if(numDraws > 15){
		//			return;
		//		}


		if(wallDetected() == false) {
			xLoc+=speed*Math.cos(-angle);//used negative angle to convert from normal math axis to screen axis
			//System.out.println(angle);
			yLoc+=speed*Math.sin(-angle);
			//System.out.println(angle);	
		}
		//else{
		//rebound();
		//}
	}



	private boolean wallDetected() {
		//the wall detection will use the angle to find what direction. it can be any of the following: north, east, south, west, northeast, northwest, southeast, southwest
		//based on the direction, it will check a certain side of the wall. in the case of a duel direction, it will check two sides
		Direction dir = getDirection();
		for(int r = 0; r<walls.length; r++) {
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					if(dir != null) {
						if(dir == Direction.EAST || dir == Direction.NORTHEAST || dir == Direction.SOUTHEAST)
							if( (int)xLoc > ((c*50)-speed) && (int)xLoc < (c*50)+speed) //adds speed because parametric movement can skip pixels 
								if( (int)yLoc > (r*50) && (int)yLoc < (r*50)+50)
									return true;
						if(dir == Direction.NORTH || dir == Direction.NORTHWEST || dir == Direction.NORTHEAST)
							if( (int)yLoc > ((r*50)+50-speed) && (int)yLoc < (r*50)+50+speed) //adds speed because parametric movement can skip pixels 
								if( (int)xLoc > (c*50) && (int)xLoc < (c*50)+50)
									return true;
						if(dir == Direction.WEST || dir == Direction.NORTHWEST || dir == Direction.SOUTHWEST)
							if( (int)xLoc > ((c*50)+50-speed) && (int)xLoc < (c*50)+50+speed) //adds speed because parametric movement can skip pixels 
								if( (int)yLoc > (r*50) && (int)yLoc < (r*50)+50)
									return true;
						if(dir == Direction.SOUTH || dir == Direction.SOUTHWEST || dir == Direction.SOUTHEAST)
							if( (int)yLoc > ((r*50)-speed) && (int)yLoc < (r*50)+speed) //adds speed because parametric movement can skip pixels 
								if( (int)xLoc > (c*50) && (int)xLoc < (c*50)+50)
									return true;
					}
			}
			}
		}
		return false;
	}



	private Direction getDirection() {
		if(angle == 0)
			return Direction.EAST;
		if(angle > 0 && angle< Math.PI/2)
			return Direction.NORTHEAST;
		if(angle == Math.PI/2)
			return Direction.NORTH;
		if(angle >Math.PI/2 && angle< Math.PI)
			return Direction.NORTHWEST;
		if(angle == Math.PI)
			return Direction.WEST;
		if(angle> -1*(Math.PI) && angle< (Math.PI/2)* -1)
			return Direction.SOUTHWEST;	
		if(angle == -1* Math.PI/2)
			return Direction.SOUTH;
		if(angle> (3*Math.PI)/-2 && angle< 0)
			return Direction.SOUTHEAST;

		return null;
	}



	private void rebound() {

	}




}
