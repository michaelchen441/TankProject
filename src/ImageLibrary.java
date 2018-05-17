import java.awt.image.BufferedImage;

public class ImageLibrary
{
	public static BufferedImage background;
	public static BufferedImage crosshair;
	public static BufferedImage turret;
	public static BufferedImage projectile;
	
	public static BufferedImage indestructableWall;
	public static BufferedImage destructableWall;
	
	public static BufferedImage greenTank;
	public static BufferedImage redTank;
	public static BufferedImage blueTank;
	public static BufferedImage blackTank;
	
	public static BufferedImage greenTurret;
	public static BufferedImage redTurret;
	public static BufferedImage blueTurret;
	public static BufferedImage blackTurret;
	
	public ImageLibrary(BufferedImage inCrosshair, 
						BufferedImage inTurret,
						BufferedImage inProjectile,
						
						BufferedImage inIndestructableWall,
						BufferedImage inDestructableWall,
						
						BufferedImage inGreenTank,
						BufferedImage inRedTank,
						BufferedImage inBlueTank,
						BufferedImage inBlackTank,
						
						BufferedImage inGreenTurret,    
						BufferedImage inRedTurret,      
						BufferedImage inBlueTurret,     
						BufferedImage inBlackTurret    
						
						)
	{
		crosshair = inCrosshair;
		turret = inTurret;
		projectile = inProjectile;
		
		indestructableWall = inIndestructableWall;
		destructableWall = inDestructableWall;
		
		greenTank = inGreenTank;
		redTank = inRedTank;
		blueTank = inBlueTank;
		blackTank = inBlackTank;
		
		greenTurret = inGreenTurret;    
		redTurret = inRedTurret;      
	 	blueTurret = inBlueTurret;     
		blackTurret = inBlackTurret;    
		
	}
	
}
