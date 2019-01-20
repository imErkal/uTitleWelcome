package ro.imerkal.uTitleWelcome.utils;

import org.bukkit.entity.Player;

public interface VersionHandler {
	
	public void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title);
	
	public void sendSubtitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle);
	
	public void sendFullTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle);
	
	public void sendActionBar(Player p, String message);
}