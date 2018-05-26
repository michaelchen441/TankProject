import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	
	private int numTankKills;
	private int level;
	
	
	public GameOver(int inNumTankKills, int inLevel){
		
		numTankKills = inNumTankKills;
		level = inLevel;
		
	}
	
	
	public void draw(Graphics g, ImageLibrary l)
	{
		g.setColor(Color.RED); //Red colored rectangle
		g.fillRect(350, 500, 700, 200); //Makes rectangle containing start button
		g.setFont(new Font("TimesRoman", Font.PLAIN, 200)); //Times New Roman font; size 200
		g.setColor(Color.BLACK); //Black colored text
		g.drawString("Game Over", 385, 660); //Constructs the text and draws it on panel
	}
}
