package ir.users;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ir.gui.FillUpFormPanel;

public class AddUserPanel extends FillUpFormPanel {
	private UsersDAO usersDAO;
	private User user;
	private JTextField usernameField, firstNameField, lastNameField;
	private JComboBox categoryBox;
	private JPasswordField passwordField;
	
	public AddUserPanel(){
		buildPanel();
		title.setText("Add New User");
	}
	
	protected void createForms(){
		
		okListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				usersDAO = new UsersDAO();

				user = usersDAO.getUserByUsername(usernameField.getText());
				try {
					if (user.getUsername()!=null){
						JOptionPane.showMessageDialog(null, "User with such username already exists");
					} 
				} catch (Exception x) {
					if (usernameField.getText().equals("") || firstNameField.getText().equals("") || lastNameField.getText().equals("") || new String(passwordField.getPassword()).equals("")) {
						
						JOptionPane.showMessageDialog(null, "Some fields are empty");
					
					} else {
						
						user = new User();
						user.setUsername(usernameField.getText());
						user.setFirstName(firstNameField.getText());
						user.setLastName(lastNameField.getText());
						user.setCategory(categoryBox.getSelectedItem().toString());
						user.setPassword(new String(passwordField.getPassword()));
						usersDAO.addUser(user);
						
						removeAll();
						revalidate();
						repaint();
						setLayout(new GridLayout());
						setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						add(new UsersPanel());
						
					}
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
				add(new UsersPanel());
			}
			
		};
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,5,5);
		
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

		usernameField = new JTextField(15);
		
		firstNameField = new JTextField(15);
		
		lastNameField = new JTextField(15);
		
		categoryBox = new JComboBox(categories);
		categoryBox.setSelectedIndex(0);
		
		passwordField = new JPasswordField(15);

		formsPanel.add(usernameField,c);
		c.gridy++;
		
		formsPanel.add(firstNameField,c);
		c.gridy++;
		
		formsPanel.add(lastNameField,c);
		c.gridy++;
		
		formsPanel.add(categoryBox,c);
		c.gridy++;
		
		formsPanel.add(passwordField,c);
		c.gridy++;
		
	}
	
}
