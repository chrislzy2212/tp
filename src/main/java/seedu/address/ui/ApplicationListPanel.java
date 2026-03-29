package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.application.Application;

/**
 * Panel containing the list of applications.
 */
public class ApplicationListPanel extends UiPart<Region> {
    private static final String FXML = "ApplicationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicationListPanel.class);

    @FXML
    private VBox containerVBox;

    @FXML
    private GridPane headerGridPane;

    @FXML
    private ListView<Application> applicationListView;

    /**
     * Creates a {@code ApplicationListPanel} with the given {@code ObservableList}.
     */
    public ApplicationListPanel(ObservableList<Application> applicationList) {
        super(FXML);
        applicationListView.setItems(applicationList);
        applicationListView.setCellFactory(listView -> new ApplicationListViewCell());
        Platform.runLater(this::setupResponsiveLayout);
    }

    /**
     * Sets up responsive layout behavior by listening to container width changes.
     * Triggers column constraint updates whenever the container width changes.
     */
    private void setupResponsiveLayout() {
        containerVBox.widthProperty().addListener((observable, oldValue, newValue) -> {
            updateColumnConstraints(newValue.doubleValue());
        });
        updateColumnConstraints(containerVBox.getWidth());
    }

    /**
     * Updates header column constraints based on the current width.
     * Uses percentage-based layout so columns remain responsive across window sizes.
     *
     * @param width the current width of the container in pixels
     */
    private void updateColumnConstraints(double width) {
        headerGridPane.getColumnConstraints().clear();
        headerGridPane.getColumnConstraints().addAll(
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

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Application} using a {@code ApplicationCard}.
     */
    class ApplicationListViewCell extends ListCell<Application> {

        ApplicationListViewCell() {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(Application application, boolean empty) {
            super.updateItem(application, empty);

            if (empty || application == null) {
                setGraphic(null);
                setText(null);
            } else {
                Region card = new ApplicationCard(application, getIndex() + 1).getRoot();
                card.prefWidthProperty().bind(widthProperty().subtract(18));
                setGraphic(card);
            }
        }
    }

}
