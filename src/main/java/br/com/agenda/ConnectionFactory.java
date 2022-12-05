package br.com.agenda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost/agenda","postgres","");
		}  catch(SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}