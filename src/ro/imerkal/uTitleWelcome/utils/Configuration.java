package ro.imerkal.uTitleWelcome.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import ro.imerkal.uTitleWelcome.Main;

public class Configuration {
	
	private File file;
	private YamlConfiguration cfile;
	
	public Configuration() {
		this.file = new File(Main.getInstance().getDataFolder(), "config.yml");
		this.cfile = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public boolean isExist() {
		return this.file.exists();
	}
	
	public void reload() {
		this.cfile = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public void addDefault(String path, Object value) {
		if(!this.cfile.contains(path)) {
			this.cfile.set(path, value);
		}
	}
	
	public void setup() {
		if(!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdirs();
		}
		if(!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			addDefault("Settings.TitleEnabled", Boolean.valueOf(true));
			addDefault("Settings.ActionBarEnabled", Boolean.valueOf(true));
			addDefault("Settings.ChatMessageEnabled", Boolean.valueOf(true));
			addDefault("Title.title", "&6Welcome, %player_name% !");
			addDefault("Title.subtitle", "&eThere are &6%server_online% &eonline players !");
			addDefault("Title.fadeIn", Integer.valueOf(20));
			addDefault("Title.stay", Integer.valueOf(30));
			addDefault("Title.fadeOut", Integer.valueOf(20));
			addDefault("ActionBar", "&6Welcome, %player_name% !");
			addDefault("ChatMessage", Arrays.asList(new String[]{"%prefix%&fWelcome &a%player_name% &fon my server!", "%prefix%&fDo not forget to read the rules!"}));
			addDefault("Messages.Prefix", "&c[&4uTitleWelcome&c] &r");
			addDefault("Messages.NoPerms", "&fYou do not have permission to execute this command.");
			addDefault("Messages.Usage", "&fUsage &c/utw reload");
			addDefault("Messages.Reloaded", "&fThe config.yml file has been reloaded succesfully!");
			try {
				this.cfile.save(this.file);
			}catch(IOException ex) {
				Main.getInstance().getLogger().info("Could not save config.yml file!");
			}
		}
	}
	
	public String getString(String path) {
		return ChatColor.translateAlternateColorCodes('&', this.cfile.getString(path));
	}
	
	public Integer getInt(String path) {
		return this.cfile.getInt(path);
	}
	
	public boolean getBoolean(String path) {
		return this.cfile.getBoolean(path);
	}
	
	public List<String> getStringList(String path) {
		return this.cfile.getStringList(path);
	}
	
	public String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', this.cfile.getString("Messages.Prefix"));
	}
	
	public String getMessage(String path) {
		return getPrefix() + getString(path);
	}
}