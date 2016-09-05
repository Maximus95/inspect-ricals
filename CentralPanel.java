package ir.gui;

import ir.users.User;
import ir.users.UsersDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public abstract class CentralPanel extends JPanel{
	
	protected JTable table;
	protected JScrollPane tablePanel;
	protected Object[] columnsName;
	protected DefaultTableModel dtm;
	protected JPanel toolBar, toolPanel;
	protected JButton addButton, editButton, deleteButton, searchButton, refreshButton;
	protected JTextField searchTextField;
	protected JLabel title;
	
	public CentralPanel(){
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(7, 20, 20, 20));
		this.setBackground(Color.WHITE);
		
	}
	
	protected void buildToolBar(){
		
		toolBar = new JPanel();
		toolBar.setLayout(new BorderLayout());
		toolBar.setBackground(Color.WHITE);
		toolPanel = new JPanel();
		toolPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolPanel.setBackground(Color.WHITE);
		title = new JLabel("Users");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		searchButton = new JButton("Search");
		refreshButton = new JButton("Refresh");
		searchTextField = new JTextField(10);
		toolBar.add(title, BorderLayout.WEST);
		toolPanel.add(searchTextField, BorderLayout.CENTER);
		toolPanel.add(searchButton, BorderLayout.CENTER);
		toolPanel.add(addButton, BorderLayout.CENTER);
		toolPanel.add(addButton, BorderLayout.CENTER);
		toolPanel.add(editButton, BorderLayout.CENTER);
		toolPanel.add(deleteButton, BorderLayout.CENTER);
		toolPanel.add(refreshButton, BorderLayout.CENTER);
		toolBar.add(toolPanel, BorderLayout.CENTER);
		
		refreshButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setTable();
				updateTable();
			}
			
		});
		
		searchButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setSearchButtonListener();
			}
			
		});
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setDeleteButtonListener();
			}
			
		});
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setEditButtonListener();
			}
			
		});
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setAddButtonListener();
			}
			
		});
		
		this.add(toolBar, BorderLayout.NORTH);
	}
	
	protected abstract void setTable();
	
	protected abstract void setTable(String ID);
	
	private void setSearchButtonListener(){
		String searchString = searchTextField.getText();
		try {
			setTable(searchString);
		} catch (Exception x) {
			setTable();
			updateTable();
		}

	}
	
	protected void setAddButtonListener(){
		
	}
	
	protected void setEditButtonListener(){
		
	}
	
	protected void setDeleteButtonListener(){
		
	}
	
	protected void updateTable() {
		table.setModel(dtm);
	}
	
	protected void buildTable() {
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePanel = new JScrollPane();
		//table.setEnabled(false);
		table.setModel(dtm);
		tablePanel.getViewport().add(table);
		tablePanel.setBorder(BorderFactory.createEmptyBorder());

		table.setRowHeight(table.getRowHeight()+4);
		table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 30));
		//table.getTableHeader().setBackground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 12));
		table.setFont(new Font("Helvetica", Font.PLAIN, 13));
		
		this.add(tablePanel, BorderLayout.CENTER);
	}
	
}
