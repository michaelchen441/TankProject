import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLibrary
{
	
	//create all images
	public BufferedImage background;
	public BufferedImage crosshair;
	public BufferedImage projectile;
	
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
		
			indestructableWall = ImageIO.read(getClass().getResource("images/Metal_50x50.jpg"));	
			destructableWall = ImageIO.read(getClass().getResource("images/Wood_50x50.png"));
			
			greenTank = ImageIO.read(getClass().getResource("images/50x50 tanks/greenTank.png"));	
			redTank = ImageIO.read(getClass().getResource("images/50x50 tanks/redTank.png"));	 
			blueTank = ImageIO.read(getClass().getResource("images/50x50 tanks/blueTank.png"));	
			blackTank = ImageIO.read(getClass().getResource("images/50x50 tanks/blackTank.png"));	
			whiteTank = ImageIO.read(getClass().getResource("images/50x50 tanks/whiteTank.png"));	 
			pinkTank = ImageIO.read(getClass().getResource("images/50x50 tanks/pinkTank.png"));	
			yellowTank = ImageIO.read(getClass().getResource("images/50x50 tanks/yellowTank.png"));	
			
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
