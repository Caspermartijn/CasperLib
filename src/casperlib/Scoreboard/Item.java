package casperlib.Scoreboard;

public class Item {

	private int position = 0;
	private String id;
	private String prefix;
	private String dataValue;
	private String dataPrefix;

	public Item(int position, String id, String prefix, String dataValue, String dataPrefix) {
		this.position = position;
		this.id = id.toLowerCase();
		this.prefix = prefix;
		this.dataValue = dataValue;
		this.dataPrefix = dataPrefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public Object getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public int getPosition() {
		return position;
	}

	public String getId() {
		return id.toLowerCase();
	}

	public String getDataPrefix() {
		return dataPrefix;
	}

}
