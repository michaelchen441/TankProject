import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
		
	public Projectile(int x, int y, TankType myType, double a)
	{
		xLoc = x;
		yLoc = y;
		color = myType;
		angle = a;
	
		
	}
	
	
	
	void draw(Graphics g, ImageLibrary l){
		double rotationRequired = Math.toRadians (angle);
		double locationX = l.projectile.getWidth() / 2;
		double locationY = l.projectile.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(l.projectile, null), xLoc, yLoc, null);	}
	
}
