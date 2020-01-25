import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AlphaBeta {
	
	ArrayList<GameState> firstLevel;
	int depth;
	
	public int maxValue(int depth, GameState gameState, int alpha, int beta)
	{
		
		if(gameState.terminalTest() == true)
		{
			return gameState.getUtilityValue();
		}
		
		if(depth == 0)
		{
			gameState.setUtilityValue(gameState.calculateUtilityValue());
			gameState.setAlphaBetaValue(gameState.getUtilityValue());
			return gameState.getUtilityValue();
		}
		
		int v = Integer.MIN_VALUE;

		ArrayList<GameState> kLevel= gameState.calculateActions();
		if(this.depth == depth)
			firstLevel = kLevel;
		
		for(GameState state : kLevel)
		{
			v = Math.max(v, minValue(depth-1, state, alpha, beta));
			if(v>=beta) 
			{
				gameState.setAlphaBetaValue(v);
				if(this.depth == depth)
					firstLevel = kLevel;
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		gameState.setAlphaBetaValue(v);
		if(this.depth == depth)
			firstLevel = kLevel;
			
		return v;
	}
	
	public int minValue(int depth, GameState gameState, int alpha, int beta)
	{
		if(gameState.terminalTest() == true)
		{
			return gameState.getUtilityValue();
		}
		if(depth == 0)
		{
			gameState.setUtilityValue(gameState.calculateUtilityValue());
			gameState.setAlphaBetaValue(gameState.getUtilityValue());
			return gameState.getUtilityValue();
		}
		
		int v = Integer.MAX_VALUE;
		
	
		
		for(GameState state : gameState.calculateActions())
		{	
			v = Math.min(v, maxValue(depth-1, state,alpha,beta));
			if(v<=alpha)
			{
				gameState.setAlphaBetaValue(v);
				return v;
			}
			beta = Math.min(beta, v);
		}
		gameState.setAlphaBetaValue(v);
		return v;
	}
	
	public void runAlphaBeta(GameState initialState, int depth)
	{
		this.depth = depth; 
		int abReturnedValue =  maxValue(depth, initialState, Integer.MIN_VALUE,Integer.MAX_VALUE);
		//System.out.println(" ab returned = " + abReturnedValue);
		
		GameState nextGameState = null, gameOverState = null;
		OutputFileWriter writeFile = new OutputFileWriter();
		ArrayList<GameState> randomSelect = new ArrayList<GameState>();
		
		
		for(GameState g : firstLevel)
		{
			System.out.println("uv = " + g.getUtilityValue() + " ab value = " + g.getAlphaBetaValue() + "prevMoves= " + g.getPreviousMoves());

			if(abReturnedValue == g.getAlphaBetaValue() && g.terminalTest() == true)
			{
				gameOverState = g;
				break;
			}
			else if(abReturnedValue == g.getAlphaBetaValue())
			{
				randomSelect.add(g);
			}
		}
		
		
		if(gameOverState != null)
		{
			writeFile.writeMoveToFile(gameOverState.getPreviousMoves().trim());
		}
		else
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0, randomSelect.size());
			nextGameState = randomSelect.get(randomNum);
			writeFile.writeMoveToFile(nextGameState.getPreviousMoves().trim());
		}
		writeFile.closeFile();
	}	
}
