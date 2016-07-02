package game.graphics;

import game.Game;
import game.entity.Entity;
import game.entity.mob.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import level.tile.Tile;

public class Screen
{
	private int width, height;
	public int[] pixels;
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	public int xOffset,yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private final int TRANSPARENT = -65281;
	private Random random = new Random(1);
	
	public Screen(int width,int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i=0;i < MAP_SIZE * MAP_SIZE;i++)
		{
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear()
	{
		for(int i =0;i<pixels.length;i++)
		{
			pixels[i] = 0;
		}
	}
	

	
	public void renderTile(int xp, int yp, Tile tile )
	{
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++)
		{
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++)
			{
				int xa = x + xp;
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				
				if (xa < 0)xa = 0; 
					pixels[xa +ya*width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
			
		}
	}
	
	public void renderEntity(int xp , int yp, Sprite sprite)
	{
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++)
		{
			int ya = y + yp;
			
			for(int x = 0; x < sprite.SIZE; x++)
			{
				int xa = x + xp;
				
				//no idea
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				
				//no idea
				if (xa < 0)
					xa = 0; 
				if(sprite.pixels[x + y * sprite.SIZE] != TRANSPARENT)
				pixels[xa +ya*width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}
	
	//show the players score
	public void renderScore(Player player)
	{
		String score = String.format("Score: %d", player.getScore());
		//maybe we can avoid creting a graphics obj in here and the renderEntity method if its a class var
		Graphics graphics = Game.game.image.getGraphics();
		graphics.setColor(Color.GRAY);
        graphics.setFont(new Font("Arial Black", Font.PLAIN, 20));
        graphics.drawString(score, 20, height-20);
        
        renderHud(graphics, player);
	}
	
	public void renderHud(Graphics graphics, Player player)
	{	
		int health = player.getHealth();
		Color colour;
		
		//set health bar colour
		if(health > 750)
			colour = Color.GREEN;
		else if(health > 500)
			colour = Color.YELLOW;
		else if(health > 250)
			colour = Color.ORANGE;
		else
			colour = Color.red;
		
		//draw health bar
		graphics.setColor(colour);
		graphics.fillRect(1, 1, health/10, 20);
		
		//draw border around the health bar
		graphics.setColor(Color.DARK_GRAY);
		//TODO remove the bellow magic num
		//1000 = max health
		graphics.drawRect(0, 0, 1000/10+1, 21);
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.drawRect(1, 1, 1000/10-1, 19);
	}
	
	public void renderEntity(int x, int y, BufferedImage image, AffineTransform at)
	{	
		
		
		Graphics2D g2 = Game.game.image.createGraphics();
		g2.setTransform(at);
		g2.drawImage(image,x,y,null);
		g2.dispose();
	
	}
	
	public void setOffset(int xOffset , int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	
	
	public int getWidth(){ return this.width;}
	public int getHeight(){ return this.height;}
}