import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {

	private int score;
	private int level;
	private boolean gameWon;

	public GameOver(int inScore, int inLevel, boolean inGameWon){

		score = inScore;
		level = inLevel;
		gameWon = inGameWon;

	}


	public void draw(Graphics g, ImageLibrary l)
	{

		g.drawImage(l.background, 0, 0, null);


		g.setColor(Color.BLACK); //Red colored rectangle
		g.fillRect(200, 100, 1000, 200); //Makes rectangle containing start button

		g.setFont(new Font("TimesRoman", Font.PLAIN, 200)); //Times New Roman font; size 200
		if(gameWon){
			g.setColor(Color.GREEN); //Black colored text
			g.drawString("You Won!", 230, 260); //Constructs the text and draws it on panel

			g.setColor(Color.BLACK); //Red colored rectangle
			g.fillRect(500, 400, 380, 100); //Makes rectangle containing start button
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
			g.setColor(Color.GREEN); //Black colored text
			g.drawString("Return to Menu", 525, 460); //Constructs the text and draws it on panel
			
			//
		}
		else{
			g.setColor(Color.RED); //Black colored text
			g.drawString("Game Over", 230, 260); //Constructs the text and draws it on panel
	
			g.setColor(Color.BLACK); //Red colored rectangle
			g.fillRect(500, 400, 380, 100); //Makes rectangle containing start button
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //Times New Roman font; size 200
			g.setColor(Color.RED); //Black colored text
			g.drawString("Return to Menu", 525, 460); //Constructs the text and draws it on panel
		}

	}


	public boolean clickedButton1(int x, int y){
		if((x >= 500 && x <= 880) 
				&&
				(y >= 400 && y <= 500)
				){
			return true;
		}
		return false;
	}
}
