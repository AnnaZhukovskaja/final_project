package edu.examples.java_classes.controller.impl;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NoteBookLogic;
import edu.examples.java_classes.output.NotebookOutput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindByDateCommand implements Command {
    private final NoteBookLogic logic = LogicProvider.getInstance().getNoteBookLogic();

    @Override
    public String execute(String request) {

        String[] params = request.split("\\s+",2);

        String d = params[1].split("=")[1];

        String response = "";

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");

        try {
            Date dateInput = format.parse(d);
            response = NotebookOutput.print(logic.find(dateInput));

        } catch (LogicException e) {
            response = "Error!!!";
        } catch (ParseException e) {
            response = "Incorrect date format!";
        }

        return response;
    }
}
