package seedu.address.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.application.Application;

/**
 * An UI component that displays information of a {@code Application}.
 */
public class ApplicationCard extends UiPart<Region> {

    private static final String FXML = "ApplicationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Application application;

    @FXML
    private HBox cardPane;
    @FXML
    private GridPane cardGridPane;
    @FXML
    private Label company;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label url;
    @FXML
    private Label applicationDate;
    @FXML
    private Label note;

    /**
     * Creates a {@code ApplicationCard} with the given {@code Application} and index to display.
     */
    public ApplicationCard(Application application, int displayedIndex) {
        super(FXML);
        this.application = application;
        id.setText(displayedIndex + ". ");
        company.setText(application.getCompany().value);
        role.setText(application.getRole().value);
        status.setText(application.getStatus().toString());
        url.setText(application.getUrl().map(u -> u.value).orElse("url: -"));
        applicationDate.setText(application.getApplicationDate().toString());
        note.setText(application.getNote().toString().isEmpty() ? "-" : application.getNote().toString());

        String statusClass = getStatusStyleClass(application);
        status.getStyleClass().add(statusClass);

        Platform.runLater(this::setupResponsiveLayout);
    }

    /**
     * Sets up responsive layout behavior by listening to card pane width changes.
     * Triggers column constraint updates whenever the card pane width changes.
     */
    private void setupResponsiveLayout() {
        cardPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            updateColumnConstraints(newValue.doubleValue());
        });
        updateColumnConstraints(cardPane.getWidth());
    }

    /**
     * Updates card column constraints based on the current width.
     * Uses percentage-based layout so columns remain responsive across window sizes.
     *
     * @param width the current width of the card pane in pixels
     */
    private void updateColumnConstraints(double width) {
        cardGridPane.getColumnConstraints().clear();
        cardGridPane.getColumnConstraints().addAll(
            createPercentColumn(5),
            createPercentColumn(12),
            createPercentColumn(19),
            createPercentColumn(14),
            createPercentColumn(12),
            createPercentColumn(19),
            createPercentColumn(19)
        );
    }

    private ColumnConstraints createPercentColumn(double percent) {
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setPercentWidth(percent);
        return constraint;
    }

    static String getStatusStyleClass(Application application) {
        return "status-" + application.getStatus().toString().toLowerCase();
    }
}
