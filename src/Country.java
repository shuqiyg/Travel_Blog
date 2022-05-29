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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Country {
	String name;
	ArrayList<City> cities = new ArrayList<City>();
	public Country(String name) {
		this.name = name;
	}
	
	public Boolean addCity(City newCity) {
		if(cities.contains(newCity)) {
			return false;
		};
		cities.add(newCity);
		return true;
	}
}
