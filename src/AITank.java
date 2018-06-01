import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AITank extends Tank //AI Tank is a specific type of Tank
{

	int numMoveTries;//Number of times the tank has tried to move
	int tankSlowMultiplier;//ex. 1 is fastest, 3 is 1/3 speed
	int fireSlowMultiplier;

	Point player;//player point
	Point ai;//ai point
	Point player1;//player point
	Point ai1;//ai point
	//s	ArrayList<Wall> wallsInBetween;
	boolean intersect;
	boolean commit;

	TankType type1;


	public AITank(TankType inType, int inX, int inY, Arena inArena)
	{
		super(inArena); // Calls superclass contructor which takes in all walls in arena
		alive = true;
		type = inType;
		xLoc = inX*50; //Each cell is 50 pixels in wide
		yLoc = inY*50; //Each cell is 50 pixels in length

		ai = new Point(xLoc, yLoc);
		ai1 = new Point(xLoc, yLoc);

		//		wallsInBetween = new ArrayList<Wall>();
		intersect = false;
		
		numMoveTries = 0;

		
		switch(type){
		case BLUE:
			tankSlowMultiplier = 10000;
			break;
		case RED:
			tankSlowMultiplier = 4;
			break;
		case BLACK:
			tankSlowMultiplier = 4;
			break;
		case WHITE:
			tankSlowMultiplier = 4;
			break;
		case PINK:
			if(arena.level == 0){
				tankSlowMultiplier = 8;
			}
			else{
				tankSlowMultiplier = 10000;
			}
			break;
		case YELLOW:
			if(arena.level == 0){
				tankSlowMultiplier = 8;
			}
			else{
				tankSlowMultiplier = 10000;
			}
			break;
		case INVISIBLE:
			tankSlowMultiplier = 10000;
			break;
		}
		
		fireSlowMultiplier = 400;
		
		commit = false;
		type1 = inType;
		//		for(int r = 0; r<surroundingWalls.length; r++) {
		//			for(int c = 0; c<surroundingWalls[r].length; c++) {
		//				if(surroundingWalls[r][c] != null) {
		//				wallsInBetween.add(surroundingWalls[r][c]);
		//				}
		//			}
		//		}
		//gets all walls in between the player tank and the AI tank and puts them into a list
		//there are four statements because, putting the ai tank at the origin of a hypothetical graph, the player tank has 4 possible quadrant locations

	}


	//Need to figure out mechanism by which AI Tank Moves
	void move()
	{
		numMoveTries++;
		player1 = new Point(arena.playerTankLocX(),  arena.playerTankLocY());
		ai1 = new Point(xLoc, yLoc);
		Direction dirX;
		Direction dirY;
		
		
		

			if(ai1.getX() - player1.getX() > 0) {
				dirX = Direction.WEST;
				
			}
			else if(ai1.getX() - player1.getX() < 0) {
				dirX = Direction.EAST;
				
			}
			else {
				dirX = null;
			}

			if((ai1.getY() - player1.getY() > 0)  ) {
				dirY = Direction.NORTH;
			}
			else if((ai1.getY() - player1.getY() < 0)){
				dirY = Direction.SOUTH;
			}
			else {
				dirY = null;
			}

			
			
			if(canMoveX(dirX,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0 && dirX == Direction.WEST && xLoc != player1.getX()) {
				xLoc += -1;
			}
			else if(canMoveX(dirX,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0 && dirX == Direction.EAST && xLoc != player1.getX()) {
				xLoc += 1;
			}
			if(canMoveY(Direction.NORTH,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0 && dirY == Direction.NORTH && yLoc != player1.getY()){
				yLoc += -1;
			}
			else if(canMoveY(Direction.SOUTH,surroundingWalls) && numMoveTries%tankSlowMultiplier == 0 && dirY == Direction.SOUTH && yLoc != player1.getY()){
				yLoc += 1;
			}

		for(Projectile p : stockPile) {
			p.move();

		}

	}
	//Need to figure out mechanism by which AI Tank Aims
	void aim()
	{

	}
	//Need to figure out mechanism by which AI Tank Fires
	void fire()
	{
		for(int i = 0; i < stockPile.size(); i++){
			if (!stockPile.get(i).active){
				stockPile.remove(i); //Removes missile from stockpile
				i--;
			}
		}
		
		intersect = false;

		player = new Point(arena.playerTankLocX(),  -arena.playerTankLocY());
		ai = new Point(xLoc, -yLoc);
		for(int r = 0; r<surroundingWalls.length; r++) {
			for(int c = 0; c<surroundingWalls[r].length; c++) {
				if(surroundingWalls[r][c] != null){
					Wall temp = surroundingWalls[r][c];
					Point point1 = new Point(temp.getXLoc(), -temp.getYLoc());
					Point point2 = new Point(temp.getXLoc() + 50, -temp.getYLoc());
					Point point3 = new Point(temp.getXLoc(), (-temp.getYLoc()-50));
					Point point4 = new Point(temp.getXLoc() + 50, (-temp.getYLoc()-50));
					if(intersect(player, ai, point1, point2) == true && surroundingWalls[r][c].destructable == false) {
						intersect = true;
					}
					else if(intersect(player, ai, point3, point4) == true && surroundingWalls[r][c].destructable == false) {
						intersect = true;
					}
					else if(intersect(player, ai, point1, point3) == true && surroundingWalls[r][c].destructable == false) {
						intersect = true;
					}
					else if(intersect(player, ai, point2, point4) == true && surroundingWalls[r][c].destructable == false) {
						intersect = true;
					}
				}
			}
		}

		//create projectile with input: type, 

		if(intersect == false){


			if(numMoveTries%fireSlowMultiplier == 0 && alive) {

				System.out.println("You fired1");
				//if it has space, it will make a new projectile

				Projectile p = new Projectile(turretTopX, turretTopY, Math.atan2(-(targetY - turretCenterY), targetX - turretCenterX),type, arena);
				stockPile.add(p);

				arena.addExplosion(turretTopX, turretTopY, ExplosionType.SMALL);
			}			

			intersect = false;
		}

		intersect = false;

	}
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

	public String getType() {

		return "aiTank";
	}

	
}
