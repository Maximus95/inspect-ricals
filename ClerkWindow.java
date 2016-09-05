package ir.gui;

import ir.customers.CustomersPanel;
import ir.orders.OrdersPanel;
import ir.users.User;

public class ClerkWindow extends MainWindow{

	public ClerkWindow(User user) {
		super(user);
		addButton("Customers",  new CustomersPanel());
		addButton("Orders", new OrdersPanel());
	}

}
