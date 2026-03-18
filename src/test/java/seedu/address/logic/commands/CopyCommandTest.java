package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CopyCommand}.
 */
public class CopyCommandTest {

    private static final String MESSAGE_COPY_APPLICATION_WITHOUT_URL = "This application does not have a URL to copy.";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeAll
    public static void setUpClass() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Toolkit already initialized.
        }
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Application applicationToCopy = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(CopyCommand.MESSAGE_COPY_APPLICATION_SUCCESS,
                Messages.format(applicationToCopy));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccessOnFxThread(copyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex);

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Application applicationToCopy = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(CopyCommand.MESSAGE_COPY_APPLICATION_SUCCESS,
                Messages.format(applicationToCopy));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showApplicationAtIndex(expectedModel, INDEX_FIRST_APPLICATION);

        assertCommandSuccessOnFxThread(copyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicationList().size());

        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex);

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_applicationWithoutUrl_throwsCommandException() {
        AddressBook addressBook = new AddressBook();
        addressBook.addApplication(new ApplicationBuilder().build());
        Model model = new ModelManager(addressBook, new UserPrefs());
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_APPLICATION);

        assertCommandFailure(copyCommand, model, MESSAGE_COPY_APPLICATION_WITHOUT_URL);
    }

    @Test
    public void equals() {
        CopyCommand copyFirstCommand = new CopyCommand(INDEX_FIRST_APPLICATION);
        CopyCommand copySecondCommand = new CopyCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(copyFirstCommand.equals(copyFirstCommand));

        // same values -> returns true
        CopyCommand copyFirstCommandCopy = new CopyCommand(INDEX_FIRST_APPLICATION);
        assertTrue(copyFirstCommand.equals(copyFirstCommandCopy));

        // different types -> returns false
        assertFalse(copyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(copyFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(copyFirstCommand.equals(copySecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        CopyCommand copyCommand = new CopyCommand(targetIndex);
        String expected = CopyCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, copyCommand.toString());
    }

    /**
     * Executes the given {@code command} on the JavaFX application thread, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    private static void assertCommandSuccessOnFxThread(Command command, Model actualModel, String expectedMessage,
                                                       Model expectedModel) {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> thrown = new AtomicReference<>();

        Platform.runLater(() -> {
            try {
                assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
            } catch (Throwable t) {
                thrown.set(t);
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new AssertionError("Test execution was interrupted.", e);
        }

        if (thrown.get() != null) {
            throw new AssertionError("Execution of command should not fail.", thrown.get());
        }
    }
}
