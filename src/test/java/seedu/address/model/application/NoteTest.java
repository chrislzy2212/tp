package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "a".repeat(201);
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        assertTrue(Note.isValidNote(""));
        assertTrue(Note.isValidNote("prepare for behavioral round"));
        assertTrue(Note.isValidNote("a".repeat(200)));

        assertFalse(Note.isValidNote("a".repeat(201)));
    }

    @Test
    public void equals() {
        Note note = new Note("Valid note");

        assertTrue(note.equals(new Note("Valid note")));
        assertTrue(note.equals(note));
        assertFalse(note.equals(null));
        assertFalse(note.equals(5.0f));
        assertFalse(note.equals(new Note("Other note")));
    }
}

