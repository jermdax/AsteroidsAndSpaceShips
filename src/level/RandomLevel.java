package level;

import game.graphics.Sprite;

import java.util.Random;

import level.tile.Tile;

public class RandomLevel extends Level
{
	private static final Random random = new Random();
	//will get a random num between 0 and this
	private final int maxAsteroids = (tiles.length / 5) + 1;
	
	public RandomLevel(int width, int height) 
	{
		super(width, height);
	}
	
	protected void generateLevel()
	{
		for(int y = 0; y< height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				//set the randomness so theres more change of it being a lower (darker) space tile
				tiles[x +y * width] = random.nextInt(28)^0b11000;
			}
		}
	}
	
	protected void addAsteroids()
	{
		final int numAsteroids = random.nextInt(maxAsteroids);
		//do for how many asteroids will be placed
		for(int i = 0; i < numAsteroids; i++)
		{
			//get a random tile and set it to an asteroid
			final int randomTile = random.nextInt(tiles.length);
			final int asteroidType = random.nextInt(Sprite.numAsteroids);
			tiles[randomTile] = asteroidType;
		}
	}

}