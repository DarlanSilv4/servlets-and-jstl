package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.agenda.ConnectionFactory;
import br.com.agenda.model.Contact;

public class ContactDao {
	private Connection connection;
	
	public ContactDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void add(Contact contact) {
		String sql = "INSERT INTO contacts "+"(name,email,address,birthday) " + "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getAddress());
			stmt.setDate(4, new Date(contact.getBirthday().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contact> getList(){
		try {
			List<Contact> contacts = new ArrayList<Contact>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contacts");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getLong("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("birthday"));
				contact.setBirthday(date);
				
				contacts.add(contact);
			}
			rs.close();
			stmt.close();
			return contacts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contact> searchByName(String name){ 
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM contacts WHERE name ILIKE '%" + name + "%'");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getLong("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("birthday"));
				contact.setBirthday(date);
				
				contacts.add(contact);
			}
			rs.close();
			stmt.close();
			
			if(contacts.isEmpty()) {
				System.out.println("404 - Not Found!");
			}
			return contacts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Contact getById(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM contacts WHERE id =?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setName(rs.getString("name"));
			contact.setEmail(rs.getString("email"));
			contact.setAddress(rs.getString("address"));
			
			Calendar date = Calendar.getInstance();
			date.setTime(rs.getDate("birthday"));
			contact.setBirthday(date);
			
			rs.close();
			stmt.close();
			return contact;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Contact contact) {
		String sql = "UPDATE contacts SET name=?, email=?, address=?, birthday=? WHERE id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getAddress());
			stmt.setDate(4, new Date(contact.getBirthday().getTimeInMillis()));
			stmt.setLong(5, contact.getId());
			
			stmt.execute();
			System.out.println("Updated!");
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Contact contact) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM contacts WHERE id=?");
			stmt.setLong(1, contact.getId());
			stmt.execute();
			System.out.println("Deleted!");
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}



