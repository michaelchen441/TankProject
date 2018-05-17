import java.awt.image.BufferedImage;

public class ImageLibrary
{
	public static BufferedImage background;
	public static BufferedImage crosshair;
	public static BufferedImage playerTank;
	public static BufferedImage turret;
	public static BufferedImage projectile;
	
	public static BufferedImage indestructableWall;
	public static BufferedImage destructableWall;
	
	public static BufferedImage redTank;
	public static BufferedImage blueTank;
	public static BufferedImage blackTank;
	
	public ImageLibrary(BufferedImage inCrosshair, 
						BufferedImage inPlayerTank,
						BufferedImage inTurret,
						BufferedImage inProjectile,
						
						BufferedImage inIndestructableWall,
						BufferedImage inDestructableWall,
						
						BufferedImage inRedTank,
						BufferedImage inBlueTank,
						BufferedImage inBlackTank
						)
	{
		crosshair = inCrosshair;
		playerTank = inPlayerTank;
		turret = inTurret;
		projectile = inProjectile;
		
		indestructableWall = inIndestructableWall;
		destructableWall = inDestructableWall;
		
		redTank = inRedTank;
		blueTank = inBlueTank;
		blackTank = inBlackTank;
	}
	
}
