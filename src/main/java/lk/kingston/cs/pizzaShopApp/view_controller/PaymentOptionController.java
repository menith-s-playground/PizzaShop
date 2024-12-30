package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.kingston.cs.pizzaShopApp.payment.CreditCardPayment;
import lk.kingston.cs.pizzaShopApp.payment.PaymentStrategy;
import lk.kingston.cs.pizzaShopApp.payment.PaypalPayment;
import lk.kingston.cs.pizzaShopApp.util.AppState;

public class PaymentOptionController {

    @FXML
    private Button btnCompletePayment;
    @FXML
    private RadioButton RdCard;

    @FXML
    private RadioButton RdPaypal;



    @FXML
    private ToggleGroup payment;


    @FXML
    void btnCompletePayment(ActionEvent event) {
        PaymentStrategy pay ;
        if (RdCard.isSelected())
            pay = new CreditCardPayment();
        else
            pay = new PaypalPayment();

        String message = pay.processPayment(total);

        System.out.println("Payment Submitted!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
//        if (toggleCard.isSelected()) {
//            processCardPayment();
//        } else if (toggleWallet.isSelected()) {
//            processWalletPayment();
//        } else {
//            showErrorDialog("Please select a payment method.");
//            return;
//        }

        AppState.getInstance().setPayed(true);
        showSuccessDialog("Payment Successful", "Your payment has been processed. Thank you for ordering from PizzaShop!");
    }

//    private void processCardPayment() {
//        // Simulate card payment processing
//        System.out.println("Processing card payment...");
//    }
//
//    private void processWalletPayment() {
//        // Simulate wallet payment processing
//        System.out.println("Processing wallet payment...");
//    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
//        // Initially, set the 'Card' option to be selected by default (green)
//        updatePaymentMethodStyles();
//
//        // Ensure only one option is selected at a time
//        toggleCard.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            updatePaymentMethodStyles();
//        });
//
//        toggleWallet.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            updatePaymentMethodStyles();
//        });
    }

    private void updatePaymentMethodStyles() {
//        if (toggleCard.isSelected()) {
//            toggleCard.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #4caf50, 10, 0.5, 0, 0); -fx-border-color: #388e3c; -fx-border-radius: 15;");
//            toggleWallet.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 0); -fx-border-color: #1976D2; -fx-border-radius: 15;");
//        } else if (toggleWallet.isSelected()) {
//            toggleWallet.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #4caf50, 10, 0.5, 0, 0); -fx-border-color: #388e3c; -fx-border-radius: 15;");
//            toggleCard.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 0); -fx-border-color: #1976D2; -fx-border-radius: 15;");
//        } else {
//            // Default state when neither is selected
//            toggleCard.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 0); -fx-border-color: #1976D2; -fx-border-radius: 15;");
//            toggleWallet.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-background-radius: 15; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 0); -fx-border-color: #1976D2; -fx-border-radius: 15;");
//        }
    }
private double total;
    public void setPaymentAmount(double amount) {
        total = amount;
    }
}
