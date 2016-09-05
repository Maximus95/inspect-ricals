package ir.orders;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.customers.Customer;
import ir.customers.CustomersDAO;
import ir.main.DAO;
import ir.users.UsersDAO;

public class OrdersDAO extends DAO{
	private CustomersDAO customersDAO;
	private UsersDAO usersDAO;
	private List<Order> orders;
	private Order order;
	private SimpleDateFormat sdf;
	private Timestamp timestamp;
	
	public void deleteOrder(Order order){
		openConnection();
		this.order = order;
		statementString = "DELETE FROM Orders WHERE order_id='"+this.order.getOrderID()+"'";
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public void updateOrder(Order order){
		openConnection();
		this.order = order;
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(this.order.getDateTime());
		int orderStatus = 0;
		if (order.isOrderStatus()) {
			orderStatus = 1;
		}
		
		statementString = "UPDATE Orders SET customer_id='"+this.order.getCustomerID()+"', engineer_id='"+this.order.getEngineerID()
						+ "', date='"+dateTime+"', order_type='"+this.order.getOrderType()+"',"+" order_status='"+orderStatus
						+"' WHERE order_id='"+this.order.getOrderID()+"'";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public List<Order> getOrdersByEngineer(String engineerID){
		openConnection();
		statementString = "SELECT * FROM Orders WHERE engineer_id='"+engineerID+"'";
		order = null;
		orders = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			orders = new ArrayList<Order>();
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getString("order_id"));
				order.setCustomerID(resultSet.getString("customer_id"));
				order.setEngineerID(resultSet.getString("engineer_id"));
				timestamp = resultSet.getTimestamp("date");
				order.setDateTime(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus==1) {
					order.setOrderStatus(true);
				} else {
					order.setOrderStatus(false);
				}
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return orders;
	}
	
	public Order getOrderByID(String orderID){
		openConnection();
		statementString = "SELECT * FROM Orders WHERE order_id='"+orderID+"'";
		order = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getString("order_id"));
				order.setCustomerID(resultSet.getString("customer_id"));
				order.setEngineerID(resultSet.getString("engineer_id"));
				timestamp = resultSet.getTimestamp("date");
				order.setDateTime(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus==1) {
					order.setOrderStatus(true);
				} else {
					order.setOrderStatus(false);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return order;
	}
	
	public List<Order> getAllOrders(){
		openConnection();
		statementString = "SELECT * FROM Orders";
		order = null;
		orders = null;
		
		try {
			customersDAO = new CustomersDAO();
			usersDAO = new UsersDAO();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			orders = new ArrayList<Order>();
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getString("order_id"));
				order.setCustomerID(resultSet.getString("customer_id"));
				order.setEngineerID(resultSet.getString("engineer_id"));
				timestamp = resultSet.getTimestamp("date");
				order.setDateTime(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus==1) {
					order.setOrderStatus(true);
				} else {
					order.setOrderStatus(false);
				}
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		closeConnection();
		return orders;
	}
	
	public void addOrder(Order order) {
		openConnection();
		this.order = order;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(this.order.getDateTime());
		int orderStatus = 0;
		if (this.order.isOrderStatus()==true){
			orderStatus = 1;
		}
				
		statementString = "INSERT INTO Orders (customer_id, engineer_id, date, order_type, order_status) "
						+ "VALUES ('"+this.order.getCustomerID()+"', '"+this.order.getEngineerID()+"', '"+dateTime+"', "
								+ "'"+this.order.getOrderType()+"', '"+orderStatus+"')";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}

}
