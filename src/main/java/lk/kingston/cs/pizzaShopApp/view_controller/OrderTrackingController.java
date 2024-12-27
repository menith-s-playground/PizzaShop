package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lk.kingston.cs.pizzaShopApp.observer.OrderObserver;
import lk.kingston.cs.pizzaShopApp.subject.OrderTracker;

public class OrderTrackingController implements OrderObserver {

    @FXML
    private Label orderStatusLabel;
    @FXML
    private ProgressBar orderProgressBar;
    @FXML
    private Label orderNotification;

    private final OrderTracker orderTracker = new OrderTracker();

    @FXML
    public void initialize() {
        orderTracker.addObserver(this);

        orderTracker.updateOrder("Preparing your pizza...", 0.3, "Your pizza is being prepared!");
    }

    @FXML
    private void refreshOrder() {
        System.out.println("Refreshing order details...");
        orderTracker.updateOrder("Pizza is being prepared!", 0.3, "Your pizza is being prepared!");
    }

    @FXML
    private void updateStatus() {
        System.out.println("Updating order status...");
        orderTracker.updateOrder("Out for delivery!", 0.8, "Your pizza is out for delivery!");
    }

    @Override
    public void update(String status, double progress, String notification) {
        orderStatusLabel.setText(status);
        orderProgressBar.setProgress(progress);
        orderNotification.setText(notification);
    }
}
