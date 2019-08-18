package casperlib;

import org.bukkit.plugin.java.JavaPlugin;

import casperlib.listeners.JoinAndLeaveEvents;
import casperlib.listeners.PersonalPlayerListener;
import casperlib.listeners.PersonalTeamListener;

public class Main extends JavaPlugin {

	public static Main main;

	public void onEnable() {
		Main.main = this;
		CasperLib.init();
		getServer().getPluginManager().registerEvents(new JoinAndLeaveEvents(this), this);
		getServer().getPluginManager().registerEvents(new PersonalTeamListener(), this);
		getServer().getPluginManager().registerEvents(new PersonalPlayerListener(), this);
	}

	public void onDisable() {
	}

}
