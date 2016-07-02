package game.npc;


import java.awt.Point;
import java.awt.Rectangle;

public class NpcTest
{
	public static void main(String[] args)
	{
		Npc testNpc = new Npc("shitto", new Rectangle(10, 10, 20, 20), new Point(40, 40), true );
		System.out.println(testNpc.getName());
		System.out.println(testNpc.getKillable());
		Point point = testNpc.getPosition();
		System.out.println(point.getX() + " = x, y = " + point.getY());
	}
}