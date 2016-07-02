package level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;



public class TileVoid extends Tile {

	public TileVoid(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4 , y << 4, this);
	}

}
