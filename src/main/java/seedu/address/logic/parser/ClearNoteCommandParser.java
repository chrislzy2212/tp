package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ClearNoteCommand} object.
 */
public class ClearNoteCommandParser implements Parser<ClearNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ClearNoteCommand}
     * and returns a {@code ClearNoteCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ClearNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.trim().isEmpty() || args.trim().split("\\s+").length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearNoteCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearNoteCommand(index);
        } catch (ParseException pe) {

            if (args.trim().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearNoteCommand.MESSAGE_USAGE), pe);
            } else {
                throw pe;
            }
        }

    }
}
