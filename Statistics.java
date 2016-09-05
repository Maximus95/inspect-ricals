package ir.statistics;

import java.util.ArrayList;
import java.util.List;

import ir.orders.Order;
import ir.orders.OrdersDAO;
import ir.users.User;

public class Statistics {
	private User engineer;
	private int numberOfInspections, numberOfInstallations, hoursWorked;
	
	public Statistics (User engineer){
		this.engineer = engineer;
		numberOfInspections = calculateInspections();
		numberOfInstallations = calculateInstallations();
		hoursWorked = calculateHoursWorked();
	}
	
	private int calculateInstallations() {
		int installations=0;
		
		List <Order> orders = new ArrayList<Order>();
		OrdersDAO ordersDAO = new OrdersDAO();
		orders = ordersDAO.getOrdersByEngineer(engineer.getUserID());
		
		for (int i=0;i<orders.size();i++) {
			if (orders.get(i).getOrderType().equals("Installation")) {
				installations++;
			}
		}
		
		return installations;
	}
	
	private int calculateInspections() {
		int inspections=0;
		
		List <Order> orders = new ArrayList<Order>();
		OrdersDAO ordersDAO = new OrdersDAO();
		orders = ordersDAO.getOrdersByEngineer(engineer.getUserID());
		
		for (int i=0;i<orders.size();i++) {
			if (orders.get(i).getOrderType().equals("Inspection")) {
				inspections++;
			}
		}
		
		return inspections;
	}
	
	private int calculateHoursWorked(){
		return numberOfInspections+numberOfInstallations;
	}
	
	public int getHoursWorked(){
		return hoursWorked;
	}
	
	public int getInspections(){
		return numberOfInspections;
	}
	
	public int getInstallations(){
		return numberOfInstallations;
	}
	
	public User getEngineer(){
		return engineer;
	}
}
