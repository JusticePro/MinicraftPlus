package minicraft.item.Special;

import java.util.Random;

import minicraft.Game;
import minicraft.entity.BossGoldPig;
import minicraft.entity.GoldPig;
import minicraft.entity.Pig;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.item.Item;
import minicraft.level.Level;
import minicraft.level.tile.Tile;

public class PigStaff extends Item {

	public PigStaff() {
		super("The Staff of Pigs", new Sprite(11, 5, Color.get(-1, Color.rgb(150, 150, 0),
				Color.rgb(200, 200, 0),
				Color.rgb(255, 180, 0))));
	}

	@Override
	public Item clone() {
		return new PigStaff();
	}
	
	@Override
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		Random ran = new Random();
		
		int num = ran.nextInt(4);
		
		if (num==1) {
			level.add(new GoldPig(), player.x, player.y);
		}else {
			level.add(new Pig(), player.x, player.y);
		}
		
		if (ran.nextInt(100)==1) {
			level.add(new BossGoldPig(), player.x, player.y);
			Game.notifyAll("A player spawned the Pig-King Midas.");
		}
		
		return false;
	}
	
}