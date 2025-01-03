package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import lk.kingston.cs.pizzaShopApp.observer.OrderObserver;
import lk.kingston.cs.pizzaShopApp.subject.OrderTracker;
import lk.kingston.cs.pizzaShopApp.util.AppState;

public class OrderTrackingController implements OrderObserver {

    @FXML
    private Label orderStatusLabel;
    @FXML
    private ProgressBar orderProgressBar;
    @FXML
    private Label orderNotification;
    @FXML
    private Label orderIdLabel;
    @FXML
    private Label pizzaNameLabel;
    @FXML
    private Label deliveryTimeLabel;
    @FXML
    private Label deliveryAddressLabel;
    @FXML
    private Label qtyLabel;
    @FXML
    private TextField searchOrderIdTextField;
    public String OrderId;
    public String pizzaName;
    public int qty;
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
    private void searchOrder() {
        String orderIdText = searchOrderIdTextField.getText();
        try {
            OrderId = orderIdText;
            String id = AppState.getInstance().getOrderId();

            if (id != null && id.equals(orderIdText)) {
                boolean isDelivery = AppState.getInstance().isDelivery();

                if (isDelivery) {
                    pizzaNameLabel.setText("Pizza Name: " + AppState.getInstance().getOrderName());

                    int qty = AppState.getInstance().getQty();
                    int baseTime = 25;
                    int additionalTimePerPizza = 5;
                    int estimatedTime = baseTime + (qty - 1) * additionalTimePerPizza;

                    deliveryTimeLabel.setText("Delivery Time: " + estimatedTime + " minutes");
                    deliveryAddressLabel.setText("Delivery Address: " + AppState.getInstance().getAddress());
                    qtyLabel.setText("Quantity: " + qty);
                } else {
                    showAlert("Error", "This order is for pickup only and cannot be tracked.");
                    clearOrderDetails();
                }
            } else {
                pizzaNameLabel.setText("No order found for the given Order ID.");
                clearOrderDetails();
            }
            System.out.println("Searching for Order ID: " + OrderId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
        }
    }

    private void clearOrderDetails() {
        pizzaNameLabel.setText("");
        deliveryTimeLabel.setText("");
        deliveryAddressLabel.setText("");
        qtyLabel.setText("");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void setOrderDetails(String orderId, String pizzaName, int qty) {
        this.OrderId = orderId;
        this.pizzaName = pizzaName;
        this.qty = qty;

        orderIdLabel.setText("Order ID: " + orderId);
        pizzaNameLabel.setText("Pizza: " + pizzaName);
        updateDeliveryTime(qty);
    }

    private void updateDeliveryTime(int qty) {
        int baseTime = 25;
        int additionalTimePerPizza = 5;
        int estimatedTime = baseTime + (qty - 1) * additionalTimePerPizza;
        deliveryTimeLabel.setText("Estimated Delivery Time: " + estimatedTime + " minutes");
        deliveryAddressLabel.setText("Delivery Address: 123 Main St, Springfield");
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

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setOrderId(String orderId) {
        this.OrderId = orderId;
    }
}
