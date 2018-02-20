package minicraft.entity;

import minicraft.Settings;
import minicraft.gfx.Color;
import minicraft.gfx.Font;
import minicraft.gfx.MobSprite;
import minicraft.gfx.Screen;
import minicraft.item.Items;

public class BossGoldPig extends EnemyMob implements Boss {
	private static MobSprite[][] sprites = MobSprite.compileMobSpriteAnimations(16, 14);
	
	private static int[] lvlcols = new int[] {
		Color.get(-1, 000, Color.rgb(255, 215, 0), Color.rgb(225, 195, 0))
	};
	
	/**
	 * Creates a pig.
	 */
	public BossGoldPig() {
		super(0, sprites, lvlcols, 50, 1000);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (random.nextInt(100)==1) {
			dropItem(1, 10, Items.get("gold"));
		}
		
	}
	
	@Override
	public void render(Screen screen) {
		super.render(screen);
		
		int textcol = Color.get(-1, Color.rgb(255, 0, 0));
		int textcol2 = Color.get(-1, Color.rgb(200, 0, 0));
		String h = health + "/" + maxHealth;
		
		int textwidth = Font.textWidth(h);
		Font.draw(h, screen, (x - textwidth/2) + 1, y - 17, textcol2);
		Font.draw(h, screen, (x - textwidth/2), y - 18, textcol);
	}
	
	@Override
	protected void die() {
		int min = 0, max = 0;
		if (Settings.get("diff").equals("Easy")) {min = 5; max = 10;}
		if (Settings.get("diff").equals("Normal")) {min = 7; max = 15;}
		if (Settings.get("diff").equals("Hard")) {min = 10; max = 20;}
		
		dropItem(50, 100, Items.get("bacon"));
		dropItem(50, 100, Items.get("gold"));
		
		super.die();
	}
}