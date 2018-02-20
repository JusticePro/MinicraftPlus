package minicraft.entity;

import java.util.ArrayList;

import minicraft.Game;
import minicraft.gfx.Color;
import minicraft.gfx.MobSprite;
import minicraft.item.Recipe;
import minicraft.screen.CraftingMenu;

public class Npc extends PassiveMob {
	public static MobSprite[][] sprites =  MobSprite.compileMobSpriteAnimations(0, 14);
	
	public static ArrayList<Recipe> recipes = new ArrayList<>();
	
	static {
		recipes.add(new Recipe("Essence_1", "Gem_5"));
		recipes.add(new Recipe("Gem_1", "Gold_5"));
		recipes.add(new Recipe("Gold_1", "Iron_5"));
		
	}
	
	/**
	 * Creates a zombie of the given level.
	 * @param lvl Zombie's level.
	 */
	public Npc() {
		super(sprites, Color.get(-1, Color.rgb(59, 36, 36), Color.rgb(73, 73, 49), Color.rgb(224, 178, 155)));
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public boolean use(Player player, int attackDir) {
		
		
		Game.setMenu(new CraftingMenu(recipes, "Merchant", player));
		
		return true;
	}
	
	@Override
	protected void die() {
		
		super.die();
	}
	
}
