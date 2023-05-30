package edu.examples.java_classes.input;

import edu.examples.java_classes.input.command.Command;
import edu.examples.java_classes.input.command.CommandProvider;
import edu.examples.java_classes.input.command.MenuCommand;

public class ConsoleMenu {

	public void execute() {

		Command command = CommandProvider.getInstance().getCommand(MenuCommand.SHOW_MAIN_MENU);
		command.execute();

	}

}
