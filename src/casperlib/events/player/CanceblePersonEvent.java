package casperlib.events.player;

import casperlib.players.Person;

public class CanceblePersonEvent extends PersonEvent{

	boolean canceled = false;
	
	public CanceblePersonEvent(Person person) {
		super(person);
	}

	public void setCanceled(boolean b) {
		canceled = b;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
}
