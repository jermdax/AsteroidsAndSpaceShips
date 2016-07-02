package game.physics;

public class Rotate {
	
	public int[] shearX(int angle, int[] pixels, int size)
	{
		int lastColor = 0;
		int center = size / 2;
		for(int j = 0; j < size ;j++ )
			for(int i = 0; i < size/2 ;i++ )
			{
				//lastColor = pixels[((i - center) + (int)(i * Math.tan(angle/2))) + (j * size)];
				
				pixels[(31&((int)(i * Math.tan(angle/2))))  + (31&(j * size))] = pixels[i + (j * size)];
				
				
				
				//pixels[(i + (int)(i * Math.tan(angle/2))) + ((j - center) * size)] = pixels[(i) + ((j - center) * size)];
				//pixels[(i + (int)(i * Math.tan(angle/2))) + ((j + center) * size)] = pixels[(i) + ((j + center) * size)];
				
			}
			
		
		return pixels;
	}

}
