package game.physics;
public class Physics {
	
	private double directionVector = 0;
	
	public static double acceleration(double xa, double speed, int speedLimit, int accerlation)
	{
		
		if(xa == 0)
		{	if(speed > 0)
			{xa = -.5;}
			else if	(speed < 0)
			{	xa =.5 ;}
		}
		speed += (xa) /accerlation;
			
		if(speed > speedLimit)speed = speedLimit;
		else if(speed < -speedLimit)speed = -speedLimit;
	
		return speed;
	}
		
/*	public static double momentium( double xa, double speed, int speedLimit, int accerlation )
	{
		
	}*/

}
