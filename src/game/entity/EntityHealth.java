package game.entity;


import game.Game;
import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;

public class EntityHealth extends Entity{

		//public int asteroidNum;
		
		//points gotten for destroying an asteroid
		private static final int points = 10;
		private static final int HEALTH_VAL = 333;
		
		
		public EntityHealth(int x, int y,int direction,double speed)
		{
			super.speed = speed;
			this.direction = direction;
			//TODO create the health sprite and add it below
			super.sprite = Sprite.health;
			this.x = x;
			this.y = y;
			Entity.allEntities.add(this);
			this.weight = 0;
		}
		
		public static int getHealthVal()
		{
			return HEALTH_VAL;
		}
		
		public void reset()
		{
			//TODO "randomize" asteroids direction and speed
		}
		
		public void onDestroy(Mob killer)
		{
			killer.addScore(points);
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
			if(hitter instanceof Player)
			{
				int x = (int)Game.game.getPlayer().x + HALF_CONTAINER;
				int y = random.nextInt(CONTAINER_SIZE) - CONTAINER_SIZE / 2;
				new EntityHealth(x, y, random.nextInt(360), random.nextDouble() * 2);
				this.removed();
			}
		}
	

}
