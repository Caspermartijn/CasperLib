package casperlib.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import casperlib.CasperLib;
import casperlib.Main;
import casperlib.events.teams.PersonChangeTeamEvent;
import casperlib.events.teams.PersonJoinTeamEvent;
import casperlib.events.teams.PersonLeaveTeamEvent;
import casperlib.events.teams.TeamChangeEvent;
import casperlib.events.teams.TeamCreateEvent;
import casperlib.events.teams.TeamCreateEvent2;
import casperlib.objects.Data;
import casperlib.players.People;
import casperlib.players.Person;

public class Team {

	List<Person> allParticipants = new ArrayList<Person>();

	private final String id;
	private String displayName;
	private int maxPlayersInTeam;
	private final boolean unlimitedPlayers;
	private Data data = new Data();

	private ChatColor teamColor = ChatColor.RESET;

	private boolean changed = false;

	// Team options
	boolean showDisplayNameOnPlayerTag = false;

	public Team(String id, String displayName) {
		this.id = id.toLowerCase();
		unlimitedPlayers = true;

		createTeam(displayName);
	}

	public Team(String id, String displayName, int maxPlayersInTeam) {
		this.id = id.toLowerCase();
		this.maxPlayersInTeam = maxPlayersInTeam;
		unlimitedPlayers = false;

		createTeam(displayName);
	}

	//Private method for constructor to initialize a team to avoid code duplication
	private void createTeam(String displayName) {
		this.displayName = displayName;

		CasperLib.getTeamManager().allTeams.add(this);
		Bukkit.getPluginManager().callEvent(new TeamCreateEvent(this));
		Bukkit.getScheduler().runTaskLater(Main.main,
				() -> Bukkit.getPluginManager().callEvent(new TeamCreateEvent2(this)), 1l);
	}

	//Change certein team options
	public void setOption(TeamOption option, boolean b) {
		switch (option) {
		case DisplayTeamNameOnPlayerTag:
			showDisplayNameOnPlayerTag = b;
			break;
		default:
			break;
		}
	}

	//Get a team option!
	public boolean getOption(TeamOption option) {
		switch (option) {
		case DisplayTeamNameOnPlayerTag:
			return showDisplayNameOnPlayerTag;
		default:
			return false;
		}
	}

	public Data getData() {
		return data;
	}

	public void setTeamColor(ChatColor color) {
		this.teamColor = color;
		changeTeam();
	}

	public ChatColor getTeamColor() {
		return teamColor;
	}

	public String getTeamTag() {
		String tag = "";
		if (showDisplayNameOnPlayerTag) {
			tag = getDisplayName() + " ";
		}
		tag += "" + teamColor;
		return tag;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		changeTeam();
	}

	public int getMaxPlayersInTeam() {
		return maxPlayersInTeam;
	}

	public void setMaxPlayersInTeam(int maxPlayersInTeam) {
		this.maxPlayersInTeam = maxPlayersInTeam;
	}

	public List<Person> getAllParticipants() {
		return allParticipants;
	}

	public String getId() {
		return id;
	}

	public boolean isUnlimitedPlayers() {
		return unlimitedPlayers;
	}

	//Delete the intire team!
	public void delete() {
		for (Person p : allParticipants) {
			p.setTeam(null);
		}
		CasperLib.getTeamManager().allTeams.remove(this);
		for (Person p : CasperLib.getOnlinePeople()) {
			p.getBoard().deleteTeam(this);
		}
	}

	//Check if a person is in the team
	public boolean isInTeam(Person p) {
		return allParticipants.contains(p);
	}

	// Add a person into the team
	public void join(Person p) {
		if (!allParticipants.contains(p)) {
			if(p.isInTeam()) {
				PersonChangeTeamEvent changeEvent = new PersonChangeTeamEvent(p, p.getTeam(), this);
				Bukkit.getPluginManager().callEvent(changeEvent);
				if(changeEvent.isCancelled()) {
					changed = false;
					return;
				}
			}
			PersonJoinTeamEvent event = new PersonJoinTeamEvent(p, this);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
				allParticipants.add(p);
				p.setTeam(this);
				System.out.println(p.player.getName() + " joined " + displayName);
				changed = true;
				Bukkit.getPluginManager().callEvent(new TeamChangeEvent(this));
			}
		}
	}
	
	// Remove a person from a team!
	public boolean leave(Person p) {
		if (allParticipants.contains(p)) {
			PersonLeaveTeamEvent event = new PersonLeaveTeamEvent(p, this);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
				allParticipants.remove(p);
				p.clearTeam();
			}
			changed = true;
			Bukkit.getPluginManager().callEvent(new TeamChangeEvent(this));
			return true;
		} else {
			return false;
		}
	}

	// Add a person using the Player object
	public void join(Player pl) {
		Person p = People.get(pl);
		join(p);
	}
	
	// Remove a person using the Player object
	public boolean leave(Player pl) {
		Person p = People.get(pl);
		return leave(p);
	}

	//Get the nearest teammate to a Person
	public Person getClosestTeammate(Person p) {
		Person pp = null;
		if (true) {
			for (Person person : allParticipants) {
				if (person != p) {
					if (pp == null) {
						pp = person;
					} else {
						if (p.player.getLocation().distance(pp.player.getLocation()) < p.player.getLocation()
								.distance(person.player.getLocation())) {
							pp = person;
						}
					}
				}
			}
		}
		return pp;
	}

	// Call the changeTeam method!
	public void changeTeam() {
		Bukkit.getPluginManager().callEvent(new TeamChangeEvent(this));
		changed = true;
	}

	public boolean hasChanged() {
		return changed;
	}

	public void doneUpdate() {
		changed = false;
	}

	// Force leave a player out of a team! (This will bypass the events)
	public void forceLeave(Person person) {
		allParticipants.remove(person);
		person.clearTeam();
		changed = true;
	}

	public boolean contains(Person person) {
		return person.getTeam() == this;
	}
	
}
