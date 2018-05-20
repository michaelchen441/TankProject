
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
// Player tank is a type of tank with more specific implementation
// Player tank characteristics and actions prompted by user input (Keypresses, mouse, etc)
public class PlayerTank extends Tank
{
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();//List of number of bullets available to fire
	double turretAngle;//Angle of turret in radians
	Direction direction;//Direction tank is facing; will use to determine where to move
	//List of specific movements in x and y directions that need to be added to the tanks location 
	//Depends on which key is pressed indicating which direction a tank needs to move in
	public int[] inputMoveArr;
	//All the walls the tank needs to account for in the arena
	Wall[][] wallArray;
	
	//Number of times the tank has tried to move
	int numMoveTries = 0;
	
	
	public PlayerTank(int inX, int inY, Wall[][] walls)
	{
		super(walls); //Superclass constructor to receive all the walls
		alive = true; //Living condition: alive or dead
		type = TankType.GREEN; //Tank color and appearance - prompts a certain image of tank to be read in draw method
		//multiples so they can be set up with the same grid as walls
		xLoc = inX * 50; 
		yLoc = inY * 50;
		direction = Direction.EAST;//Initial direction set to EAST
		wallArray = walls;
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
		if(canMove(dir,wallArray) && numMoveTries%2 == 0) {
			xLoc += inputMoveArr[0];
			yLoc -= inputMoveArr[1]; //Minus equals is used because the way a panel is numbered is top down, not bottom up like a standard set of coordinte axes
		}
	}


// Checks which direction to move in based upon what the inputMoveArr contains
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


	private ArrayList<Direction> touchingWallDirections()
	{
		//if(xLoc )
		return null;
	}

//Aim method not implemented yet, but based on mouse movement
	public void aim(){

	}

//Firing method
	public void fire()
	{
		//Checks to see if the tank has any ammo left in the stockpile to fire
		if(stockPile.size() > 0) {
			//if it has space, it will make a new projectile
			new Projectile(xLoc,yLoc,TankType.GREEN, turretAngle);
			stockPile.remove(stockPile.size()-1); //Removes missile from stockpile
			//Use this to control reload time
		}

	}

	public void draw(Graphics g, ImageLibrary l){
		move(); //Implements move everytime timer ticks because draw is called everytime it ticks
		g.drawImage(l.greenTank, xLoc, yLoc, null); //Tank image is drawn based on its x and y location
		g.drawImage(l.greenTurret, xLoc+15, yLoc-15, null);
		
		
		
		//TODO draw turret		

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
	
	
}


