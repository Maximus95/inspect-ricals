package ir.gui;

import ir.users.User;
import ir.users.UsersDAO;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.Font;

public class LogInWindow extends JFrame {
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton logInButton;
	private String username;
	private String password;
	private Color color = new Color(255, 153, 0);
	
	public LogInWindow(){
		
		this.setSize(300,250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		try {
			JPanel logoPanel = new JPanel();
			logoPanel.setBackground(color);
			BufferedImage image = ImageIO.read(new File("img/logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(image));
			logoPanel.add(logoLabel);
			getContentPane().add(logoPanel, BorderLayout.NORTH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel main = new JPanel();
		main.setLayout(null);
		main.setBackground(color);
		
		usernameTextField = new JTextField(10);
		usernameTextField.setBounds(121, 18, 157, 28);
		main.add(usernameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(121, 58, 157, 28);
		main.add(passwordTextField);

		logInButton = new JButton("Log In");
		logInButton.setBounds(83, 104, 134, 40);
		logInButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				try {
					UsersDAO usersDAO = new UsersDAO();
					username = getUsername();
					password = getPassword();
					User user = usersDAO.getUserByUsername(getUsername());
					if (user!=null){
						
						if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
							setVisible(false);
							dispose();
							
							MainWindow window;
							
							switch (user.getCategory()) {
								case "Administrator": {
									window = new AdministratorWindow(user);
								} break;
								case "Management": {
									window = new ManagementWindow(user);
								} break;
								case "Clerk": {
									window = new ClerkWindow(user);
								} break;
								case "Engineer": {
									window = new EngineerWindow(user);
								} break;
								
								default: {
									
								}
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "Incorrect username or password!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password!");
					}
						
				
				} catch (Exception x) {
					JOptionPane.showMessageDialog(null, "Couldn't connect to database");
					x.printStackTrace();
				} 
			}
		
		});
		main.add(logInButton);
		getContentPane().add(main, BorderLayout.CENTER);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setBounds(28, 24, 81, 16);
		main.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(28, 64, 81, 16);
		main.add(lblPassword);
		
	}
	
	public String getUsername(){
		username = usernameTextField.getText();
		return username;
	}
	
	public String getPassword(){
		password = new String(passwordTextField.getPassword());
		return password;
	}
}
