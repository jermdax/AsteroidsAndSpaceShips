package game.entity;

import game.Game;
import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;

public class EntityPlasma extends Entity{
	
	public int lifeCycle = 200;
	private Player owner;
	
	public EntityPlasma(double x, double y,int direction, double speed, double weight, Player owner)
	{
		
		super.speed = speed;
		this.direction = direction;
		this.owner = owner;
		//this.asteroidNum = asteroidNum;
		super.sprite = Sprite.plasma[0];
		this.x = x;
		this.y = y;
		this.weight = weight;
		Entity.allEntities.add(this);
		
	}
	public void reset()
	{
		//TODO "randomize" asteroids direction and speed
	}
	
	public void update()
	{
		x += (speed * Math.sin(Math.toRadians(direction)));
		y += (speed * Math.cos(Math.toRadians(direction)));
		super.sprite = Sprite.plasma[((int)counter++ >> 4)&3];//counter for animated sprite
		if(counter > lifeCycle)
		{
			this.removed();
		}
	}
	
	public void render(Screen screen)
	{
		screen.renderEntity((int)x,(int)y, sprite);
		
	}
	
	public void testBounds()
	{
		//wrap the allEntities[i]s around if they are out of bounds of the container
		if(this.x >= Game.game.getPlayer().x + HALF_CONTAINER)
		{
			this.removed();
		}
		else if(this.x <= Game.game.getPlayer().x - HALF_CONTAINER)
		{
			this.removed();
		}
		
		if(this.y >= Game.game.getPlayer().y + HALF_CONTAINER)
		{
			this.removed();
		}
		else if(this.y <= Game.game.getPlayer().y - HALF_CONTAINER)
		{
			this.removed();
		}
	}
	
	public void isHit(Entity hitter)
	{
		if( ! (hitter instanceof EntityHealth))
		{
			this.removed();
		}
	}

	public Player getOwner()
	{
		return this.owner;
	}
}
