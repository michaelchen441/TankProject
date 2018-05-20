import java.awt.Graphics;

public class Wall
{
	private boolean destructable;
	private int xLoc;
	private int yLoc;


	public Wall(int y, int x, boolean inDestructable) {
		xLoc = x*50;
		yLoc = y*50;
		destructable = inDestructable;
	
		
}

	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}


	void draw(Graphics g, ImageLibrary l){
		if(destructable){
			g.drawImage(l.destructableWall, xLoc, yLoc, null);		
		}
		else{
			g.drawImage(l.indestructableWall, xLoc, yLoc, null);	
		}
		
	}
}
