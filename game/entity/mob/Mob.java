package game.entity.mob;

import game.entity.Entity;
import game.graphics.Sprite;

public abstract class Mob extends Entity{

	protected Sprite sprite;
	protected boolean moving = false;
	//the players score (points)
	private int score = 0;

	/*public Mob(Sprite sprite, int direction, boolean moving, int asteroidNum)
	{
		this.sprite = sprite;
		this.direction = direction;
		this.moving = moving;
	}*/
	
	public void addScore(int score)
	{
		this.score += score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	protected void move(int xa, int ya)
	{	if(xa > 0) direction = 1;
		if(xa < 0) direction = 3;
		if(ya > 0) direction = 2;
		if(ya < 0) direction = 0;
		
		 
		if (!collision())
		{
			x += xa;
			y += ya;
		}
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
	
	private boolean collision()
	{
		return false;
	}
	
}
