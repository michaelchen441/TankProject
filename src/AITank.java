import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AITank extends Tank //AI Tank is a specific type of Tank
{

	int numMoveTries;//Number of times the tank has tried to move
	int tankSlowMultiplier;//ex. 1 is fastest, 3 is 1/3 speed
	int fireSlowMultiplier;
	
	Point player;//player point
	Point ai;//ai point
	ArrayList<Wall> wallsInBetween;
	boolean intersect;
	int count;

	
	public AITank(TankType inType, int inX, int inY, Arena inArena)
	{
		super(inArena); // Calls superclass contructor which takes in all walls in arena
		alive = true;
		type = inType;
		xLoc = inX*50; //Each cell is 50 pixels in wide
		yLoc = inY*50; //Each cell is 50 pixels in length
		
		ai = new Point(xLoc, yLoc);
		
		wallsInBetween = new ArrayList<Wall>();
		intersect = false;
		player = new Point(arena.playerTankLocX(), arena.playerTankLocY());
		
		numMoveTries = 0;
		tankSlowMultiplier = 3;
		fireSlowMultiplier = 500;
		count = 0;
		
		for(int r = 0; r<surroundingWalls.length; r++) {
			for(int c = 0; c<surroundingWalls[r].length; c++) {
				if(surroundingWalls[r][c] != null) {
				wallsInBetween.add(surroundingWalls[r][c]);
				}
			}
		}
		//gets all walls in between the player tank and the AI tank and puts them into a list
		//there are four statements because, putting the ai tank at the origin of a hypothetical graph, the player tank has 4 possible quadrant locations
			
	}

	
	//Need to figure out mechanism by which AI Tank Moves
	void move()
	{
		numMoveTries++;
		
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
		for(Projectile projectile: stockPile){
			if (!projectile.active){
				stockPile.remove(projectile); //Removes missile from stockpile
			}
		}
		
		
		intersect = false;
		
		player = new Point(arena.playerTankLocX(),  -arena.playerTankLocY());
		ai = new Point(xLoc, -yLoc);
		for(int i = 0; i<wallsInBetween.size(); i++) {
			Wall temp = wallsInBetween.get(i);
			Point point1 = new Point(temp.getXLoc(), -temp.getYLoc());
			Point point2 = new Point(temp.getXLoc() + 50, -temp.getYLoc());
			Point point3 = new Point(temp.getXLoc(), (-temp.getYLoc()-50));
			Point point4 = new Point(temp.getXLoc() + 50, (-temp.getYLoc()-50));
			if(intersect(player, ai, point1, point2) == true) {
				intersect = true;
			}
			else if(intersect(player, ai, point3, point4) == true) {
				intersect = true;
			}
			else if(intersect(player, ai, point1, point3) == true) {
				intersect = true;
			}
			else if(intersect(player, ai, point2, point4) == true) {
				intersect = true;
			}
		}
		
		//create projectile with input: type, 
		
		if(intersect == false){
		
			
			if(numMoveTries%fireSlowMultiplier == 0 ) {

				System.out.println("You fired1");
				//if it has space, it will make a new projectile
				
				Projectile p = new Projectile(xLoc+25 , yLoc+25, Math.atan2(-(targetY - turretCenterY), targetX - turretCenterX),type, arena);
				stockPile.add(p);
				

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
