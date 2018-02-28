package minicraft.mods;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import minicraft.Game;
import minicraft.entity.Entity;
import minicraft.item.Item;
import minicraft.item.ToolItem;
import minicraft.level.tile.Tile;

public class ModManager {
	
	private ArrayList<LoadedMod> modList;
	
	public ModManager() {
		modList = new ArrayList<>();
	}
	
	public void loadMod(LoadedMod mod) {
		mod.onEnable().run();
		modList.add(mod);
	}
	
	public void loadMods() throws MalformedURLException, ClassNotFoundException {
		File modDir = new File(Game.modsDir);
		/* Load all jar files at mods folder */
		for (File file : modDir.listFiles()) {
			if (file.getName().endsWith(".jar")) {
				URL[] urls = new URL[] {
						file.toURL()
				};
				URLClassLoader classLoader = new URLClassLoader(urls);
				Class<?> mod = classLoader.loadClass("minicraftmod.ModCore");
				try {
					LoadedMod lmod = classToMod(mod);
					
					loadMod(lmod);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public LoadedMod classToMod(Class modClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		LoadedMod mod;
		
		/*
		 * n-Name v-Version d-Description t-Tiles i-Items
		 */
		
		String n = null;
		String v = null;
		String d = null;
		
		Tile[] t = null;
		Item[] i = null;
		Entity[] e = null;
		
		Runnable r = null;
		Runnable tick = null;
		
		Object instance = modClass.newInstance();
		
		for (Method method : modClass.getMethods()) {
			if (method.getName().equalsIgnoreCase("getname")) {
				n = method.invoke(instance, null) + "";
			}
			
			if (method.getName().equalsIgnoreCase("getversion")) {
				v = method.invoke(instance, null) + "";
			}
			
			if (method.getName().equalsIgnoreCase("getdescription")) {
				d = method.invoke(instance, null) + "";
			}
			
			if (method.getName().equalsIgnoreCase("gettiles")) {
				t = (Tile[]) method.invoke(instance, null);
			}
			
			
			if (method.getName().equalsIgnoreCase("getitems")) {
				i = (Item[]) method.invoke(instance, null);
			}
			
			if (method.getName().equalsIgnoreCase("onenable")) {
				r = (Runnable) method.invoke(instance, null);
			}
			
			if (method.getName().equalsIgnoreCase("tick")) {
				tick = (Runnable) method.invoke(instance, null);
			}
			
			if (method.getName().equalsIgnoreCase("getentity")) {
				e = (Entity[]) method.invoke(instance, null);
			}
			
		}
		
		mod = new LoadedMod(n, v, d, i, t, e, r, tick);
		
		return mod;
	}
	
	public ArrayList<LoadedMod> getModList() {
		return modList;
	}
	
}