package level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class TileSpace extends Tile
{
	public TileSpace(Sprite sprite)
	{
		super(sprite);	
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 5, y << 5, this);
	}
}


