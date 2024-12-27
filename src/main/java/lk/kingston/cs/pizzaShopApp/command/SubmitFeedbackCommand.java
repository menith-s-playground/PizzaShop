package lk.kingston.cs.pizzaShopApp.command;

import lk.kingston.cs.pizzaShopApp.view_controller.FeedbackAndRatingsController;

public class SubmitFeedbackCommand implements Command {
    private FeedbackAndRatingsController receiver;
    private int rating;
    private String feedback;

    public SubmitFeedbackCommand(FeedbackAndRatingsController receiver, int rating, String feedback) {
        this.receiver = receiver;
        this.rating = rating;
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        receiver.submitFeedback(rating, feedback);
    }

    @Override
    public void undo() {
        receiver.undoFeedbackSubmission(rating, feedback);
    }
}
