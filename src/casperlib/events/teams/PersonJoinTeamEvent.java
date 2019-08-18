package casperlib.events.teams;

import casperlib.players.Person;
import casperlib.teams.Team;

public class PersonJoinTeamEvent extends PlayerTeamEvent {

	public PersonJoinTeamEvent(Person player, Team team) {
		super(player, team);
	}

}
