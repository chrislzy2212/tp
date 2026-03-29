package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.application.Application;
import seedu.address.model.application.ApplicationDate;
import seedu.address.model.application.Company;
import seedu.address.model.application.Note;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.application.Url;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_COMPANY = "Amazon";
    public static final String DEFAULT_ROLE = "Software Engineer Intern";
    public static final String DEFAULT_APPLICATION_DATE = "2026-03-09";
    public static final String DEFAULT_NOTE = "";

    private Company company;
    private Role role;
    private ApplicationDate applicationDate;
    private Optional<Url> url;
    private Status status;
    private Note note;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        company = new Company(DEFAULT_COMPANY);
        role = new Role(DEFAULT_ROLE);
        applicationDate = new ApplicationDate(DEFAULT_APPLICATION_DATE);
        url = Optional.empty();
        status = Status.DEFAULT;
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        company = applicationToCopy.getCompany();
        role = applicationToCopy.getRole();
        applicationDate = applicationToCopy.getApplicationDate();
        url = applicationToCopy.getUrl();
        status = applicationToCopy.getStatus();
        note = applicationToCopy.getNote();
    }

    /**
     * Sets the {@code Company} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withStatus(String status) {
        this.status = Status.fromUserInput(status);
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withUrl(String url) {
        this.url = (url != null) ? Optional.of(new Url(url)) : Optional.empty();
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code ApplicationDate} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withApplicationDate(String applicationDate) {
        this.applicationDate = new ApplicationDate(applicationDate);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Builds and returns an {@code Application} object with the current builder state.
     *
     * @return a new {@code Application} instance with the configured values
     */
    public Application build() {
        return new Application(company, role, applicationDate, url, status, note);
    }

}
