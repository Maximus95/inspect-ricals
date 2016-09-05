package ir.users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ir.main.DAO;

public class UsersDAO extends DAO{
	private List<User> users;
	private User user;
	
	public List<User> getUsersByCategory(String category){
		openConnection();
		statementString = "SELECT * FROM Users WHERE category='"+category+"'";
		user = null;
		users = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			users = new ArrayList<User>();
			
			while (resultSet.next()){
				user = new User();
				user.setUserID(resultSet.getString("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPassword(resultSet.getString("password"));
				user.setCategory(resultSet.getString("category"));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return users;
	}
	
	public User getUserByUsername(String username){
		openConnection();
		statementString = "SELECT * FROM Users WHERE username='"+username+"'";
		user = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				user = new User();
				user.setUserID(resultSet.getString("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPassword(resultSet.getString("password"));
				user.setCategory(resultSet.getString("category"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return user;
	}
	
	public User getUserByID(String userID){
		openConnection();
		statementString = "SELECT * FROM Users WHERE user_id='"+userID+"'";
		user = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				user = new User();
				user.setUserID(resultSet.getString("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPassword(resultSet.getString("password"));
				user.setCategory(resultSet.getString("category"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return user;
	}
	
	public void updateUser(User user){
		openConnection();
		this.user = user;
		statementString = "UPDATE Users SET username='"+this.user.getUsername()+"', first_name='"+this.user.getFirstName()+"', last_name='"+this.user.getLastName()
						+"', category='"+this.user.getCategory()+"', password='"+this.user.getPassword()+"' WHERE user_id='"+this.user.getUserID()+"'";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public void deleteUser(User user){
		openConnection();
		this.user = user;
		statementString = "DELETE FROM Users WHERE user_id='"+this.user.getUserID()+"' AND username='"+this.user.getUsername()+"'"; 
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public void addUser(User user){
		openConnection();
		this.user = user;
		statementString = "INSERT INTO Users (username, first_name, last_name, category, password) VALUES ('" 
							+this.user.getUsername()+"', '"+this.user.getFirstName()+"', '"+this.user.getLastName()+"','"
							+this.user.getCategory()+"', '"+this.user.getPassword()+"')";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	
	public List<User> getAllUsers(){
		openConnection();
		statementString = "SELECT * FROM Users";
		user = null;
		users = null;
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			users = new ArrayList<User>();
			
			while (resultSet.next()){
				user = new User();
				user.setUserID(resultSet.getString("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPassword(resultSet.getString("password"));
				user.setCategory(resultSet.getString("category"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		closeConnection();
		
		return users;
		
	}
		
}
