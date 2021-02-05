package adventOfCodeThirteen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

	public static ArrayList<GuestDataLine> ReadData(String fileName) {
		ArrayList<GuestDataLine> dataLines = new ArrayList<GuestDataLine>();
		
		try {
			
		      File myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String rawDataLine = myReader.nextLine();
		        GuestDataLine guest = FormatData(rawDataLine);
		        
		        dataLines.add(guest);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return dataLines;
	}
	
	private static GuestDataLine FormatData(String rawData) {
		GuestDataLine formatedData = new GuestDataLine();
		int firstIndexAfterName = rawData.indexOf(" would");		
		formatedData.setName(	rawData.substring(0, firstIndexAfterName)	);
		
		int hapinessFirstIndex = firstIndexAfterName + 12;
		int hapinessLastIndex = rawData.indexOf(" happiness");			
		
		String happinessSTR = rawData.substring(hapinessFirstIndex, hapinessLastIndex);
		
		int hapiness = Integer.parseInt(happinessSTR);
		if(rawData.contains("would lose")) {
			hapiness  = 0 - hapiness;
		}
		formatedData.setHappinessModifier(hapiness);
		
		
		
		int neighbourIndexFirst = rawData.lastIndexOf(" ") +1;
		int neighbourIndexLast = rawData.length() -1;
		String neighbour = rawData.substring(neighbourIndexFirst, neighbourIndexLast);
		formatedData.setNeighbour(neighbour);
		
		//System.out.println(formatedData.getName() + ", " + formatedData.getHappinessModifier() + ", " + formatedData.getNeighbour());
		return formatedData;
	}
	
}
