package ir.customers;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ir.gui.FillUpFormPanel;
import ir.users.User;
import ir.users.UsersDAO;
import ir.users.UsersPanel;

public class AddCustomerPanel extends FillUpFormPanel{
	private Customer customer;
	private CustomersDAO customersDAO;
	private JTextField firstNameField, lastNameField, emailField, contactNumberField, addressField, cityField, stateField, countryField;
	
	public AddCustomerPanel () {
		buildPanel();
		title.setText("Add New Customer");
	}
	
	protected void createForms(){
		
		okListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customersDAO = new CustomersDAO();

					if ( firstNameField.getText().equals("") || lastNameField.getText().equals("") || 
						 emailField.getText().equals("") || contactNumberField.getText().equals("") || 
						 addressField.getText().equals("") || cityField.getText().equals("") || 
						 stateField.getText().equals("") || countryField.getText().equals("")) {
						
						JOptionPane.showMessageDialog(null, "Some fields are empty or has incorrect format");
					
					} else {
						
						customer = new Customer();
						
						customer.setFirstName(firstNameField.getText());
						customer.setLastName(lastNameField.getText());
						customer.setEmail(emailField.getText());
						customer.setContactNumber("+6"+contactNumberField.getText());
						customer.setAddress(addressField.getText());
						customer.setCity(cityField.getText());
						customer.setState(stateField.getText());
						customer.setCountry(countryField.getText());

						customersDAO.addCustomer(customer);
						
						removeAll();
						revalidate();
						repaint();
						setLayout(new GridLayout());
						setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						add(new CustomersPanel());
						
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
				add(new CustomersPanel());
			}
			
		};
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,5,5);
		
		formsPanel.add(new JLabel("First Name: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Last Name: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("E-Mail: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Contact Number: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Address: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("City: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("State: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Country: "),c);
		c.gridy++;
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		firstNameField = new JTextField(15);
		
		lastNameField = new JTextField(15);
		
		emailField = new JTextField(15);
		
		try {
			contactNumberField = new JFormattedTextField(new MaskFormatter("+6-###-#######"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		addressField = new JTextField(15);
		
		cityField = new JTextField(15);
		
		stateField = new JTextField(15);
		
		countryField = new JTextField(15);
		
		formsPanel.add(firstNameField,c);
		c.gridy++;
		
		formsPanel.add(lastNameField,c);
		c.gridy++;
	
		formsPanel.add(emailField,c);
		c.gridy++;
		
		formsPanel.add(contactNumberField,c);
		c.gridy++;
		
		formsPanel.add(addressField,c);
		c.gridy++;
		
		formsPanel.add(cityField,c);
		c.gridy++;
		
		formsPanel.add(stateField,c);
		c.gridy++;
		
		formsPanel.add(countryField,c);
		c.gridy++;
		
	}
}
