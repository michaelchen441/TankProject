import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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

	double turretAngle;//Angle of turret in radians
	int turretCenterX;
	int turretCenterY;
	int targetX;
	int targetY;
	
	public Tank(Wall[][] walls) {
		surroundingWalls = walls; 
		height = 28; //Fixed height for all tanks
		width = 56; //Fixed width for all tanks
		
		
	}
	
	
	public void setTurretAngleByTarget(int inTargetX, int inTargetY)
	{
		targetX = inTargetX;
		targetY = inTargetY;
		turretCenterX = xLoc + 25;	// should be width / 2
		turretCenterY = yLoc + 25;	// should be height / 2
		turretAngle = Math.atan2(-(inTargetY - turretCenterY), inTargetX - turretCenterX);


	}

	public void drawTurret(Graphics g, ImageLibrary l) //function is called by a tanks draw function
	{
		{	// draw turret
			Graphics2D	g2D = (Graphics2D)g;
			AffineTransform	backupAT = g2D.getTransform();
			AffineTransform	theAT = new AffineTransform();

			int xTurretImageLoc = xLoc + 15;
			int yTurretImageLoc = yLoc - 15;
			int	xTurretRotateOffset = 10;
			int yTurretRotateOffset = 40;

			theAT.rotate((Math.PI * 0.5) - turretAngle,	xTurretImageLoc + xTurretRotateOffset,
					yTurretImageLoc + yTurretRotateOffset);

			g2D.transform(theAT);
			g.drawImage(l.greenTurret, xTurretImageLoc, yTurretImageLoc, null);

			g2D.setTransform(backupAT);
		}
		
	}
	
	
	public boolean canMove(Direction dir, Wall[][] walls) {
		//System.out.println("begin wall detection");
		//If no direction is indicated, movement cannot be checked in any specific direction
		if(dir == null){
			//System.out.println("end wall detection: no direction");
			return true;
		}
		//If tank is facing North, it calls checkNorth method which checks if movement is possible North
		if(dir == Direction.NORTH) {
			//System.out.println("end wall detection: north");
			return checkNorth(walls);
		}
		//If tank is facing East, it calls checkEast method which checks if movement is possible East
		if(dir == Direction.EAST) {
			//System.out.println("end wall detection: east");
			return checkEast(walls);
		}
		//If tank is facing South, it calls checkSouth method which checks if movement is possible South
		if(dir == Direction.SOUTH) {
			//System.out.println("end wall detection: south");
			return checkSouth(walls);
		}
		//If tank is facing West, it calls checkWest method which checks if movement is possible West
		if(dir == Direction.WEST) {
			//System.out.println("end wall detection: west");
			return checkWest(walls);
		}
		//If tank is facing NorthWest, it calls checkNorth and checkWest methods which check if movement is possible NorthWest
		if(dir == Direction.NORTHWEST){
			//System.out.println("end wall detection: northwest");
			return checkNorth(walls) && checkWest(walls);
		}
		//If tank is facing NorthEast, it calls checkNorth and checkEast methods which check if movement is possible NorthEast
		if(dir == Direction.NORTHEAST) {
			//System.out.println("end wall detection: northeast");
			return checkNorth(walls) && checkEast(walls);
		}
		//If tank is facing SouthWest, it calls checkSouth and checkWest methods which check if movement is possible SouthWest
		if(dir == Direction.SOUTHWEST) {
			//System.out.println("end wall detection: southwest");
			return checkSouth(walls) && checkWest(walls);
		}
		//If tank is facing SouthEast, it calls checkSouth and checkEast methods which check if movement is possible SouthEast
		if(dir == Direction.SOUTHEAST) {
			//System.out.println("end wall detection: southeast");
			return checkSouth(walls) && checkEast(walls);
		}


		//System.out.println("end wall detection");
		return true;
	}
	/*All check direction methods
	 * Take in all walls in arena
	 * Checks every wall in the 2d array by detecting if the cell in the 2D array is not null
	 * Multiplies r and c by 50 because each cell is 50 pixels wide; 
	 */
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
	//Movement, aiming, firing, and draw are coded and implemented differently between Player and AI Tanks
	//As a result, the abstract class has these as abstract methods and no implementation provided
	/*Likely idea is that user sends inputs into the player tank
	 *In contrast AI Tank prompts its own location, aiming, and firing
	 *AI Tank will probably utilize the playerTank's location and wall locations to prompt its own movement
	 */
	abstract void move();
	abstract void aim();
	abstract void fire() throws Exception;
	abstract void draw(Graphics g, ImageLibrary l);


}
