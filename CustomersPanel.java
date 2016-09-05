package ir.customers;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ir.gui.CentralPanel;
import ir.users.AddUserPanel;
import ir.users.EditUserPanel;
import ir.users.User;
import ir.users.UsersDAO;

public class CustomersPanel extends CentralPanel{
	protected List<Customer> customers;
	protected Customer customer;
	protected CustomersDAO customersDAO;
	
	public CustomersPanel() {
		
		columnsName = new Object[9];
		columnsName[0] = "Customer ID";
		columnsName[1] = "First name";
		columnsName[2] = "Last name";
		columnsName[3] = "E-mail";
		columnsName[4] = "Contact Number";
		columnsName[5] = "Address";
		columnsName[6] = "City";
		columnsName[7] = "State";
		columnsName[8] = "Country";
		
		buildToolBar();
		title.setText("Customers");
		setTable();
		buildTable();
	}
	
	protected void setTable(String ID){
		customersDAO = new CustomersDAO();
		customer = new Customer();
		customer = customersDAO.getCustomerByID(ID);
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[9];
	
		rowData[0] = customer.getCustomerID();
		rowData[1] = customer.getFirstName();
		rowData[2] = customer.getLastName();
		rowData[3] = customer.getEmail();
		rowData[4] = customer.getContactNumber();
		rowData[5] = customer.getAddress();
		rowData[6] = customer.getCity();
		rowData[7] = customer.getState();
		rowData[8] = customer.getCountry();
			
		dtm.addRow(rowData);
		
		updateTable();
	}
	
	protected void setEditButtonListener(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			customersDAO = new CustomersDAO();
			customer = customersDAO.getCustomerByID(id);
			
			removeAll();
			revalidate();
			repaint();
			EditCustomerPanel editPanel = new EditCustomerPanel(customer);
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			add(editPanel, BorderLayout.CENTER);
		}
	}
	
	protected void setDeleteButtonListener() {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			customersDAO = new CustomersDAO();
			customer = customersDAO.getCustomerByID(id);
			customersDAO.deleteCustomer(customer);
			
			JOptionPane.showMessageDialog(null, "Customer "+customer.getFirstName()+" "+customer.getLastName()+" has been deleted");
			
			setTable();
			updateTable();
		}
	}
	
	protected void setAddButtonListener(){
		removeAll();
		revalidate();
		repaint();
		AddCustomerPanel addPanel = new AddCustomerPanel();
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(addPanel, BorderLayout.CENTER);
	}
	
	protected void setTable(){
		
		customersDAO = new CustomersDAO();
		customers = new ArrayList<Customer>();
		customers = customersDAO.getAllCustomers();
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[9];
	
		for (int i=0; i<customers.size(); i++) {
			rowData[0] = customers.get(i).getCustomerID();
			rowData[1] = customers.get(i).getFirstName();
			rowData[2] = customers.get(i).getLastName();
			rowData[3] = customers.get(i).getEmail();
			rowData[4] = customers.get(i).getContactNumber();
			rowData[5] = customers.get(i).getAddress();
			rowData[6] = customers.get(i).getCity();
			rowData[7] = customers.get(i).getState();
			rowData[8] = customers.get(i).getCountry();
				
			dtm.addRow(rowData);
		}
		
		

	}
	
}
