package ir.gui;

import ir.customers.CustomersPanel;
import ir.orders.EngineerOrdersPanel;
import ir.orders.OrdersPanel;
import ir.statistics.StatisticsPanel;
import ir.users.User;
import ir.users.UsersPanel;

public class AdministratorWindow extends MainWindow{

	public AdministratorWindow(User user) {
		super(user);
		addButton("Users", new UsersPanel());
		addButton("Customers",  new CustomersPanel());
		addButton("Orders", new OrdersPanel());
		addButton("Statistics", new StatisticsPanel());
	}

}
