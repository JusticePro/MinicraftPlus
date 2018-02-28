package minicraft.mods;

import minicraft.entity.Entity;
import minicraft.item.Item;
import minicraft.item.ToolItem;
import minicraft.level.tile.Tile;

public interface Mod {
	
	/*
	 * Mod Information
	 */
	public String getName();
	public String getVersion();
	public String getDescription();
	
	/*
	 * Mod Features
	 */
	
	public Item[] getItems();
	public Tile[] getTiles();
	public Entity[] getEntites();
	
	public Runnable onEnable();
	public Runnable tick();
	
}