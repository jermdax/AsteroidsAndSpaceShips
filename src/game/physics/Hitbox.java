package game.physics;

import java.awt.Point;

public class Hitbox
{
	private Point p1, p2;
	
	public Hitbox(Point p1, Point p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1(){return this.p1;}
	public Point getP2(){return this.p2;}
}