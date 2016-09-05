package ir.users;

import ir.gui.CentralPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UsersPanel extends CentralPanel{
	protected List<User> users;
	protected UsersDAO usersDAO;
	protected User user;
	
	public UsersPanel(){
		
		columnsName = new Object[6];
		columnsName[0] = "User ID";
		columnsName[1] = "Username";
		columnsName[2] = "First name";
		columnsName[3] = "Last name";
		columnsName[4] = "Category";
		columnsName[5] = "Password";
		
		buildToolBar();
		setTable();
		buildTable();
		
	}

	protected void setAddButtonListener(){
		removeAll();
		revalidate();
		repaint();
		AddUserPanel addPanel = new AddUserPanel();
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(addPanel, BorderLayout.CENTER);
	}
	
	protected void setEditButtonListener(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			usersDAO = new UsersDAO();
			user = usersDAO.getUserByID(id);
			
			removeAll();
			revalidate();
			repaint();
			EditUserPanel editPanel = new EditUserPanel(user);
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			add(editPanel, BorderLayout.CENTER);
		}
	}
	
	protected void setDeleteButtonListener(){
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow!=-1) {
			String id = (String)table.getValueAt(selectedRow, 0);
			
			usersDAO = new UsersDAO();
			user = usersDAO.getUserByID(id);
			usersDAO.deleteUser(user);
			
			JOptionPane.showMessageDialog(null, "User "+user.getUsername()+" has been deleted");
			
			setTable();
			updateTable();
		}
	}
	
	protected void setTable(String ID){
		usersDAO = new UsersDAO();
		user = new User();
		user = usersDAO.getUserByID(ID);
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[6];
	
		rowData[0] = user.getUserID();
		rowData[1] = user.getUsername();
		rowData[2] = user.getFirstName();
		rowData[3] = user.getLastName();
		rowData[4] = user.getCategory();
		rowData[5] = user.getPassword();
			
		dtm.addRow(rowData);
		
		updateTable();
	}
	
	protected void setTable(){
		
		usersDAO = new UsersDAO();
		users = new ArrayList<User>();
		users = usersDAO.getAllUsers();
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[6];
		
		for (int i=0; i<users.size(); i++) {
			rowData[0] = users.get(i).getUserID();
			rowData[1] = users.get(i).getUsername();
			rowData[2] = users.get(i).getFirstName();
			rowData[3] = users.get(i).getLastName();
			rowData[4] = users.get(i).getCategory();
			rowData[5] = users.get(i).getPassword();
			
			dtm.addRow(rowData);
		}

	}

}
