package seedu.address.model.application;

import java.util.Locale;

/**
 * Represents the status of an application in the address book.
 * Guarantees: immutable; can only take predefined values declared in {@link Status}.
 */
public enum Status {
    APPLIED,
    OA,
    INTERVIEW,
    OFFERED,
    REJECTED,
    WITHDRAWN;

    public static final Status DEFAULT = APPLIED;

    public static final String MESSAGE_CONSTRAINTS =
            "Status must be one of: Applied, OA, Interview, Offered, Rejected, Withdrawn.";

    /**
     * Returns true if {@code input} can be parsed into a valid {@code Status}.
     */
    public static boolean isValidStatus(String input) {
        try {
            fromUserInput(input);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns the {@code Status} corresponding to the given user input.
     * Input is case-insensitive and leading/trailing whitespace will be ignored.
     *
     * @param input User-provided status string.
     * @return Corresponding {@code Status}.
     * @throws IllegalArgumentException if the input does not match any valid status.
     */
    public static Status fromUserInput(String input) {
        String normalized = input.trim().toUpperCase(Locale.ROOT);
        return Status.valueOf(normalized);
    }

    /**
     * Returns the next status in the application workflow sequence.
     * The sequence progresses as follows: APPLIED → OA → INTERVIEW → OFFERED → REJECTED → WITHDRAWN → APPLIED (cycles).
     *
     * @return the next {@code Status} in the sequence
     * @throws IllegalArgumentException if the current status is unknown
     */
    public Status getNextStatus() throws IllegalArgumentException {
        switch (this) {
        case APPLIED:
            return OA;
        case OA:
            return INTERVIEW;
        case INTERVIEW:
            return OFFERED;
        case OFFERED:
            return REJECTED;
        case REJECTED:
            return WITHDRAWN;
        case WITHDRAWN:
            return APPLIED;
        default:
            throw new IllegalArgumentException("Unknown status: " + this);
        }
    }

    @Override
    public String toString() {
        if (this == OA) {
            return "OA";
        }
        String lower = name().toLowerCase(Locale.ROOT);
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
