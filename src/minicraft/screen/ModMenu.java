package minicraft.screen;

import java.util.ArrayList;

import minicraft.Game;
import minicraft.gfx.Color;
import minicraft.mods.Mod;
import minicraft.screen.entry.BlankEntry;
import minicraft.screen.entry.ListEntry;
import minicraft.screen.entry.SelectEntry;
import minicraft.screen.entry.StringEntry;

public class ModMenu extends Display {
	
	public ModMenu() {
		super(true, new Menu.Builder(false, 6, RelPos.LEFT, mods())
			.setTitle("Mods WIP")
			.createMenu());
	}
	
	public static ListEntry[] mods() {
		ArrayList<ListEntry> mods = new ArrayList<>();
		mods.add(new SelectEntry("Minicraft+", () -> Game.setMenu(new BookDisplay("Minicraft+\nVersion: 1.0\n\nA mod based on the original Minicraft made by Marcus Perrson (Notch).")) ));
		mods.add(new StringEntry("Builtin", Color.GRAY));
		mods.add(new BlankEntry());
		for (Mod mod : Game.getModManager().getModList()) {
			mods.add(new SelectEntry(mod.getName(), () -> Game.setMenu(new BookDisplay(mod.getName() + " " + mod.getVersion() + "\n" + mod.getDescription()))));
			mods.add(new BlankEntry());
		}
		return (ListEntry[]) mods.toArray(new ListEntry[mods.size()]);
	}
	
}