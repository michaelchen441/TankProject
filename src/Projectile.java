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
	int numMoveTries;
	int projectileSlowMultiplier;

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
		numMoveTries = 0;
		projectileSlowMultiplier = 2;
		//	numDraws = 0;

	}



	void draw(Graphics g, ImageLibrary l){

		//draws black box projectile - use to test where image should be 
		//g.setColor(Color.BLACK);
		//g.fillRect((int)(xLoc), (int)(yLoc), 15, 15);
		if(numWallHits > 1) { //once its hit a second wall, it dies
			active = false;
		}

		if(active){	
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

		numMoveTries++;
		detectWalls();
		if(numMoveTries%projectileSlowMultiplier == 0) 
			xLoc+=speed*Math.cos(-angle);//used negative angle to convert from normal math axis to screen axis
			//System.out.println(angle);
		if(numMoveTries%projectileSlowMultiplier == 0) {
			yLoc+=speed*Math.sin(-angle);
			//System.out.println(angle);
		
		}
		//else{
		//rebound();
		//}
	}



	private void detectWalls() {
		//the wall detection will use the angle to find what direction. it can be any of the following: north, east, south, west, northeast, northwest, southeast, southwest
		//based on the direction, it will check a certain side of the wall. in the case of a duel direction, it will check two sides
		Direction dir = getDirection();
		System.out.println(dir);
		for(int r = 0; r<walls.length; r++) {
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					if(dir != null) {
						if(dir == Direction.EAST || dir == Direction.NORTHEAST || dir == Direction.SOUTHEAST)
							if( (int)xLoc > ((c*50)-speed) && (int)xLoc < (c*50)+speed) //adds speed because parametric movement can skip pixels 
								if( (int)yLoc > (r*50) && (int)yLoc < (r*50)+50) {
									numWallHits++;
									angle = Math.PI-angle;
									if(angle < 0) {
										angle += 2*Math.PI;
									}
									return;
								}
						if(dir == Direction.NORTH || dir == Direction.NORTHWEST || dir == Direction.NORTHEAST)
							if( (int)yLoc > ((r*50)+50-speed) && (int)yLoc < (r*50)+50+speed) //adds speed because parametric movement can skip pixels 
								if( (int)xLoc > (c*50) && (int)xLoc < (c*50)+50) {
									numWallHits++;
									angle = angle * -1;
									if(angle < 0) {
										angle += 2*Math.PI;
									}
									return;
								}
									
						if(dir == Direction.WEST || dir == Direction.NORTHWEST || dir == Direction.SOUTHWEST)
							if( (int)xLoc > ((c*50)+50-speed) && (int)xLoc < (c*50)+50+speed) //adds speed because parametric movement can skip pixels 
								if( (int)yLoc > (r*50) && (int)yLoc < (r*50)+50) {
									numWallHits++;
									angle = Math.PI-angle;
									if(angle < 0) {
										angle += 2*Math.PI;
									}
									return;
								}
									
						if(dir == Direction.SOUTH || dir == Direction.SOUTHWEST || dir == Direction.SOUTHEAST)
							if( (int)yLoc > ((r*50)-speed) && (int)yLoc < (r*50)+speed) //adds speed because parametric movement can skip pixels 
								if( (int)xLoc > (c*50) && (int)xLoc < (c*50)+50) {
									numWallHits++;
									angle = angle *-1;
									if(angle < 0) {
										angle += 2*Math.PI;
									}
									return;
								}
									
					}
			}
			}
		}
	}



	private Direction getDirection() {
		double degrees = (angle * 180)/Math.PI;
		if(degrees<0) {
			degrees+=360;
		}
		if(degrees == 0)
			return Direction.EAST;
		if(degrees > 0 && degrees< 90)
			return Direction.NORTHEAST;
		if(degrees == 90)
			return Direction.NORTH;
		if(degrees > 90 && degrees< 180)
			return Direction.NORTHWEST;
		if(degrees == 180)
			return Direction.WEST;
		if(degrees> 180 && degrees< 270)
			return Direction.SOUTHWEST;	
		if(degrees == 180)
			return Direction.SOUTH;
		if(degrees > 270 && degrees< 360)
			return Direction.SOUTHEAST;
		
		return null;
	}



	private void rebound() {

	}




}
