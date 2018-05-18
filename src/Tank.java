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
			for(int r = 0; r<walls.length; r++) 
				for(int c = 0; c<walls[r].length; c++) 
					if(walls[r][c] != null) 
						if(yLoc == (r*50)+50) 
							for(int i = 0;i<50; i++) 
								for(int b = 0;b<50; b++) 
									if(xLoc+i == (c*50)+b)
										return false;
								
				if(dir == Direction.EAST) {
					for(int r = 0; r<walls.length; r++) 
						for(int c = 0; c<walls[r].length; c++) 
							if(walls[r][c] != null) 
								if(xLoc+50 == c*50) 
									for(int i = 0;i<50; i++) 
										for(int b = 0;b<50; b++) 
											if(yLoc+i == (r*50)+b)
												return false;
					
				}
				if(dir == Direction.SOUTH) {
					for(int r = 0; r<walls.length; r++) 
						for(int c = 0; c<walls[r].length; c++) 
							if(walls[r][c] != null) 
								if(yLoc+50 == r*50) 
									for(int i = 0;i<50; i++) 
										for(int b = 0;b<50; b++) 
											if(xLoc+i == (c*50)+b)
												return false;
				}
				if(dir == Direction.WEST) {
					for(int r = 0; r<walls.length; r++) 
						for(int c = 0; c<walls[r].length; c++) 
							if(walls[r][c] != null) 
								if(xLoc == (c*50)+50) 
									for(int i = 0;i<50; i++) 
										for(int b = 0;b<50; b++) 
											if(yLoc+i == (r*50)+b)
												return false;
				}
				
		return true;

	}
	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g, ImageLibrary l);


}
