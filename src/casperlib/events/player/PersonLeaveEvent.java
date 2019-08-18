package casperlib.events.player;

import casperlib.players.Person;

public class PersonLeaveEvent extends PersonEvent{

	public PersonLeaveEvent(Person p) {
		super(p);
	}

}
