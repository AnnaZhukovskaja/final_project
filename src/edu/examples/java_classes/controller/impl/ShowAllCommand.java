package edu.examples.java_classes.controller.impl;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NoteBookLogic;
import edu.examples.java_classes.output.NotebookOutput;

public class ShowAllCommand implements Command {

    private final NoteBookLogic logic = LogicProvider.getInstance().getNoteBookLogic();

    @Override
    public String execute(String request) {

        String response = "";

        try {
            response = "All notes. ";
            logic.allNotes();

        } catch (LogicException e) {
            response = "Error!!!";
        }
        return response;
    }
}
