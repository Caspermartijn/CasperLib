package casperlib.events.teams;

import casperlib.players.Person;
import casperlib.teams.Team;

public class PersonChangeTeamEvent extends PlayerTeamEvent {

	Team oldTeam;
	
	public PersonChangeTeamEvent(Person player, Team oldTeam, Team newTeam) {
		super(player, newTeam);
		this.oldTeam = oldTeam;
	}
	
	public Team getNewTeam() {
		return super.getTeam();
	}
	
	public Team getOldTeam() {
		return oldTeam;
	}

}
