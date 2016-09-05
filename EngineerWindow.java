package ir.gui;

import ir.orders.EngineerOrdersPanel;
import ir.users.User;

public class EngineerWindow extends MainWindow{

	public EngineerWindow(User user) {
		super(user);
		addButton("Orders Engineer", new EngineerOrdersPanel(user));
	}

}
