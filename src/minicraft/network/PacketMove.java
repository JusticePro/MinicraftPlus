package minicraft.network;

import me.justicepro.networkapi.Packet;

public class PacketMove implements Packet {
	
	private int x;
	private int y;
	
	public PacketMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String parse() {
		return "MOVE//" + getX() + ";" + getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
