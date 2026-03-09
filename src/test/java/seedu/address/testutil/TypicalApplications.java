package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_BOB;
import seedu.address.model.AddressBook;
import seedu.address.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    public static final Application ALICE = new ApplicationBuilder().withCompany("Alice Pauline")
            .withUrl("http://alice.example.com").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Application BENSON = new ApplicationBuilder().withCompany("Benson Meier")
            .withUrl("http://benson.example.com")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Application CARL = new ApplicationBuilder().withCompany("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withUrl("http://carl.example.com").build();
    public static final Application DANIEL = new ApplicationBuilder().withCompany("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withUrl("http://daniel.example.com").withTags("friends").build();
    public static final Application ELLE = new ApplicationBuilder().withCompany("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withUrl("http://elle.example.com").build();
    public static final Application FIONA = new ApplicationBuilder().withCompany("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withUrl("http://fiona.example.com").build();
    public static final Application GEORGE = new ApplicationBuilder().withCompany("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withUrl("http://george.example.com").build();

    // Manually added
    public static final Application HOON = new ApplicationBuilder().withCompany("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withUrl("http://hoon.example.com").build();
    public static final Application IDA = new ApplicationBuilder().withCompany("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withUrl("http://ida.example.com").build();

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application AMY = new ApplicationBuilder().withCompany(VALID_COMPANY_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withUrl(VALID_URL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Application BOB = new ApplicationBuilder().withCompany(VALID_COMPANY_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withUrl(VALID_URL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
