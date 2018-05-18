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
		if(dir == null)
			return true;
		if(dir == Direction.NORTH) 
			return checkNorth(walls);
		if(dir == Direction.EAST) 
			return checkEast(walls);
		if(dir == Direction.SOUTH) 
			return checkSouth(walls);
		if(dir == Direction.WEST) 
			return checkWest(walls);
		if(dir == Direction.NORTHWEST) 
			return checkNorth(walls) && checkWest(walls);
		if(dir == Direction.NORTHEAST) 
			return checkNorth(walls) && checkEast(walls);
		if(dir == Direction.SOUTHWEST) 
			return checkSouth(walls) && checkWest(walls);
		if(dir == Direction.SOUTHEAST) 
			return checkSouth(walls) && checkEast(walls);
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
