package game.entity.mob;

import game.entity.Entity;
import game.entity.EntityHealth;
import game.entity.EntityPlasma;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.physics.Physics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class Player extends Mob {
	public final int SIZE = 32;
	private Keyboard input;
	int speedLimit = 13;
	double sx = 0,sy =  0;
	private Physics physics;
	public int direction;
	public int height, width;
	private int health = 1000;
	private int accerlation1 = 8;
	private int rotateSpeed = 2;
	private int shotCoolDown = 10,shotCounter = 0,shotSpeed = 5;
	private int counter = 0;
	public BufferedImage image;
	public BufferedImage changed;
	public BufferedImage fireImage;
	public BufferedImage[] fire = new BufferedImage[4];
	private static final int MAX_HEALTH = 1000;
	//****************************************************
	AffineTransform at = new AffineTransform();

	static BufferedImage deepCopy(BufferedImage bi){
		//added to create the temp image from bi
		BufferedImage newImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		g2.drawImage(bi, 0, 0, null);
		return newImage;
		//does the same thing differently
		/*
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);*/
		}
	
	//rotate the player sprite
	public void rotate()
	{	
		changed = deepCopy(image);

		at = new AffineTransform();
		int centerX =  changed.getWidth()/2;
		int centerY =  changed.getHeight()/2;
		at.translate(height/2 + centerY, width/2 + centerX);
		at.rotate(Math.toRadians(-direction));//directionLast - direction));
		at.translate(-centerY, -centerX);
	}
	
	//****************************************************
	//TODO change render method to work with image
	//public Sprite[]  = Sprite.ship0;
	
	public Player(Keyboard input,int height ,int width)
	{
		this.input = input;
		this.height = height;
		this.width = width;
		
		try
		{
			image = ImageIO.read(new File("game/textures/SpriteSheetShip.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.err.println("Error reading the spriteSheet");
		}
		try
		{
			fireImage = ImageIO.read(new File("game/textures/Fire.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.err.println("Error reading the spriteSheet");
		}
		fire[0] = fireImage.getSubimage(0, 0, 16, 16);
		fire[1] = fireImage.getSubimage(16, 0, 16, 16);
		fire[2] = fireImage.getSubimage(0, 16, 16, 16);
		fire[3] = fireImage.getSubimage(16, 16, 16, 16);
		
	}
	
	public Player(int x , int y, Keyboard input)
	{
		speed = 0.0;
		direction = 0;
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update()
	{
		counter++;
		int moving = 0;
		
		if(input.up)moving--;
		if(input.down)moving++;
		if(input.left)
		{
			direction += rotateSpeed;
		}
		if(input.right)
		{
			direction -= rotateSpeed;
		}
		if(direction >= 360) direction = 0;
		else if (direction < 0) direction = 360;
		
	
		speed = Physics.acceleration( moving ,speed, speedLimit,accerlation1);
		//   spawns a Plasma Entity if SPACE is pressed and has cooled 
		if(input.space)
		{
			if(shotCounter > shotCoolDown)
			{
				//shoot a new plasma
				int plasmaOffset = - 35;
				new EntityPlasma(x + (speed + plasmaOffset) * Math.sin(Math.toRadians(direction)),y + (speed + plasmaOffset) * Math.cos(Math.toRadians(direction)),direction-180,shotSpeed-((int)speed),10, this);
				shotCounter = 0;
			}
		}
		shotCounter++;	
		
		move((int)(Math.sin(Math.toRadians(direction))*speed), (int)(Math.cos(Math.toRadians(direction)) * speed));
		
	}
	
	public void die()
	{
		System.exit(99);
	}
	
	public void render(Screen screen)
	{
			this.rotate();
			if(input.up)
			screen.renderEntity(8, 32, fire[counter >> 2 & 3], at);
			//render ship to center of screen
			screen.renderEntity(0, 0, changed, at);
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public void updateHealth(int health)
	{
		this.health += health;
		
		if(this.health <= 0)
		{
			die();
		}
		
		if(this.health > MAX_HEALTH)
		{
			this.health = MAX_HEALTH;
		}
	}
	
	public void isHit(Entity hitter)
	{
		if(hitter instanceof EntityHealth)
		{
			updateHealth(EntityHealth.getHealthVal());
		}
		else
		{
			updateHealth(-100);
		}
	}
}
