package ro.imerkal.uTitleWelcome.bukkit;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import ro.imerkal.uTitleWelcome.utils.VersionHandler;

public class v1_8_R3 implements VersionHandler {
	
	public void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
	    
	    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
	    connection.sendPacket(packetPlayOutTimes);
	    if (title != null) {
		      IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		      PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
		      connection.sendPacket(packetPlayOutTitle);
		    }
	}

	public void sendSubtitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
		PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
	    
	    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
	    connection.sendPacket(packetPlayOutTimes);
	    if (subtitle != null) {
	      IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	      PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
	      connection.sendPacket(packetPlayOutSubTitle);
	    }
	}

	public void sendFullTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
	    
	    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
	    connection.sendPacket(packetPlayOutTimes);
	    if (subtitle != null) {
	      IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	      PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
	      connection.sendPacket(packetPlayOutSubTitle);
	    }
	    if (title != null) {
	      IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
	      PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
	      connection.sendPacket(packetPlayOutTitle);
	    }
	}
	public void sendActionBar(Player p, String text) {
		IChatBaseComponent bar = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(bar, (byte)2);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
}