package casperlib;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import casperlib.players.People;
import casperlib.players.Person;
import casperlib.teams.TeamManager;

public class CasperLib {

	private static Main main;
	private static TeamManager teamManager;

	public static void init() {
		main = Main.main;
		teamManager = new TeamManager();
	}

	public static void hideAllNametags() {
		for (Player player : main.getServer().getOnlinePlayers()) {
			Person p = People.get(player);
			p.hideAllOtherPlayersTag();
		}
	}

	public static void showAllNametags() {
		for (Player player : main.getServer().getOnlinePlayers()) {
			Person p = People.get(player);
			p.showAllOtherPlayersTag();
		}
	}

	public static TeamManager getTeamManager() {
		return teamManager;
	}

	//Return a list of all Person objects currently connected to the server
	public static List<Person> getOnlinePeople() {
		List<Person> p = new ArrayList<Person>();
		p.addAll(People.getAllValues());
		return p;
	}
	
	public static void updateAllScoreboards() {
		for(Person p : getOnlinePeople()) {
			if(p.getBoard() != null) {
				p.getBoard().update();
				p.getBoard().updateTeams();
			}
		}
	}

}
