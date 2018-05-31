import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//Pops up everytime you die/lose
public class Menu
{
	int titleX = 200;
	int titleY = 175;
	
	int scoreTextX = 50;
	int buttonText1X = 935;
	int buttonText2X = 925;
	int text1Y = 370;
	int text2Y = 570;
	
	int buttonX = 900;
	int button1Y = 300;
	int button2Y = 500;
	int buttonHeight = 100;
	int buttonWidth = 440;
	
	
	
	
	
	

	private int highScore; //Displays your highscore

	//at some point make panels inMenu false
	void draw(Graphics g, ImageLibrary l, int inSurvivalHighScore, int inClassicHighScore)
	{
		//draw background
		g.drawImage(l.background, 0, 0, null);

		//TODO draw title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 175)); //Times New Roman font; size 200
		g.setColor(Color.BLACK); //Black colored text
		g.drawString("TANK GAME", titleX, titleY); //Constructs the text and draws it on panel
		
		//TODO draw high scores
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200

		Color darkGreen = new Color(0, 102, 0);
		g.setColor(darkGreen); 
		g.drawString("Classic high score = "+inClassicHighScore, scoreTextX, text1Y); //Constructs the text and draws it on panel
		Color darkBlue = new Color(0, 0, 153);
		g.setColor(darkBlue); //Black colored text
		g.drawString("Survival high score = "+inSurvivalHighScore, scoreTextX, text2Y); //Constructs the text and draws it on panel

		//TODO draw button to start classsic game
		g.setColor(Color.GRAY); //Red colored rectangle
		g.fillRect(buttonX, button1Y, buttonWidth, buttonHeight); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(darkGreen); //Black colored text
		g.drawString("Play Classic Mode", buttonText1X, text1Y); //Constructs the text and draws it on panel

		
		
		//TODO draw button to start game mode 2
		g.setColor(Color.GRAY); //Red colored rectangle
		g.fillRect(buttonX, button2Y, buttonWidth, buttonHeight); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(darkBlue); //Black colored text
		g.drawString("Play Survival Mode", buttonText2X, text2Y); //Constructs the text and draws it on panel

		
		
	}



	public boolean clickedButton1(int x, int y){ //to start classic game
		if((x >= buttonX && x <= buttonX+buttonWidth) 
				&&
				(y >= button1Y && y <= button1Y+buttonHeight)
				)
		{
			return true;
		}
		return false;
	}

	public boolean clickedButton2(int x, int y){ //to start survival mode
		if((x >= buttonX && x <= buttonX+buttonWidth) 
				&&
				(y >= button2Y && y <= button2Y+buttonHeight)
				)
		{
			return true;
		}
		return false;
	}
}
