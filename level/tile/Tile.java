package level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class Tile {
	
	public int x,y;
	public Sprite sprite;
	public static Tile[] spaceTiles;
	
//	public static Tile ground = new TileSpace(Sprite.ground);
	
	//initialize the space tiles
	static
	{
		spaceTiles = new Tile[Sprite.getNumSpaceSprites()];
		
		for(int i = 0; i < Sprite.getNumSpaceSprites(); i++)
		{
			spaceTiles[i] = new TileSpace(Sprite.spaceSprites[i]);
		}
	}
	
	public static Tile ship0 = new TileShip(Sprite.ship0);
	public static Tile ship90 = new TileShip(Sprite.ship90);
	public static Tile ship180 = new TileShip(Sprite.ship180);
	public static Tile ship270 = new TileShip(Sprite.ship270);
	
	public static Tile ship45 = new TileShip(Sprite.ship45);
	public static Tile ship135 = new TileShip(Sprite.ship135);
	public static Tile ship225 = new TileShip(Sprite.ship225);
	public static Tile ship315 = new TileShip(Sprite.ship315);
	
	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen)
	{
		
	}
	public boolean solid()
	{
		return false;
	}
	
}
