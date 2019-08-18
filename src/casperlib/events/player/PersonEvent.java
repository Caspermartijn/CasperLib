package casperlib.events.player;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import casperlib.players.Person;

public class PersonEvent extends Event {

	protected Person person;

	public PersonEvent(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}