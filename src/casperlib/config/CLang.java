package casperlib.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import casperlib.config.objects.Language;
import casperlib.config.objects.Message;

public class CLang extends CConfig {

	private HashMap<String, Message> messages = new HashMap<String, Message>();

	public CLang(List<Language> languages, JavaPlugin instance) {
		super("lang", instance);
		
		System.out.println("[CasperLib] Loading: " + super.FILENAME);
		
		if (justCreated) {
			for (Message m : messages) {
				this.messages.put(m.getId(), m);
				String id = m.getId();
				String message = m.getMessage();

				set(id, message);
			}
			save();
		} else {
			for (String id : getConfig().getKeys(true)) {
				this.messages.put(id, new Message(id, getConfig().getString(id)));
			}
		}

		// Here I am going to check if their is a difference between the given messages
		// and the read messages becouse I want to be sure the file is actually up to
		// date
		if (!justCreated) {
			List<Message> potentialErrorMessages = new ArrayList<Message>();
			for (Message m : messages) {
				if (!this.messages.containsKey(m.getId())) {
					potentialErrorMessages.add(m);
				}
			}
			
			for(Message m : potentialErrorMessages) {
				set(m.getId(), m.getMessage());
			}
			save();
		}
	}

	public Message getMessage(String id) {
		return messages.get(id);
	}

}