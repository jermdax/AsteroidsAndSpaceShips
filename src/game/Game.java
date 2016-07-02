package game;

import game.entity.Entity;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.input.Keyboard;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.RandomLevel;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static int width = 700;
	public static int height = width;///16*9;		
	public static int scale = 1;		
	public static String title = "game";
	public static Game game;
	public static  int xScroll,yScroll;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	public Keyboard key;
	private Level level;
	public Player player;
	private Screen screen;
   // int x = 0, y = 0;
	public BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game()
	{
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen = new Screen(width,height);
		frame = new JFrame();
		
		key = new Keyboard();
		level = new RandomLevel(64, 64);
		player = new Player(key,width,height);
		Entity.allEntities.add(player);
		
		addKeyListener(key);
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this,"Display");
		thread.start();	
	}
	
	public synchronized void stop()
	{	
		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}		
	}
	
	public void run()
	{
		long timer = System.currentTimeMillis();
		
		long lastTime = System.nanoTime();
		final double ns= 1000000000.0 /60.0;
		double delta = 0;
		int update = 0;
		int frames = 0;
		requestFocus();
		while(running)
		{
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1)
			{
				
				delta--;
				update++;
				update();
				
			}
			render();
			frames++; 
			
			if(System.currentTimeMillis() - timer >= 1000)
			{
				
				timer += 1000;
				//System.out.println(update + "ups," + frames + "fps");
				frame.setTitle(title +"    |       " + update+ "ups, " + frames + "fps");
				frames = 0;
				update = 0;
			}
		}
		stop();
	}
	
	public void update()
	{
		key.update();
		Entity.updateEntities();
		//player.update();
	
	}
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		xScroll = (int)player.x - screen.getWidth() /2;
	    yScroll = (int)player.y - screen.getHeight() /2;
		level.render(xScroll, yScroll, screen);
		//player.render(screen);
		
		//@davyjonesno1
		Entity.renderEntities(screen);
		for (int i = 0 ; i < pixels.length;i++)
		{
			pixels[i] = screen.pixels[i];
		}
		

		player.render(screen);
		screen.renderScore(player);
		Graphics g = bs.getDrawGraphics();
	//	g.setColor(Color.black);
	//	g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image,0,0,getWidth(),getHeight(),null);

		
		g.dispose(); /* deletes g */
		bs.show();
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("user.dir"));
		game = new Game();
		game.frame.setResizable(false);
		
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocation(50,50);
		game.frame.setVisible(true);
		
		game.start();
	}	
	
	public Player getPlayer()
	{
		return this.player;
	}

}
