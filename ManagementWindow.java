package ir.gui;

import ir.customers.CustomersPanel;
import ir.orders.OrdersPanel;
import ir.statistics.StatisticsPanel;
import ir.users.User;

public class ManagementWindow extends MainWindow{

	public ManagementWindow(User user) {
		super(user);
		addButton("Customers",  new CustomersPanel());
		addButton("Orders", new OrdersPanel());
		addButton("Statistics", new StatisticsPanel());
	}

}
