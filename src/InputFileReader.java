import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileReader {
	
	private String gameType;
	private String player;
	private double totalTimeLeft;
	private String[][] board;
	private int boardLength = 16;
	
	public String getGameType()
	{
		return gameType;
	}
	public String getPlayer()
	{
		return player;
	}
	
	public double getRemainingTime()
	{
		return totalTimeLeft;
	}
	
	public String[][] getBoard()
	{
			return board;
	}
	
	
	public void loadValues() throws Exception
	{
		
			String[][] tempBoard = new String[boardLength][boardLength];
			Scanner input = null;
			try {
				input = new Scanner(new File("input.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			ArrayList<String> fileContents = new ArrayList<String>();
			while (input.hasNextLine()) {
			    fileContents.add(input.nextLine());
			}
	
			gameType = fileContents.get(0);
			
			player = fileContents.get(1);
			
			totalTimeLeft = Double.parseDouble(fileContents.get(2));
			
			board = new String[boardLength][boardLength];
			
			for(int i=3, j=0;i<boardLength+3 && j<boardLength;i++,j++)
			{
				tempBoard[j] = fileContents.get(i).split("");
			}
			
			//-------------invert board (j,i)------------------
			for(int i=0;i<boardLength;i++)
				for(int j=0;j<boardLength;j++)
					board[j][i] = new String(tempBoard[i][j]);
			//--------------------------------
			
			/*System.out.println("**************INPUT FILE****************");
			System.out.println("gametype: "+ gameType);
			System.out.println("colorIPlay = " + player);
			System.out.println("remainingTime = " + totalTimeLeft);
			
			for(int i=0;i<boardLength;i++)
			{
				for(int j=0;j<boardLength;j++)
				{
					System.out.print(board[i][j] + ",  ");
				}
				System.out.println();
			}
			System.out.println("**********END OF INPUT FILE************");*/
		}
}
