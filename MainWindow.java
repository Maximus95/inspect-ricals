package ir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ir.users.User;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public abstract class MainWindow extends JFrame{
	private User user;
	protected JPanel mainPanel;
	protected JPanel navBar;
	protected JPanel headerPanel;	
	protected JPanel menu;
	private JPanel profilePanel;
	private Color color = new Color(255, 153, 0);
	
	public MainWindow(User user){
		setVisible(false);
		setUser(user);
		setStyle();
		createWindow();
		createMainPanel();
		createNavigationBar();
		createHeaderPanel();
		setVisible(true);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private void createWindow(){
		this.setVisible(false);
		this.setTitle("Inspect Ricals");
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// set icon
	}
	
	protected void createHeaderPanel(){
		
		// creates a header panel
		headerPanel = new JPanel();
		headerPanel.setBackground(color);
		headerPanel.setPreferredSize(new Dimension(0,80));
		
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		BufferedImage image;
		JLabel logoLabel;
		
		try {
			image = ImageIO.read(new File("img/logo.png"));
			logoLabel = new JLabel(new ImageIcon(image));
			headerPanel.add(logoLabel, BorderLayout.WEST);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		profilePanel = new JPanel();
		profilePanel.setBackground(color);
		JLabel userInfo = new JLabel("<html>"+user.getFirstName()+" "+user.getLastName()+"<br>("+user.getCategory()+")</html>");
		userInfo.setForeground(Color.WHITE);
		userInfo.setFont(new Font("Helvetica", Font.BOLD, 13));
		JButton profileButton;
		
		try {
			image = ImageIO.read(new File("img/usericon2.png"));
			ImageIcon icon = new ImageIcon(image);
			profileButton = new JButton(icon);
			profileButton.setFocusPainted(false);
			profileButton.setPreferredSize(new Dimension(70,70));
			profileButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, 
							   "Do you want to switch account?",null, JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.YES_OPTION) {
							    signOut();
							} 
				}
				
			});
			profilePanel.add(userInfo);
			profilePanel.add(profileButton);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		headerPanel.add(profilePanel, BorderLayout.EAST);
		
	}
	
	private void signOut(){
		this.setVisible(false);
		this.dispose();
		
		LogInWindow login = new LogInWindow();
		login.setVisible(true);
	}
	
	protected void createMainPanel(){
		
		// creates a central panel
		mainPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		mainPanel.setBackground(color);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new GridLayout());
		mainPanel.add(emptyPanel);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
	}
	
	private void createNavigationBar() {
		
		// creates a navigation bar
		navBar = new JPanel();
		navBar.setBackground(color);
		navBar.setLayout(new GridLayout());
		menu = new JPanel();
		menu.setPreferredSize(new Dimension(200,300));
		menu.setBackground(color);
		navBar.add(menu);
		getContentPane().add(navBar, BorderLayout.WEST);
		
	}
	
	protected void addButton(String buttonName, CentralPanel centralPanel){
		JButton button = new JButton(buttonName);
		
		button.setPreferredSize(new Dimension(200,40));
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				mainPanel.removeAll();;
				mainPanel.add(centralPanel);
				mainPanel.revalidate();
				mainPanel.repaint();
				
			}
			
		});
		
		menu.add(button);
	}
	
	protected void setStyle(){
			
	}
	
}
