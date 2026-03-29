package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_DATE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_DATE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_BYTEDANCE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    public static final Application ALICE = new ApplicationBuilder().withCompany("Accenture")
            .withUrl("http://alice.example.com").withApplicationDate("2026-01-01")
            .withRole("Software Engineer")
            .withStatus("Applied").build();
    public static final Application BENSON = new ApplicationBuilder().withCompany("Meta")
            .withUrl("http://benson.example.com")
            .withApplicationDate("2026-01-02").withRole("Data Analyst")
            .withStatus("Interview").build();
    public static final Application CARL = new ApplicationBuilder().withCompany("Google").withRole("Mobile Developer")
            .withApplicationDate("2026-01-03").withUrl("http://carl.example.com").build();
    public static final Application DANIEL = new ApplicationBuilder().withCompany("Dyson").withRole("Backend Developer")
            .withApplicationDate("2026-01-04").withUrl("http://daniel.example.com").withStatus("Offered").build();
    public static final Application ELLE = new ApplicationBuilder().withCompany("Tencent").withRole("UX Designer")
            .withApplicationDate("2026-01-05").withUrl("http://elle.example.com").build();
    public static final Application FIONA = new ApplicationBuilder().withCompany("Foodpanda")
            .withRole("Frontend Developer")
            .withApplicationDate("2026-01-06").withUrl("http://fiona.example.com").build();
    public static final Application GEORGE = new ApplicationBuilder().withCompany("Grab").withRole("DevOps Engineer")
            .withApplicationDate("2026-01-07").withUrl("http://george.example.com").build();

    // Manually added
    public static final Application HOON = new ApplicationBuilder().withCompany("HP").withRole("ML Engineer")
            .withApplicationDate("2026-01-08").withUrl("http://hoon.example.com").build();
    public static final Application IDA = new ApplicationBuilder().withCompany("IBM").withRole("Systems Analyst")
            .withApplicationDate("2026-01-09").withUrl("http://ida.example.com").build();

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application AMY = new ApplicationBuilder().withCompany(VALID_COMPANY_AMAZON)
            .withRole(VALID_ROLE_AMAZON)
            .withApplicationDate(VALID_APPLICATION_DATE_AMAZON).withUrl(VALID_URL_AMAZON)
            .withStatus(VALID_STATUS_AMAZON).build();
    public static final Application BOB = new ApplicationBuilder().withCompany(VALID_COMPANY_BYTEDANCE)
            .withRole(VALID_ROLE_BYTEDANCE)
            .withApplicationDate(VALID_APPLICATION_DATE_BYTEDANCE).withUrl(VALID_URL_BYTEDANCE)
            .withStatus(VALID_STATUS_BYTEDANCE).build();

    // Shared fixtures for commands that operate on terminal statuses.
    public static final Application REJECTED_APPLICATION = new ApplicationBuilder()
            .withCompany("Reject Co")
            .withRole("Backend Intern")
            .withApplicationDate("2026-04-01")
            .withStatus("Rejected")
            .build();
    public static final Application WITHDRAWN_APPLICATION = new ApplicationBuilder()
            .withCompany("Withdraw Co")
            .withRole("Frontend Intern")
            .withApplicationDate("2026-04-02")
            .withStatus("Withdrawn")
            .build();
    public static final Application APPLIED_APPLICATION = new ApplicationBuilder()
            .withCompany("Active Co")
            .withRole("QA Intern")
            .withApplicationDate("2026-04-03")
            .withStatus("Applied")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplications() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical applications.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Application application : getTypicalApplications()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static List<Application> getTypicalApplications() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
