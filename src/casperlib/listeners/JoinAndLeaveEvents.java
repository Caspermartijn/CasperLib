package casperlib.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import casperlib.events.player.PersonJoinEvent;
import casperlib.events.player.PersonLeaveEvent;
import casperlib.players.People;

public class JoinAndLeaveEvents implements Listener {

	//The class here adds a Player object to the CasperLib plugin and removes them when they leave!
	
	JavaPlugin instance;

	public JoinAndLeaveEvents(JavaPlugin instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		People.add(instance, event.getPlayer());
		PersonJoinEvent pje = new PersonJoinEvent(People.get(event.getPlayer()));
		Bukkit.getPluginManager().callEvent(pje);
		event.getPlayer().teleport(pje.getCustomSpawnLocation());
		Bukkit.getScheduler().scheduleSyncDelayedTask(instance,new Runnable() {

			@Override
			public void run() {
				event.getPlayer().teleport(pje.getCustomSpawnLocation());
			}
		
		},2l);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Bukkit.getPluginManager().callEvent(new PersonLeaveEvent(People.get(event.getPlayer())));
		People.get(event.getPlayer()).leave();
		People.remove(event.getPlayer());
	}

}
