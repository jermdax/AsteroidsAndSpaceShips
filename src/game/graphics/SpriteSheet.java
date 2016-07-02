package game.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public int[] pixels;
	//public static SpriteSheet tiles =  new SpriteSheet("/textures/SpriteSheet.png",512);
	public static SpriteSheet ship = new SpriteSheet("game/textures/SpriteSheet.png",512);
	public static SpriteSheet space = new SpriteSheet("game/textures/SpriteSheetSpace.png",256);
	public static SpriteSheet plasma = new SpriteSheet("game/textures/Plasma.png",64);
	public static SpriteSheet health = new SpriteSheet("game/textures/Health.png",64);
	
 	private SpriteSheet(String path, int size)
	{
		this.path = path;
		this.SIZE = size;
		pixels = new int [SIZE * SIZE];
		load();
	}
	
	private void load()
	{
		try
		{
			System.out.println(System.getProperty("user.dir"));
      System.out.println(path);
			BufferedImage image = ImageIO.read(new File(path));
			//BufferedImage image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		//	Graphics2D g = image.createGraphics();
		//	g.drawImage(sourceImage, 0, 0, null);
			//g.dispose();
			int w = image.getWidth();
			int h = image.getHeight();
		
			image.getRGB(0, 0, w, h, pixels, 0, w);
			//resetArray(pixels);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	//TODO move this to the right place
	public void resetArray(int[] array1)
	{
		for(int i = 0;i > array1.length;i++ )
			array1[i] = 0;
		System.out.print("yes");
	}
}
