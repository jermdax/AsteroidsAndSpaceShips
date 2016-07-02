package game.graphics;

public class Sprite
{	
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	public static final int tileSize = 32;
	
	//set the number of asteroids in the spritesheet
	public static int numAsteroids = 4,numPlasma = 4;
	
	private static final int NUM_SPACE_SPRITES = 20, START_X = 0, START_Y = 3, END_X = 3, END_Y = 7;
	public static Sprite[] spaceSprites = new Sprite[NUM_SPACE_SPRITES];
	public static Sprite[] asteroids = new Sprite[numAsteroids];
	public static Sprite[] plasma = new Sprite[numPlasma];
	public static Sprite health = new Sprite(32, 1, 0, SpriteSheet.health);
	//Sprite(size, xOnSpriteSheet, yObSpriteSheet, SpriteSheet)
	//static block will be executed once to set the shits inside it
	static
	{
		//space Sprites 0-19(inclusive)
		for(int i = START_Y, counter = 0; i <= END_Y; i++)
		{
			for(int j = START_X; j <= END_X; j++)
			{
				spaceSprites[counter++] = new Sprite(tileSize, j, i, SpriteSheet.space);
			}
		}
		
		//the sprites for (ass)teroids
		asteroids[0] = new Sprite(tileSize, 0, 1, SpriteSheet.space);
		asteroids[1] = new Sprite(tileSize, 1, 1, SpriteSheet.space);
		asteroids[2] = new Sprite(tileSize, 0, 2, SpriteSheet.space);
		asteroids[3] = new Sprite(tileSize, 1, 2, SpriteSheet.space);
	
		plasma[0] = new Sprite(tileSize, 0, 0, SpriteSheet.plasma);
		plasma[1] = new Sprite(tileSize, 0, 1, SpriteSheet.plasma);
		plasma[2] = new Sprite(tileSize, 1, 0, SpriteSheet.plasma);
		plasma[3] = new Sprite(tileSize, 1, 1, SpriteSheet.plasma);
	
	}
	
	//the sprite for the players space ship
	public static Sprite ship0 = new Sprite(tileSize, 0, 0, SpriteSheet.space);
	public static Sprite ship45 = new Sprite(tileSize, 1, 0, SpriteSheet.space);
	public static Sprite ship90 = new Sprite(tileSize, 2, 0, SpriteSheet.space);
	public static Sprite ship135 = new Sprite(tileSize, 3, 0, SpriteSheet.space);
	
	public static Sprite ship180 = new Sprite(tileSize, 4, 0, SpriteSheet.space);
	public static Sprite ship225 = new Sprite(tileSize, 5, 0, SpriteSheet.space);
	public static Sprite ship270 = new Sprite(tileSize, 6, 0, SpriteSheet.space);
	public static Sprite ship315 = new Sprite(tileSize, 7, 0, SpriteSheet.space);
	
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x =x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
		
	}
	
	public Sprite (int size, int color)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(color);
	}
	
	public void setColour(int colour)
	{
		for(int i = 0;i < SIZE * SIZE;i++)
		{
			pixels[i] = colour;
		}
	}
	
			
	
	private void load()
	{
		for(int y = 0;y < SIZE;y++)
		{
			for(int x = 0;x < SIZE; x++)
			{
				pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y+this.y) * sheet.SIZE];
			}
		}
		
	}
	
	public static int getNumSpaceSprites()
	{
		return NUM_SPACE_SPRITES;
	}
}