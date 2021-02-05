package adventOfCodeThirteen;

import java.util.ArrayList;

public class KnightsOfTheDinnerTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Table table = new Table();			
		ArrayList<GuestDataLine> inputLines = DataReader.ReadData("data.txt");
		
		int guestID = 0;
		for(GuestDataLine guestDataLine:inputLines) {
			String nameCandidate = guestDataLine.getName();
			if(!table.Contains(nameCandidate)) {				
				table.addGuests( 	new Guest(nameCandidate, guestID) 	);
				guestID++;
			}			
		}
		
		for(GuestDataLine data:inputLines) {
			table.addOpinion(data.getName(), data.getHappinessModifier(), data.getNeighbour());
		}
		
	
		System.out.println("part 1=" + table.OptimalHappinessTotal());
		
		
		int oldGuestAmount = table.guestAmount();	
		int pontusId = guestID;
		Guest pontus = new Guest("Pontus", guestID);	
		
		table.addGuests(pontus);
	
		
		for(int i = 0; i < oldGuestAmount;i++) {
			table.addOpinion(i, 0, pontusId);
			table.addOpinion(pontusId, 0, i);
		}

	
		System.out.println("part 2: " +table.OptimalHappinessTotal());
		
	}

}
