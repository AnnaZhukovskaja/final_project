package edu.examples.java_classes.start;

import edu.examples.java_classes.input.ConsoleMenu;
import edu.examples.java_classes.logic.LogicException;

public class Main {

	public static void main(String[] args) throws LogicException {

        ConsoleMenu menu = new ConsoleMenu();
		
		menu.execute();

	}

}
