import java.util.ArrayList;

public class GameState {

	private String[][] board;
	//private long depth;
	private static int depthLimit;
	private int utilityValue;
	private int alphaBetaValue;
	private String player;
	//private ArrayList<GameState> actions;
	private GameState parent;
	private ArrayList<Pawn> black, white;
	private boolean[][] visited;
	private ArrayList<Tile> jumpQueue = new ArrayList<Tile>();
	private String previousMoves;
	public static long totalGameNodes;
	private String rootPlayer;
	

	public GameState(String rootPlayer, String[][] board, GameState parent, String player, ArrayList<Pawn> black, ArrayList<Pawn> white, String nextMove)
	{
		this.board = board;
		this.player = player;
		this.rootPlayer = rootPlayer;
		this.parent = parent;
		this.black = black;
		previousMoves =  new String(nextMove);
		this.white = white;
		visited = new boolean[16][16];
		//terminalTest();
	}
	
	public String getRootPlayer()
	{
		return this.rootPlayer;
	}
	
	public GameState getParent()
	{
		return parent;
	}
	
	public String getPreviousMoves()
	{
		return previousMoves;
	}
	
	public void setDepthLimit(int maxDepth)
	{
		GameState.depthLimit = maxDepth;
	}
	
	public int getAlphaBetaValue()
	{
		return this.alphaBetaValue;
	}
	
	public int getDepthLimit()
	{
		return GameState.depthLimit;
	}
	
	public int getUtilityValue()
	{
		return utilityValue;
	}
	
	public void setAlphaBetaValue(int ab)
	{
		this.alphaBetaValue = ab;
	}
	
	public String getPlayer()
	{
		return player;
	}

	public boolean whitePawnsPresentInWarZone()
	{
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(!board[i][j].equals("W"))
						return true;
			}
		}
		
		for( int j=0; j<4;j++)
			if(!board[2][j].equals("W"))
				return true;
			
		for( int j=0; j<3;j++)
			if(!board[3][j].equals("W"))
				return true;
		
		for( int j=0; j<2;j++)
			if(!board[4][j].equals("W"))
				return true;
		
		return false;
	}
	
	public boolean blackPawnsPresentInWarZone()
	{
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(!board[i][j].equals("B"))
						return true;
			}
		}
		for( int j=12; j<16;j++)
			if(!board[13][j].equals("B"))
				return true;
				
		for( int j=13; j<16;j++)
			if(!board[12][j].equals("B"))
				return true;			
		
		for( int j=14; j<16;j++)
			if(!board[11][j].equals("B"))
				return true;
		
		return false;
	}
	
	public boolean pawnsPresentInWhiteHomeCamp()
	{
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(board[i][j].equals("W"))
						return true;
			}
		}
		for( int j=12; j<16;j++)
			if(board[13][j].equals("W"))
				return true;
				
		for( int j=13; j<16;j++)
			if(board[12][j].equals("W"))
				return true;			
		
		for( int j=14; j<16;j++)
			if(board[11][j].equals("W"))
				return true;
		
		return false;
	}
	
	public int countWhiteHomeCampPieces()
	{
		int whiteCount = 0;
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(board[i][j].equals("W"))
						whiteCount++;
			}
		}
		for( int j=12; j<16;j++)
			if(board[13][j].equals("W"))
				whiteCount++;
				
		for( int j=13; j<16;j++)
			if(board[12][j].equals("W"))
				whiteCount++;			
		
		for( int j=14; j<16;j++)
			if(board[11][j].equals("W"))
				whiteCount++;
		
		return whiteCount;
	}
	
	public boolean pawnsPresentInBlackHomeCamp()
	{
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(board[i][j].equals("B"))
						return true;
			}
		}
		
		for( int j=0; j<4;j++)
			if(board[2][j].equals("B"))
				return true;
			
		for( int j=0; j<3;j++)
			if(board[3][j].equals("B"))
				return true;
		
		for( int j=0; j<2;j++)
			if(board[4][j].equals("B"))
				return true;
		
		return false;
	}
	
	public int countBlackHomeCampPieces()
	{
		int blackCount = 0;
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(board[i][j].equals("B"))
						blackCount++;
			}
		}
		
		for( int j=0; j<4;j++)
			if(board[2][j].equals("B"))
				blackCount++;
			
		for( int j=0; j<3;j++)
			if(board[3][j].equals("B"))
				blackCount++;
		
		for( int j=0; j<2;j++)
			if(board[4][j].equals("B"))
				blackCount++;
		
		return blackCount;
	}
	
	public boolean blackPawnsPresentInOpponentCamp()
	{
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(board[i][j].equals("B"))
						return true;
			}
		}
		for( int j=12; j<16;j++)
			if(board[13][j].equals("B"))
				return true;
				
		for( int j=13; j<16;j++)
			if(board[12][j].equals("B"))
				return true;			
		
		for( int j=14; j<16;j++)
			if(board[11][j].equals("B"))
				return true;
		
		return false;
	}
	
	public boolean whitePawnsPresentInOpponentCamp()
	{
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(board[i][j].equals("W"))
						return true;
			}
		}
		
		for( int j=0; j<4;j++)
			if(board[2][j].equals("W"))
				return true;
			
		for( int j=0; j<3;j++)
			if(board[3][j].equals("W"))
				return true;
		
		for( int j=0; j<2;j++)
			if(board[4][j].equals("W"))
				return true;
		
		return false;
	}
	
	public boolean terminalTest()
	{
		
		boolean terminalTest = false;
		int topLeftBlacks = 0, topLeftWhites = 0, bottomRightBlacks = 0, bottomRightWhites = 0;
		
		//calculatetopleft
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(board[i][j].equals("."))
						terminalTest = false;
					else if(board[i][j].equals("W"))
						topLeftWhites++;
					else
						topLeftBlacks++;
			}
		}
		for( int j=0; j<4;j++)
			if(board[2][j].equals("."))
				terminalTest = false;
			else if(board[2][j].equals("W"))
				topLeftWhites++;
			else
				topLeftBlacks++;
		
		for( int j=0; j<3;j++)
			if(board[3][j].equals("."))
				terminalTest = false;
			else if(board[3][j].equals("W"))
				topLeftWhites++;
			else
				topLeftBlacks++;
		
		for( int j=0; j<2;j++)
			if(board[4][j].equals("."))
				terminalTest = false;
			else if(board[4][j].equals("W"))
				topLeftWhites++;
			else
				topLeftBlacks++;
		//endcalctopleft
		
		//calculatebottomright
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(board[i][j].equals("."))
						terminalTest = false;
					else if(board[i][j].equals("W"))
						bottomRightWhites++;
					else
						bottomRightBlacks++;
			}
		}
		for( int j=12; j<16;j++)
			if(board[13][j].equals("."))
				terminalTest = false;
			else if(board[13][j].equals("W"))
				bottomRightWhites++;
			else
				bottomRightBlacks++;
		
		for( int j=13; j<16;j++)
			if(board[12][j].equals("."))
				terminalTest = false;
			else if(board[12][j].equals("W"))
				bottomRightWhites++;
			else
				bottomRightBlacks++;
		
		for( int j=14; j<16;j++)
			if(board[11][j].equals("."))
				terminalTest = false;
			else if(board[11][j].equals("W"))
				bottomRightWhites++;
			else
				bottomRightBlacks++;
		//endcalctopright
		
		if(topLeftWhites + topLeftBlacks == 19 && topLeftWhites > 0)
		{
			if(this.rootPlayer.equals("BLACK"))
			{
				this.utilityValue = -1000000;
				this.alphaBetaValue = -1000000;
			}
			else
			{
				this.utilityValue = 1000000;
				this.alphaBetaValue = 1000000;
			}
			return true;
		}
		else if(bottomRightWhites + bottomRightBlacks == 19 && bottomRightBlacks > 0)
		{
			if(this.rootPlayer.equals("WHITE"))
			{
				this.utilityValue = -1000000;
				this.alphaBetaValue = -1000000;
			}
			else
			{
				this.utilityValue = 1000000;
				this.alphaBetaValue = 1000000;
			}
			return true;
		}		
		return terminalTest;
		//end bottomright
	}
	
	public ArrayList<GameState> calculateActions()
	{
		ArrayList<GameState> actions = new ArrayList<GameState>();
		
		if(getPlayer().equals("BLACK"))
		{
			//--------trying remove from home camp first-------
			if(pawnsPresentInBlackHomeCamp())
			{
				int gotMoves = 0;
				for(Pawn p: getBlacksList())
				{
					if(p.inBlackHomeCamp())
					{
						markAllVisitedFalse();
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y,new String("")));
						getVisitedArray()[x][y] = true;
						initBehindTopLeftPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
								gotMoves++;
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							gotMoves++;
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
				}
				if(gotMoves == 0)
				{
					//normal code again.
					for(Pawn p: getBlacksList())
					{
						markAllVisitedFalse();//mark all i,j as false;visited.
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y,new String("")));
						getVisitedArray()[x][y] = true;
						initBehindTopLeftPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
				}
			}
				else
				{
					for(Pawn p: getBlacksList())
					{
						markAllVisitedFalse();//mark all i,j as false;visited.
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y,new String("")));
						getVisitedArray()[x][y] = true;
						initBehindTopLeftPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
				}
				
				//----------------------------------------
				
			//}	
		}
		else
		{
			//--------------home camp first part------------
			if(pawnsPresentInWhiteHomeCamp())
			{
				int gotMoves = 0;
				for(Pawn p : getWhitesList())
				{
					if(p.inWhiteHomeCamp())
					{
						markAllVisitedFalse(); //mark all i,j as false;visited.
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y, new String("")));
						getVisitedArray()[x][y] = true;
						initBehindBottomRightPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
							gotMoves++;
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							gotMoves++;
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
				}
				if(gotMoves == 0)
				{
					//normal - all pawns checking code.
					for(Pawn p: getWhitesList())
					{
						markAllVisitedFalse(); //mark all i,j as false;visited.
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y, new String("")));
						getVisitedArray()[x][y] = true;
						initBehindBottomRightPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
			
				}
			}
			
				else
				{
					for(Pawn p: getWhitesList())
					{
						markAllVisitedFalse(); //mark all i,j as false;visited.
						int x = p.getX();
						int y = p.getY();
						getJumpQ().add(new Tile(x,y, new String("")));
						getVisitedArray()[x][y] = true;
						initBehindBottomRightPlacesTrue(x, y);
						//collect all 8-adj positions in an arraylist, add it to bfsQ and actions
						ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
						newAdj8Moves = checkAdjEightPlaces(x, y);
						for(GameState state : newAdj8Moves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
						//collect all jump nodes and add it to bfsq and actions
						ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
						newJumpMoves = performJumps(x, y);
						for(GameState state : newJumpMoves)
						{
							//gameStateBfsQ.add(state);
							actions.add(state);
						}
					}
				}
		}
		return actions;
	}
	
	public void setUtilityValue(int d)
	{
		this.utilityValue = d;
	}
	
	public String[][] getBoard()
	{
		return this.board;
	}
	
	public ArrayList<Pawn> getBlacksList()
	{
		return black;
	}
	
	public ArrayList<Pawn> getWhitesList()
	{
		return white;
	}
	
	public boolean[][] getVisitedArray()
	{
		return visited;
	}
	
	public ArrayList<Tile> getJumpQ()
	{
		return jumpQueue;
	}
	
	public void initAllBottomRightPlacesFalse()
	{
		for(int i = 14; i < 16 ; i++)
			for(int j = 11; j < 16 ; j++)
						visited[i][j] = false;
		
		for( int j=12; j<16;j++)
				visited[13][j] = false;
		
		for( int j=13; j<16;j++)
			visited[12][j] = false;
		
		for( int j=14; j<16;j++)
				visited[11][j] = false;
	}
	
	public void initBehindBottomRightPlacesTrue(int x, int y)
	{
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(x+y <= i+j)
						visited[i][j] = true;
			}
		}
		for( int j=12; j<16;j++)
			if(x+y <= 13+j)
				visited[13][j] = true;
		
		for( int j=13; j<16;j++)
			if(x+y <= 12+j)
				visited[12][j] = true;
		
		for( int j=14; j<16;j++)
			if(x+y <= 11+j)
				visited[11][j] = true;
		
	}
	
	public void initAllTopLeftPlacesFalse()
	{
		for(int i = 0; i < 2 ; i++)
			for(int j = 0; j < 5 ; j++)
						visited[i][j] = false;

		for( int j=0; j<4;j++)
				visited[2][j] = false;
		
		for( int j=0; j<3;j++)
				visited[3][j] = false;
		
		for( int j=0; j<2;j++)
				visited[4][j] = false;
	}
	
	public void initBehindTopLeftPlacesTrue(int x, int y)
	{
		  
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(i+j <= x+y)
						visited[i][j] = true;
			}
		}
		for( int j=0; j<4;j++)
			if(2+j <= x+y)
				visited[2][j] = true;
		
		for( int j=0; j<3;j++)
			if(3+j <= x+y)
				visited[3][j] = true;
		
		for( int j=0; j<2;j++)
			if(4+j <= x+y)
				visited[4][j] = true;
	}

	public void printBoard()
	{
		for(int i=0;i<16;i++)
		{
			for(int j=0;j<16;j++)
			{
				System.out.print(this.board[i][j] + ",  ");
			}
			System.out.println();
		}
	}

	public void markAllVisitedFalse()
	{
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++)
				this.visited[i][j] = false;
	}

	public boolean isValidPosition(int x, int y)
	{
		if(x<0 || y<0 || x>15 || y>15) return false;
		if(board[x][y].equals(".") == false) return false;
		return true;
	}
	
	public boolean containsPawn(int x, int y)
	{
		if(board[x][y].equals("."))
		{
			return false;
		}
		else return true;
	}
	
	public boolean inCampBlack(int x, int y)
	{
		for(int i = 0; i < 2 ; i++)
			for(int j = 0; j < 5 ; j++)
					if(x == i && y == j)
						return true;
		
		for(int j=0; j<4;j++)
			if(x==2 && y==j)
				return true;
		
		for( int j=0; j<3;j++)
			if(x==3 && y==j)
				return true;
		
		for( int j=0; j<2;j++)
			if(x==4 && y==j)
				return true;
		
		return false;
	}
	
	public boolean inCampWhite(int x, int y)
	{
		for(int i = 14; i < 16 ; i++)
			for(int j = 11; j < 16 ; j++)
					if(x==i && y==j)
						return true;
		
		for( int j=12; j<16;j++)
			if(x==13 && y==j)
				return true;
		
		for( int j=13; j<16;j++)
			if(x==12 && y==j)
				return true;
		
		for( int j=14; j<16;j++)
			if(x==11 && y==j)
				return true;
		
		return false;
		
	}
	
	public ArrayList<GameState> checkAdjEightPlaces(int i, int j)
	{
		ArrayList<GameState> newAdj8Moves = new ArrayList<GameState>();
		if(this.player.equals("BLACK"))
		{
			if(inCampWhite(i,j))
			{
				if(isValidPosition(i-1,j-1) && containsPawn(i-1,j-1) == false && !visited[i-1][j-1] && inCampWhite(i-1,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j-1,"E " + i + "," + j + " " + (i-1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i-1,j) && containsPawn(i-1,j) == false && !visited[i-1][j] && inCampWhite(i-1,j))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j,"E " + i + "," + j + " " + (i-1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i-1,j+1) && containsPawn(i-1,j+1) == false && !visited[i-1][j+1] && inCampWhite(i-1,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j+1,"E " + i + "," + j + " " + (i-1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i,j+1) && containsPawn(i,j+1) == false && !visited[i][j+1] && inCampWhite(i,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j+1,"E " + i + "," + j + " " + (i) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j+1) && containsPawn(i+1,j+1) == false && !visited[i+1][j+1] && inCampWhite(i+1,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j+1,"E " + i + "," + j + " " + (i+1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j) && containsPawn(i+1,j) == false && !visited[i+1][j] && inCampWhite(i+1,j))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j,"E " + i + "," + j + " " + (i+1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i+1,j-1) && containsPawn(i+1,j-1) == false && !visited[i+1][j-1] && inCampWhite(i+1,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j-1,"E " + i + "," + j + " " + (i+1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i,j-1) && containsPawn(i,j-1) == false && !visited[i][j-1] && inCampWhite(i,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j-1,"E " + i + "," + j + " " + (i) + "," + (j-1) + "\n"));
				}
			
			}
			else
			{
				if(isValidPosition(i-1,j-1) && containsPawn(i-1,j-1) == false && !visited[i-1][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j-1,"E " + i + "," + j + " " + (i-1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i-1,j) && containsPawn(i-1,j) == false && !visited[i-1][j])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j,"E " + i + "," + j + " " + (i-1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i-1,j+1) && containsPawn(i-1,j+1) == false && !visited[i-1][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j+1,"E " + i + "," + j + " " + (i-1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i,j+1) && containsPawn(i,j+1) == false && !visited[i][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j+1,"E " + i + "," + j + " " + (i) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j+1) && containsPawn(i+1,j+1) == false && !visited[i+1][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j+1,"E " + i + "," + j + " " + (i+1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j) && containsPawn(i+1,j) == false && !visited[i+1][j])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j,"E " + i + "," + j + " " + (i+1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i+1,j-1) && containsPawn(i+1,j-1) == false && !visited[i+1][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j-1,"E " + i + "," + j + " " + (i+1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i,j-1) && containsPawn(i,j-1) == false && !visited[i][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j-1,"E " + i + "," + j + " " + (i) + "," + (j-1) + "\n"));
				}
			
			}
		}
		else
		{
			if(inCampBlack(i,j))
			{
				if(isValidPosition(i-1,j-1) && containsPawn(i-1,j-1) == false && !visited[i-1][j-1] && inCampBlack(i-1,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j-1,"E " + i + "," + j + " " + (i-1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i-1,j) && containsPawn(i-1,j) == false && !visited[i-1][j] && inCampBlack(i-1,j))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j,"E " + i + "," + j + " " + (i-1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i-1,j+1) && containsPawn(i-1,j+1) == false && !visited[i-1][j+1] && inCampBlack(i-1,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j+1,"E " + i + "," + j + " " + (i-1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i,j+1) && containsPawn(i,j+1) == false && !visited[i][j+1] && inCampBlack(i,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j+1,"E " + i + "," + j + " " + (i) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j+1) && containsPawn(i+1,j+1) == false && !visited[i+1][j+1] && inCampBlack(i+1,j+1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j+1,"E " + i + "," + j + " " + (i+1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i+1,j) && containsPawn(i+1,j) == false && !visited[i+1][j] && inCampBlack(i+1,j))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j,"E " + i + "," + j + " " + (i+1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i+1,j-1) && containsPawn(i+1,j-1) == false && !visited[i+1][j-1] && inCampBlack(i+1,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j-1,"E " + i + "," + j + " " + (i+1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i,j-1) && containsPawn(i,j-1) == false && !visited[i][j-1] && inCampBlack(i,j-1))
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j-1,"E " + i + "," + j + " " + (i) + "," + (j-1) + "\n"));
				}
			
			}
			else
			{
				if(isValidPosition(i-1,j-1) && containsPawn(i-1,j-1) == false && !visited[i-1][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j-1,"E " + i + "," + j + " " + (i-1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i-1,j) && containsPawn(i-1,j) == false && !visited[i-1][j])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j,"E " + i + "," + j + " " + (i-1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i-1,j+1) && containsPawn(i-1,j+1) == false && !visited[i-1][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i-1,j+1,"E " + i + "," + j + " " + (i-1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i,j+1) && containsPawn(i,j+1) == false && !visited[i][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j+1,"E " + i + "," + j + " " + (i) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j+1) && containsPawn(i+1,j+1) == false && !visited[i+1][j+1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j+1,"E " + i + "," + j + " " + (i+1) + "," + (j+1) + "\n"));
				}
				if(isValidPosition(i+1,j) && containsPawn(i+1,j) == false && !visited[i+1][j])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j,"E " + i + "," + j + " " + (i+1) + "," + (j) + "\n"));
				}
				if(isValidPosition(i+1,j-1) && containsPawn(i+1,j-1) == false && !visited[i+1][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i+1,j-1,"E " + i + "," + j + " " + (i+1) + "," + (j-1) + "\n"));
				}
				if(isValidPosition(i,j-1) && containsPawn(i,j-1) == false && !visited[i][j-1])
				{
					newAdj8Moves.add(addLegalMove(i,j,i,j-1,"E " + i + "," + j + " " + (i) + "," + (j-1) + "\n"));
				}
			}
		}
		return newAdj8Moves;
	}
	
	public String[][] getBoardCopy(String board[][])
	{
		String[][] childBoard = new String[16][16];
		for(int i = 0;i<childBoard.length; i++)
			for(int j=0;j<childBoard.length; j++)
				childBoard[i][j] = new String(board[i][j]);
		return childBoard;
	}
	
	public GameState addLegalMove(int startX, int startY, int endX, int endY, String previousMoves)
	{
			String childPlayer;
			
			if(player.equals("BLACK"))
				childPlayer = new String("WHITE");
			else
				childPlayer = new String("BLACK");
			
			ArrayList<Pawn> blackChild = new ArrayList<Pawn>();
			ArrayList<Pawn> whiteChild = new ArrayList<Pawn>();
			String[][] childBoard = getBoardCopy(board);
			String temp = new String(childBoard[startX][startY]);
			childBoard[startX][startY] = new String(childBoard[endX][endY]);
			childBoard[endX][endY] = new String(temp);
			
			GameState newMove;
			//System.out.println("Start X,Y = " + startX + "," + startY);
			if(this.player.equals("BLACK"))
			{
				
				for(Pawn p: black)
				{
					if(p.getX() == startX && p.getY() == startY)
					{
						blackChild.add(new Pawn(endX,endY));
					}
					else
					{
							blackChild.add(p);
					}
				}
				
				
				newMove = new GameState(this.rootPlayer,childBoard, this, childPlayer, blackChild,white,previousMoves);
			}
			else
			{
				
				for(Pawn p: white)
				{
					if(p.getX() == startX && p.getY() == startY)
					{
						whiteChild.add(new Pawn(endX,endY));
					}
					else
					{
						whiteChild.add(p);
					}
				}
				
				
				newMove = new GameState(this.rootPlayer,childBoard, this, childPlayer, black,whiteChild,previousMoves);
			}
			visited[endX][endY] = true;
			return newMove;
	}
	
	public ArrayList<GameState> performJumps(int x, int y)
	{
		ArrayList<GameState> newJumpMoves = new ArrayList<GameState>();
		
		while(jumpQueue.isEmpty() != true)
		{
			Tile t = jumpQueue.remove(0);
			int i = t.getX(), j = t.getY();
			//----------------------trying camp oppnt----
			if(this.player.equals("BLACK"))
			{
				if(inCampWhite(x,y))
				{
					if(isValidPosition(i-2,j-2) && containsPawn(i-1,j-1) && !visited[i-2][j-2])
					{
						if(inCampWhite(i-2,j-2))
							newJumpMoves.add(addLegalMove(x,y,i-2,j-2,t.getPreviousMoves() + "J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n")); 
						jumpQueue.add(new Tile(i-2,j-2,t.getPreviousMoves()+ "J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n"));
						visited[i-2][j-2] = true;
					}
					if(isValidPosition(i-2,j) && containsPawn(i-1,j) && !visited[i-2][j])
					{
						if(inCampWhite(i-2,j))
							newJumpMoves.add(addLegalMove(x,y,i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						visited[i-2][j] = true;
					}
					if(isValidPosition(i-2,j+2) && containsPawn(i-1,j+1) && !visited[i-2][j+2])
					{
						if(inCampWhite(i-2,j+2))
							newJumpMoves.add(addLegalMove(x,y,i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						visited[i-2][j+2] = true;
					}
					if(isValidPosition(i,j+2) && containsPawn(i,j+1) && !visited[i][j+2])
					{
						if(inCampWhite(i,j+2))
							newJumpMoves.add(addLegalMove(x,y,i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						visited[i][j+2] = true;
					}
					if(isValidPosition(i+2,j+2) && containsPawn(i+1,j+1) && !visited[i+2][j+2])
					{
						if(inCampWhite(i+2,j+2))
							newJumpMoves.add(addLegalMove(x,y,i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						visited[i+2][j+2] = true;
					}
					if(isValidPosition(i+2,j) && containsPawn(i+1,j) && !visited[i+2][j])
					{
						if(inCampWhite(i+2,j))
							newJumpMoves.add(addLegalMove(x,y,i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						visited[i+2][j] = true;
					}
					if(isValidPosition(i+2,j-2) && containsPawn(i+1,j-1) && !visited[i+2][j-2])
					{
						if(inCampWhite(i+2,j-2))
							newJumpMoves.add(addLegalMove(x,y,i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						visited[i+2][j-2] = true;
					}
					if(isValidPosition(i,j-2) && containsPawn(i, j-1) && !visited[i][j-2])
					{
						if(inCampWhite(i,j-2))
							newJumpMoves.add(addLegalMove(x,y,i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + i + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
						visited[i][j-2] = true;
					}

				}
				else
				{
					if(isValidPosition(i-2,j-2) && containsPawn(i-1,j-1) && !visited[i-2][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n")); 
						jumpQueue.add(new Tile(i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n"));
					}
					if(isValidPosition(i-2,j) && containsPawn(i-1,j) && !visited[i-2][j])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
					}
					if(isValidPosition(i-2,j+2) && containsPawn(i-1,j+1) && !visited[i-2][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i,j+2) && containsPawn(i,j+1) && !visited[i][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i+2,j+2) && containsPawn(i+1,j+1) && !visited[i+2][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i+2,j) && containsPawn(i+1,j) && !visited[i+2][j])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
					}
					if(isValidPosition(i+2,j-2) && containsPawn(i+1,j-1) && !visited[i+2][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
					}
					if(isValidPosition(i,j-2) && containsPawn(i, j-1) && !visited[i][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
					}

				} 
			}
			else
			{
				if(inCampBlack(x,y))
				{
					if(isValidPosition(i-2,j-2) && containsPawn(i-1,j-1) && !visited[i-2][j-2])
					{
						if(inCampBlack(i-2,j-2))
							newJumpMoves.add(addLegalMove(x,y,i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n")); 
						jumpQueue.add(new Tile(i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n"));
						visited[i-2][j-2] = true;
						
					}
					if(isValidPosition(i-2,j) && containsPawn(i-1,j) && !visited[i-2][j])
					{
						if(inCampBlack(i-2,j))
							newJumpMoves.add(addLegalMove(x,y,i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						visited[i-2][j] = true;
					}
					if(isValidPosition(i-2,j+2) && containsPawn(i-1,j+1) && !visited[i-2][j+2])
					{
						if(inCampBlack(i-2,j+2))
							newJumpMoves.add(addLegalMove(x,y,i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						visited[i-2][j+2] = true;
					}
					if(isValidPosition(i,j+2) && containsPawn(i,j+1) && !visited[i][j+2])
					{
						if(inCampBlack(i,j+2))
							newJumpMoves.add(addLegalMove(x,y,i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						visited[i][j+2] = true;
					}
					if(isValidPosition(i+2,j+2) && containsPawn(i+1,j+1) && !visited[i+2][j+2])
					{
						if(inCampBlack(i+2,j+2))
							newJumpMoves.add(addLegalMove(x,y,i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						visited[i+2][j+2] = true;
					}
					if(isValidPosition(i+2,j) && containsPawn(i+1,j) && !visited[i+2][j])
					{
						if(inCampBlack(i+2,j))
							newJumpMoves.add(addLegalMove(x,y,i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						visited[i+2][j] = true;
					}
					if(isValidPosition(i+2,j-2) && containsPawn(i+1,j-1) && !visited[i+2][j-2])
					{
						if(inCampBlack(i+2,j-2))
							newJumpMoves.add(addLegalMove(x,y,i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						visited[i+2][j-2] = true;
					}
					if(isValidPosition(i,j-2) && containsPawn(i, j-1) && !visited[i][j-2])
					{
						if(inCampBlack(i,j-2))
							newJumpMoves.add(addLegalMove(x,y,i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
						visited[i][j-2] = true;
					}

				}
				else
				{
					if(isValidPosition(i-2,j-2) && containsPawn(i-1,j-1) && !visited[i-2][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n")); 
						jumpQueue.add(new Tile(i-2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j-2) + "\n"));
					}
					if(isValidPosition(i-2,j) && containsPawn(i-1,j) && !visited[i-2][j])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j,t.getPreviousMoves() + "J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i-2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j) + "\n"));
					}
					if(isValidPosition(i-2,j+2) && containsPawn(i-1,j+1) && !visited[i-2][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i-2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i-2) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i,j+2) && containsPawn(i,j+1) && !visited[i][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i+2,j+2) && containsPawn(i+1,j+1) && !visited[i+2][j+2])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
						jumpQueue.add(new Tile(i+2,j+2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j+2) + "\n"));
					}
					if(isValidPosition(i+2,j) && containsPawn(i+1,j) && !visited[i+2][j])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
						jumpQueue.add(new Tile(i+2,j,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j) + "\n"));
					}
					if(isValidPosition(i+2,j-2) && containsPawn(i+1,j-1) && !visited[i+2][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i+2,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i+2) + "," + (j-2) + "\n"));
					}
					if(isValidPosition(i,j-2) && containsPawn(i, j-1) && !visited[i][j-2])
					{
						newJumpMoves.add(addLegalMove(x,y,i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
						jumpQueue.add(new Tile(i,j-2,t.getPreviousMoves()+"J " + i + "," + j + " " + (i) + "," + (j-2) + "\n"));
					}

				}
			}
			
			//----------------------
		}
		return newJumpMoves;
	}
	
	public double getEuclideanDistance(int startX, int startY, int endX, int endY)
	{
		return Math.pow(startX-endX,2)+Math.pow(startY-endY, 2);
	}
	
	public int calculateUtilityValue()
	{
		int utilityValue = 0;
		
		if(this.parent != null) //if it isn't root node
		{
			if(this.rootPlayer.equals("BLACK"))
			{
					
				//----------
				if(this.parent.pawnsPresentInBlackHomeCamp())
				{
					int parentBlackHomeCampCount = this.parent.countBlackHomeCampPieces();
					int currBlackHomeCampCount = this.countBlackHomeCampPieces();
					if(parentBlackHomeCampCount > currBlackHomeCampCount)
					{
						return 2000;
					}
					
				}
				
				//----------
				for(Pawn p : this.black)
					{
						
						for(int i = 14; i < 16 ; i++)
							for(int j = 11; j < 16 ; j++)
								if(this.board[i][j].equals(".") || this.board[i][j].equals("W"))
									utilityValue += getEuclideanDistance(p.getX(),p.getY(),i,j);
							
						for( int j=12; j<16;j++)
							if(this.board[13][j].equals(".") || this.board[13][j].equals("W"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),13,j);
								
						for( int j=13; j<16;j++)
							if(this.board[12][j].equals(".") || this.board[12][j].equals("W"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),12,j);			
						
						for( int j=14; j<16;j++)
							if(this.board[11][j].equals(".") || this.board[11][j].equals("W"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),11,j);
					}
				return -utilityValue;
			}
			else //if player is white
			{
				//----------
				if(this.parent.pawnsPresentInWhiteHomeCamp())
				{
					int parentWhiteHomeCampCount = this.parent.countWhiteHomeCampPieces();
					int currWhiteHomeCampCount = this.countWhiteHomeCampPieces();
					if(parentWhiteHomeCampCount > currWhiteHomeCampCount)
					{
						return 2000;
					}
				}
				//----------	
				
				
				for(Pawn p : this.white)
					{
						
						for(int i = 0; i < 2 ; i++)
							for(int j = 0; j < 5 ; j++)
								if(this.board[i][j].equals(".") || this.board[i][j].equals("B"))
									utilityValue += getEuclideanDistance(p.getX(),p.getY(),i,j);

						for( int j=0; j<4;j++)
							if(this.board[2][j].equals(".") || this.board[2][j].equals("B"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),2,j);
						
						for( int j=0; j<3;j++)
							if(this.board[3][j].equals(".") || this.board[3][j].equals("B"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),3,j);
						
						for( int j=0; j<2;j++)
							if(this.board[4][j].equals(".") || this.board[4][j].equals("B"))
								utilityValue += getEuclideanDistance(p.getX(),p.getY(),4,j);
						
					}
				return -utilityValue;
			}
		}
		else //if it is root node
		{
			return -1000000;
		}
	}
}
