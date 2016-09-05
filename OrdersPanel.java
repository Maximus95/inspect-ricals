package ir.orders;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ir.customers.Customer;
import ir.customers.CustomersDAO;
import ir.gui.CentralPanel;
import ir.users.AddUserPanel;
import ir.users.EditUserPanel;
import ir.users.User;
import ir.users.UsersDAO;

public class OrdersPanel extends CentralPanel{
	protected List<Order> orders;
	protected CustomersDAO customersDAO;
	protected Customer customer;
	protected User engineer;
	protected UsersDAO usersDAO;
	protected Order order;
	protected OrdersDAO ordersDAO;
	protected SimpleDateFormat sdf;
	protected User user;
	
	public OrdersPanel() {
		
		columnsName = new Object[9];
		columnsName[0] = "Order ID";
		columnsName[1] = "Customer ID";
		columnsName[2] = "Customer Name";
		columnsName[3] = "Engineer ID";
		columnsName[4] = "Engineer Name";
		columnsName[5] = "Date";
		columnsName[6] = "Time";
		columnsName[7] = "Order Type";
		columnsName[8] = "Order Status";
		
		setPanel();
	}
	
	protected void setPanel(){
		buildToolBar();
		setTable();
		buildTable();
		title.setText("Orders");;
	}
	
	protected void setAddButtonListener(){
		removeAll();
		revalidate();
		repaint();
		AddOrderPanel addPanel = new AddOrderPanel();
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(addPanel, BorderLayout.CENTER);
	}
	
	protected void setEditButtonListener(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			ordersDAO = new OrdersDAO();
			order = ordersDAO.getOrderByID(id);
			
			removeAll();
			revalidate();
			repaint();
			EditOrderPanel editPanel = new EditOrderPanel(order);
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			add(editPanel, BorderLayout.CENTER);
		}
	}
	
	protected void setDeleteButtonListener(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			ordersDAO = new OrdersDAO();
			order = ordersDAO.getOrderByID(id);
			ordersDAO.deleteOrder(order);
			
			JOptionPane.showMessageDialog(null, "Order "+order.getOrderID()+" has been deleted");
			
			setTable();
			updateTable();
		}
	}
	
	protected void setTable(String ID){
		
		ordersDAO = new OrdersDAO();
		order = new Order();
		order = ordersDAO.getOrderByID(ID);
		customersDAO = new CustomersDAO();
		customer = customersDAO.getCustomerByID(order.getCustomerID());
		usersDAO = new UsersDAO();
		engineer = usersDAO.getUserByID(order.getEngineerID());
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[9];
	
		rowData[0] = order.getOrderID();
		rowData[1] = customer.getCustomerID();
		rowData[2] = customer.getFirstName()+" "+customer.getLastName();
		rowData[3] = engineer.getUserID();
		rowData[4] = engineer.getFirstName()+" "+engineer.getLastName();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		rowData[5] = sdf.format(order.getDateTime());
		sdf = new SimpleDateFormat("HH:mm");
		rowData[6] = sdf.format(order.getDateTime());
		rowData[7] = order.getOrderType();
		rowData[8] = order.isOrderStatus();
			
		dtm.addRow(rowData);
		
		updateTable();
	}
	
	protected void setTable(){
		
		ordersDAO = new OrdersDAO();
		customersDAO = new CustomersDAO();
		usersDAO = new UsersDAO();
		
		orders = new ArrayList<Order>();
		orders = ordersDAO.getAllOrders();
		customer = new Customer();
		engineer = new User();
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[9];
		try {
			for (int i=0; i<orders.size(); i++) {
				customer = customersDAO.getCustomerByID(orders.get(i).getCustomerID());
				engineer = usersDAO.getUserByID(orders.get(i).getEngineerID());
				
				rowData[0] = orders.get(i).getOrderID();
				rowData[1] = customer.getCustomerID();
				rowData[2] = customer.getFirstName()+" "+customer.getLastName();
				rowData[3] = engineer.getUserID();
				rowData[4] = engineer.getFirstName()+" "+engineer.getLastName();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				rowData[5] = sdf.format(orders.get(i).getDateTime());
				sdf = new SimpleDateFormat("HH:mm");
				rowData[6] = sdf.format(orders.get(i).getDateTime());
				rowData[7] = orders.get(i).getOrderType();
				rowData[8] = orders.get(i).isOrderStatus();
				
				dtm.addRow(rowData);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
