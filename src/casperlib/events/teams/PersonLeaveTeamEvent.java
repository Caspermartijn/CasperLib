package casperlib.events.teams;

import casperlib.players.Person;
import casperlib.teams.Team;

public class PersonLeaveTeamEvent extends PlayerTeamEvent {

	public PersonLeaveTeamEvent(Person player, Team team) {
		super(player, team);
	}

}
