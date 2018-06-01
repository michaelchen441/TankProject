import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Component;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Point;




// Player tank is a type of tank with more specific implementation
// Player tank characteristics and actions prompted by user input (Keypresses, mouse, etc)
public class PlayerTank extends Tank
{

	public int[] inputMoveArr;
	//All the walls the tank needs to account for in the arena



	public PlayerTank(int inX, int inY, Arena inArena)
	{

		super(inArena); //Superclass constructor to receive all the walls
		alive = true; //Living condition: alive or dead
		type = TankType.GREEN; //Tank color and appearance - prompts a certain image of tank to be read in draw method
		//multiples so they can be set up with the same grid as walls
		xLoc = inX * 50; 
		yLoc = inY * 50;
		turretCenterX = xLoc+25;
		turretCenterY = yLoc+25;
		targetX = 0;
		targetY = 0;
		direction = Direction.EAST;//Initial direction set to EAST
		inputMoveArr = new int[2];//Initializes array containing necessary information about moves in x and y locations


	}

	// Move function for tank; dependent on direction and inputMoveArr which depend on keys pressed
	public void move(){
		//Everytime a tank moves, the number of times it has tried increments 
		numMoveTries++;
		//	if(touchingWallDirections().indexOf(Direction.NORTH)>-1)
		Direction dir = whichDir(inputMoveArr);	//Checks to see which direction to move in
		/*
		 * Checks to see if it is possible to move in a particular direction given all the walls in the arena
		 * Only moves every other or even tick to slow the movement of the tank
		 * Tank moves everytime it ticks, but even if the move is called, it may not move anywhere because the inputmoveArr may be [0,0]
		 */

		if(canMoveX(dir,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0) {
			xLoc += inputMoveArr[0];
		}
		if(canMoveY(dir,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0) {
			yLoc -= inputMoveArr[1];
			//Minus equals is used because the way a panel is numbered is top down, not bottom up like a standard set of coordinte axes
		}


		for(Projectile p : stockPile) {
			p.move();
		}
		//remove any projectiles if they are nonactive to make room for new projectiles
		for(int i = 0; i < stockPile.size(); i++){
			if (!stockPile.get(i).active){
				stockPile.remove(stockPile.get(i)); //Removes missile from stockpile
				i--;
				//Use this to control reload time

			}
		}
	}

	//	public void setTurretAngle(double angle) {
	//		turretAngle = angle;
	//	}


	// Checks which direction to move in based upon what the wArr contains
	// The input move array is filled with certain x and y loc changes based on user input
	// Based off of these movements, one can detect which direction the movement will occur in
	private Direction whichDir(int[] temp) {

		if(temp[0] == 1 && temp[1] == 0) //Moving 1 to the right is moving east
			return Direction.EAST;
		if(temp[0] == 0 && temp[1] == 1) //Moving 1 up is moving north
			return Direction.NORTH;
		if(temp[0] == -1 && temp[1] == 0) //Moving 1 to the left is moving west
			return Direction.WEST;
		if(temp[0] == 0 && temp[1] == -1) //Moving 1 down is moving south
			return Direction.SOUTH;
		if(temp[0] == 1 && temp[1] == 1) //Moving 1 across and 1 up is moving northeast
			return Direction.NORTHEAST;
		if(temp[0] == -1 && temp[1] == -1) //Moving 1 left and 1 down is moving southwest
			return Direction.SOUTHWEST;
		if(temp[0] == 1 && temp[1] == -1) //Moving 1 right and 1 down is moving southeast
			return Direction.SOUTHEAST;
		if(temp[0] == -1 && temp[1] == 1) //Moving 1 left and 1 up is moving northwest
			return Direction.NORTHWEST;
		return null;
	}


	//Aim method not implemented yet, but based on mouse movement
	public void aim(){

	}

	//Firing method
	public void fire()
	{

		if(canFire) {
			//Checks to see if the tank has any ammo left in the stockpile to fire
			if(stockPile.size() < 5) {


				//			System.out.println("You fired");
				//if it has space, it will make a new projectile

				Projectile p = new Projectile(turretTopX,turretTopY, Math.atan2(-(targetY - turretCenterY), targetX - turretCenterX),type, arena);
				stockPile.add(p);

				arena.addExplosion(turretTopX, turretTopY, ExplosionType.SMALL);


			}
		}
	}


	//Establishes the movements that need to be made into the inputMoveArr instance variable for the Playertank object
	public void setInputMoveArr(int[] inInputMoveArr)
	{
		inputMoveArr[0] = inInputMoveArr[0];
		inputMoveArr[1] = inInputMoveArr[1];

	}

	//Returns x and y locations (getters)
	public int getY() {return yLoc;}
	public int getX() {return xLoc;}

	//sets x and y locations (setters)
	public void setY(int y) {yLoc = y*50;}
	public void setX(int x) {xLoc = x*50;}

	public int orientation(Point p, Point q, Point r) {
		double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
				- (q.getX() - p.getX()) * (r.getY() - q.getY());

		if (val == 0.0)
			return 0; // colinear
		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	public boolean intersect(Point p1, Point q1, Point p2, Point q2) {

		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		if (o1 != o2 && o3 != o4)
			return true;

		return false;
	}



}


