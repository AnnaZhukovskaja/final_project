package edu.examples.java_classes.input.command.impl;

import edu.examples.java_classes.input.command.Command;
import edu.examples.java_classes.controller.Controller;

public class ConsoleShowAll implements Command {
    private Controller controller = Controller.getInstance();

    @Override
    public void execute() {

            String request = "SHOW_ALL";

            String response;

            response = controller.doAction(request);

        System.out.println(response);

    }
}
