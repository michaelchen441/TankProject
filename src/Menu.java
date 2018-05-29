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
		//draw background
		g.drawImage(l.background, 0, 0, null);

		//TODO draw title

		//TODO draw high scores


		//draw button to start classic game
		g.setColor(Color.BLACK); //Red colored rectangle
		g.fillRect(1000, 100, 320, 100); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(Color.GREEN); //Black colored text
		g.drawString("Classic Mode", 1025, 170); //Constructs the text and draws it on panel


		//TODO draw button to start game mode 2
		g.setColor(Color.BLACK); //Red colored rectangle
		g.fillRect(1000, 300, 340, 100); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(Color.BLUE); //Black colored text
		g.drawString("Survival Mode", 1025, 370); //Constructs the text and draws it on panel

		//TODO draw button to start game mode 2
		g.setColor(Color.BLACK); //Red colored rectangle
		g.fillRect(1000, 500, 320, 100); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(Color.WHITE); //Black colored text
		g.drawString("Classic Mode", 1025, 570); //Constructs the text and draws it on panel

		


		//TODO draw button to start game mode 3
	}


	public boolean clickedButton1(int x, int y){ //to start classic game
		//old g.fillRect(350, 100, 700, 200)
		//old g.drawString("START", 385, 260); 
		if((x >= 1000 && x <= 1320) 
				&&
				(y >= 100 && y <= 200)
				)
		{
			return true;
		}
		return false;
	}

	public boolean clickedButton2(int x, int y){ //to start zombie mode
		if((x >= 1000 && x <= 1340) 
				&&
				(y >= 300 && y <= 400)
				)
		{
			return true;
		}
		return false;
	}

	public boolean clickedButton3(int x, int y){ //TODO third game mode
		if((x >= 1000 && x <= 1320) 
				&&
				(y >= 500 && y <= 600)
				)
		{
			return true;
		}
		return false;
	}
}
