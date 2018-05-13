import java.awt.Color;
import java.awt.Graphics;

public class Wall
{
	private boolean destructable;
	private int xLoc;
	private int yLoc;
	private int length;
	private int width;
	
	public Wall(int x, int y,int l,int w) {
		xLoc = x;
		yLoc = y;
		length = l;
		width = w;
		
	}
	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}
	void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(xLoc, yLoc, length, width);
		g.fillRect(xLoc, yLoc, length, width);
	}
}
