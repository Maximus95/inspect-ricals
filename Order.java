package ir.orders;

import java.util.Date;

public class Order {
	private String orderID;
	private String customerID;
	private String engineerID;
	private Date dateTime;
	private String orderType;
	private boolean orderStatus;
	
	public Order(){
		
	}
	
	public Order(String orderID, String customerID, String engineerID, Date dateTime, String orderType, boolean orderStatus){
		this.orderID = orderID;
		this.customerID = customerID;
		this.engineerID = engineerID;
		this.dateTime = dateTime;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getEngineerID() {
		return engineerID;
	}
	
	public void setEngineerID(String engineerID) {
		this.engineerID = engineerID;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public boolean isOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
