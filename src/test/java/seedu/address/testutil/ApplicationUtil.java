package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;

/**
 * A utility class for Application.
 */
public class ApplicationUtil {

    /**
     * Returns an add command string for adding the {@code application}.
     */
    public static String getAddCommand(Application application) {
        return AddCommand.COMMAND_WORD + " " + getApplicationDetails(application);
    }

    /**
     * Returns the part of command string for the given {@code application}'s details.
     */
    public static String getApplicationDetails(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + application.getCompany().value + " ");
        sb.append(PREFIX_ROLE + application.getRole().value + " ");
        sb.append(PREFIX_APPLICATION_DATE).append(application.getApplicationDate().toString()).append(" ");
        application.getUrl().ifPresent(url -> sb.append(PREFIX_URL + url.value + " "));
        sb.append(PREFIX_STATUS + application.getStatus().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicationDescriptor}'s details.
     */
    public static String getEditApplicationDescriptorDetails(EditApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.value).append(" "));
        descriptor.getApplicationDate().ifPresent(applicationDate ->
                sb.append(PREFIX_APPLICATION_DATE).append(applicationDate.toString()).append(" "));
        descriptor.getUrl().ifPresent(url -> sb.append(PREFIX_URL).append(url.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status).append(" "));
        return sb.toString();
    }
}
