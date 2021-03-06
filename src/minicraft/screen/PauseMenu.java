package minicraft.screen;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.istack.internal.NotNull;

import minicraft.Game;
import minicraft.InputHandler;
import minicraft.Settings;
import minicraft.Sound;
import minicraft.gfx.Color;
import minicraft.saveload.Save;
import minicraft.screen.entry.Action;
import minicraft.screen.entry.BlankEntry;
import minicraft.screen.entry.ListEntry;
import minicraft.screen.entry.SelectEntry;
import minicraft.screen.entry.StringEntry;

public class PauseMenu extends Display {
	
	public PauseMenu() {
		ArrayList<ListEntry> entries = new ArrayList<>();
		entries.addAll(Arrays.asList(
			new BlankEntry(),
			new SelectEntry("Return to Game", () -> Game.setMenu(null)),
			new SelectEntry("Options", () -> Game.setMenu(new OptionsMenu())),
			new SelectEntry("Cheats", cheatsAction())
			));
		
		if(!Game.ISONLINE)
			entries.add(new SelectEntry("Make World Multiplayer", () -> {
				Game.setMenu(null);
				Game.startMultiplayerServer();
			}));
		
		entries.addAll(Arrays.asList(
			new SelectEntry("Save Game", saveAction()),
			new SelectEntry("Main Menu", () -> {
				
				/* Stop all Songs */
				Sound.menu.getClip().stop();
				
				Game.setMenu(new TitleMenu());
			}),
			
			new BlankEntry(),
			
			new StringEntry(Game.input.getMapping("up")+" and "+Game.input.getMapping("down")+" to Scroll", Color.GRAY),
			new StringEntry(Game.input.getMapping("select")+": Choose", Color.GRAY)
		));
		
		//Menu.Builder msgBuilder = new Menu.Builder(8);
		
		menus = new Menu[] {
			new Menu.Builder(true, 4, RelPos.CENTER, entries)
				.setTitle("Paused", 550)
				.createMenu()/*,
			
			msgBuilder.setEntries(new StringEntry("Save Game?"), new StringEntry("(Hint: Press \"r\" to save in-game)", Color.DARK_GRAY))
				.createMenu(),
			
			msgBuilder.setEntries(new StringEntry(""))*/
		};
		
	}
	
	public Action cheatsAction() {
		return new Action() {
			
			@Override
			public void act() {
				if (Settings.get("mode") != "Creative") {
					Game.notifications.add("Cheats are Creative Only.");
					Game.setMenu(null);
				}else {
					Game.setMenu(new CheatMenu());
				}
			}
		};
	}
	
	public Action saveAction() {
		return new Action() {
			
			@Override
			public void act() {
				if (Game.isMode("speedrun")) {
					Game.notifications.add("You can't save in a Speedrun.");
					Game.setMenu(null);
				}else {
					Game.setMenu(null);
					if(!Game.isValidServer())
						new Save(WorldSelectMenu.getWorldName());
					else {
						Game.server.saveWorld();
					}
				}
			}
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
