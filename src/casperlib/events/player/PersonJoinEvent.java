package casperlib.events.player;

import org.bukkit.Location;

import casperlib.players.Person;

public class PersonJoinEvent extends PersonEvent{

	private Location loc;
	
	public PersonJoinEvent(Person person) {
		super(person);
	}
	
	public void setCustomSpawnLocation(Location loc) {
		this.loc = loc;
	}

	public Location getCustomSpawnLocation() {
		return loc;
	}
	
}
