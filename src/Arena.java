import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Timer;

public class Arena
{
	int level; // Specifies which level and a specific arena 
	// is drawn based on which level is passed in
	private Wall[][] walls; //List of all walls in the arena
	//Every cell in the arena is can be made into a wall
	// Remains null if no wall is created in the cell
	PlayerTank playerTank; // Tank controlled by player
	AITank blueTank1;  // An AI Tank
	AITank redTank1; // An AI Tank
	AITank redTank2; // An AI Tank
	//Note about TankList
	//New Tanklist is created with the creation of a new arena
	//This prevents tanks from previous levels from being drawn in later levels
	ArrayList<Tank> tankList; // List of all tanks to keep track of

	int[] inputMoveInfo; // Information for how to change x and y locations
	// Dependent on keypressed
	int numWallsAcross; // Dimensions across
	int numWallsDown;	// Dimensions down

	private int timer;
	public boolean transition;
	private int timerStartTransition;
	private boolean startingTransition;
	public boolean advanceLevel;
	private Wall[][] transitionWalls;

	// Arena Constructor
	public Arena(int inLevel, int inNumWallsAcross, int  inNumWallsDown) {
		level = inLevel; // Sets up which level

		// Sets up arena dimensions
		numWallsAcross = inNumWallsAcross; 
		numWallsDown = inNumWallsDown;

		//Construct 2D Area of walls
		walls = new Wall[numWallsDown][numWallsAcross];

		timer = 0;
		transition = false;
		timerStartTransition = 0;
		advanceLevel = false;
		startingTransition = false;
		transitionWalls = new Wall[numWallsDown][numWallsAcross];


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

		//sets up transition walls
		for(int r = 6; r < 10; r++){
			for(int c = 0; c < walls[r].length; c++){
				transitionWalls[r][c] = new Wall(r, c, false);

			}
		}	


		// Constructs a player tank with location (3,10)
		playerTank = new PlayerTank(3,10, this);
		// Constructs list of tanks
		tankList = new ArrayList<Tank>();
		// Adds player tank to arraylist of tanks to keep track of
		tankList.add(playerTank);

		// Determines which level to draw based on level number passed into constructor
		// Each setup for a level is coded for in a separate method
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



	}
	// Enables access to all walls in a specific arena
	public Wall[][] getWalls(){
		return walls;
	}


	public void moveTanks(){
		if(!transition){
			for(Tank tank: tankList){
				tank.move();
				if(tank.type != TankType.GREEN) {

					tank.fire();

				}
			}
		}
	}

	// Arena draw method
	public void draw(Graphics g, ImageLibrary l) {
		timer++;
		// draws wood panel background image
		g.drawImage(l.background,0,0,null);

		if (transition){
			
			if(startingTransition == true){
				timerStartTransition = timer;//start timer so transition only lasts so long
				startingTransition = false;		
			}
			for(int r = 0; r < walls.length; r++){ //draws all of the walls in transition screen
				for(int c = 0; c < walls[r].length; c++){
					if(transitionWalls[r][c] != null){
						transitionWalls[r][c].draw(g, l);	
					}
				}
			}
			drawTransition(g, l);
			System.out.println(timerStartTransition);
			if(timer - timerStartTransition > 500){ //check if transition should end
				System.out.println("timer ran out");
				advanceLevel = true;//tells arena to start next level
			}
		}
		else{
			// If a cell in the arena is not null, it is considered to be a wall
			// We call the wall's draw function here to make our wall
			for(int r = 0; r < walls.length; r++){
				for(int c = 0; c < walls[r].length; c++){
					if(walls[r][c] != null){
						walls[r][c].draw(g, l);	
					}
				}
			}

			//Draws all the tanks in the tanklist
			for(Tank tank: tankList){
				tank.draw(g, l);
			}

			//Determines if all enemy tanks are dead
			//If condition is met, level is incremented
			//Doesn't check index 0 because it is a playertank
			boolean allDead = true;
			for(int i = 1; i < tankList.size(); i++){
				if (tankList.get(i).alive == true){
					allDead = false;
				}
			}
			if(allDead){
				transition = true;
				startingTransition = true;
			}
		}

	}

	private void drawTransition(Graphics g, ImageLibrary l)
	{
		//TODO display level completed in green
		//TODO  display current number of tanks killed

	}

	//Method containing all the information of level 1
	//When level is equal to 1, an arena with these objects and conditions are drawn
	public void level1() {
		//Makes one blue enemy tank and adds to tanklist
		playerTank.setX(3);
		playerTank.setY(10);
		//		blueTank1 = new AITank(TankType.BLUE, 20, 8, walls, playerTank); //TODO choose coordinates
		//		tankList.add(blueTank1);
		//		

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

		blueTank1 = new AITank(TankType.BLUE, 20, 8, this); //TODO choose coordinates
		tankList.add(blueTank1);
	}
	//Method containing all the information of level 2
	//When level is equal to 2, an arena with these objects and conditions are drawn
	public void level2() {
		//Makes one blue enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 24, 3, this); //TODO choose coordinates
		tankList.add(redTank1);

		playerTank.setX(3);
		playerTank.setY(13);

		for(int i = 4; i<6; i++) {
			for(int j = 5; j<15; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
			for(int j = 15; j<21; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
		}

		for(int i = 10; i<12; i++) {
			for(int j = 7; j<13; j++) {
				walls[i][j] = new Wall(i,j, true);
			}
			for(int j = 13; j<23; j++) {
				walls[i][j] = new Wall(i,j, false);
			}
		}	
	}
	//Method containing all the information of level 3
	//When level is equal to 3, an arena with these objects and conditions are drawn
	public void level3() {
		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this); //TODO choose coordinates
		//Makes one red enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 23, 14, this); //TODO choose coordinates
		//Makes one red enemy tank and adds to tanklist
		redTank2 =  new AITank(TankType.RED, 6, 2, this); //TODO choose coordinates
		tankList.add(blueTank1);
		tankList.add(redTank1);
		tankList.add(redTank2);

		playerTank.setX(3);
		playerTank.setY(8);


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

		for(int i = 5; i<9; i++) {
			walls[i][14] = new Wall(i,14, false);
		}
		for(int i = 7; i<11; i++) {
			walls[i][13] = new Wall(i,13, false);
		}

	}
	//Method containing all the information of level 4
	//When level is equal to 4, an arena with these objects and conditions are drawn
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
	//Method containing all the information of level 5
	//When level is equal to 5, an arena with these objects and conditions are drawn
	public void level5() {

		walls[9][3] = new Wall(9, 3, true);
		walls[9][4] = new Wall(9, 4, true);
		walls[9][5] = new Wall(9, 5, true);
		walls[9][6] = new Wall(9, 6, false);
		walls[10][4] = new Wall(10, 6, true);
		walls[11][4] = new Wall(11, 6, true);
		walls[12][4] = new Wall(12, 6, true);

		walls[3][3] = new Wall(3, 6, true);
		walls[4][6] = new Wall(4, 6, true);
		walls[5][6] = new Wall(5, 6, true);
		walls[6][6] = new Wall(6, 6, false);
		walls[6][5] = new Wall(6, 5, true);
		walls[6][4] = new Wall(6, 4, true);
		walls[6][3] = new Wall(6, 3, true);

		walls[3][22] = new Wall(3, 22, true);
		walls[4][22] = new Wall(4, 22, true);
		walls[5][22] = new Wall(5, 22, true);
		walls[6][22] = new Wall(6, 22, false);
		walls[6][23] = new Wall(6, 23, true);
		walls[6][24] = new Wall(6, 24, true);
		walls[6][25] = new Wall(6, 25, true);

		walls[9][22] = new Wall(9, 22, false);
		walls[9][23] = new Wall(9, 23, true);
		walls[9][24] = new Wall(9, 24, true);
		walls[9][25] = new Wall(9, 25, true);
		walls[10][22] = new Wall(10, 22, true);
		walls[11][22] = new Wall(11, 22, true);
		walls[12][22] = new Wall(12, 22, true);

		walls[5][9] = new Wall(5, 9, true);
		walls[5][10] = new Wall(5, 10, true);
		walls[5][11] = new Wall(5, 11, true);
		walls[5][12] = new Wall(5, 12, true);
		walls[5][13] = new Wall(5, 13, true);
		walls[5][14] = new Wall(5, 14, true);
		walls[5][15] = new Wall(5, 15, true);
		walls[5][16] = new Wall(5, 16, true);
		walls[5][17] = new Wall(5, 17, true);
		walls[5][18] = new Wall(5, 18, true);
		walls[5][19] = new Wall(5, 19, true);
		walls[6][9] = new Wall(6, 9, false);
		walls[7][9] = new Wall(7, 9, false);
		walls[8][9] = new Wall(8, 9, false);
		walls[9][9] = new Wall(9, 9, false);
		walls[10][9] = new Wall(10, 9, true);
		walls[10][10] = new Wall(10, 10, true);
		walls[10][11] = new Wall(10, 11, true);
		walls[10][12] = new Wall(10, 12, true);
		walls[10][13] = new Wall(10, 13, true);
		walls[10][14] = new Wall(10, 14, true);
		walls[10][15] = new Wall(10, 15, true);
		walls[10][16] = new Wall(10, 16, true);
		walls[10][17] = new Wall(10, 17, true);
		walls[10][18] = new Wall(10, 18, true);
		walls[10][19] = new Wall(10, 19, true);
		walls[9][19] = new Wall(9, 19, false);
		walls[8][19] = new Wall(8, 19, false);
		walls[7][19] = new Wall(7, 19, false);
		walls[6][19] = new Wall(6, 19, false);



	}
	//Sets amount a certain tank has to move based on keypress
	//Calls the player tanks setInputMoveArr which takes the information of how to move
	//Everytime tank is moved, actual x and y loc changes provided are executed
	public void setInputMoveArr(int[] inInputMoveArr){
		playerTank.setInputMoveArr(inInputMoveArr);
	}

	//Returns x location of playerTank
	public int playerTankLocX() {return playerTank.getX();}
	//Returns y location of playerTank
	public int playerTankLocY() {return playerTank.getY();}
	public ArrayList<Tank> getTanks() { return tankList;}





}
