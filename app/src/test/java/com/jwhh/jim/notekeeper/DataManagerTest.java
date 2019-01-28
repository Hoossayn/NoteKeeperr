package com.jwhh.jim.notekeeper;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    static DataManager sDatamanger;

    @BeforeClass
    public void clasSetUp() throws Exception{
        sDatamanger = DataManager.getInstance();
    }

    @Before
    public void setUp() throws Exception{

        sDatamanger.getNotes().clear();
        sDatamanger.initializeExampleNotes();
    }

    @Test
    public void createNewNote() {


        final CourseInfo course = sDatamanger.getCourse("android async");
        final String noteTitle = "Test Note Title";
        final String noteText = "This is the body of the text of my note";

        int noteIndex = sDatamanger.createNewNote();
        NoteInfo newNote = sDatamanger.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);


        NoteInfo compareNote = sDatamanger.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
    }

    @Test
    public void findSimilarNotes() throws Exception {


        final CourseInfo course = sDatamanger.getCourse("android async");
        final String noteTitle = "Test Note Title";
        final String noteText1 = "This is the body of the text of my note";
        final String noteText2 = "This is the body of the second text of my note";

        int noteIndex1 = sDatamanger.createNewNote();
        NoteInfo newNote1 = sDatamanger.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2 = sDatamanger.createNewNote();
        NoteInfo newNote2 = sDatamanger.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);


        int findIndex1 = sDatamanger.findNote(newNote1);
        assertEquals(noteIndex1, findIndex1);

        int findIndex2 = sDatamanger.findNote(newNote2);
        assertEquals(noteIndex2, findIndex2);
    }

    @Test
    public void createNewNoteOneStepCreation(){
        final CourseInfo course = sDatamanger.getCourse("android async");
        final String noteTitle = "Test Note Title";
        final String noteText = "This is the body of the text of my note";

        int noteIndex = sDatamanger.createNewNote(course, noteTitle, noteText);

        NoteInfo compareNote = sDatamanger.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
    }
}