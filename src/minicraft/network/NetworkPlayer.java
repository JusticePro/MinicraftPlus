package minicraft.network;

import java.io.IOException;

import me.justicepro.networkapi.PacketInputStream;
import me.justicepro.networkapi.PacketOutputStream;
import minicraft.entity.Player;

public class NetworkPlayer {
	
	private PacketInputStream input;
	private PacketOutputStream output;
	
	public NetworkPlayer(PacketOutputStream output, PacketInputStream input) {
		this.input = input;
		this.output = output;
	}
	
	public void move(Player player) throws IOException {
		output.writePacket(new PacketMove(player.x, player.y));
	}
	
}