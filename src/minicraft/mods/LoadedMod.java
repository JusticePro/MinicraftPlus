package minicraft.mods;

import minicraft.entity.Entity;
import minicraft.item.Item;
import minicraft.level.tile.Tile;

public class LoadedMod implements Mod {
	
	String name, version, description;
	Item[] items; Tile[] tiles; Entity[] entites;
	
	Runnable onEnable, tick;
	
	public LoadedMod(String name, String version, String description, Item[] items, Tile[] tiles, Entity[] entites, Runnable onEnable, Runnable tick) {
		this.name = name;
		this.version = version;
		this.description = description;
		this.items = items;
		this.tiles = tiles;
		this.onEnable = onEnable;
		this.tick = tick;
		this.entites = entites;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Item[] getItems() {
		return items;
	}

	@Override
	public Tile[] getTiles() {
		return tiles;
	}

	@Override
	public Runnable onEnable() {
		return onEnable;
	}

	@Override
	public Entity[] getEntites() {
		return entites;
	}

	@Override
	public Runnable tick() {
		return tick;
	}
	
}