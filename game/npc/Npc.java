package game.npc;

import game.Game;
import game.graphics.Screen;
import game.physics.Hitbox;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Npc
{
	private final String name;
	private final boolean killable;
	private static BufferedImage spriteSheet;
	private final BufferedImage characterSprite;
	private Hitbox characterHitbox;
	private	static final String pathToSpriteSheet = "/home/davidq/Desktop/Game/src/game/images/spriteSheet.png";
	private static boolean spriteSheetSet = false;
	//the position of the hitbox and sprite
	private Point position;
	
	private void setSpriteSheet()
	{
		//try to set the spritesheet
		try
		{
			spriteSheet = ImageIO.read(new File(pathToSpriteSheet));
			spriteSheetSet = true;
		}
		catch(IOException e)
		{
			System.err.println("There was a problem getting the spriteSheet. Are you sure its in located in " + pathToSpriteSheet);
		}
	}
	
	//constructor
	public Npc(String name, Rectangle spriteSubImage, Point pos, boolean killable)
	{
		if(!spriteSheetSet)
		{
			setSpriteSheet();
		}
		this.name = name;
		this.characterSprite = spriteSheet.getSubimage(spriteSubImage.x, spriteSubImage.y, spriteSubImage.width, spriteSubImage.height);
		//set the hitbox for the sprite, from Point pos to another point that is the pos width/height  + the width/height of the sprite
		this.characterHitbox = new Hitbox(pos, new Point( pos.x+characterSprite.getWidth(), pos.y+characterSprite.getHeight() ) );
		this.position = pos;
		this.killable = killable;
	}
	
	public static void drawNpcs(Screen screen, BufferedImage sprite)
	{
		for(int x = 0; x < sprite.getWidth(); x++)
		{
			for(int y = 0; y < sprite.getHeight(); y++)
			{
				screen.pixels[x + (y * Game.width)] = sprite.getRGB(x, y);
			}
		}
	}
	
	//getters for name, character sprite and spritesheet
	public String getName()	{return this.name;}
	public BufferedImage getSpriteSheet() {return spriteSheet;}
	public BufferedImage characterSprite() {return this.characterSprite;}
	public boolean getKillable() {return this.killable;}
	public Point getPosition() {return this.position;}
	public static String getPathToSpriteSheet() {return pathToSpriteSheet;}
}