package minicraft.item.Special;

import java.util.ArrayList;

import minicraft.entity.BossHate;
import minicraft.entity.BossZombie;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.item.Item;
import minicraft.item.StackableItem;

public class SpecialItems {
	
	public static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
		
		items.add(new StackableItem("Essence", new Sprite(25, 4, Color.MAGENTA)));
		items.add(new PigStaff());
		items.add(new ZombieBossSpawnEgg(new BossZombie()));
		items.add(new HateBossSpawnEgg(new BossHate(0)));
		
		return items;
	}
	
}