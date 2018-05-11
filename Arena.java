import java.awt.Graphics;
import java.util.ArrayList;

public class Arena
{
	private ArrayList<Wall> walls;
	public Arena(ArrayList<Wall> w) {
		walls = w;
	}
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	public void draw(Graphics g) {
		for(Wall w : walls) {
			w.draw(g);
		}
	}
}
