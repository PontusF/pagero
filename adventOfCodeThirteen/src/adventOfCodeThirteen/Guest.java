package adventOfCodeThirteen;

import java.util.HashMap;

public class Guest {
	
	private HashMap<String, Integer> opinions =  new HashMap<String, Integer>();
	private String name;
	private int id;
	public Guest(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public void SetOpinion(Guest otherGuest, int opinion){
		opinions.put(otherGuest.getName(), opinion);
	}
	
	public int getOpinion(Guest guest) {
		return opinions.get(guest.getName());
	}
	public int getOpinion(String guestName) {
		return opinions.get(guestName);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
