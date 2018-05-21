import java.awt.Graphics;
import java.util.ArrayList;
/* Tank is an abstract class with variations between the 
 implementation of certain methods in 

 */
public abstract class Tank
{
	TankType type; // AI or PlayerTank
	int height; //Dimension
	int width; //Dimension
	int speed; //Tank Speed
	int xLoc; //X Location
	int yLoc; //Y Location
	boolean alive; // Status alive or dead
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>(); //Number of bullets available to fire
	Wall[][] surroundingWalls; //Used to keep track of surrounding walls

	public Tank(Wall[][] walls) {
		surroundingWalls = walls; 
		height = 28; //Fixed height for all tanks
		width = 56; //Fixed width for all tanks
	}

	public boolean canMoveX(Direction dir, Wall[][] walls) {
		if(dir == null)
			return true;
		if(dir == Direction.WEST)
			return checkWest(walls);
		if(dir == Direction.EAST)
			return checkEast(walls);
		if(dir == Direction.SOUTHEAST)
			return checkEast(walls);
		if(dir == Direction.SOUTHWEST)
			return checkWest(walls);
		if(dir == Direction.NORTHWEST)
			return checkWest(walls);
		if(dir == Direction.NORTHEAST)
			return checkEast(walls);
		return true;
		
	}
	
	public boolean canMoveY(Direction dir, Wall[][] walls) {
		if(dir == null)
			return true;
		if(dir == Direction.NORTH)
			return checkNorth(walls);
		if(dir == Direction.SOUTH)
			return checkSouth(walls);
		if(dir == Direction.SOUTHEAST)
			return checkSouth(walls);
		if(dir == Direction.SOUTHWEST)
			return checkSouth(walls);
		if(dir == Direction.NORTHWEST)
			return checkNorth(walls);
		if(dir == Direction.NORTHEAST)
			return checkNorth(walls);
		return true;
	}
	
	private boolean checkWest(Wall[][] walls) { 
		for(int r = 0; r<walls.length; r++) { 
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					//Checks if the certain wall is west of the tank by adding 50 in the x direction to a walls location
					if(xLoc == (c*50)+50) {
						//Checks both edges of tank to see if they are within the borders of the west wall, including the endpoints of the wall				
						if(yLoc > (r*50)-50 && yLoc < (r*50)+50){	
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	private boolean checkEast(Wall[][] walls) {

		for(int r = 0; r<walls.length; r++) { 
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					//Checks if the certain wall is east of the tank by adding 50 in the x direction to tank's location			
					if(xLoc+50 == c*50) {
						//Checks both edges of tank to see if they are within the borders of the East wall, including the endpoints of the wall					
						if(yLoc > (r*50)-50 && yLoc < (r*50)+50){	
							return false;
						}
					}
				}
			}
		}				
		return true;
	}
	private boolean checkNorth(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) { 
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					//Checks if the certain wall is North of the tank by adding 50 in the x direction to tank's location	
					if(yLoc == (r*50)+50) {
						//Checks both edges of tank to see if they are within the borders of the North wall, including the endpoints of the wall	
						if(xLoc > (c*50)-50 && xLoc < (c*50)+50){
							return false;
						}
					}
				}
			}
		}
		return true;
 
	}
	private boolean checkSouth(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) {
			for(int c = 0; c<walls[r].length; c++) {
				if(walls[r][c] != null) {
					//Checks if the certain wall is south of the tank by adding 50 in the y direction to tank's location			
					if(yLoc+50 == r*50) {
						//Checks both edges of tank to see if they are within the borders of the south wall, including the endpoints of the wall					
						if(xLoc > (c*50)-50 && xLoc < (c*50)+50){	
							return false;
						}
					}
				}
			}
		}
		return true;

	}

	//Movement, aiming, firing, and draw are coded and implemented differently between Player and AI Tanks
	//As a result, the abstract class has these as abstract methods and no implementation provided
	/*Likely idea is that user sends inputs into the player tank
	 *In contrast AI Tank prompts its own location, aiming, and firing
	 *AI Tank will probably utilize the playerTank's location and wall locations to prompt its own movement
	 */
	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g, ImageLibrary l);


}
