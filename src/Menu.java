import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//Pops up everytime you die/lose
public class Menu
{
	int titleX = 200;
	int titleY = 175;
	
	int scoreTextX = 50;
	int buttonText1X = 908;
	int buttonText2X = 925;
	int text1Y = 300;
	int text2Y = 440;
	
	int buttonX = 895;
	int button1Y = 230;
	int button2Y = 370;
	int buttonHeight = 100;
	int buttonWidth = 460;
	
	
	
	
	
	

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
		g.drawString("Campaign high score = "+inClassicHighScore, scoreTextX, text1Y); //Constructs the text and draws it on panel
		Color darkBlue = new Color(0, 0, 153);
		g.setColor(darkBlue); //Black colored text
		g.drawString("Survival high score = "+inSurvivalHighScore, scoreTextX, text2Y); //Constructs the text and draws it on panel

		//TODO draw button to start classsic game
		g.setColor(Color.GRAY); //Red colored rectangle
		g.fillRect(buttonX, button1Y, buttonWidth, buttonHeight); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(darkGreen); //Black colored text
		g.drawString("Play Campaign Mode", buttonText1X, text1Y); //Constructs the text and draws it on panel

		
		
		//TODO draw button to start game mode 2
		g.setColor(Color.GRAY); //Red colored rectangle
		g.fillRect(buttonX, button2Y, buttonWidth, buttonHeight); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		g.setColor(darkBlue); //Black colored text
		g.drawString("Play Survival Mode", buttonText2X, text2Y); //Constructs the text and draws it on panel

		g.setColor(Color.BLACK); 
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
		//g.drawString("Play Survival Mode", buttonText2X, text2Y); //Constructs the text and draws it on panel
		g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
		
		g.setColor(Color.BLUE);
		g.drawString("Controls", 50, 550);
		g.drawLine(45, 552, 186, 552);
		g.drawString("Right Handed - W, A, S, D to Move", 50, 600);
		g.drawString("Left Handed - Arrow Keys to Move", 50, 640);
		g.drawString("Aim With Mouse", 50, 680);
		g.drawString("Press Space Bar or Click Mouse to Fire", 50, 720);
		g.drawString("Press P to Pause", 50, 760);
		g.setColor(Color.BLACK);
		g.drawString("Instructions", 700, 550);
		g.drawLine(695, 552, 877, 552);
		g.setColor(darkGreen);
		g.drawString("Campaign Mode: Destroy all tanks to procceed", 700, 590);
		g.drawString("to next level. Beat all 12 levels to win the game.", 700, 630);
		
		g.setColor(darkBlue); 
		g.drawString("Survival Mode: Try to last as long as you can.", 700, 690);
		g.drawString("New tanks emerge each time you destroy a tank.", 700, 730);
	
	
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
