package ir.report;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import ir.customers.Customer;
import ir.customers.CustomersDAO;
import ir.orders.Order;
import ir.users.User;
import ir.users.UsersDAO;

public class Report {
	private String reportMessage;
	private Order order;
	private Customer customer;
	private User user;
	private SimpleDateFormat sdf;
	private CustomersDAO customersDAO;
	private UsersDAO usersDAO;
	
	public Report(Order order, String reportMessage){
		customersDAO = new CustomersDAO();
		usersDAO = new UsersDAO();
		
		this.order = order;
		this.customer = customersDAO.getCustomerByID(order.getCustomerID());
		this.user = usersDAO.getUserByID(order.getEngineerID());
		this.reportMessage = reportMessage;
		
		//generate a report and create a new text file
		generateReport();
		
	}
	
	private void generateReport(){
		sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		try {
			FileWriter fileWriter = new FileWriter("reports/report_"+order.getOrderID()+".txt");
			fileWriter.write("\n------ REPORT ------");
			fileWriter.write("\n");
			fileWriter.write("\nOrder ID: "+order.getOrderID());
			fileWriter.write("\nOrder Type: "+order.getOrderType());
			fileWriter.write("\nOrder Date & Time: "+sdf.format(order.getDateTime()));
			fileWriter.write("\nCustomer: "+customer.getFirstName()+" "+customer.getLastName()+" ("+customer.getCustomerID()+")");
			fileWriter.write("\nEngineer: "+user.getFirstName()+" "+user.getLastName()+" ("+user.getUserID()+")");
			fileWriter.write("\n");
			fileWriter.write("\nReport Message: "+reportMessage);
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n__________________________");
			fileWriter.write("\nSignature of the engineer");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getReportMessage() {
		return reportMessage;
	}

	public Order getOrder() {
		return order;
	}

	public User getUser() {
		return user;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
}
