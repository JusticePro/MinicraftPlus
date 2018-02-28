package minicraft;

import javafx.scene.media.AudioClip;

public class Sound {
	//creates sounds from their respective files
	public static final Sound playerHurt = new Sound("/resources/sounds/playerhurt.wav");
	public static final Sound playerDeath = new Sound("/resources/sounds/death.wav");
	public static final Sound monsterHurt = new Sound("/resources/sounds/monsterhurt.wav");
	public static final Sound bossDeath = new Sound("/resources/sounds/bossdeath.wav");
	public static final Sound fuse = new Sound("/resources/sounds/fuse.wav");
	public static final Sound explode = new Sound("/resources/sounds/explode.wav");
	public static final Sound pickup = new Sound("/resources/sounds/pickup.wav");
	public static final Sound craft = new Sound("/resources/sounds/craft.wav");
	public static final Sound select = new Sound("/resources/sounds/select.wav");
	public static final Sound confirm = new Sound("/resources/sounds/confirm.wav");
	
	/*
	 * Music
	 */
	public static final Sound bitQuest = new Sound("/resources/sounds/songs/Bit Quest.mp3");
	public static final Sound dungeon = new Sound("/resources/sounds/songs/8bit Dungeon Level.mp3");
	
	private AudioClip clip; // Creates a audio clip to be played
	
	private Sound(String name) {
		clip = new AudioClip(Sound.class.getResource(name).toString());
	}
	
	public AudioClip getClip() {
		return clip;
	}
	
	public void play() {
		if (!(boolean)Settings.get("sound")) return;
		try {
			/*//creates a new thread (string of events)
			new Thread(() -> {
				//if (Settings.get("sound"))
				//clip.stop();
				//clip.setFramePosition(0);
				clip.start();
				//clip.play(); // plays the sound clip when called
			}).start(); //runs the thread*/
			clip.play();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
