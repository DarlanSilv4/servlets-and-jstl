package br.com.agenda.model;

import java.lang.Long;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Contact {
	private Long id;
	private String name;
	private String email;
	private String address;
	private Calendar birthday;
	

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Calendar getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	
	public void printOut() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		System.out.println("Name: "+ this.getName());
		System.out.println("Email: "+ this.getEmail());
		System.out.println("Address: "+ this.getAddress());
		
		Date date = this.getBirthday().getTime();
		String formattedDate = simpleDateFormat.format(date);
		System.out.println("Birthday: "+ formattedDate+ "\n");
	}
	
}
