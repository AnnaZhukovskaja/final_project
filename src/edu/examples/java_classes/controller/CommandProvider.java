package edu.examples.java_classes.controller;

import java.util.HashMap;
import java.util.Map;

import edu.examples.java_classes.controller.impl.*;

public class CommandProvider {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("ADD_NOTE", new AddNoteCommand());
		commands.put("FIND_BY_CONTENT", new FindByContentCommand());
		commands.put("FIND_BY_DATE", new FindByDateCommand());
		commands.put("NO_SUCH_COMMAND", new NoSuchCommand());
		commands.put("SHOW_ALL", new ShowAllCommand());
	}

	public Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		if (command == null) {
			command = commands.get("NO_SUCH_COMMAND");
		}
		return command;
	}
	

}
