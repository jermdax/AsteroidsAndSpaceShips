package level;

import java.util.Random;

import game.graphics.Screen;
import level.tile.Tile;

public class Level
{
	
	protected int width,height;
	protected int[] tiles;
	
	public Level(int width , int height)
	{
		this.width = width;
		this.height = height;
		tiles = new int [width *height]; 
		
		generateLevel();
	}

	public Level(String path)
	{
		loadLevel(path);
	}
	protected void generateLevel() {
	
		
	}
	
	private void loadLevel(String path)
	{
		
	}
	public void update()
	{
		
	}
	
	private void time()
	{
		
	}
	
	public void render( int xScroll, int yScroll, Screen screen )
	{	
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 5;
		int x1 = (xScroll + screen.getWidth() + 32) >> 5 ;
		int y0 = yScroll >> 5;
		int y1 = (yScroll+ screen.getHeight() + 32) >> 5 ;
		
		for(int y = y0; y < y1;y++)
		{
			for(int x = x0; x < x1;x++)
			{
				getSpaceTile(x&63,y&63).render(x, y, screen);
			}
		}
	}
	
	public Tile getSpaceTile(int x, int y)
	{		
		//if tiles[x+y * width] is an index in the spaceTiles array
		if(tiles[x + y * width] < Tile.spaceTiles.length)
			return Tile.spaceTiles[tiles[x + y * width]];
		else
			return Tile.spaceTiles[0];
	}
}
