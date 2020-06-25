package casperlib.config.objects;

import lombok.Getter;
import lombok.Setter;

public class Message {

	@Getter private String id;
	@Getter @Setter private String message;
	
	public Message(String id, String message) {
		this.id = id;
		this.message = message;
	}
	
}
