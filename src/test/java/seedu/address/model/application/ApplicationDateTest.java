package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ApplicationDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationDate((String) null));
    }

    @Test
    public void constructor_invalidApplicationDate_throwsIllegalArgumentException() {
        String invalidApplicationDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ApplicationDate(invalidApplicationDate));
    }

    @Test
    public void constructor_noArguments_setsCurrentDate() {
        ApplicationDate applicationDate = new ApplicationDate();
        assertEquals(LocalDate.now(), applicationDate.getValue());
    }

    @Test
    public void constructor_localDate_storesDateCorrectly() {
        LocalDate date = LocalDate.of(2026, 3, 9);
        ApplicationDate applicationDate = new ApplicationDate(date);

        assertEquals(date, applicationDate.getValue());
        assertEquals("2026-03-09", applicationDate.toString());
    }

    @Test
    public void isValidApplicationDate() {
        // null application date
        assertThrows(NullPointerException.class, () -> ApplicationDate.isValidApplicationDate(null));

        // invalid application dates
        assertFalse(ApplicationDate.isValidApplicationDate("")); // empty string
        assertFalse(ApplicationDate.isValidApplicationDate(" ")); // spaces only
        assertFalse(ApplicationDate.isValidApplicationDate("2026/03/09")); // wrong separator
        assertFalse(ApplicationDate.isValidApplicationDate("09-03-2026")); // wrong format
        assertFalse(ApplicationDate.isValidApplicationDate("2026-3-9")); // not zero-padded
        assertFalse(ApplicationDate.isValidApplicationDate("2026-02-30")); // invalid day
        assertFalse(ApplicationDate.isValidApplicationDate("2025-13-01")); // invalid month
        assertFalse(ApplicationDate.isValidApplicationDate("abcd-ef-gh")); // non-numeric

        // valid application dates
        assertTrue(ApplicationDate.isValidApplicationDate("2026-03-09"));
        assertTrue(ApplicationDate.isValidApplicationDate("2025-01-01"));
        assertTrue(ApplicationDate.isValidApplicationDate("2024-02-29")); // leap year
        assertTrue(ApplicationDate.isValidApplicationDate("2024-12-31"));
    }
}
