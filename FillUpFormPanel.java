package ir.gui;

import ir.users.User;
import ir.users.UsersPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public abstract class FillUpFormPanel extends JPanel{
	protected String[] categories = {"Administrator","Management","Clerk","Engineer"};
	protected JPanel formsPanel, bottomPanel;
	protected JLabel title;
	protected JButton okButton, cancelButton;
	protected ActionListener okListener, cancelListener;
	protected GridBagConstraints c;
	
	protected void buildPanel(){
		
		this.setSize(320,350);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		
		formsPanel = new JPanel();
		formsPanel.setLayout(new GridBagLayout());
		formsPanel.setPreferredSize(new Dimension(140,200));
		formsPanel.setBackground(Color.WHITE);
		
		c = new GridBagConstraints();
		
		createForms();
		
		title = new JLabel("Title", SwingConstants.LEFT);
		title.setPreferredSize(new Dimension(100,50));
		title.setBackground(Color.WHITE);
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		okButton = new JButton("OK");
		okButton.setPreferredSize(new Dimension(100,30));
		okButton.addActionListener(okListener);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(100,30));
		cancelButton.addActionListener(cancelListener);
		
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setPreferredSize(new Dimension(80,60));
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.add(okButton);
		bottomPanel.add(cancelButton);
		
		this.add(formsPanel,BorderLayout.CENTER);
		this.add(title,BorderLayout.NORTH);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
	}
	
	protected abstract void createForms();

}
