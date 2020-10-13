import java.util.ArrayList;

public class homework {
	//order actions according to uv for efficient ab pruning? (priority queue) CAN BE DONE
	
	
	InputFileReader readFile;
	ArrayList<GameState> gameStateBfsQ;
	
	public homework()
	{
		readFile = new InputFileReader();
		gameStateBfsQ = new ArrayList<GameState>();
	}
	
	public GameState readFileAndReturnInitState()
	{
		try {
			readFile.loadValues();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//****PRINT EQUIVALENT OF FAIL, or failed to parse i/p file.***
		}
		
		ArrayList<Pawn> blackList = new ArrayList<Pawn>();
		ArrayList<Pawn> whiteList = new ArrayList<Pawn>();
		String[][] initialBoard = readFile.getBoard();
		for(int i=0;i<initialBoard.length;i++)
			for(int j = 0;j<initialBoard.length;j++)
			{
				if(initialBoard[i][j].equals("W")) {
					whiteList.add(new Pawn(i,j));
				}
				else if(initialBoard[i][j].equals("B"))
				{
					blackList.add(new Pawn(i,j));
				}
			}
		GameState initialState = new GameState(readFile.getPlayer(), readFile.getBoard(),null,readFile.getPlayer(),blackList,whiteList,new String(""));
		return initialState;
	}

	public static void main(String args[])
	{
		//long startTime = System.nanoTime();
		homework hw2 = new homework();
		GameState initialState = hw2.readFileAndReturnInitState();
		AlphaBeta ab = new AlphaBeta();
		
		if(initialState.getPlayer().equals("BLACK") && initialState.pawnsPresentInBlackHomeCamp())
		{
			ab.runAlphaBeta(initialState, 1);
		}
		else if(initialState.getPlayer().equals("WHITE") && initialState.pawnsPresentInWhiteHomeCamp())
		{
			ab.runAlphaBeta(initialState, 1);
		}
		else if(hw2.readFile.getRemainingTime() > 25)
		{
			ab.runAlphaBeta(initialState,1);
		}
		else
		{
			ab.runAlphaBeta(initialState,1);
		}
		
		//System.out.println("Time Taken = " + (System.nanoTime() - startTime)/1000000000.0 + "s");
	}
}