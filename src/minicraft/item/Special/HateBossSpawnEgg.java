package minicraft.item.Special;

import minicraft.Game;
import minicraft.entity.BossHate;
import minicraft.entity.BossZombie;
import minicraft.entity.MobAi;
import minicraft.entity.Player;
import minicraft.gfx.Sprite;
import minicraft.item.StackableItem;
import minicraft.level.Level;
import minicraft.level.tile.Tile;

public class HateBossSpawnEgg extends StackableItem {

	public HateBossSpawnEgg(MobAi m) {
		super("Hate Spawn", new Sprite(13, 4, m.col));
	}

	@Override
	public StackableItem clone() {
		return new HateBossSpawnEgg(new BossHate(0));
	}
	
	@Override
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		
		level.add(new BossHate(0), player.x, player.y);
		Game.notifyAll("A player spawned Hate.");
		
		return super.interactOn(true);
	}
	
}