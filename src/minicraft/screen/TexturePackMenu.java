package minicraft.screen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import minicraft.Game;
import minicraft.exception.ErrorMenu;
import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.gfx.SpriteSheet;
import minicraft.screen.entry.BlankEntry;
import minicraft.screen.entry.ListEntry;
import minicraft.screen.entry.SelectEntry;
import minicraft.screen.entry.StringEntry;

public class TexturePackMenu extends Display {
	
	public TexturePackMenu() {
		super(true, new Menu.Builder(false, 6, RelPos.LEFT, texturepacks())
			.setTitle("Texture Packs")
			.createMenu());
	}
	
	public static ListEntry[] texturepacks() {
		File packs = new File(Game.textureDir);
		ArrayList<ListEntry> texturepacks = new ArrayList<>();
		texturepacks.add(new SelectEntry("Minicraft+", () -> setTexturePack("default") ));
		texturepacks.add(new StringEntry("Builtin", Color.GRAY));
		texturepacks.add(new BlankEntry());
		for (File file : packs.listFiles()) {
			texturepacks.add(new SelectEntry(file.getName().substring(0, file.getName().length() - 4), () -> setTexturePack(file.getAbsolutePath())));
			texturepacks.add(new BlankEntry());
		}
		return (ListEntry[]) texturepacks.toArray(new ListEntry[texturepacks.size()]);
	}
	
	public static String getTexturePack() {
		File file2 = new File(Game.gameDir);
		
		if (!file2.exists()) {
			file2.mkdirs();
		}
		
		File data = new File(Game.gameDir, "pack"); // Where the path for the pack is saved
		if (!data.exists()) {
			try {
				data.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ErrorMenu frame = new ErrorMenu(e);
				frame.setVisible(true);
			}
			resetTexturePack();
		}
		String result = "";
		try {
			Scanner scanner = new Scanner(data);
			result = scanner.nextLine();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "A texture pack was removed while it was in use. Please restart to resolve this error.");
			resetTexturePack();
			e.printStackTrace();
			ErrorMenu frame = new ErrorMenu(e);
			frame.setVisible(true);
		}
		return result;
	}
	
	public static void resetTexturePack() {
		File data = new File(Game.gameDir, "pack"); // Where the path for the pack is saved
		if (!data.exists()) {
			try {
				data.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ErrorMenu frame = new ErrorMenu(e);
				frame.setVisible(true);
			}
		}
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(data));
			printStream.print("default");
			printStream.flush();
			printStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorMenu frame = new ErrorMenu(e);
			frame.setVisible(true);
		}
	}
	
	public static void setTexturePack(String file) {
		File data = new File(Game.gameDir, "pack"); // Where the path for the pack is saved
		if (!data.exists()) {
			try {
				data.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ErrorMenu frame = new ErrorMenu(e);
				frame.setVisible(true);
			}
		}
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(data));
			printStream.print(file);
			printStream.flush();
			printStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorMenu frame = new ErrorMenu(e);
			frame.setVisible(true);
		}
	}
	
}