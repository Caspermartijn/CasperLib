package casperlib.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import casperlib.events.player.PersonDamagedByPersonEvent;
import casperlib.players.People;
import casperlib.players.Person;

public class PersonalPlayerListener implements Listener {

	//This listener is meant to serve as my own listener for the team and person related methods
	
	@EventHandler
	public void edbee(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			
			Person p = People.get(((Player) event.getEntity()));
			Person d = People.get(((Player) event.getDamager()));
			
			PersonDamagedByPersonEvent pdbpe = new PersonDamagedByPersonEvent(event, p, d);
			Bukkit.getPluginManager().callEvent(pdbpe);
			
			if(pdbpe.isCanceled()) {
				event.setCancelled(true);
			}
		}
	}

}
