package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_DATE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.testutil.EditApplicationDescriptorBuilder;


public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicationDescriptor descriptorWithSameValues = new EditApplicationDescriptor(DESC_AMAZON);
        assertTrue(DESC_AMAZON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMAZON.equals(DESC_AMAZON));

        // null -> returns false
        assertFalse(DESC_AMAZON.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMAZON.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMAZON.equals(DESC_BYTEDANCE));

        // different company -> returns false
        EditApplicationDescriptor editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withCompany(VALID_COMPANY_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different role -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON).withRole(VALID_ROLE_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different application date -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withApplicationDate(VALID_APPLICATION_DATE_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different url -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON).withUrl(VALID_URL_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON).withStatus(VALID_STATUS_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        String expected = EditApplicationDescriptor.class.getCanonicalName() + "{company="
                + editApplicationDescriptor.getCompany().orElse(null) + ", role="
                + editApplicationDescriptor.getRole().orElse(null) + ", applicationDate="
                + editApplicationDescriptor.getApplicationDate().orElse(null) + ", url="
                + editApplicationDescriptor.getUrl().orElse(null) + ", status="
                + editApplicationDescriptor.getStatus().orElse(null) + "}";
        assertEquals(expected, editApplicationDescriptor.toString());
    }
}
