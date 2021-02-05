package adventOfCodeThirteen;

import java.util.ArrayList;

public class Table {
	private ArrayList<Guest> guests = new ArrayList<Guest>();
	private ArrayList<Guest[]> permutations = new ArrayList<Guest[]>();
	
	
	
	public void addGuests(Guest guest) {
		guests.add(guest);
	}
	public void addOpinion(String name, int opinion, String neighbour) {
		Guest guest = getGuest(name);
		Guest neighbourGuest = getGuest(neighbour);
		guest.SetOpinion(neighbourGuest, opinion);
	}
	public void addOpinion(int idGuest, int opinion, int idNeighbour) {
		String guestName = getGuest(idGuest).getName();
		String neighbourname = getGuest(idNeighbour).getName();
		
		addOpinion(guestName,opinion, neighbourname);
	}
	
	
	public boolean Contains(String name) {
		return getGuest(name) != null;	
	}
	private Guest getGuest(int id) {
		for(Guest guest:guests) {
			if (guest.getId() == id){
				return guest;
			}
		}
		return null;
	}
	public int guestAmount() {
		return guests.size();
	}
	
	private Guest getGuest(String name) {
		for(Guest guest:guests) {
			if (guest.getName().equals(name)){
				return guest;
			}
		}
		return null;
	}
	
	public int OptimalHappinessTotal() {

		int optimalHapiness = -9999999;
		permutations = new ArrayList<Guest[]>();
		calcAllPermutations();
		
		for(Guest[] permutation: permutations) {
			int thisHapiness = HappinessTotal(permutation);
			if(thisHapiness > optimalHapiness) {
				optimalHapiness = thisHapiness;
			}
		}
		
		return optimalHapiness;
	}
	
	private void  calcAllPermutations(){
		int guestAmount = guests.size();
		ArrayList<Guest> copyOfList = new ArrayList<Guest>(guests);
		ArrayList<Guest> guestPermutations= new ArrayList<Guest>();
		
		//magic
		RecursiveGetGuestPermutations(guestPermutations, copyOfList);
		
		return;
	}
	
	private ArrayList<Guest> RecursiveGetGuestPermutations(ArrayList<Guest> pendingPermutation, ArrayList<Guest> NotYetUsed){
		//If complete permutation created aka in the deepest recursion.
		if(NotYetUsed.size() == 0) {			
			Guest[] permutation = new Guest[pendingPermutation.size()];
			for(int i = 0; i < pendingPermutation.size(); i++) {
				permutation[i] = pendingPermutation.get(i);
			}
			permutations.add(permutation);			
			return pendingPermutation;
		}			

		//which values "This" function call still hasn't shifted through to add to pendingPermutations"
		ArrayList<Guest> usableNow = new ArrayList<Guest>(NotYetUsed);
		//which values the next function call to RecursiveGetGuestPermutations() are allowed to shift through."
		ArrayList<Guest> usableNextCall = new ArrayList<Guest>(NotYetUsed);
		Guest currentGuest = GetLowestIdGuest(usableNow);
		
		while(usableNow.size()!=0) {

			usableNow.remove(currentGuest);
			usableNextCall.remove(currentGuest);
			
			pendingPermutation.add(currentGuest);
			RecursiveGetGuestPermutations(pendingPermutation,usableNextCall);

			Guest nextGuest = GetLowestIdGuest(usableNow);
			usableNextCall.add(currentGuest);
			pendingPermutation.remove(currentGuest);
			currentGuest = nextGuest;
		}	
		
		return pendingPermutation;

	}
	
	private void PrintGuestArrayIds(ArrayList<Guest> guests) {
		 String ids = "";
		 
			for(Guest guest: guests) {
				ids += guest.getId();
			}
			System.out.println(ids);
	}

	private Guest GetLowestIdGuest(ArrayList<Guest> array) {
		int lowestId = 99999;
		Guest lowestGuest = null;		
			for(Guest guest:array) {
				if(guest.getId() < lowestId) {
					lowestId = guest.getId();
					lowestGuest = guest;
				}
			}		

		return lowestGuest;
	}
	
	
	private int HappinessTotal(Guest[] orderedTable) {
		String guestOrder = "";
		
		/*
		 
			guestOrder += guest.getName();
		}
		System.out.println(guestOrder);
		*/
		
		//EdgeCases index 0 next		
		int total = orderedTable[0].getOpinion(		orderedTable[orderedTable.length -1 ]		);
		total += orderedTable[0].getOpinion(		orderedTable[1]		);
		
		//EdgeCases index Last		
		total += orderedTable[orderedTable.length-1].getOpinion(		orderedTable[orderedTable.length-2]		);		
		total += orderedTable[orderedTable.length-1].getOpinion(		orderedTable[0]		);
		
		for(int i = 1; i < orderedTable.length-1; i++) {
			total += orderedTable[i].getOpinion(		orderedTable[i-1]		);		
			total += orderedTable[i].getOpinion(		orderedTable[i+1]		);
		}
	
		return total;
	}
}
