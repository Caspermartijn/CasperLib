package casperlib.events.teams;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import casperlib.teams.Team;

public class TeamEvent extends Event{

	protected Team team;
	protected boolean isCancelled = false;
	
    public TeamEvent(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}
	
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

	public boolean isCancelled() {
		return isCancelled;
	}

	private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
