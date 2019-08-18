package casperlib.events.teams;

import casperlib.players.Person;
import casperlib.teams.Team;

public class PlayerTeamEvent extends TeamEvent {

	Person p;
	
	public PlayerTeamEvent(Person player, Team team) {
		super(team);
		p = player;
	}

	public Person getPlayer() {
		return p;
	}

}
