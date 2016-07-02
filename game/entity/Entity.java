/**
 * 
 */
package game.entity;

import game.Game;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.util.ArrayList;
import java.util.Random;

import level.Level;

/**
 * @author Nigel & David
 *
 */
public class Entity {
	
	public double x,y;
	private boolean removed = false;
	protected Level level;
	public Sprite sprite;
	public double speed;
	public double weight;
	//the entities direction, between 0° and 360°
	public double direction;
	public int counter;
	private static final int ASTEROID_SCORE = 100;
	
	
	protected static final Random random = new Random();
	public static final int ASTEROID_TYPES = 4;
	private static final int startAsteroids = 1000;
	private static final int startHealthItems = 10;
	public static final int CONTAINER_SIZE = 3000;
	public static final int HALF_CONTAINER = CONTAINER_SIZE / 2;
	
	public static ArrayList<Entity> allEntities = new ArrayList<>();
	//initialized statically at start
	static
	{
		
		for(int i = 0; i < startAsteroids; i++)
		{
			int x = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
			int y = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
			//TODO get random x and y and asteroid num
			//x, y, asteroidNum
			new EntityAsteroid(x, y, random.nextInt(4),random.nextInt(360),random.nextDouble() * 2,random.nextDouble() * 10);
		}
		for(int i = 0; i < startHealthItems; i++)
		{
			int x = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
			int y = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
			new EntityHealth(x, y, random.nextInt(360),random.nextDouble() * 2);
		}
	}
	
	//TODO constructor??
	public static void updateEntities()
	{
		int size = allEntities.size();
		try
		{
			for( int i = 0; i < allEntities.size(); i++)
			{	if(allEntities.get(i).getRemoved())
				{
					allEntities.get(i).remove(i);
				}
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		 size = allEntities.size();
		for(int i = 0; i < size; i++)
		{
			//System.out.print("|" + size+":");
			allEntities.get(i).testBounds();
			allEntities.get(i).checkCollissions(i, size);
			
			allEntities.get(i).update();
		
		}
		//remove entity if remove is true stops array out of bounds exception.
		
	}
	

	
	public void testBounds()
	{
		//wrap the allEntities[i]s around if they are out of bounds of the container
		if(this.x >= Game.game.getPlayer().x + HALF_CONTAINER)
		{
			this.x -= CONTAINER_SIZE;
		}
		else if(this.x <= Game.game.getPlayer().x - HALF_CONTAINER)
		{
			this.x += CONTAINER_SIZE;
		}
		
		if(this.y >= Game.game.getPlayer().y + HALF_CONTAINER)
		{
			this.y -= CONTAINER_SIZE;
		}
		else if(this.y <= Game.game.getPlayer().y - HALF_CONTAINER)
		{
			this.y += CONTAINER_SIZE;
		}
	}
	
	public static void renderEntities(Screen screen)
	{
		
		//int counter = 0;
		//@davyjonesno1 test
		int size = allEntities.size();
		for(int i = 0; i < size; i++)
		{
			allEntities.get(i).render(screen);
		}
	}
	
	public void render(Screen screen)
	{
		screen.renderEntity((int)this.x, (int)this.y, this.sprite);
	}
	
	public void update()
	{
		
	}
	
	public void remove(int i)
	{
		allEntities.remove(i);
	}
	public void removed()
	{
		removed = true;
	}
	
	public boolean getRemoved()
	{
		return removed;
	}
	
	public void checkCollissions(int i, int size)
	{
		//int size = allEntities.size();
		for(int j = i+1; j < size; j++)
		{
			if(this.collidesWith(allEntities.get(j)))
			{
				handleCollission(this, allEntities.get(j));
			}
		}
	}
	
	//default isHit action
	public void isHit(Entity hitter)
	{
		//default
		this.removed();
	}
	
	public static void handleCollission(Entity entity1, Entity entity2)
	{	
		entity1.isHit(entity2);
		entity2.isHit(entity1);
	}
	
	public boolean collidesWith(Entity that)
	{
		double bigX, bigY, smallX, smallY;
		
		if(this.x > that.x)
		{
			bigX = this.x;
			smallX = that.x;
		}
		else
		{
			bigX = that.x;
			smallX = this.x;
		}
		
		if(this.y > that.y)
		{
			bigY = this.y;
			smallY = that.y;
		}
		else
		{
			bigY = that.y;
			smallY = this.y;
		}
		
		//TODO make a better collission test
		
		//if the entities are colliding
		if( Math.abs((bigX + 16)-(smallX + 16)) <= 23 && Math.abs(((bigY + 16) - (smallY + 16))) <= 23)
		{
			return true;
		}
		
		//they aren't colliding
		return false;
	}
}

