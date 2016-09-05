package ir.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
	private final String connectionString = "jdbc:mysql://localhost:3306/InspectRicals?autoReconnect=true&useSSL=false";
	private final String username = "root";
	private final String password = "pwd123";
	protected String statementString;
	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;
	
	public DAO(){
		setDriver();
	}
	
	private void setDriver(){
		try {
			
			java.sql.Driver driver = new com.mysql.jdbc.Driver();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void openConnection(){
		try {
			
			connection = DriverManager.getConnection(connectionString, username, password);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	protected void closeConnection(){
		if (resultSet!=null){
			try {
				
				resultSet.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (statement!=null) {
			try {
				
				statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (connection!=null) {
			try {
				
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
