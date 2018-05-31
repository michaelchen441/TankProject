import java.awt.Graphics;

public class Explosion
{
	private int xLoc;
	private int yLoc;
	private ExplosionType type;
	public int frameCount;
	public boolean doneDrawing;

	private int drawTries;
	private int drawSlowMultiplier;

	private int srcFrameSize = 134;
	private int dstFrameSize;
	private int objectSize;

	public Explosion(int inX, int inY, ExplosionType inType){
		//TODO switch based on inType to choose where to locate xLoc and yLoc
		type = inType;

		switch(type){
		case LARGE: //for tanks dying
			objectSize = 50;
			dstFrameSize = 134;
			drawSlowMultiplier = 5;
			break;
		case MEDIUM: //for projectile dying (hitting a wall or a tank)
			objectSize = 0;
			dstFrameSize = 134/3;
			drawSlowMultiplier = 5;
			break;
		case SMALL: //for projectile shooting. draws at tip of turret
			objectSize = 0;
			dstFrameSize = 134/6;
			drawSlowMultiplier = 2;
			break;
		}


		xLoc = (inX + (objectSize/2) - (dstFrameSize/2)); 
		yLoc = (inY + (objectSize/2) - (dstFrameSize/2)); 

		frameCount = 0;

		drawTries = 0;
	}


	public void draw(Graphics g, ImageLibrary l){

		if(frameCount >= 12){
			doneDrawing = true; 
		}
		else{
			//TODO switch based on type to change destination size
			g.drawImage(l.explosion,
					xLoc, 
					yLoc, 
					xLoc + dstFrameSize, 
					yLoc + dstFrameSize, 
					frameCount * srcFrameSize, 
					0, 
					(frameCount * srcFrameSize) + srcFrameSize, 
					srcFrameSize, 
					null);
		}

		drawTries++;
		if(drawTries % drawSlowMultiplier == 0){
			frameCount++;
		}
	}

}
