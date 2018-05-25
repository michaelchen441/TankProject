import java.awt.Graphics;

public class Wall
{
	public boolean destructable; //Type of wall
	//Wall x and y location
	private int xLoc;
	private int yLoc;


	public Wall(int y, int x, boolean inDestructable) {
		// Wall is 50 pixels wide and long
		xLoc = x*50;
		yLoc = y*50;
		destructable = inDestructable;
	
		
}

	public int getXLoc() {
		return xLoc; //Returns x location
	}
	public int getYLoc() {
		return yLoc; //Returns y location
	}


	void draw(Graphics g, ImageLibrary l){
		//Draws destructable wall
		if(destructable){
			g.drawImage(l.destructableWall, xLoc, yLoc, null);		
		}
		//Draws indestructable wall
		else{
			g.drawImage(l.indestructableWall, xLoc, yLoc, null);	
		}
		
	}
}
