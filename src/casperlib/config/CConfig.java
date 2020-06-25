package casperlib.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CConfig {
	
	//This class is made to make making config's easier!
	
	private final JavaPlugin PLUGIN;
	protected final String FILENAME;
	private final File FOLDER;
	private FileConfiguration config;
	private File configFile;
	public boolean justCreated = false;

	public CConfig(String filename, JavaPlugin instance) {
		if (!filename.endsWith(".yml")) {
			filename += ".yml";
		}
		this.FILENAME = filename;
		this.PLUGIN = instance;
		this.FOLDER = this.PLUGIN.getDataFolder();
		this.config = null;
		this.configFile = null;
		reload();
	}

	public CConfig(File folder, String filename, JavaPlugin instance) {
		if (!filename.endsWith(".yml")) {
			filename += ".yml";
		}
		this.FILENAME = filename;
		this.PLUGIN = instance;
		this.FOLDER = folder;
		this.config = null;
		this.configFile = null;
		reload();
	}

	public FileConfiguration getConfig() {
		if (config == null) {
			reload();
		}
		return config;
	}

	public void reload() {
		if (!this.FOLDER.exists()) {
			try {
				if (this.FOLDER.mkdir()) {
					System.out.println("Folder " + this.FOLDER.getName() + " created.");
				} else {
					System.out.println("Unable to create folder " + this.FOLDER.getName() + ".");
				}
			} catch (Exception e) {

			}
		}
		configFile = new File(this.FOLDER, this.FILENAME);
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				justCreated = true;
			} catch (IOException e) {

			}
		}
		config = YamlConfiguration.loadConfiguration(configFile);
	}

	public void saveDefaultConfig() {
		if (configFile == null) {
			configFile = new File(this.PLUGIN.getDataFolder(), this.FILENAME);
		}

		if (!configFile.exists()) {
			this.PLUGIN.saveResource(this.FILENAME, false);
		}
	}

	public void saveNew() {
		if (config == null || configFile == null) {
			return;
		}
		try {
			saveDefaultConfig();
			getConfig().save(configFile);
		} catch (IOException ex) {
			System.out.println("Could not save config to " + configFile.getName());
		}
	}
	
	public void save() {
		if (config == null || configFile == null) {
			return;
		}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			System.out.println("Could not save config to " + configFile.getName());
		}
	}

	public void set(String path, Object o) {
		getConfig().set(path, o);
	}
	
	public void setLocation(String path, Location l) {
		getConfig().set(path + ".w", l.getWorld().getName());
		getConfig().set(path + ".x", l.getX());
		getConfig().set(path + ".y", l.getY());
		getConfig().set(path + ".z", l.getZ());
		getConfig().set(path + ".yaw", l.getYaw());
		getConfig().set(path + ".pitch", l.getPitch());
		save();
	}

	public Location getLocation(String path) {
		if(!getConfig().contains(path)) {
			Location l = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
			return l;
		}
		Location l = new Location(Bukkit.getWorld(getConfig().getString(path + ".w")),
				getConfig().getDouble(path + ".x"), getConfig().getDouble(path + ".y"),
				getConfig().getDouble(path + ".z"), Float.parseFloat("" + getConfig().getDouble(path + ".yaw")),
				Float.parseFloat("" + getConfig().getDouble(path + ".pitch")));
		return l;
	}
	
	public void reset() {
		config = null;
		reload();
	}
}
