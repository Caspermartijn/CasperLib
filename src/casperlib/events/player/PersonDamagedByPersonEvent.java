package casperlib.events.player;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import casperlib.players.Person;

public class PersonDamagedByPersonEvent extends CanceblePersonEvent {

	Person damager;
	private EntityDamageByEntityEvent event;

	public PersonDamagedByPersonEvent(EntityDamageByEntityEvent event, Person damaged, Person damager) {
		super(damaged);
		this.damager = damager;
		this.event = event;
	}

	public Person getDamager() {
		return damager;
	}

	public double getDamage() {
		return event.getDamage();
	}

	public void setDamage(double damage) {
		event.setDamage(damage);
	}

}
