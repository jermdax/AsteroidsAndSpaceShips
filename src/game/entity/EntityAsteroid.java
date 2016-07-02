package game.entity;

import game.Game;
import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;

public class EntityAsteroid extends Entity
{
	//public int asteroidNum;
	
	//points gotten for destroying an asteroid
	private static final int asteroidPoints = 10;
	private final int asteroidNum;
	
	public EntityAsteroid(int x, int y, int asteroidNum,int direction,double speed,double weight)
	{
		super.speed = speed;
		this.direction = direction;
		this.asteroidNum = asteroidNum;
		super.sprite = Sprite.asteroids[asteroidNum];
		this.x = x;
		this.y = y;
		Entity.allEntities.add(this);
		this.weight = weight;
		
	}
	
	public int getAsteroidNum()
	{
		return this.asteroidNum;
	}
	
	public void reset(int x,int y)
	{
		super.speed = random.nextDouble() * 4;
		//this.direction = random.nextInt(360);
		//super.sprite = Sprite.asteroids[random.nextInt(4)];
		this.x = x;
		this.y = y;
	}
	
	public void onDestroy(Mob killer)
	{
		killer.addScore(asteroidPoints);
	}
	
	public void update()
	{
		x += (speed * Math.sin(Math.toRadians(direction)));
		y += (speed * Math.cos(Math.toRadians(direction)));
	}
	
	public void render(Screen screen)
	{
		screen.renderEntity((int)x,(int)y, sprite);
		
	}
	
	public void isHit(Entity hitter)
	{
		if( ! (hitter instanceof EntityHealth))
		{
			int x = (int)Game.game.getPlayer().x + HALF_CONTAINER;
			int y = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
			//new EntityAsteroid(x,y, random.nextInt(4),random.nextInt(360),random.nextDouble() * 2,random.nextDouble() * 10);
			this.reset(x,y);
			if(hitter instanceof EntityPlasma)
			{
				((EntityPlasma)hitter).getOwner().addScore(100);
			}
		}
	}
}
