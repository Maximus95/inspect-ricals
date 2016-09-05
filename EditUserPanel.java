package ir.users;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ir.gui.FillUpFormPanel;

public class EditUserPanel extends FillUpFormPanel{
	private UsersDAO usersDAO;
	private User user;
	private JTextField userIDField, usernameField, firstNameField, lastNameField, categoryField, passwordField;
	
	public EditUserPanel(User user){
		this.user = user;
		buildPanel();
		title.setText("Edit User Information");
	}
	
	protected void createForms(){
		
		okListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				usersDAO = new UsersDAO();
				user.setFirstName(firstNameField.getText());
				user.setLastName(lastNameField.getText());
				user.setPassword(passwordField.getText());
				usersDAO.updateUser(user);
				
				removeAll();
				revalidate();
				repaint();
				setLayout(new GridLayout());
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				add(new UsersPanel());
				
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
				add(new UsersPanel());
			}
			
		};
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,5,5);
		
		formsPanel.add(new JLabel("User ID: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Username: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("First Name: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Last Name: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Category: "),c);
		c.gridy++;
		
		formsPanel.add(new JLabel("Password: "),c);
		c.gridy++;
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		
		userIDField = new JTextField(15);
		userIDField.setEditable(false);
		userIDField.setText(user.getUserID());
		
		usernameField = new JTextField(15);
		usernameField.setEditable(false);	
		usernameField.setText(user.getUsername());
		
		firstNameField = new JTextField(15);
		firstNameField.setText(user.getFirstName());
		
		lastNameField = new JTextField(15);
		lastNameField.setText(user.getLastName());
		
		categoryField = new JTextField(15);
		categoryField.setEditable(false);
		categoryField.setText(user.getCategory());
		
		passwordField = new JTextField(15);
		passwordField.setText(user.getPassword());
		
		formsPanel.add(userIDField,c);
		c.gridy++;
		
		formsPanel.add(usernameField,c);
		c.gridy++;
		
		formsPanel.add(firstNameField,c);
		c.gridy++;
		
		formsPanel.add(lastNameField,c);
		c.gridy++;
		
		formsPanel.add(categoryField,c);
		c.gridy++;
		
		formsPanel.add(passwordField,c);
		c.gridy++;
		
	}
}
