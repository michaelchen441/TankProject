//Every tank has a direction which can be obtained, set, and changed based upon keypress
//This enum is important for wall detection and movement
//Arrow key presses will change direction which prompts which parts of walls to check 
//Arrow key presses also tell how to move in a particular direction by altering x and y locs
public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST

}
