import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall
{
	private boolean destructable;
	private int xLoc;
	private int yLoc;

	BufferedImage indestructableWall;
	BufferedImage destructableWall;



	public Wall(int y, int x, boolean inDestructable) {
		xLoc = x*50;
		yLoc = y*50;
		destructable = inDestructable;
				
		try
		{
			indestructableWall = ImageIO.read(new File("images/Metal_50x50.jpg"));	
			destructableWall = ImageIO.read(new File("images/Wood_50x50.png"));	

		} catch (IOException e)                      
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}

	public int getXLoc() {
		return xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}


	void draw(Graphics g){
		if(destructable){
			g.drawImage(destructableWall, xLoc, yLoc, null);		
		}
		else{
			g.drawImage(indestructableWall, xLoc, yLoc, null);	
		}
		
	}
}
