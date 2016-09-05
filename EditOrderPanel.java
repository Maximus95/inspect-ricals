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
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ir.gui.FillUpFormPanel;
import ir.users.UsersDAO;
import ir.users.UsersPanel;

public class EditOrderPanel extends FillUpFormPanel{
	private OrdersDAO ordersDAO;
	private Order order;
	private JTextField orderIDField, customerIDField, engineerIDField, dateField, timeField, orderTypeField;
	private JComboBox orderStatusBox;
	private SimpleDateFormat sdf;
	
	public EditOrderPanel(Order order){
		this.order = order;
		buildPanel();
		title.setText("Edit Order Information");
	}
	
	protected void createForms(){
		
		okListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ordersDAO = new OrdersDAO();
				
				if(orderStatusBox.getSelectedItem().toString().equals("Delivered")){
					order.setOrderStatus(true);
				} else {
					order.setOrderStatus(false);
				}
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String dateString = dateField.getText()+" "+timeField.getText();
					Date newDate = df.parse(dateString);
					order.setDateTime(newDate);
					ordersDAO.updateOrder(order);
					
				} catch (Exception x) {
					
				}
			
				removeAll();
				revalidate();
				repaint();
				setLayout(new GridLayout());
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				add(new OrdersPanel());
				
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
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,5,5);
		
		formsPanel.add(new JLabel("Order ID: "),c);
		c.gridy++;
		
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
		
		formsPanel.add(new JLabel("Order Status: "),c);
		c.gridy++;
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		orderIDField = new JTextField();
		orderIDField.setText(order.getOrderID());
		orderIDField.setEditable(false);
		
		customerIDField = new JTextField();
		customerIDField.setText(order.getCustomerID());
		customerIDField.setEditable(false);
		
		engineerIDField = new JTextField();
		engineerIDField.setText(order.getEngineerID());
		engineerIDField.setEditable(false);
		
		
		try {
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			dateField = new JFormattedTextField(new MaskFormatter("##/##/##"));
			dateField.setText(sdf.format(order.getDateTime()));
			
			sdf = new SimpleDateFormat("HH:mm");
			
			timeField = new JFormattedTextField(new MaskFormatter("##:##"));
			timeField.setText(sdf.format(order.getDateTime()));
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		
		orderTypeField = new JFormattedTextField();
		orderTypeField.setText(order.getOrderType());
		
		String[] statuses = {"Delivered","Not Delivered"};
		orderStatusBox = new JComboBox(statuses);
		
		if (order.isOrderStatus()){
			orderStatusBox.setSelectedIndex(0);
		} else {
			orderStatusBox.setSelectedIndex(1);
		}
		
		formsPanel.add(orderIDField,c);
		c.gridy++;
		
		formsPanel.add(customerIDField,c);
		c.gridy++;
		
		formsPanel.add(engineerIDField,c);
		c.gridy++;
		
		formsPanel.add(dateField,c);
		c.gridy++;
		
		formsPanel.add(timeField,c);
		c.gridy++;
		
		formsPanel.add(orderTypeField,c);
		c.gridy++;
		
		formsPanel.add(orderStatusBox,c);
		c.gridy++;
	}
}
