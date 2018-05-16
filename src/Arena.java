import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Arena
{
	int level;
	private Wall[][] wallsLevel1;
	private Wall[][] wallsLevel2;
	private BufferedImage background;
	
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
			for(int i = 8; i<18; i++) {
				wallsLevel1[2][i] = new Wall(2, i, false);
			}
			wallsLevel1[3][18] = new Wall(3, 18, false);
			wallsLevel1[3][19] = new Wall(3, 19, false);
			wallsLevel1[4][20] = new Wall(4, 20, false);
			wallsLevel1[5][21] = new Wall(5, 21, false);
			wallsLevel1[6][21] = new Wall(6, 21, false);
			wallsLevel1[7][21] = new Wall(7, 21, false);
			wallsLevel1[8][20] = new Wall(8, 20, false);
			wallsLevel1[9][20] = new Wall(9, 20, false);
			wallsLevel1[10][19] = new Wall(10, 19, false);
			wallsLevel1[11][18] = new Wall(11, 18, false);
			wallsLevel1[12][18] = new Wall(12, 18, false);
			wallsLevel1[13][17] = new Wall(13, 17, false);
			wallsLevel1[13][16] = new Wall(13, 16, false);
			wallsLevel1[13][15] = new Wall(13, 15, false);
			wallsLevel1[13][14] = new Wall(13, 14, false);
			wallsLevel1[13][13] = new Wall(13, 13, false);


		}

		try
		{
			background = ImageIO.read(new File("images/Background2.png"));	
				

		} catch (IOException e)                      
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		g.drawImage(background,0,0,null);
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
