import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlayerTank extends Tank
{
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();
	double turretAngle;
	Direction direction;
	public int[] inputMoveArr;

	public PlayerTank(int inX, int inY)
	{
		super();
		type = TankType.GREEN;
		xLoc = inX * 50; //multiples so they can be set up with the same grid as walls
		yLoc = inY * 50;
		direction = Direction.EAST;

		inputMoveArr = new int[2];

	
	}


	public void move(){
	//	if(touchingWallDirections().indexOf(Direction.NORTH)>-1)
			xLoc += inputMoveArr[0];
			yLoc -= inputMoveArr[1];
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

		g.drawImage(l.playerTank, xLoc, yLoc, null);
		//TODO draw turret

	}


	private boolean detect()
	{
		// TODO Auto-generated method stub
		return false;
	}


	public void setInputMoveArr(int[] inInputMoveArr)
	{
		inputMoveArr[0] = inInputMoveArr[0];
		inputMoveArr[1] = inInputMoveArr[1];

	}
}


