package casperlib.Scoreboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import casperlib.CasperLib;
import casperlib.Main;
import casperlib.players.Person;

public class SBoard {

	Scoreboard board;
	Objective objective;

	HashMap<Team, Item> allItems = new HashMap<Team, Item>();

	HashMap<casperlib.teams.Team, Team> cTeamsWsTeams = new HashMap<casperlib.teams.Team, Team>();

	Team ownTeam;

	public SBoard(String displayName, List<Item> items) {
		board = Main.main.getServer().getScoreboardManager().getNewScoreboard();
		objective = board.registerNewObjective("dummy", "dummy");
		objective.setDisplayName(displayName);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		ownTeam = board.registerNewTeam("own");
		for (Item i : items) {
			addItem(i);
		}
		show();
	}

	public Scoreboard getBoard() {
		return board;
	}

	//Update the scoreboard
	public void update() {
		for (Team t : allItems.keySet()) {
			Item item = allItems.get(t);
			t.setSuffix(item.getDataPrefix() + item.getDataValue());
		}
	}

	//Private method to add a item to the scoreboard
	private void addItem(Item item) {
		Team t = board.registerNewTeam(item.getId());
		t.setSuffix(item.getDataPrefix() + item.getDataValue());
		t.addEntry(item.getPrefix());

		if (!allItems.containsKey(item.getDataValue()) && !allItems.containsValue(item))
			allItems.put(t, item);
	}

	//Get an item by id
	public Item getItem(String id) {
		Item i = null;
		for (Item item : allItems.values()) {
			if (item.getId().toLowerCase() == id.toLowerCase()) {
				i = item;
				break;
			}
		}
		return i;
	}

	//Hide the scoreboard
	public void hide() {
		for (String p : board.getEntries()) {
			board.resetScores(p);
		}
	}

	//Show the scoreboard
	public void show() {
		for (Team t : allItems.keySet()) {
			Item item = allItems.get(t);
			objective.getScore(item.getPrefix()).setScore(item.getPosition());
		}
	}

	//Private method to add an player to the scoreboard team!
	public void addToOwnTeam(Player player) {
		ownTeam.addEntry(player.getName());
	}

	//Get the players own Scoreboard team!
	public Team getOwnTeam() {
		return ownTeam;
	}

	//Unregister the intire SBoard
	public void unregister() {
		for (Team t : board.getTeams()) {
			t.unregister();
		}
		objective.unregister();
		board = Main.main.getServer().getScoreboardManager().getNewScoreboard();
	}

	//Update the scoreboard teams to CasperLib teams
	public void updateTeams() {
		for (casperlib.teams.Team t : CasperLib.getTeamManager().getAllTeams()) {
			if (!cTeamsWsTeams.containsKey(t)) {
				cTeamsWsTeams.put(t, board.registerNewTeam(t.getId()));
				Team scoreboardTeam = cTeamsWsTeams.get(t);

				scoreboardTeam.setPrefix(t.getTeamTag());

				for (Person p : t.getAllParticipants()) {
					scoreboardTeam.addEntry(p.player.getName());
				}
			}

			if (t.hasChanged()) {
				Team scoreboardTeam = cTeamsWsTeams.get(t);
				scoreboardTeam.setPrefix(t.getTeamTag());

				for (String player : scoreboardTeam.getEntries()) {
					scoreboardTeam.removeEntry(player);
				}

				for (Person p : t.getAllParticipants()) {
					scoreboardTeam.addEntry(p.player.getName());
				}

				t.doneUpdate();
			}
		}
	}

	//Delete a CasperLibTeam from the scoreboard team!
	public void deleteTeam(casperlib.teams.Team team) {
		if (cTeamsWsTeams.containsKey(team)) {
			Team t = cTeamsWsTeams.get(team);
			t.unregister();
		}
		cTeamsWsTeams.remove(team);
	}

	public Collection<Item> getAllItems() {
		return allItems.values();
	}

}
