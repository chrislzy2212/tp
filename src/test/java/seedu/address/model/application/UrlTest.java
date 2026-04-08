package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class UrlTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Url(null));
    }

    @Test
    public void constructor_invalidUrl_throwsIllegalArgumentException() {
        String invalidUrl = "";
        assertThrows(IllegalArgumentException.class, () -> new Url(invalidUrl));
    }

    @Test
    public void isValidUrl() {
        // null url
        assertThrows(NullPointerException.class, () -> Url.isValidUrl(null));

        // invalid urls
        assertFalse(Url.isValidUrl("")); // empty string
        assertFalse(Url.isValidUrl(" ")); // spaces only
        assertFalse(Url.isValidUrl("google.com")); // missing protocol
        assertFalse(Url.isValidUrl("www.google.com")); // missing protocol
        assertFalse(Url.isValidUrl("ftp://example.com")); // wrong protocol
        assertFalse(Url.isValidUrl("https:// hello.com")); // space after protocol

        // valid urls
        assertTrue(Url.isValidUrl("https://www.google.com"));
        assertTrue(Url.isValidUrl("http://localhost:8080"));
    }

    @Test
    public void equals() {
        Url url = new Url("https://www.google.com");

        // same values -> returns true
        assertTrue(url.equals(new Url("https://www.google.com")));

        // same object -> returns true
        assertTrue(url.equals(url));

        // null -> returns false
        assertFalse(url.equals(null));

        // different types -> returns false
        assertFalse(url.equals(5.0f));

        // different values -> returns false
        assertFalse(url.equals(new Url("https://www.github.com")));
    }
}
