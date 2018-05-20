import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Tank
{
	TankType type;
	int height;
	int width;
	int speed;
	int xLoc;
	int yLoc;
	boolean alive;
	ArrayList<Projectile> stockPile = new ArrayList<Projectile>();
	Wall[][] surroundingWalls;

	public Tank(Wall[][] walls) {
		surroundingWalls = walls;
		height = 28;
		width = 56;
	}

	public boolean canMove(Direction dir, Wall[][] walls) {
		System.out.println("begin wall detection");
		
		if(dir == null){
			System.out.println("end wall detection: no direction");
			return true;
		}
		if(dir == Direction.NORTH) {
			System.out.println("end wall detection: north");
			return checkNorth(walls);
		}
		if(dir == Direction.EAST) {
			System.out.println("end wall detection: east");
			return checkEast(walls);
		}
		if(dir == Direction.SOUTH) {
			System.out.println("end wall detection: south");
			return checkSouth(walls);
		}
		if(dir == Direction.WEST) {
			System.out.println("end wall detection: west");
			return checkWest(walls);
		}
		if(dir == Direction.NORTHWEST){
			System.out.println("end wall detection: northwest");
			return checkNorth(walls) && checkWest(walls);
		}
		if(dir == Direction.NORTHEAST) {
			System.out.println("end wall detection: northeast");
			return checkNorth(walls) && checkEast(walls);
		}
		if(dir == Direction.SOUTHWEST) {
			System.out.println("end wall detection: southwest");
			return checkSouth(walls) && checkWest(walls);
		}
		if(dir == Direction.SOUTHEAST) {
			System.out.println("end wall detection: southeast");
			return checkSouth(walls) && checkEast(walls);
		}

		
		System.out.println("end wall detection");
		return true;
	}
	private boolean checkWest(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) 
			for(int c = 0; c<walls[r].length; c++) 
				if(walls[r][c] != null) 
					if(xLoc == (c*50)+50) 
						if(yLoc >= r*50 && yLoc <= (r*50)+50 || yLoc+50 >= r*50 && yLoc+50 <= (r*50)+50)
							return false;
		return true;
	}

	private boolean checkSouth(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) 
			for(int c = 0; c<walls[r].length; c++) 
				if(walls[r][c] != null) 
					if(yLoc+50 == r*50) 
						if(xLoc >= c*50 && xLoc <= (c*50)+50 || xLoc+50 >= c*50 && xLoc+50 <= (c*50)+50)
							return false;

		return true;
	}

	private boolean checkEast(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) 
			for(int c = 0; c<walls[r].length; c++) 
				if(walls[r][c] != null) 
					if(xLoc+50 == c*50) 
						if(yLoc >= r*50 && yLoc <= (r*50)+50 || yLoc+50 >= r*50 && yLoc+50 <= (r*50)+50)
							return false;
		return true;
	}

	private boolean checkNorth(Wall[][] walls) {
		for(int r = 0; r<walls.length; r++) 
			for(int c = 0; c<walls[r].length; c++) 
				if(walls[r][c] != null) 
					if(yLoc == (r*50)+50) 
						if(xLoc >= c*50 && xLoc <= (c*50)+50 || xLoc+50 >= c*50 && xLoc+50 <= (c*50)+50)
							return false;
		return true;

	}

	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g, ImageLibrary l);


}
