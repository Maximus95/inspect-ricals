package ir.statistics;

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

public class StatisticsPanel extends CentralPanel{
	protected List<User> users;
	protected UsersDAO usersDAO;
	protected User user;
	protected Statistics s;
	
	public StatisticsPanel(){
		
		columnsName = new Object[6];
		columnsName[0] = "Engineer ID";
		columnsName[1] = "First name";
		columnsName[2] = "Last name";
		columnsName[3] = "Inspections";
		columnsName[4] = "Installations";
		columnsName[5] = "Hours Worked";
		
		buildToolBar();
		addButton.setVisible(false);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
		searchButton.setVisible(false);
		searchTextField.setVisible(false);
		title.setText("Statistics");
		setTable();
		buildTable();
		
	}
	
	protected void setTable(String ID){
		
	}
	
	protected void setTable(){
		
		usersDAO = new UsersDAO();
		users = new ArrayList<User>();
		users = usersDAO.getUsersByCategory("Engineer");
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[6];
		
		for (int i=0; i<users.size(); i++) {
			s = new Statistics(users.get(i));
			rowData[0] = s.getEngineer().getUserID();
			rowData[1] = s.getEngineer().getFirstName();
			rowData[2] = s.getEngineer().getLastName();
			rowData[3] = s.getInspections();
			rowData[4] = s.getInstallations();
			rowData[5] = s.getHoursWorked();
			
			dtm.addRow(rowData);
		}

	}
}
