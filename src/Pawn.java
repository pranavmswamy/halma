
public class Pawn {

	private int posX, posY, sumXY;
	private boolean inBlackHomeCamp, inWhiteHomeCamp;
	
	public Pawn(int x, int y)
	{
		posX = x;
		posY = y;
		sumXY = x+y;
		inBlackHomeCamp = this.computeInBlackHomeCamp();
		inWhiteHomeCamp = this.computeInWhiteHomeCamp();
	}
	public int getX()
	{
		return posX;
	}
	
	public int getSumXY()
	{
		return sumXY;
	}
	
	public int getY()
	{
		return posY;
	}
	
	public void setX(int x)
	{
		this.posX = x;
	}
	
	public void setY(int y)
	{
		this.posY = y;
	}
	
	public boolean inWarZone()
	{
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(posX == i && posY == j)
						return false;
			}
		}
		
		for( int j=0; j<4;j++)
			if(posX == 2 && posY == j)
				return false;
			
		for( int j=0; j<3;j++)
			if(posX == 3 && posY == j)
				return false;
		
		for( int j=0; j<2;j++)
			if(posX == 4 && posY == j)
				return false;
		
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(posX == i && posY ==j)
						return false;
			}
		}
		for( int j=12; j<16;j++)
			if(posX == 13 && posY == j)
				return false;
				
		for( int j=13; j<16;j++)
			if(posX == 12 && posY == j)
				return false;			
		
		for( int j=14; j<16;j++)
			if(posX == 11 && posY == j)
				return false;
		
		
		return true;
	}
	
	private boolean computeInBlackHomeCamp()
	{
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 5 ; j++)
			{
					if(posX == i && posY == j)
						return true;
			}
		}
		
		for( int j=0; j<4;j++)
			if(posX == 2 && posY == j)
				return true;
			
		for( int j=0; j<3;j++)
			if(posX == 3 && posY == j)
				return true;
		
		for( int j=0; j<2;j++)
			if(posX == 4 && posY == j)
				return true;
		
		return false;
	}
	
	private boolean computeInWhiteHomeCamp()
	{
		for(int i = 14; i < 16 ; i++)
		{
			for(int j = 11; j < 16 ; j++)
			{
					if(posX == i && posY ==j)
						return true;
			}
		}
		for( int j=12; j<16;j++)
			if(posX == 13 && posY == j)
				return true;
				
		for( int j=13; j<16;j++)
			if(posX == 12 && posY == j)
				return true;			
		
		for( int j=14; j<16;j++)
			if(posX == 11 && posY == j)
				return true;
		
		return false;
	}

	public boolean inBlackHomeCamp()
	{
		return inBlackHomeCamp;
	}
	
	public boolean inWhiteHomeCamp()
	{
		return inWhiteHomeCamp;
	}
}
