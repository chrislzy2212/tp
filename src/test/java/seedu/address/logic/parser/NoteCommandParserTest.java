package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_BYTEDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.application.Note;

public class NoteCommandParserTest {

    private final NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, "1" + NOTE_DESC_AMAZON,
                new NoteCommand(INDEX_FIRST_APPLICATION, new Note("Follow up next Monday.")));
    }

    @Test
    public void parse_missingNotePrefix_throwsParseException() {
        assertParseFailure(parser, "1 Prepare for OA",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyNoteValue_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_NOTE, Note.MESSAGE_EMPTY_NOTE);
    }

    @Test
    public void parse_whitespaceOnlyNoteValue_throwsParseException() {
        assertParseFailure(parser, "1 " + PREFIX_NOTE + "   ", Note.MESSAGE_EMPTY_NOTE);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a " + PREFIX_NOTE + "hello", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        assertParseFailure(parser, PREFIX_NOTE + "hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedNotePrefix_throwsParseException() {
        assertParseFailure(parser, "1" + NOTE_DESC_AMAZON + NOTE_DESC_BYTEDANCE,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NOTE));
    }

    @Test
    public void parse_noteTooLong_throwsParseException() {
        String longNote = "a".repeat(201);
        assertParseFailure(parser, "1 " + PREFIX_NOTE + longNote, Note.MESSAGE_CONSTRAINTS);
    }
}
