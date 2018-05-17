import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//Pops up everytime you die
public class Menu
{
	
	private int highScore;
	
	
	//at some point make panels inMenu false
	void draw(Graphics g, ImageLibrary imageLibrary)
	{
		
		g.setColor(Color.RED);
		g.fillRect(350, 500, 700, 200);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 200)); 
		g.setColor(Color.BLACK);
		g.drawString("START", 385, 660);
	}
			
}
