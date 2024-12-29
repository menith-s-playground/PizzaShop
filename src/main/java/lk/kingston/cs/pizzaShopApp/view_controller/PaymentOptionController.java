package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class PaymentOptionController {

    @FXML
    private Button btnCompletePayment;

    @FXML
    void btnCompletePayment(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("Payment Loyalty Points");
        newOrderStage.centerOnScreen();

// Get the current stage
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();


        newOrderStage.initOwner(currentStage);


        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            // Load the FXML for the new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PaymentLoyalty.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        newOrderStage.show();


    }
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
