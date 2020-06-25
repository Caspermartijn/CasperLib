package casperlib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import casperlib.events.player.PersonJoinEvent;
import casperlib.listeners.JoinAndLeaveEvents;
import casperlib.listeners.PersonalPlayerListener;
import casperlib.listeners.PersonalTeamListener;
import casperlib.players.People;

public class Main extends JavaPlugin {

	public static Main main;

	public void onEnable() {
		Main.main = this;
		CasperLib.init();
		getServer().getPluginManager().registerEvents(new JoinAndLeaveEvents(this), this);
		getServer().getPluginManager().registerEvents(new PersonalTeamListener(), this);
		getServer().getPluginManager().registerEvents(new PersonalPlayerListener(), this);
		for(Player player : Bukkit.getOnlinePlayers()) {
			People.add(this,player);
			PersonJoinEvent pje = new PersonJoinEvent(People.get(player));
			Bukkit.getPluginManager().callEvent(pje);
			if (pje.getCustomSpawnLocation() != null) {
				player.teleport(pje.getCustomSpawnLocation());
				Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						player.teleport(pje.getCustomSpawnLocation());
					}

				}, 1l);
			}
		}
	}

	public void onDisable() {
	}

}
