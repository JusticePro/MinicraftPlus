package minicraft.entity;

import minicraft.Settings;
import minicraft.gfx.Color;
import minicraft.gfx.Font;
import minicraft.gfx.MobSprite;
import minicraft.gfx.Screen;
import minicraft.item.Items;

public class BossZombie extends EnemyMob implements Boss {
	private static MobSprite[][] sprites = MobSprite.compileMobSpriteAnimations(0, 14);
	private static int[] lvlcols = {
		Color.get(-1, Color.rgb(0, 235, 21), Color.rgb(106, 255, 120), Color.rgb(0, 255, 0)),
	};
	
	/**
	 * Creates a zombie of the given level.
	 */
	public BossZombie() {
		super(0, sprites, lvlcols, 500, 100);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (random.nextInt(500)==1) {
			getLevel().add(new Zombie(0), x, y + 5);
			getLevel().add(new Zombie(0), x, y - 5);
			getLevel().add(new Zombie(0), x + 5, y);
			getLevel().add(new Zombie(0), x - 5, y);
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
		if (Settings.get("diff").equals("Easy")) dropItem(2, 4, Items.get("cloth"));
		if (Settings.get("diff").equals("Normal")) dropItem(2, 3, Items.get("cloth"));
		if (Settings.get("diff").equals("Hard")) dropItem(1, 2, Items.get("cloth"));
		
		dropItem(5, 10, Items.get("iron"));
		
		dropItem(10, 20, Items.get("essence"));
		
		if(random.nextInt(40) == 19) {
			int rand = random.nextInt(3);
			if(rand == 0) {
				level.dropItem(x, y, Items.get("green clothes"));
			} else if(rand == 1) {
				level.dropItem(x, y, Items.get("red clothes"));
			} else if(rand == 2) {
				level.dropItem(x, y, Items.get("blue clothes"));
			}
		}
		
		dropItem(10, 20, Items.get("cloth"));
		
		super.die();
	}
}
