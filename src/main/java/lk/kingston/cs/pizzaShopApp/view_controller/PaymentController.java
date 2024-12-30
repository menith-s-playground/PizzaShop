package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import lk.kingston.cs.pizzaShopApp.payment.CreditCardPayment;
import lk.kingston.cs.pizzaShopApp.payment.PaymentStrategy;
import lk.kingston.cs.pizzaShopApp.payment.PaypalPayment;

import java.io.IOException;

public class PaymentController {

    @FXML
    private TextField paymentAmountField;

    @FXML
    private Button submitPaymentButton;

    @FXML
    private TextField orderIdField;

    @FXML
    private TextField pizzaNameField;
    @FXML
    private RadioButton RdCard;

    @FXML
    private RadioButton RdPaypal;

    @FXML
    private Button btnCompletePayment;

    @FXML
    private ToggleGroup payment;
    private Stage paymentStage;

    public  int qty;
    private double total ;

    public void setPaymentAmount(double amount) {

        total = amount;
        paymentAmountField.setText(String.format("Rs%.2f", amount));
    }

    public void setOrderDetails(int orderId, String pizzaName,int qty) {
        orderIdField.setText("Order ID: " + orderId);
        pizzaNameField.setText("Pizza: " + pizzaName);
        this.qty=qty;
    }

    public void setPaymentStage(Stage stage) {
        this.paymentStage = stage;
    }

    @FXML
    private void submitPayment() {
        PaymentStrategy pay ;
        if (RdCard.isSelected())
            pay = new CreditCardPayment();
        else
            pay = new PaypalPayment();

        String message = pay.processPayment(total);

        System.out.println("Payment Submitted!");

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

        if (paymentStage != null){
            paymentStage.close();
        }
        openOrderTrackingWindow();

    }


    public void openOrderTrackingWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OrderTracking.fxml"));
            VBox orderTrackingWindow = loader.load();

            Stage orderTrackingStage = new Stage();
            orderTrackingStage.setTitle("Order Tracking");

            OrderTrackingController orderTrackingController = loader.getController();
            orderTrackingController.setPizzaName(pizzaNameField.getText());
//            orderTrackingController.setOrderId(Integer.parseInt(orderIdField.getText().substring(10)));
//            orderTrackingController.setOrderDetails(Integer.parseInt(orderIdField.getText().substring(10)), pizzaNameField.getText(),qty);
            Scene scene = new Scene(orderTrackingWindow);
            orderTrackingStage.setScene(scene);
            orderTrackingStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the order tracking window.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
