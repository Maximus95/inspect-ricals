package ir.orders;

import ir.customers.Customer;
import ir.customers.CustomersDAO;
import ir.users.User;
import ir.users.UsersDAO;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class EngineerOrdersPanel extends OrdersPanel{
	private JButton completeButton;
	
	public EngineerOrdersPanel(User user){
		this.user = user;
		
		buildToolBar();
		setTable();
		buildTable();
		title.setText("Orders");;
		
		addButton.setVisible(false);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
		searchButton.setVisible(false);
		searchTextField.setVisible(false);
		
		completeButton = new JButton("Complete Order");
		completeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				completeOrder();
			}
			
		});
		
		toolPanel.add(completeButton, BorderLayout.CENTER);
	}
	
	private void completeOrder(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			ordersDAO = new OrdersDAO();
			order = ordersDAO.getOrderByID(id);
			if (order.isOrderStatus()==false) {
				order.setOrderStatus(true);
				removeAll();
				revalidate();
				repaint();
				CompleteOrdersPanel editPanel = new CompleteOrdersPanel(order);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				add(editPanel, BorderLayout.CENTER);
			}
		}
	}

	protected void setTable(){
		
		ordersDAO = new OrdersDAO();
		customersDAO = new CustomersDAO();
		usersDAO = new UsersDAO();
		
		orders = new ArrayList<Order>();
		orders = ordersDAO.getOrdersByEngineer(user.getUserID());
		customer = new Customer();
		engineer = new User();
		
		if (orders!=null){
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

	
	protected void setPanel(){
		
	}
}
