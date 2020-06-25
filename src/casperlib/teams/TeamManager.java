package casperlib.teams;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {

	List<Team> allTeams = new ArrayList<Team>();
	
	public List<Team> getAllTeams(){
		return allTeams;
	}
	
	//Reset all teams currently created
	public void resetTeams() {
		for(int i = 0; i < allTeams.size(); i++) {
			Team t = allTeams.get(i);
			t.delete();
		}
		allTeams.clear();
	}
	
	public Team getTeam(String id) {
		for(Team t : allTeams) {
			if(t.getId() == id) {
				return t;
			}
		}
		return null;
	}
}
