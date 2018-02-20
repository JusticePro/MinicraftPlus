package minicraft.entity;

import minicraft.Settings;
import minicraft.gfx.Color;
import minicraft.gfx.MobSprite;
import minicraft.item.Items;

public class GoldPig extends PassiveMob {
	private static MobSprite[][] sprites = MobSprite.compileMobSpriteAnimations(16, 14);
	
	/**
	 * Creates a pig.
	 */
	public GoldPig() {
		super(sprites, Color.get(-1, 000, Color.rgb(255, 215, 0), Color.rgb(225, 195, 0)));
	}
	
	@Override
	protected void die() {
		int min = 0, max = 0;
		if (Settings.get("diff").equals("Easy")) {min = 5; max = 10;}
		if (Settings.get("diff").equals("Normal")) {min = 7; max = 15;}
		if (Settings.get("diff").equals("Hard")) {min = 10; max = 20;}
		
		if (random.nextInt(100)==1) {
			dropItem(1, 1, Items.get("The Staff of Pigs"));
		}
		
		dropItem(min, max, Items.get("bacon"));
		
		super.die();
	}
}
