package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.payment.PaymentStrategy;
import lk.kingston.cs.pizzaShopApp.payment.CreditCardPayment;
import lk.kingston.cs.pizzaShopApp.payment.PaypalPayment;
import lk.kingston.cs.pizzaShopApp.util.AppState;

public class PaymentLoyaltyController {

    @FXML
    private Label loyaltyPointsLabel;
    @FXML
    private ProgressBar pointsProgressBar;
    @FXML
    private TextField orderIdTextField;
    @FXML
    private Label pizzaNameLabel;
    @FXML
    private Label amount;
    @FXML
    private Label quntity;

    private PaymentStrategy paymentStrategy;
    private AppState appState;

    public PaymentLoyaltyController() {
        this.paymentStrategy = new CreditCardPayment();  // Default payment strategy
    }

    @FXML
    public void initialize() {
        appState = AppState.getInstance();
        System.out.println("Initializing PaymentLoyaltyController...");

        // Check if payment has already been made and update loyalty points
        if (appState.isPayed()) {
            // Add one point to loyalty points
            updateLoyaltyPoints(100 + 1);
        }

        // Show order details if an order exists
        String orderId = appState.getOrderId();
        if (orderId != null) {
            pizzaNameLabel.setText("Pizza Name: " + appState.getOrderName());
            amount.setText("Amount: " + appState.getAmount());
            quntity.setText("Quantity: " + appState.getQty());
        } else {
            pizzaNameLabel.setText("No order found for the given Order ID.");
        }

        loyaltyPointsLabel.setText("Loyalty Points: " + 100);
        pointsProgressBar.setProgress((double) 100 / 200);
    }

    @FXML
    private void processPayment() {
        double amount = 50.0;
        System.out.println("Processing payment...");
        paymentStrategy.processPayment(amount);

        if (appState.isPayed()) {
            updateLoyaltyPoints(100 + 1);
        }
    }

    @FXML
    private void cancelPayment() {
        System.out.println("Payment canceled.");
    }

    @FXML
    private void claimReward() {
         showErrorMessage("You need at least 200 points to claim a free pizza.");

    }

    @FXML
    private void viewLoyaltyHistory() {
        System.out.println("Viewing loyalty history...");
    }

    private void updateLoyaltyPoints(int points) {
//        appState.setLoyaltyPoints(points);
        loyaltyPointsLabel.setText("Loyalty Points: " + points);
        pointsProgressBar.setProgress((double) points / 200);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) loyaltyPointsLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void searchOrder() {
        String orderIdText = orderIdTextField.getText().trim();

        if (!orderIdText.isEmpty()) {
            try {
                String orderId = orderIdText;
                String storedOrderId = appState.getOrderId();

                if (storedOrderId != null && storedOrderId.equals(orderId)) {
                    pizzaNameLabel.setText("Pizza Name: " + appState.getOrderName());
                    amount.setText("Amount: " + appState.getAmount());
                    quntity.setText("Quantity: " + appState.getQty());
                } else {
                    pizzaNameLabel.setText("No order found for the given Order ID.");
                }
            } catch (NumberFormatException e) {
                pizzaNameLabel.setText("Invalid Order ID format.");
            }
        } else {
            pizzaNameLabel.setText("Please enter an Order ID.");
        }
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    private void showSuccessMessage(String message) {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("Success!");
        successAlert.setContentText(message);
        successAlert.showAndWait();
    }

    private void showErrorMessage(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Error!");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

}
