package casperlib.objects;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

public class Data {

	//This class is meant to store custom data of a player!
	
	HashMap<String, Object> data = new HashMap<String, Object>();

	//Reset all the data
	public void reset() {
		data.clear();
	}

	//Remove data from the HashMap
	public void remove(String s) {
		data.remove(s.toLowerCase());
	}

	//Check if data is in the HashMap
	public boolean contains(String s) {
		return data.containsKey(s.toLowerCase());
	}

	//Set a data in the HashMap
	public void set(String s, Object b) {
		if (data.containsKey(s.toLowerCase())) {
			data.remove(s.toLowerCase());
			data.put(s.toLowerCase(), b);
		} else {
			data.put(s.toLowerCase(), b);
		}
	}

	//Get a String from the data
	public String getString(String s) {
		return (String) data.get(s.toLowerCase());
	}

	//Get a Boolean from the data
	public boolean getBoolean(String s, boolean defaultB) {
		if (data.containsKey(s.toLowerCase())) {
			return (boolean) data.get(s.toLowerCase());
		} else {
			set(s.toLowerCase(), defaultB);
			return defaultB;
		}
	}

	//Get a Double from the data
	public double getDouble(String s, double defaultD) {
		if (data.containsKey(s.toLowerCase())) {
			return (double) data.get(s.toLowerCase());
		} else {
			set(s.toLowerCase(), defaultD);
			return defaultD;
		}
	}

	//Get a Float from the data
	public float getFloat(String s, float defaultF) {
		if (data.containsKey(s.toLowerCase())) {
			return (float) data.get(s.toLowerCase());
		} else {
			set(s.toLowerCase(), defaultF);
			return defaultF;
		}
	}

	//Get a int from the data
	public int getInt(String s, int defaultI) {
		if (data.containsKey(s.toLowerCase())) {
			return (int) data.get(s.toLowerCase());
		} else {
			set(s.toLowerCase(), defaultI);
			return defaultI;
		}
	}

	//Get a String from the data
	public String getString(String s, String defaultS) {
		if (data.containsKey(s.toLowerCase())) {
			return (String) data.get(s.toLowerCase());
		} else {
			set(s.toLowerCase(), defaultS);
			return defaultS;
		}
	}

	// With no default value

	public int getInt(String s) {
		return (int) data.get(s.toLowerCase());
	}

	@SuppressWarnings("unchecked")
	public List<String> getStringList(String s) {
		return (List<String>) data.get(s.toLowerCase());
	}

	public boolean getBoolean(String s) {
		return (boolean) data.get(s.toLowerCase());
	}

	public double getDouble(String s) {
		return (double) data.get(s.toLowerCase());
	}

	public float getFloat(String s) {
		return (float) data.get(s.toLowerCase());
	}

	public HashMap<String, Object> getData() {
		return data;
	}
	
	public Location getLocation(String s) {
		return (Location) data.get(s.toLowerCase());
	}

}
