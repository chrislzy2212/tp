package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an application's URL in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class Url {

    public static final String MESSAGE_CONSTRAINTS =
            "URL must start with http:// or https://, must not be blank, "
                    + "and must not contain spaces";

    /*
     * The first character of the URL must not be a whitespace and starts with http or https,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(http|https)://\\S+$";

    public final String value;

    /**
     * Constructs an {@code Url}.
     *
     * @param url A valid url.
     */
    public Url(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        value = url;
    }

    /**
     * Returns true if a given string is a valid url.
     */
    public static boolean isValidUrl(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Url)) {
            return false;
        }

        Url otherUrl = (Url) other;
        return value.equals(otherUrl.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
