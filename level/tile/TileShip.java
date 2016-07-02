package level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class TileShip extends Tile
{
	public TileShip(Sprite sprite)
	{
		super(sprite);
	}

	@Override
	public boolean solid()
	{
		return true;
	}
	
	public void render(int x, int y, Screen screen)
	{
			screen.renderTile(x << 5, y << 5, this);
	}
}
