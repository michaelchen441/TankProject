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

	AITank blueTank1;  // possible blue AI Tanks, not all ai tanks are used for each level,
	AITank blueTank2; //							but this is enough for any level

	AITank redTank1; // possible red AI Tank
	AITank redTank2; 

	AITank blackTank1;// possible black AI Tanks
	AITank blackTank2;
	AITank blackTank3;
	AITank blackTank4;

	AITank whiteTank1;// possible white AI Tank
	AITank whiteTank2;
	AITank whiteTank3;

	AITank invisibleTank1;// possible invisible AI Tank
	AITank invisibleTank2;
	AITank invisibleTank3;
	AITank invisibleTank4;
	AITank invisibleTank5;
	AITank invisibleTank6;




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
		switch(level){
		case 1: 
			level1Setup();
			break;
		case 2: 
			level2Setup();
			break;
		case 3: 
			level3Setup();
			break;
		case 4: 
			level4Setup();
			break;
		case 5: 
			level5Setup();
			break;
		case 6: 
			level6Setup();
			break;
		case 7: 
			level7Setup();
			break;
		case 8: 
			level8Setup();
			break;
		case 9: 
			level9Setup();
			break;
		case 10: 
			level10Setup();
			break;
		case 11: 
			level11Setup();
			break;
		case 12: 
			level12Setup();
			break;
		}




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
	public void level1Setup() {
		//Makes one blue enemy tank and adds to tanklist
		playerTank.setX(3);
		playerTank.setY(10);

		wallSetup1();


		blueTank1 = new AITank(TankType.BLUE, 20, 8, this);
		tankList.add(blueTank1);
	}
	//Method containing all the information of level 2
	//When level is equal to 2, an arena with these objects and conditions are drawn
	public void level2Setup() {
		//Makes one blue enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 24, 3, this);
		tankList.add(redTank1);

		playerTank.setX(3);
		playerTank.setY(13);


		wallSetup2();

	}

	//Method containing all the information of level 3
	//When level is equal to 3, an arena with these objects and conditions are drawn
	public void level3Setup() {
		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		//Makes one red enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 23, 14, this); 
		//Makes one red enemy tank and adds to tanklist
		redTank2 =  new AITank(TankType.RED, 6, 2, this);
		tankList.add(blueTank1);
		tankList.add(redTank1);
		tankList.add(redTank2);

		playerTank.setX(3);
		playerTank.setY(8);

		wallSetup3();


	}
	//Method containing all the information of level 4
	//When level is equal to 4, an arena with these objects and conditions are drawn
	public void level4Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 14, 2, this); //TODO choose coordinates
		//Makes one blue enemy tank and adds to tanklist
		blueTank2 =  new AITank(TankType.BLUE, 24, 8, this); //TODO choose coordinates
		//Makes one red enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 14, 8, this); //TODO choose coordinates
		//Makes one red enemy tank and adds to tanklist
		redTank2 =  new AITank(TankType.RED, 24, 2, this); //TODO choose coordinates
		tankList.add(blueTank1);
		tankList.add(blueTank2);
		tankList.add(redTank1);
		tankList.add(redTank2);

		playerTank.setX(3);
		playerTank.setY(13);


		wallSetup4();

	}
	//Method containing all the information of level 5
	//When level is equal to 5, an arena with these objects and conditions are drawn
	public void level5Setup() {


		//Makes one red enemy tank and adds to tanklist
		blackTank1 =  new AITank(TankType.BLACK, 24, 2, this); 
		//Makes one red enemy tank and adds to tanklist
		blackTank2 =  new AITank(TankType.BLACK, 25, 3, this);


		tankList.add(blackTank1);
		tankList.add(blackTank2);



		playerTank.setX(3);
		playerTank.setY(12);

		wallSetup5();


	}

	//Method containing all the information of level 6
	//When level is equal to 6, an arena with these objects and conditions are drawn
	public void level6Setup() {

		//Makes one red enemy tank and adds to tanklist
		blackTank1 =  new AITank(TankType.BLACK, 4, 4, this); 
		//Makes one red enemy tank and adds to tanklist
		blackTank2 =  new AITank(TankType.BLACK, 24, 4, this);
		//Makes one red enemy tank and adds to tanklist
		blackTank3 =  new AITank(TankType.BLACK, 24, 11, this);

		tankList.add(blackTank1);
		tankList.add(blackTank2);
		tankList.add(blackTank3);

		blueTank1 =  new AITank(TankType.BLUE, 12, 7, this);
		//Makes one red enemy tank and adds to tanklist
		blueTank2 =  new AITank(TankType.BLUE, 16, 8, this);
		tankList.add(blueTank1);
		tankList.add(blueTank2);


		playerTank.setX(2);
		playerTank.setY(14);



		wallSetup6();


	}

	//Method containing all the information of level 7
	//When level is equal to 7, an arena with these objects and conditions are drawn
	public void level7Setup() {

		//Makes one blue enemy tank and adds to tanklist
		redTank1 =  new AITank(TankType.RED, 22, 5, this);
		redTank2 =  new AITank(TankType.RED, 22, 13, this);
		blackTank1 =  new AITank(TankType.BLACK, 24, 8, this);
		blackTank2 =  new AITank(TankType.BLACK, 25, 11, this);
		tankList.add(redTank1);
		tankList.add(redTank2);
		tankList.add(blackTank1);
		tankList.add(blackTank2);

		//make more tanks

		playerTank.setX(3);
		playerTank.setY(8);

		wallSetup7();


	}

	//Method containing all the information of level 8
	//When level is equal to 8, an arena with these objects and conditions are drawn
	public void level8Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		tankList.add(blueTank1);
		//make more tanks


		playerTank.setX(3);
		playerTank.setY(8);


		wallSetup8();


	}

	//Method containing all the information of level 9
	//When level is equal to 9, an arena with these objects and conditions are drawn
	public void level9Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		tankList.add(blueTank1);
		//make more tanks


		playerTank.setX(3);
		playerTank.setY(8);


		wallSetup9();


	}

	//Method containing all the information of level 10
	//When level is equal to 10, an arena with these objects and conditions are drawn
	public void level10Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		tankList.add(blueTank1);
		//make more tanks


		playerTank.setX(3);
		playerTank.setY(8);


		wallSetup10();


	}



	//Method containing all the information of level 11
	//When level is equal to 11, an arena with these objects and conditions are drawn
	public void level11Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		tankList.add(blueTank1);
		//make more tanks


		playerTank.setX(3);
		playerTank.setY(8);


		wallSetup11();


	}


	//Method containing all the information of level 11
	//When level is equal to 11, an arena with these objects and conditions are drawn
	public void level12Setup() {

		//Makes one blue enemy tank and adds to tanklist
		blueTank1 =  new AITank(TankType.BLUE, 23, 8, this);
		tankList.add(blueTank1);
		//make more tanks


		playerTank.setX(3);
		playerTank.setY(8);

		wallSetup12();



	}

	//make multiple wallsetup functions and have level setupfunctions call them

	private void wallSetup1(){
		//use for levels 1 and ...
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


	private void wallSetup2(){
		//use for levels 2 and ...

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

	private void wallSetup3(){
		//use for levels 3 and ...
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

	private void wallSetup4(){
		//use for levels 4 and ...
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

	private void wallSetup5(){
		//use for levels 5 and ...

		walls[11][3] = new Wall(11, 3, false);
		walls[11][4] = new Wall(11, 4, false);
		walls[12][4] = new Wall(12, 4, false);
		walls[3][25] = new Wall(3, 23, false);
		walls[4][25] = new Wall(4, 23, false);
		walls[4][26] = new Wall(4, 24, false);


	}

	private void wallSetup6(){
		//use for levels 6 and ...
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

	private void wallSetup7(){
		//use for levels 7 and ...

		for(int i = 5; i<23; i++) {
			if(i == 6 || i == 9 || i ==12){
				walls[4][i] = new Wall(4, i, true);
			}
			else{
				walls[4][i] = new Wall(4, i, false);
			}
		}
		for(int i = 5; i<23; i++) {
			if(i == 15 || i == 18 || i ==21){
				walls[11][i] = new Wall(11, i, true);
			}
			else{
				walls[11][i] = new Wall(11, i, false);
			}
			
		}
		walls[5][13] = new Wall(5, 13, true);
		walls[6][13] = new Wall(6, 13, true);
		walls[7][13] = new Wall(7, 13, true);
		walls[8][14] = new Wall(8, 14, true);
		walls[9][14] = new Wall(9, 14, true);
		walls[10][14] = new Wall(10, 14, true);

	}

	private void wallSetup8(){
		//use for levels 8 and ...

		walls[9][3] = new Wall(9, 3, false);
	}

	private void wallSetup9(){
		//use for levels 9 and ...

		walls[9][3] = new Wall(9, 3, false);
	}

	private void wallSetup10(){
		//use for levels 10 and ...

		walls[9][3] = new Wall(9, 3, false);
	}

	private void wallSetup11(){
		//use for levels 11 and ...

		walls[9][3] = new Wall(9, 3, false);
	}

	private void wallSetup12(){
		//use for levels 12 and ...

		walls[9][3] = new Wall(9, 3, false);
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
