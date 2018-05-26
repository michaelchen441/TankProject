import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AITank extends Tank //AI Tank is a specific type of Tank
{

	int numMoveTries;//Number of times the tank has tried to move
	int tankSlowMultiplier;//ex. 1 is fastest, 3 is 1/3 speed
	int fireSlowMultiplier;
	
	Point2D player;//player point
	Point2D ai;//ai point
	ArrayList<Wall> wallsInBetween;
	boolean intersect;
	

	
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
		if((targetY < yLoc) & (targetX < xLoc)) {
			for(int r = (targetY/50) ; r<(yLoc/50) ; r++){
				for(int c = (targetX/50) ; c<(xLoc/50) ; c++) {
					if(surroundingWalls[r][c] != null) {
						wallsInBetween.add(surroundingWalls[r][c]);
					}
				}
			}
		}
		else if((targetY < yLoc) & (targetX > xLoc)){
			for(int r = (targetY/50) ; r<(yLoc/50) ; r++){
				for(int c = (xLoc/50) ; c<(targetX/50); c++) {
					if(surroundingWalls[r][c] != null) {
						wallsInBetween.add(surroundingWalls[r][c]);
					}
				}
			}
		}
		else if((targetY > yLoc) & (targetX < xLoc)){
			for(int r = (yLoc/50) ; r<(targetY/50) ; r++){
				for(int c = (targetX/50) ; c<(int)(xLoc/50) ; c++) {
					if(surroundingWalls[r][c] != null) {
						wallsInBetween.add(surroundingWalls[r][c]);
					}
				}
			}
		}
		else if((targetY > yLoc) & (targetX > xLoc)){
			for(int r = (yLoc/50) ; r<(targetY/50) ; r++){
				for(int c = (xLoc/50) ; c<(targetX/50) ; c++) {
					if(surroundingWalls[r][c] != null) {
						wallsInBetween.add(surroundingWalls[r][c]);
					}
				}
			}
		}
		
		outerloop1:
		for(int i = 0; i<wallsInBetween.size() ; i++) {
			Wall temp = wallsInBetween.get(i);
			for(int x = temp.getXLoc(); x < 50; x++) { //searches if any point along the north side of the wall intersects the path between player and ai
				Point2D between = new Point(x, temp.getYLoc());
				if(between.distance(player) + between.distance(ai) == player.distance(ai)) {
					intersect = true;
					break outerloop1;
				}
			}
		}
		if(intersect == false) {
			outerloop2:
			for(int i = 0; i<wallsInBetween.size() ; i++) {
				Wall temp = wallsInBetween.get(i);
				for(int y = temp.getYLoc(); y < 50; y++) { //searches if any point along the west side of the wall intersects the path between player and ai
					Point2D between = new Point(temp.getXLoc(), y);
					if(between.distance(player) + between.distance(ai) == player.distance(ai)) {
						intersect = true;
						break outerloop2;
					}
				}
			}
		}
		if(intersect == false) {
			outerloop3:
				for(int i = 0; i<wallsInBetween.size() ; i++) {
					Wall temp = wallsInBetween.get(i);
					for(int y = temp.getYLoc(); y < 50; y++) { //searches if any point along the east side of the wall intersects the path between player and ai
						Point2D between = new Point(temp.getXLoc() + 50, y);
						if(between.distance(player) + between.distance(ai) == player.distance(ai)) {
							intersect = true;
							break outerloop3;
						}
					}
				}
		}
		//create projectile with input: type, 
		
		
		if(intersect == false){
			
			for(Projectile projectile: stockPile){
				if (!projectile.active){
					stockPile.remove(projectile); //Removes missile from stockpile
				}
			}
			
		
			if(numMoveTries%fireSlowMultiplier == 0 ) {

				System.out.println("You fired1");
				//if it has space, it will make a new projectile
				Projectile p = new Projectile(xLoc+25 , yLoc+25, Math.atan2(-(targetY - turretCenterY), targetX - turretCenterX),type, arena);
				stockPile.add(p);
				

			}			
			
			
		}
	
	wallsInBetween.clear();
	
	}


	public String getType() {

		return "aiTank";
	}

	
	
}
