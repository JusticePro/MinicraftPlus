package minicraft.item.Special;

import minicraft.Game;
import minicraft.entity.BossZombie;
import minicraft.entity.MobAi;
import minicraft.entity.Player;
import minicraft.gfx.Sprite;
import minicraft.item.Item;
import minicraft.item.StackableItem;
import minicraft.level.Level;
import minicraft.level.tile.Tile;

public class ZombieBossSpawnEgg extends StackableItem {

	public ZombieBossSpawnEgg(MobAi m) {
		super("Zombie-King Spawn", new Sprite(13, 4, m.col));
	}

	@Override
	public StackableItem clone() {
		return new ZombieBossSpawnEgg(new BossZombie());
	}
	
	@Override
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		
		level.add(new BossZombie(), player.x, player.y);
		Game.notifyAll("A player spawned the Zombie King.");
		
		return super.interactOn(true);
	}
	
}