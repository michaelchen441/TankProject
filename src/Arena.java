import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Arena
{
	int level;
	private Wall[][] walls;

	PlayerTank playerTank;
	int[] inputMoveInfo;
	
	int numWallsAcross;
	int numWallsDown;
	
	
	
	public Arena(int inLevel, int inNumWallsAcross, int  inNumWallsDown) {
		level = inLevel;
		
		
		
	
		numWallsAcross = inNumWallsAcross;
		numWallsDown = inNumWallsDown;
		
		walls = new Wall[numWallsDown][numWallsAcross];
		
		
		//sets up border walls
		for(int r = 0; r < numWallsDown; r++){
			walls[r][0] = new Wall(r, 0, false);
		}
		for(int r = 0; r < numWallsDown; r++){
			walls[r][numWallsAcross - 1] = new Wall(r, numWallsAcross - 1, false);
		}
		for(int c = 0; c < numWallsAcross; c++){
			walls[0][c] =  new Wall(0, c, false);
		}
		for(int c = 0; c < numWallsAcross; c++){
			walls[numWallsDown - 1][c] =  new Wall(numWallsDown - 1, c, false);
		}
		
		
		if(level == 1)
			level1();
		if(level == 2)
			level2();
		if(level == 3)
			level3();
		if(level == 4)
			level4();
		if(level == 5)
			level5();
			
		
		playerTank = new PlayerTank(3,10, walls);

	
	
	}
	
	public Wall[][] getWalls(){
			return walls;
	}
	
	
	public void draw(Graphics g, ImageLibrary l) {
		g.drawImage(l.background,0,0,null);
		
		if(level == 1){
			for(int r = 0; r < walls.length; r++){
				for(int c = 0; c < walls[r].length; c++){
					if(walls[r][c] != null){
						walls[r][c].draw(g, l);	
					}
				}
			}

		}
		//draw the tanks
		playerTank.draw(g, l);
		
	}
	
	public void level1() {
		
		walls[4][5] = new Wall(4,5, false);
		walls[5][5] = new Wall(5,5, false);
		walls[6][5] = new Wall(6,5, false);
		walls[4][6] = new Wall(4,6, false);
		walls[5][6] = new Wall(5,6, false);
		walls[6][6] = new Wall(6,6, false);
		
		walls[9][5] = new Wall(9,5, false);
		walls[10][5] = new Wall(10,5, false);
		walls[11][5] = new Wall(11,5, false);
		walls[9][6] = new Wall(9,6, false);
		walls[10][6] = new Wall(10,6, false);
		walls[11][6] = new Wall(11,6, false);

		walls[4][14] = new Wall(4,14, false);
		walls[5][14] = new Wall(5,14, false);
		walls[6][14] = new Wall(6,14, false);
		walls[7][14] = new Wall(7,14, true);
		walls[8][14] = new Wall(8,14, true);
		walls[9][14] = new Wall(9,14, false);
		walls[10][14] = new Wall(10,14, false);
		walls[11][14] = new Wall(11,14, false);

		walls[4][15] = new Wall(4,15, false);
		walls[5][15] = new Wall(5,15, false);
		walls[6][15] = new Wall(6,15, false);
		walls[7][15] = new Wall(7,15, true);
		walls[8][15] = new Wall(8,15, true);
		walls[9][15] = new Wall(9,15, false);
		walls[10][15] = new Wall(10,15, false);
		walls[11][15] = new Wall(11,15, false);
		
	}
	
	public void level2() {

		for(int i = 4; i<6; i++) {
			for(int j = 5; j<17; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
			for(int j = 17; j<23; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
		}
		
		for(int i = 10; i<12; i++) {
			for(int j = 5; j<11; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
			for(int j = 11; j<23; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
		}	
	}
	
	public void level3() {
		
		for(int i = 3; i<5; i++) {
			for(int j = 4; j<6; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
			for(int j = 6; j<13; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
			for(int j = 13; j<15; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
		}
		
		for(int i = 11; i<13; i++) {
			for(int j = 13; j<24; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
		}
		
		for(int i = 11; i<13; i++) {
			for(int j = 13; j<15; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
			for(int j = 15; j<22; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
			for(int j = 22; j<24; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
		}
		
		for(int i = 5; i<8; i++) {
				walls[i][14] = new Wall(i,14, false);
		}
		for(int i = 8; i<11; i++) {
			walls[i][13] = new Wall(i,13, false);
		}
			
	}
	
	public void level4() {
		
			for(int i = 1; i<9; i++) {
				if(i%2 == 0) {
					walls[i][9] = new Wall(i,9, false);
				}		
				else {
					walls[i][9] = new Wall(i,9, true);
				}
			}
			for(int i = 12; i<15; i++) {
				if(i%2 == 1) {
					walls[i][9] = new Wall(i,9, false);
				}
				else {
					walls[i][9] = new Wall(i,9, true);
				}
			}
			
			for(int i = 1; i<4; i++) {
				if(i%2 == 0) {
					walls[i][18] = new Wall(i,18, false);
				}
				else {
					walls[i][18] = new Wall(i,18, true);
				}
			}
			for(int i = 7; i<16; i++) {
				if(i%2 == 1) {
				walls[i][18] = new Wall(i,18, false);
				}
				else {
					walls[i][18] = new Wall(i,18, true);
				}
			}
			
			for(int i = 1; i<8; i++) {
				if(i%2 == 0) {
				walls[5][i] = new Wall(5,i, false);
				}
				else {
					walls[5][i] = new Wall(5,i, true);
				}
			}
			for(int i = 11; i<28; i++) {
				if(i%2 == 1) {
				walls[5][i] = new Wall(5,i, false);
				}
				else {
					walls[5][i] = new Wall(5,i, true);
				}
			}
			
			for(int i = 1; i<17; i++) {
				if(i%2 == 0) {
				walls[10][i] = new Wall(10,i, false);
				}
				else {
					walls[10][i] = new Wall(10,i, true);
				}
			}
			for(int i = 20; i<28; i++) {
				if(i%2 == 1) {
				walls[10][i] = new Wall(10,i, false);
				}
				else {
					walls[10][i] = new Wall(10,i, true);

				}
			}
				
				
	}
	
	public void level5() {
		
	}
	
	public void setInputMoveArr(int[] inInputMoveArr){
		playerTank.setInputMoveArr(inInputMoveArr);
	}

	public int playerTankLocX() {return playerTank.getX();}

	public int playerTankLocY() {return playerTank.getY();}
	
	
	
	
}
