import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore
{

	private int highScoreSurvival;
	private int highScoreClassic;
	private String fileName;


	public HighScore(){
		fileName = "data/highScores.txt";

		if(!readFromFile()){		
			// file is empty, start it with "0 0"
			highScoreSurvival = 0;
			highScoreClassic = 0;
			writeToFile();
		}

	}

	public void setHighScoreSurvival(int score){
		highScoreSurvival = score;
	}

	public void setHighScoreClassic(int score){
		highScoreClassic = score;
	}

	public int getHighScoreSurvival(){
		return highScoreSurvival;
	}

	public int getHighScoreClassic(){
		return highScoreClassic;
	}

	public void writeToFile(){
		//check if file exists

	

		File file = new File(fileName);

		FileWriter fr = null;
		BufferedWriter br = null;
		
		String dataForFile1 = ""+highScoreClassic+System.getProperty("line.separator");
		String dataForFile2 = ""+highScoreSurvival+System.getProperty("line.separator");
		
		
		
		try{
			fr = new FileWriter(file, false);
			br = new BufferedWriter(fr);
			br.write(dataForFile1);
			br.write(dataForFile2);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}




	}

	private boolean readFromFile(){//set variables by reading in from file
		//return true if successfully read
		//open file and read
		File file = new File(fileName);

		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		String str = null;
		try
		{
			str = br.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		highScoreClassic = Integer.parseInt(str);

		String str2 = null;
		try
		{
			str2 = br.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		highScoreSurvival = Integer.parseInt(str2);

		return true;
	}
}
