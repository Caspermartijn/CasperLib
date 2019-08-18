package casperlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import casperlib.CasperLib;
import casperlib.events.teams.TeamCreateEvent2;
import casperlib.players.Person;

public class PersonalTeamListener implements Listener {

	//Event that prioritizes over the normal TeamCreateEvent!
	
	@EventHandler
	public void teamCreateEvent2(TeamCreateEvent2 event) {
		for (Person p : CasperLib.getOnlinePeople()) {
			if (p.getBoard() != null) {
				p.getBoard().updateTeams();
			}
		}
	}

}
