package minicraft.item.Special;

import java.util.Random;

import minicraft.Game;
import minicraft.entity.BossHate;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.item.FoodItem;
import minicraft.item.StackableItem;
import minicraft.level.Level;
import minicraft.level.tile.Tile;

public class BeefJerky extends FoodItem {

	public BeefJerky() {
		super("Beef Jerky", new Sprite(20, 4, Color.get(-1, 100, 333, 211)), 1);
	}
	
	@Override
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		
		Random ran = new Random();
		if (ran.nextInt(10000)==1) {
			Game.notifyAll("Curse of the Bad Suggestions");
			Game.notifyAll("A player spawned Hate.");
			
			level.add(new BossHate(0), player.x, player.y);
			
		}
		
		return super.interactOn(true);
	}
	
}