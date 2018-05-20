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

public class PlayerTank extends Tank
{
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();
	double turretAngle;
	Direction direction;
	public int[] inputMoveArr;
	Wall[][] wallArray;
	
	
	int numMoveTries = 0;
	
	
	public PlayerTank(int inX, int inY, Wall[][] walls)
	{
		super(walls);
		alive = true;
		type = TankType.GREEN;
		xLoc = inX * 50; //multiples so they can be set up with the same grid as walls
		yLoc = inY * 50;
		direction = Direction.EAST;
		wallArray = walls;
		inputMoveArr = new int[2];


	}


	public void move(){
		numMoveTries++;
		//	if(touchingWallDirections().indexOf(Direction.NORTH)>-1)
		Direction dir = whichDir(inputMoveArr);	
		if(canMove(dir,wallArray) && numMoveTries%2 == 0) {
			xLoc += inputMoveArr[0];
			yLoc -= inputMoveArr[1];
		}
	}



	private Direction whichDir(int[] temp) {
		
		if(temp[0] == 1 && temp[1] == 0)
			return Direction.EAST;
		if(temp[0] == 0 && temp[1] == 1)
			return Direction.NORTH;
		if(temp[0] == -1 && temp[1] == 0)
			return Direction.WEST;
		if(temp[0] == 0 && temp[1] == -1)
			return Direction.SOUTH;
		if(temp[0] == 1 && temp[1] == 1)
			return Direction.NORTHEAST;
		if(temp[0] == -1 && temp[1] == -1)
			return Direction.SOUTHWEST;
		if(temp[0] == 1 && temp[1] == -1)
			return Direction.SOUTHEAST;
		if(temp[0] == -1 && temp[1] == 1)
			return Direction.NORTHWEST;
		return null;
	}


	private ArrayList<Direction> touchingWallDirections()
	{
		//if(xLoc )
		return null;
	}


	public void aim(){

	}

	public void fire()
	{
		if(stockPile.size() > 0) {
			new Projectile(xLoc,yLoc,TankType.GREEN, turretAngle);
			stockPile.remove(stockPile.size());
		}

	}

	public void draw(Graphics g, ImageLibrary l){
		move();
		g.drawImage(l.greenTank, xLoc, yLoc, null);
		g.drawImage(l.greenTurret, xLoc+15, yLoc-15, null);
		
		
		
		//TODO draw turret		

	}

	private boolean detect(Direction dir, Wall[][] myWalls){

		return false;
	}


	public void setInputMoveArr(int[] inInputMoveArr)
	{
		inputMoveArr[0] = inInputMoveArr[0];
		inputMoveArr[1] = inInputMoveArr[1];

	}


	public int getY() {return yLoc;}


	public int getX() {return xLoc;}
	
	
}


