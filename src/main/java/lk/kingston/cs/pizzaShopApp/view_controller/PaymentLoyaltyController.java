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

    public PaymentLoyaltyController() {
        this.paymentStrategy = new CreditCardPayment();
    }

    @FXML
    public void initialize() {
        System.out.println("Initializing OrderTrackingController...");
        AppState appState = AppState.getInstance();
    }
    @FXML
    private void processPayment() {
        double amount = 50.0;
        System.out.println("Processing payment...");
        paymentStrategy.processPayment(amount);
        updateLoyaltyPoints(120);
    }

    @FXML
    private void cancelPayment() {
        System.out.println("Payment canceled.");
    }

    @FXML
    private void claimReward() {
        System.out.println("Claiming reward... Free pizza!");
    }

    @FXML
    private void viewLoyaltyHistory() {
        System.out.println("Viewing loyalty history...");
    }

    private void updateLoyaltyPoints(int points) {
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
                String orderId =orderIdText;

                String Id = AppState.getInstance().getOrderId();
                if (Id != null) {
                    pizzaNameLabel.setText("Pizza Name: " + AppState.getInstance().getOrderName());
                    amount.setText("Amount: " + AppState.getInstance().getAmount());
                    quntity.setText("Quantity: " + AppState.getInstance().getQty());
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

    @FXML
    private void choosePaymentPackage() {
                showSuccessMessage();
    }

    private void showSuccessMessage() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Payment Success");
        successAlert.setHeaderText("Payment Successful!");
        successAlert.setContentText("Your payment has been successfully processed. Thank you for your purchase.");

        successAlert.showAndWait();
    }

}
