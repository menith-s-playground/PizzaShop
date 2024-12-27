package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lk.kingston.cs.pizzaShopApp.payment.PaymentStrategy;
import lk.kingston.cs.pizzaShopApp.payment.CreditCardPayment;
import lk.kingston.cs.pizzaShopApp.payment.PaypalPayment;

public class PaymentLoyaltyController {

    @FXML
    private Label loyaltyPointsLabel;
    @FXML
    private ProgressBar pointsProgressBar;

    private PaymentStrategy paymentStrategy;

    public PaymentLoyaltyController() {
        this.paymentStrategy = new CreditCardPayment();
    }

    @FXML
    private void processPayment() {
        double amount = 50.0; // For example
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

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
