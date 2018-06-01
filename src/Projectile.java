import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
	Arena myArena;

	//use to stop projectile early for visualizing
	//int numDraws; 

	public Projectile(int x, int y, double a, TankType inType, Arena inArena)

	{
		xLoc = x;
		yLoc = y;
		angle = a;
		walls = inArena.walls;
		type = inType;
		angle = a;
		myArena = inArena;
		switch(type){
		case GREEN: 
			speed = 3;
			break;
		case BLUE:
			speed = 3;
			break;
		case RED:
			speed = 3;
			break;
		case BLACK:
			speed = 5;
			break;
		case WHITE:
			speed = 5;
			break;
		case PINK:
			speed = 5;
			break;
		case YELLOW:
			speed = 5;
			break;
		case INVISIBLE:
			speed = 3;
			break;
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
		if(active) {
			numMoveTries++;
			if(!detectTanks()) {
				detectWalls();
				if(numMoveTries%projectileSlowMultiplier == 0) 
					xLoc+=speed*Math.cos(-angle);//used negative angle to convert from normal math axis to screen axis
				//System.out.println(angle);
				if(numMoveTries%projectileSlowMultiplier == 0) {
					yLoc+=speed*Math.sin(-angle);
					//ddtem.out.println(angle);

				}
			}
			checkProjectile();

		}
		switch(type){
		case GREEN: 
			if(numWallHits > 1) { //once its hit a second wall, it dies //change back to 1
				active = false;
			}
			break;
		case BLUE:
			if(numWallHits > 0) { //once its hit a wall, it dies //change back to 1
				active = false;
			}				
			break;
		case RED:
			if(numWallHits > 1) { //once its hit a second wall, it dies //change back to 1
				active = false;
			}
			break;
		case BLACK:
			if(numWallHits > 0) { //once its hit a wall, it dies //change back to 1
				active = false;
			}
			break;
		case WHITE:
			if(numWallHits > 2) { //once its hit a third wall, it dies //change back to 1
				active = false;
			}
			break;
		case PINK:
			if(numWallHits > 2) { //once its hit a second wall, it dies //change back to 1
				active = false;
			}
			break;
		case YELLOW:
			if(numWallHits > 2) { //once its hit a second wall, it dies //change back to 1
				active = false;
			}
			break;
		case INVISIBLE:
			if(numWallHits > 1) { //once its hit a second wall, it dies //change back to 1
				active = false;
			}
			break;
		}

		if(!active){
			myArena.addExplosion((int)xLoc, (int)yLoc, ExplosionType.MEDIUM);	//call arena addExplosion
		}


	}



	private boolean detectTanks() {
		ArrayList<Tank> tankList = myArena.getTanks();
		Direction dir = getDirection();
		for(Tank t : tankList) {
			if (t.alive){
				//works great
				if(dir == Direction.EAST || dir == Direction.NORTHEAST || dir == Direction.SOUTHEAST ) {
					if(xLoc <= t.getX()+speed && xLoc >= t.getX()-speed) {
						if(yLoc >= t.getY() && yLoc <= t.getY()+50) {
							active = false;
							myArena.addExplosion((int)xLoc, (int)yLoc, ExplosionType.MEDIUM);	//call arena addExplosion
							t.alive = false;
							myArena.addExplosion(t.getX(), t.getY(), ExplosionType.LARGE);	//call arena addExplosion
							return true;

						}
					}

				}
				if(dir == Direction.NORTH || dir == Direction.NORTHEAST || dir == Direction.NORTHWEST ) {
					if(yLoc >= t.getY()-speed+50 && yLoc <= t.getY()+speed+50) {
						if(xLoc >= t.getX()-speed && xLoc <= t.getX()+50+speed) {
							active = false;
							myArena.addExplosion((int)xLoc, (int)yLoc, ExplosionType.MEDIUM);	//call arena addExplosion
							t.alive = false;
							myArena.addExplosion(t.getX(), t.getY(), ExplosionType.LARGE);	//call arena addExplosion
							return true;
						}
					}
				}
				if(dir == Direction.SOUTH || dir == Direction.SOUTHWEST || dir == Direction.SOUTHEAST ) {
					if(yLoc >= t.getY()-speed && yLoc <= t.getY()+speed) {
						if(xLoc >= t.xLoc-speed && xLoc <= t.xLoc+50+speed) {
							active = false;
							myArena.addExplosion((int)xLoc, (int)yLoc, ExplosionType.MEDIUM);	//call arena addExplosion
							t.alive = false;
							myArena.addExplosion(t.getX(), t.getY(), ExplosionType.LARGE);	//call arena addExplosion
							return true;
						}
					}
				}
				//works great
				if(dir == Direction.WEST || dir == Direction.NORTHWEST || dir == Direction.SOUTHWEST ) {
					if(xLoc <= t.getX()+speed+50 && xLoc >= t.getX()-speed+50) {
						if(yLoc >= t.yLoc && yLoc <= t.yLoc+50) {
							active = false;
							myArena.addExplosion((int)xLoc, (int)yLoc, ExplosionType.MEDIUM);	//call arena addExplosion
							t.alive = false;
							myArena.addExplosion(t.getX(), t.getY(), ExplosionType.LARGE);	//call arena addExplosion
							return true;
						}
					}
				}
			}
		}

		return false;

	}



	private void detectWalls() {
		//the wall detection will use the angle to find what direction. it can be any of the following: north, east, south, west, northeast, northwest, southeast, southwest
		//based on the direction, it will check a certain side of the wall. in the case of a duel direction, it will check two sides
		Direction dir = getDirection();

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
									if(walls[r][c].destructable)
									{
										walls[r][c] = null;
										active = false;
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
									if(walls[r][c].destructable)
									{
										walls[r][c] = null;
										active = false;
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
									if(walls[r][c].destructable)
									{
										walls[r][c] = null;
										active = false;
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
									if(walls[r][c].destructable)
									{
										walls[r][c] = null;
										active = false;
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
		if(degrees == 270)
			return Direction.SOUTH;
		if(degrees > 270 && degrees< 360)
			return Direction.SOUTHEAST;

		return null;
	}



	private void rebound() {

	}

	private void checkProjectile() {
		for(Tank t: myArena.tankList) {
			for(Projectile p : t.stockPile) {
				if(p.equals(this)) {
					
				}
				else {
					if(xLoc - 5 < p.xLoc && xLoc + 5 > p.xLoc) {
						if(yLoc - 5 < p.yLoc && yLoc + 5 > p.yLoc) {
							active = false;
							p.active = false;
						}

					}
							
				}
			}
		}
	}


}
