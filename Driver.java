package ir.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import ir.customers.Customer;
import ir.customers.CustomersDAO;
import ir.gui.AdministratorWindow;
import ir.gui.ClerkWindow;
import ir.gui.EngineerWindow;
import ir.gui.LogInWindow;
import ir.gui.MainWindow;
import ir.gui.ManagementWindow;
import ir.orders.Order;
import ir.orders.OrdersDAO;
import ir.report.Report;
import ir.users.User;
import ir.users.UsersDAO;

public class Driver {

	public static void main(String[] args){

		LogInWindow login = new LogInWindow();
		login.setVisible(true);
	}
}
