package casperlib.players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import casperlib.Scoreboard.Item;
import casperlib.Scoreboard.SBoard;
import casperlib.objects.Data;
import casperlib.teams.Team;

public class Person {

	public Player player;

	private Team team;

	private Data data = new Data();

	private SBoard board;

	JavaPlugin instance;

	public Person(JavaPlugin instance, Player p) {
		player = p;
		this.instance = instance;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		if (this.team != null) {
			this.team.forceLeave(this);
		}
		this.team = team;
	}

	public boolean isInTeam() {
		return team != null;
	}

	public Data getData() {
		return data;
	}

	//Display a sidebar scoreboard to a person using a displayname and items
	public void createSBoard(String displayName, List<Item> items) {
		if (board != null) {
			board.unregister();
		}
		board = new SBoard(displayName, items);
		player.setScoreboard(board.getBoard());
	}

	public SBoard getBoard() {
		return board;
	}

	public JavaPlugin getInstance() {
		return instance;
	}

	//Hide all other players their nametag
	public void hideAllOtherPlayersTag() {
		if (board == null) {
			board = new SBoard("doodoo", new ArrayList<Item>());
			board.hide();
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (getTeam() != null) {
				Person p = People.get(player);
				if (!getTeam().getAllParticipants().contains(p)) {
					board.addToOwnTeam(player);
				}
				if (p == this) {
					if (!board.getOwnTeam().hasEntry(player.getName())) {
						board.addToOwnTeam(player);
					}
				}
			} else {
				board.addToOwnTeam(player);
			}
		}
		if (getTeam() != null) {
			board.getOwnTeam().setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OWN_TEAM);
		} else {
			board.getOwnTeam().setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		}

	}

	//Show all others players name tag
	public void showAllOtherPlayersTag() {
		if (board != null) {
			for (String entry : board.getOwnTeam().getEntries()) {
				board.getOwnTeam().removeEntry(entry);
			}
			board.getOwnTeam().setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		}
	}

	//Kick the player from their team!
	public void leave() {
		if (team != null) {
			team.forceLeave(this);
		}
	}

	//Clear the players team variable
	public void clearTeam() {
		team = null;
	}
}
