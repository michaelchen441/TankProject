import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.javafx.tk.Toolkit;

public class ImageLibrary
{
	
	//create all images
	public BufferedImage background;
	public BufferedImage crosshair;
	public BufferedImage projectile;
	public BufferedImage explosion;
	
	public BufferedImage indestructableWall;
	public BufferedImage destructableWall;
	
	public BufferedImage greenTank;
	public BufferedImage redTank;
	public BufferedImage blueTank;
	public BufferedImage blackTank;
	public BufferedImage whiteTank;
	public BufferedImage pinkTank;
	public BufferedImage yellowTank;
	
	public BufferedImage greenTurret;
	public BufferedImage redTurret;
	public BufferedImage blueTurret;
	public BufferedImage blackTurret;
	public BufferedImage whiteTurret;
	public BufferedImage pinkTurret;
	public BufferedImage yellowTurret;

	// constructed once in tank panel and sent to other classes as an imput in draw methods
	public ImageLibrary()
	{
		try
		{
			
			background = ImageIO.read(getClass().getResource("images/Background2.png"));	
			crosshair = ImageIO.read(getClass().getResource("images/crosshair.png"));		
			projectile = ImageIO.read(getClass().getResource("images/projectile.png"));	
			explosion = ImageIO.read(getClass().getResource("images/explosion.png"));
		
			indestructableWall = ImageIO.read(getClass().getResource("images/Metal_50x50.jpg"));	
			destructableWall = ImageIO.read(getClass().getResource("images/Wood_50x50.png"));
			
			greenTank = ImageIO.read(getClass().getResource("images/greenTank.png"));	
			redTank = ImageIO.read(getClass().getResource("images/redTank.png"));	 
			blueTank = ImageIO.read(getClass().getResource("images/blueTank.png"));	
			blackTank = ImageIO.read(getClass().getResource("images/blackTank.png"));	
			whiteTank = ImageIO.read(getClass().getResource("images/whiteTank.png"));	 
			pinkTank = ImageIO.read(getClass().getResource("images/pinkTank.png"));	
			yellowTank = ImageIO.read(getClass().getResource("images/yellowTank.png"));	
			
			greenTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/greenTurret.png"));
			redTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/redTurret.png"));
			blueTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/blueTurret.png"));
			blackTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/blackTurret.png"));
			whiteTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/whiteTurret.png"));
			pinkTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/pinkTurret.png"));
			yellowTurret = ImageIO.read(getClass().getResource("images/20x50 turrets/yellowTurret.png"));
		}
		catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
