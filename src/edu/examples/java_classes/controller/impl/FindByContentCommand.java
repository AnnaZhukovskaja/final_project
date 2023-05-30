package edu.examples.java_classes.controller.impl;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NoteBookLogic;
import edu.examples.java_classes.output.NotebookOutput;

import java.util.List;

public class FindByContentCommand implements Command {

	private final NoteBookLogic logic = LogicProvider.getInstance().getNoteBookLogic();

	@Override
	public String execute(String request) {
		String[] params = request.split("\\s+");

		String content = params[1].split("=")[1];
		String response = "";

		try {
			response =NotebookOutput.print(logic.find(content));
		} catch (LogicException e) {
			response = "Error!!!";
		}

		return response;
	}

}
