package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NextCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new NextCommand object
 */
public class NextCommandParser implements Parser<NextCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NextCommand
     * and returns a NextCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NextCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new NextCommand(index);
        } catch (ParseException pe) {

            if (args.trim().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextCommand.MESSAGE_USAGE), pe);
            } else {
                throw pe;
            }
        }
    }

}
