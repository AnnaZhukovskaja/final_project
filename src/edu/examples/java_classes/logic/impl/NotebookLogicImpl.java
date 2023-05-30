package edu.examples.java_classes.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.examples.java_classes.controller.Controller;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.DaoProvider;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.dao.impl.FileNoteBookDao;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.NoteBookLogic;

public class NotebookLogicImpl implements NoteBookLogic {

    private final DaoProvider provider = DaoProvider.getInstance();
    private final NoteBookDao dao = provider.getNoteBookDao();
    private final Controller controller = Controller.getInstance();

    public void add(Note n) throws LogicException {

        try {
            dao.save(n);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void add(String title, String content) throws LogicException {


        if (!isTitleValid(title)) {
            throw new LogicException("Title not valid");
        }

        Note n = new Note(title, content);
        try {
            dao.save(n);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    private boolean isTitleValid(String title) {
        Pattern p = Pattern.compile(".*[0-9]+.*");
        Matcher m = p.matcher(title);

        boolean b = true;

        while(m.find()) {
            b = false;
        }
        return b;
    }


    public List<Note> find(String text) throws LogicException {
        List<Note> result = new ArrayList<Note>();
        List<Note> myNotes = null;


        try {
            myNotes = dao.allNotes();

       } catch (DaoException e) {
            throw new LogicException(e);
        }

        for (Note n : myNotes) {
            if (isTextInNote(n, text)) {
                result.add(n);
            }
        }
        return result;
    }


    private boolean isTextInNote(Note n, String text) {
        return n.getTitle().contains(text) || n.getContent().contains(text);
    }


    public List<Note> find(Date date) throws LogicException {
        List<Note> result = new ArrayList<Note>();

        try {
            List<Note> myNotes = dao.allNotes();
            for (Note n : myNotes) {
                if (isDateNote(n, date)) {
                    result.add(n);
                }
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return result;
    }

    private boolean isDateNote(Note n, Date date) {
        return n.getD().getDay() == date.getDay() && n.getD().getMonth() == date.getMonth()
                && n.getD().getYear() == date.getYear();
    }

    public List<Note> allNotes() throws LogicException {
        try {
            return dao.allNotes();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
