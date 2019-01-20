package ro.imerkal.uTitleWelcome;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.clip.placeholderapi.PlaceholderAPI;
import ro.imerkal.uTitleWelcome.bukkit.v1_10_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_11_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_12_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_13_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_13_R2;
import ro.imerkal.uTitleWelcome.bukkit.v1_8_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_8_R2;
import ro.imerkal.uTitleWelcome.bukkit.v1_8_R3;
import ro.imerkal.uTitleWelcome.bukkit.v1_9_R1;
import ro.imerkal.uTitleWelcome.bukkit.v1_9_R2;
import ro.imerkal.uTitleWelcome.events.ActionbarTitleSendEvent;
import ro.imerkal.uTitleWelcome.events.TitleSendEvent;
import ro.imerkal.uTitleWelcome.utils.Configuration;
import ro.imerkal.uTitleWelcome.utils.VersionHandler;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

	private static Main instance;
	public VersionHandler version;
	Configuration config;
	
	public void onEnable() {
		if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			Bukkit.getPluginManager().disablePlugin(this);
			getServer().getConsoleSender().sendMessage("[uTitleWelcome] Cannot find dependency. Make sure PlaceholderAPI are installed. Plugin cannot start until dependency errors are solved!");
			return;
		}
		setInstance(this);
		getServerVersion();
		this.config = new Configuration();
		if(!this.config.isExist()) {
			this.config.setup();
		}
		getCommand("utw").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	public static Main getInstance() {
		return instance;
	}

	private void setInstance(Main instance) {
		Main.instance = instance;
	}
	
	private void getServerVersion() {
		String ver = Bukkit.getServer().getClass().getPackage().getName();
		
		ver = ver.substring(ver.lastIndexOf('.') + 1);
		
		switch(ver) {
		case "v1_10_R1":
			this.version = new v1_10_R1();
			break;
		case "v1_11_R1":
			this.version = new v1_11_R1();
			break;
		case "v1_12_R1":
			this.version = new v1_12_R1();
			break;
		case "v1_13_R1":
			this.version = new v1_13_R1();
			break;
		case "v1_13_R2":
			this.version = new v1_13_R2();
			break;
		case "v1_8_R1":
			this.version = new v1_8_R1();
			break;
		case "v1_8_R2":
			this.version = new v1_8_R2();
			break;
		case "v1_8_R3":
			this.version = new v1_8_R3();
			break;
		case "v1_9_R1":
			this.version = new v1_9_R1();
			break;
		case "v1_9_R2":
			this.version = new v1_9_R2();
			break;
		}
		if(this.version == null) {
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
	
	void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		TitleSendEvent e = new TitleSendEvent(p, title, null, fadeIn, stay, fadeOut);
		if(!this.config.getBoolean("Settings.TitleEnabled")) {
			e.setCancelled(true);
			return;
		}
		this.version.sendTitle(p, fadeIn, stay, fadeOut, title);
	}
	
	void sendSubtitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
		TitleSendEvent e = new TitleSendEvent(p, null, subtitle, fadeIn, stay, fadeOut);
		if(!this.config.getBoolean("Settings.TitleEnabled")) {
			e.setCancelled(true);
			return;
		}
		this.version.sendSubtitle(p, fadeIn, stay, fadeOut, subtitle);
	}
	
	void sendFullTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		TitleSendEvent e = new TitleSendEvent(p, title, subtitle, fadeIn, stay, fadeOut);
		if(!this.config.getBoolean("Settings.TitleEnabled")) {
			e.setCancelled(true);
			return;
		}
		if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			this.version.sendFullTitle(p, fadeIn, stay, fadeOut, PlaceholderAPI.setPlaceholders(p, title), PlaceholderAPI.setPlaceholders(p, subtitle));
		}else {
			this.version.sendFullTitle(p, fadeIn, stay, fadeOut, title, subtitle);
		}
	}
	
	void sendActionBar(Player p, String message) {
		ActionbarTitleSendEvent e = new ActionbarTitleSendEvent(p, message);
		if(!this.config.getBoolean("Settings.ActionBarEnabled")) {
			e.setCancelled(true);
			return;
		}
		if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			this.version.sendActionBar(p, PlaceholderAPI.setPlaceholders(p, message));
		}else {
			this.version.sendActionBar(p, message);
		}
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		sendActionBar(p, this.config.getString("ActionBar"));
		sendFullTitle(p,
				this.config.getInt("Title.fadeIn"),
				this.config.getInt("Title.stay"),
				this.config.getInt("Title.fadeIn"),
				this.config.getString("Title.title"),
				this.config.getString("Title.subtitle"));
		if(this.config.getBoolean("Settings.ChatMessageEnabled")) {
			for(String msg : this.config.getStringList("ChatMessage")) {
				if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, msg.replace("%prefix%", this.config.getPrefix()))));
				}else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.replace("%prefix%", this.config.getPrefix())));
				}
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)) {
			if(args.length == 0) {
				cs.sendMessage(this.config.getMessage("Messages.Usage"));
			}else {
				this.config.reload();
				cs.sendMessage(this.config.getMessage("Messages.Reloaded"));
			}
		}else {
			Player p = (Player) cs;
			if(args.length == 0) {
				p.sendMessage(this.config.getMessage("Messages.Usage"));
			}else {
				if(p.hasPermission("utw.reload")) {
					this.config.reload();
					p.sendMessage(this.config.getMessage("Messages.Reloaded"));
				}else {
					p.sendMessage(this.config.getMessage("Messages.NoPerms"));
				}
			}
		}
		return false;
	}
}