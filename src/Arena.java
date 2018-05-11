import java.awt.Graphics;
import java.util.ArrayList;

public class Arena
{
	int level;
	private Wall[][] wallsLevel1;
	private Wall[][] wallsLevel2;
	
	int numWallsAcross;
	int numWallsDown;
	
	public Arena(int inLevel, int inNumWallsAcross, int  inNumWallsDown) {
		level = inLevel;
	
		numWallsAcross = inNumWallsAcross;
		numWallsDown = inNumWallsDown;
		
		wallsLevel1 = new Wall[numWallsDown][numWallsAcross];
		wallsLevel2 = new Wall[numWallsDown][numWallsAcross];
		
		for(int r = 0; r < numWallsDown; r++){
			wallsLevel1[r][0] = new Wall(r, 0, false);
			wallsLevel2[r][0] = new Wall(r, 0, false);
		}
		for(int r = 0; r < numWallsDown; r++){
			wallsLevel1[r][numWallsAcross - 1] = new Wall(r, numWallsAcross - 1, false);
			wallsLevel2[r][numWallsAcross - 1] = new Wall(r, numWallsAcross - 1, false);
		}
		for(int c = 0; c < numWallsAcross; c++){
			wallsLevel1[0][c] =  new Wall(0, c, false);
			wallsLevel2[0][c] =  new Wall(0, c, false);
		}
		for(int c = 0; c < numWallsAcross; c++){
			wallsLevel1[numWallsDown - 1][c] =  new Wall(numWallsDown - 1, c, false);
			wallsLevel2[numWallsDown - 1][c] =  new Wall(numWallsDown - 1, c, false);
		}
		
		
		
		if(level == 1){
			//read form arena1walls txt file	
			//read form arena1tanks txt file
		}

	
	}
	
	public Wall[][] getWalls(){
		if(level == 1){
			return wallsLevel1;
		}
		if(level == 2){
			return wallsLevel1;
		}
		else{
			return null;
		}
			
	}
	public void draw(Graphics g) {
		
		if(level == 1){
			for(int r = 0; r < wallsLevel1.length; r++){
				for(int c = 0; c < wallsLevel1[r].length; c++){
					if(wallsLevel1[r][c] != null){
						wallsLevel1[r][c].draw(g);	
					}
				}
			}

		}
		//draw the tanks
		
		
		
	}
}
