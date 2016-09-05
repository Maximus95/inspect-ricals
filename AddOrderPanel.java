package ir.orders;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ir.customers.CustomersDAO;
import ir.gui.FillUpFormPanel;
import ir.users.User;
import ir.users.UsersDAO;
import ir.users.UsersPanel;

public class AddOrderPanel extends FillUpFormPanel{
	private OrdersDAO ordersDAO;
	private Order order;
	private CustomersDAO customersDAO;
	private UsersDAO usersDAO;
	private JTextField orderIDField, customerIDField, engineerIDField; 
	private JFormattedTextField dateField, timeField;
	private JComboBox orderStatusBox, orderTypeBox;
	private SimpleDateFormat sdf;
	private Date date;
	
	public AddOrderPanel(){
		buildPanel();
		title.setText("Create New Order");
	}
	
	protected void createForms(){
		
		okListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				order = new Order();
				
				ordersDAO = new OrdersDAO();
				customersDAO = new CustomersDAO();
				usersDAO = new UsersDAO();
				boolean error = false;
				
				try {
					
					if (customersDAO.getCustomerByID(customerIDField.getText())==null){
						JOptionPane.showMessageDialog(null, "Customer doesn't exist");
						error = true;
					} else if (usersDAO.getUserByID(engineerIDField.getText())==null){
						JOptionPane.showMessageDialog(null, "Engineer doesn't exist");
						error = true;
					} 
					
					if (error==false){
						
						order.setCustomerID(customerIDField.getText());
						order.setEngineerID(engineerIDField.getText());
						order.setOrderType(orderTypeBox.getSelectedItem().toString());
						
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						String dateString = dateField.getText()+" "+timeField.getText();
						Date newDate = df.parse(dateString);
						order.setDateTime(newDate);
						
						ordersDAO.addOrder(order);
						
						removeAll();
						revalidate();
						repaint();
						setLayout(new GridLayout());
						setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						add(new OrdersPanel());
					}
					
				} catch (Exception x) {
					JOptionPane.showMessageDialog(null, "Wrong Date & Time Format");
				}
				
			}
			
		};
		
		cancelListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				revalidate();
				repaint();
				setLayout(new GridLayout());
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				add(new OrdersPanel());
			}
			
		};
		
		date = new Date();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,5,5);
		
		formsPanel.add(new JLabel("Customer ID: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Engineer ID: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Date: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Time: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Order Type: "),c);
		c.gridy++;
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		customerIDField = new JTextField(15);
		//customerIDField.setEditable(false);
		
		engineerIDField = new JTextField(15);
		//engineerIDField.setEditable(false);
		
		try {
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dateField.setText(sdf.format(date));
			
			sdf = new SimpleDateFormat("HH:mm");
			timeField = new JFormattedTextField(new MaskFormatter("##:##"));
			timeField.setText(sdf.format(date));
		} catch (Exception x){
			x.printStackTrace();
		}
		
		String[] types = {"Inspection","Installation"};
		orderTypeBox = new JComboBox(types);
		orderTypeBox.setSelectedIndex(0);
		
		formsPanel.add(customerIDField,c);
		c.gridy++;
		
		formsPanel.add(engineerIDField,c);
		c.gridy++;
		
		formsPanel.add(dateField,c);
		c.gridy++;
		
		formsPanel.add(timeField,c);
		c.gridy++;
		
		formsPanel.add(orderTypeBox,c);
		c.gridy++;

		
	}
}
