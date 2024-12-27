package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lk.kingston.cs.pizzaShopApp.command.Command;
import lk.kingston.cs.pizzaShopApp.command.SubmitFeedbackCommand;
import lk.kingston.cs.pizzaShopApp.invoker.FeedbackInvoker;

public class FeedbackAndRatingsController {

    @FXML
    private Slider ratingSlider;
    @FXML
    private TextArea feedbackTextArea;
    @FXML
    private GridPane feedbackGrid;
    @FXML
    private Button submitFeedbackButton;
    @FXML
    private Button undoFeedbackButton;

    private FeedbackInvoker invoker = new FeedbackInvoker();

    @FXML
    public void initialize() {
        submitFeedbackButton.setOnAction(this::handleFeedbackSubmission);
        undoFeedbackButton.setOnAction(this::handleUndoFeedback);
    }

    @FXML
    private void handleFeedbackSubmission(ActionEvent event) {
        int rating = (int) ratingSlider.getValue();
        String feedback = feedbackTextArea.getText();
        Command submitFeedbackCommand = new SubmitFeedbackCommand(this, rating, feedback);
        invoker.invoke(submitFeedbackCommand);
        submitFeedback();
    }

    @FXML
    private void handleUndoFeedback(ActionEvent event) {
        invoker.undo();
    }

    private void submitFeedback() {
        int rating = (int) ratingSlider.getValue();
        String feedback = feedbackTextArea.getText();

        VBox feedbackBox = new VBox(5);
        feedbackBox.getChildren().addAll(
                new Label("Rating: " + rating + " stars"),
                new Label("Feedback: " + feedback)
        );
        feedbackGrid.add(feedbackBox, 0, feedbackGrid.getChildren().size());

        feedbackTextArea.clear();
        ratingSlider.setValue(3);
    }

    public void undoFeedbackSubmission() {
        feedbackGrid.getChildren().remove(feedbackGrid.getChildren().size() - 1);
    }

    public void submitFeedback(int rating, String feedback) {
    }

    public void undoFeedbackSubmission(int rating, String feedback) {
    }
}
