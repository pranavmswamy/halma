
public class Tile {
private int posX, posY;
private String jumpParentTile;
	
	public Tile(int x, int y, String parent)
	{
		posX = x;
		posY = y;
		jumpParentTile = new String(parent);
	}
	public int getX()
	{
		return posX;
	}
	
	public int getY()
	{
		return posY;
	}
	
	public String getPreviousMoves()
	{
		return this.jumpParentTile;
	}
	
	public void setX(int x)
	{
		this.posX = x;
	}
	
	public void setY(int y)
	{
		this.posY = y;
	}
}
