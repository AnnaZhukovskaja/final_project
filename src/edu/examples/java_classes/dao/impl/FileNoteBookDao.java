package edu.examples.java_classes.dao.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;

public class FileNoteBookDao implements NoteBookDao {

    @Override
    public void save(Note n) throws DaoException {

        try (BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("my_notes/text1.txt", true)))) {

            bwriter.write(n.toString());
            bwriter.newLine();

        } catch (IOException e) {
            throw new DaoException("Notepad data not saved.", e);
        }
    }

    @Override
    public List<Note> allNotes() {

        List<Note> allnotes = new ArrayList<Note>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("my_notes/text1.txt"));
            String line = reader.readLine();

            while (line != null) {

                allnotes.add(formNote(line));
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении");
        }
        return allnotes;
    }

    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    private Note formNote(String line) {

        String[] params = line.split("\\s+", 5);
        String idString = params[1].split("=")[1];
        int idNote = Integer.parseInt(idString.replaceAll("\\,", ""));
        String title = params[2].split("=")[1];
        String content = params[3].split("=")[1];
        String dateNoteAll = (params[4].split("=")[1]).replaceAll("\\]", "");

        String[] date = dateNoteAll.split("\\.");

        int dateday = Integer.parseInt(date[0]);
        int montch = Integer.parseInt(date[1].replaceAll("^\0?", ""));
        int years = Integer.parseInt(date[2]);

        Note note = new Note(idNote, title, content, new Date(years-1900, montch-1,dateday));

        return note;
    }
}
