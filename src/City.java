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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class City {
	String name, description;
	LocalDate from, to;
	int rating;
	String imageURL;
	public City(String name, String desc, LocalDate from, LocalDate to, int rating) {
		this.name = name;
		this.description = desc;
		this.from = from;
		this.to = to;
		this.rating = rating;
	}
	
	
	public void addImage(String url) {
		imageURL = url;
	}
	public String getName() {
		return this.name;
	}
	public String getDesc() {
		return this.description;
	}
	public LocalDate getFrom() {
		return this.from;
	}
	public LocalDate getTo() {
		return this.to;
	}
	public int getRating() {
		return this.rating;
	}
	public String getURLs(){
		return this.imageURL;
	}
}
