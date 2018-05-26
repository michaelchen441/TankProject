import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//Pops up everytime you die/lose
public class Menu
{
	
	private int highScore; //Displays your highscore
	
	//at some point make panels inMenu false
	void draw(Graphics g, ImageLibrary l)
	{
		
		g.setColor(Color.RED); //Red colored rectangle
		g.fillRect(350, 500, 700, 200); //Makes rectangle containing start button
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 200)); //Times New Roman font; size 200
		g.setColor(Color.BLACK); //Black colored text
		g.drawString("START", 385, 660); //Constructs the text and draws it on panel
	}
			
}
