import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/********************************************** 
Workshop # 8
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-28
**********************************************/ 


public class TravelLog {
	ArrayList<Country> countries = new ArrayList<Country>();
	public TravelLog() {
		 
	}

	public Boolean addCountry(Country newCountry) {
		if(this.countries.contains(newCountry)) {
			return false;
		};
		countries.add(newCountry);
		return true;
	
	}
//	public static final Operation fileterByR = (Set<Country> cts)->{
//		
//	};
}
