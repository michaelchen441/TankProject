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
		if(dir == Direction.NORTH) {
			for(int r = 0; r<walls.length; r++) {
				for(int c = 0; c<walls[r].length; c++) {
					if(walls[r][c] != null) {
						
						
						if(yLoc == (r*50)+50) {
							
							if(xLoc > (c*50)-50 && xLoc < (c*50)+50){
								return false;
							}
							
							//c is x of the wall
							
//							for(int i = 0;i<50; i++) {
//								for(int j = 0; j<50; j++) {
//									if(xLoc+i == (c*50)+b)
//										return false;
//								}
//							}
						}
					}
				}

			}
		}
		//		if(dir == Direction.EAST) {
		//			for(int i = 0; i<50; i++) {
		//				if()
		//					return false;
		//			}
		//			
		//		}
		//		if(dir == Direction.SOUTH) {
		//			return false;
		//		}
		//		if(dir == Direction.WEST) {
		//			return false;
		//		}
		//		if(dir == Direction.NORTHEAST) {
		//			return false;
		//		}
		//		if(dir == Direction.SOUTHEAST) {
		//			return false;
		//		}
		//		if(dir == Direction.SOUTHWEST) {
		//			return false;
		//		}
		//		if(dir == Direction.NORTHWEST) {
		//			return false;
		//		}
		return true;

	}
	abstract void move();
	abstract void aim();
	abstract void fire();
	abstract void draw(Graphics g, ImageLibrary l);


}
