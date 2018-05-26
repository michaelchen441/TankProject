import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	void draw(Graphics g, ImageLibrary imageLibrary)
	{
		g.setColor(Color.RED); //Red colored rectangle
		g.fillRect(350, 500, 700, 200); //Makes rectangle containing start button
		g.setFont(new Font("TimesRoman", Font.PLAIN, 200)); //Times New Roman font; size 200
		g.setColor(Color.BLACK); //Black colored text
		g.drawString("Game Over", 385, 660); //Constructs the text and draws it on panel
	}
}
