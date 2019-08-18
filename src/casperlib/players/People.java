package casperlib.players;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class People {

	static HashMap<Player, Person> people = new HashMap<Player, Person>();

	public static Person get(Player player) {
		return people.get(player);
	}

	//Get a person using a Players getName()
	public static Person get(String name) {
		Person p = null;
		if (true) {
			for (Person person : people.values()) {
				if (person.player.getName().equals(name)) {
					p = person;
				}
			}
		}

		return p;
	}

	//Get a person using a Player UUID
	public static Person get(UUID uuid) {
		Person p = null;
		if (true) {
			for (Person person : people.values()) {
				if (person.player.getUniqueId().equals(uuid)) {
					p = person;
				}
			}
		}
		return p;
	}

	//Add a person to the plugin!
	public static void add(JavaPlugin instance, Player p) {
		Person person = new Person(instance, p);
		people.put(p, person);
	}

	//Remove a player from the plugin
	public static void remove(Player player) {
		people.remove(player);
	}

	//Get a collection of all players
	public static Collection<? extends Person> getAllValues() {
		return people.values();
	}

}
