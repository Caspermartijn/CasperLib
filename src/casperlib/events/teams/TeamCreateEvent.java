package casperlib.events.teams;

import casperlib.teams.Team;

public class TeamCreateEvent extends TeamEvent {

	public TeamCreateEvent(Team team) {
		super(team);
	}

}
