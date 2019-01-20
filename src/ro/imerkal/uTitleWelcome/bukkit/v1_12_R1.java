package ro.imerkal.uTitleWelcome.bukkit;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import ro.imerkal.uTitleWelcome.utils.VersionHandler;

public class v1_12_R1 implements VersionHandler {

	public void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		CraftPlayer localCraftPlayer = (CraftPlayer)p;
		IChatBaseComponent localIChatBaseComponent;
		PacketPlayOutTitle localPacketPlayOutTitle;
		if (title != null) {
			localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
	        localPacketPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, localIChatBaseComponent);
	        try {
				localPacketPlayOutTitle.getClass().getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE })
				  .newInstance(new Object[] {fadeIn.intValue(), stay.intValue(), fadeOut.intValue() });
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	        localCraftPlayer.getHandle().playerConnection.sendPacket(localPacketPlayOutTitle);
		}
	}

	public void sendSubtitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
		CraftPlayer localCraftPlayer = (CraftPlayer)p;
		IChatBaseComponent localIChatBaseComponent;
		PacketPlayOutTitle localPacketPlayOutTitle;
		if(subtitle != null) {
			localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	        localPacketPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, localIChatBaseComponent);
	        try {
				localPacketPlayOutTitle.getClass().getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE })
				  .newInstance(new Object[] {fadeIn.intValue(), stay.intValue(), fadeOut.intValue() });
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	        localCraftPlayer.getHandle().playerConnection.sendPacket(localPacketPlayOutTitle);
		}
	}

	public void sendFullTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		try {
			CraftPlayer localCraftPlayer = (CraftPlayer)p;
			IChatBaseComponent localIChatBaseComponent;
			PacketPlayOutTitle localPacketPlayOutTitle;
			if (title != null) {
				localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		        localPacketPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, localIChatBaseComponent);
		        localPacketPlayOutTitle.getClass().getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE })
		          .newInstance(new Object[] {fadeIn.intValue(), stay.intValue(), fadeOut.intValue() });
		        localCraftPlayer.getHandle().playerConnection.sendPacket(localPacketPlayOutTitle);
			}
			if(subtitle != null) {
				localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
		        localPacketPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, localIChatBaseComponent);
		        localPacketPlayOutTitle.getClass().getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE })
		          .newInstance(new Object[] {fadeIn.intValue(), stay.intValue(), fadeOut.intValue() });
		        localCraftPlayer.getHandle().playerConnection.sendPacket(localPacketPlayOutTitle);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendActionBar(Player p, String message) {
		CraftPlayer localCraftPlayer = (CraftPlayer)p;
	    IChatBaseComponent localIChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
	    PacketPlayOutChat localPacketPlayOutChat = new PacketPlayOutChat(localIChatBaseComponent, ChatMessageType.GAME_INFO);
	    localCraftPlayer.getHandle().playerConnection.sendPacket(localPacketPlayOutChat);
	}
}