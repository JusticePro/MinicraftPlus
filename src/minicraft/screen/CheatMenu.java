package minicraft.screen;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.istack.internal.NotNull;

import minicraft.Game;
import minicraft.InputHandler;
import minicraft.Settings;
import minicraft.gfx.Color;
import minicraft.saveload.Save;
import minicraft.screen.entry.Action;
import minicraft.screen.entry.ArrayEntry;
import minicraft.screen.entry.BlankEntry;
import minicraft.screen.entry.BooleanEntry;
import minicraft.screen.entry.ListEntry;
import minicraft.screen.entry.SelectEntry;
import minicraft.screen.entry.StringEntry;

public class CheatMenu extends Display {
	
	public static ArrayEntry<String> speed = new ArrayEntry<String>("Speed", true, "Normal", "Double", "Triple", "Quadruple", "Quintuple");
	
	public static void resetCheats() {
		speed = new ArrayEntry<String>("Speed", true, "Normal", "Double", "Triple", "Quadruple", "Quintuple");
		
	}
	
	public CheatMenu() {
		ArrayList<ListEntry> entries = new ArrayList<>();
		entries.addAll(Arrays.asList(
			new BlankEntry(),
			new SelectEntry("Return to Game", () -> Game.setMenu(null)),
			new BlankEntry(),
			speed,
			new SelectEntry("Survival", () -> Settings.set("mode", "survival"))
			));
		
		entries.addAll(Arrays.asList(
			new BlankEntry(),
			
			new StringEntry(Game.input.getMapping("up")+" and "+Game.input.getMapping("down")+" to Scroll", Color.GRAY),
			new StringEntry(Game.input.getMapping("select")+": Choose", Color.GRAY)
		));
		
		//Menu.Builder msgBuilder = new Menu.Builder(8);
		
		menus = new Menu[] {
			new Menu.Builder(true, 4, RelPos.CENTER, entries)
				.setTitle("", 550)
				.createMenu()/*,
			
			msgBuilder.setEntries(new StringEntry("Save Game?"), new StringEntry("(Hint: Press \"r\" to save in-game)", Color.DARK_GRAY))
				.createMenu(),
			
			msgBuilder.setEntries(new StringEntry(""))*/
		};
		
	}
	
	@Override
	public void init(Display parent) {
		super.init(null); // ignore; pause menus always lead back to the game
	}
	
	@Override
	public void tick(InputHandler input) {
		super.tick(input);
		if (input.getKey("pause").clicked)
			Game.exitMenu();
	}
}
