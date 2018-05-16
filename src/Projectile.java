import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile
{
		int xLoc;
		int yLoc;
		TankType color;
		double angle;
		BufferedImage bullet;
	public Projectile(int x, int y, TankType myType, double a)
	{
		xLoc = x;
		yLoc = y;
		color = myType;
		angle = a;
		
		try
		{
			bullet = ImageIO.read(new File("images/bullet.jpeg"));	
				

		} catch (IOException e)                      
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	void draw(Graphics g){
		g.drawImage(bullet, 0, 0, null);
	}
	
}
