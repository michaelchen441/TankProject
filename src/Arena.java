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
		
		
		
		if(level == 1)
			level1();
		if(level == 1)
			level2();
		if(level == 1)
			level3();
		if(level == 1)
			level4();
		if(level == 1)
			level5();
			
		
		

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
	
	public void level1() {
		
		wallsLevel1[4][5] = new Wall(4,5, false);
		wallsLevel1[5][5] = new Wall(5,5, false);
		wallsLevel1[6][5] = new Wall(6,5, false);
		wallsLevel1[4][6] = new Wall(4,6, false);
		wallsLevel1[5][6] = new Wall(5,6, false);
		wallsLevel1[6][6] = new Wall(6,6, false);
		
		wallsLevel1[9][5] = new Wall(9,5, false);
		wallsLevel1[10][5] = new Wall(10,5, false);
		wallsLevel1[11][5] = new Wall(11,5, false);
		wallsLevel1[9][6] = new Wall(9,6, false);
		wallsLevel1[10][6] = new Wall(10,6, false);
		wallsLevel1[11][6] = new Wall(11,6, false);

		wallsLevel1[4][14] = new Wall(4,14, false);
		wallsLevel1[5][14] = new Wall(5,14, false);
		wallsLevel1[6][14] = new Wall(6,14, false);
		wallsLevel1[7][14] = new Wall(7,14, true);
		wallsLevel1[8][14] = new Wall(8,14, true);
		wallsLevel1[9][14] = new Wall(9,14, false);
		wallsLevel1[10][14] = new Wall(10,14, false);
		wallsLevel1[11][14] = new Wall(11,14, false);

		wallsLevel1[4][15] = new Wall(4,15, false);
		wallsLevel1[5][15] = new Wall(5,15, false);
		wallsLevel1[6][15] = new Wall(6,15, false);
		wallsLevel1[7][15] = new Wall(7,15, true);
		wallsLevel1[8][15] = new Wall(8,15, true);
		wallsLevel1[9][15] = new Wall(9,15, false);
		wallsLevel1[10][15] = new Wall(10,15, false);
		wallsLevel1[11][15] = new Wall(11,15, false);
		
	}
	
	public void level2() {

		for(int i = 4; i<6; i++) {
			for(int j = 5; j<17; j++) {
				wallsLevel1[i][j] = new Wall(i,j, false);
			}
			for(int j = 17; j<23; j++) {
				wallsLevel1[i][j] = new Wall(i,j, true);
			}
		}
		
		for(int i = 10; i<12; i++) {
			for(int j = 5; j<11; j++) {
				wallsLevel1[i][j] = new Wall(i,j, true);
			}
			for(int j = 11; j<23; j++) {
				wallsLevel1[i][j] = new Wall(i,j, false);
			}
		}	
	}
	
	public void level3() {
		
	}
	
	public void level4() {
		
	}
	
	public void level5() {
		
	}
	
	
	
	
}
