package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button btnFeedback;

    @FXML
    private Button btnLoyaltyPoints;

    @FXML
    private Button btnNewOrder;

    @FXML
    private Button btnPromotions;

    @FXML
    private Button btnTrackOrder;

    @FXML
    private Button btnViewFavorites;

    @FXML
    private ImageView imgPizza;

    @FXML
    void btnFeedbackOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoyaltyPointsOnAction(ActionEvent event) {

    }

    @FXML
    public void btnNewOrderOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("New Order");
        newOrderStage.centerOnScreen();

        // Set the owner of the new stage
        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL); // Ensures focus remains on the new window

        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            // Log the exception and show an error dialog
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        // Show the stage
        newOrderStage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void btnPromotionsOnAction(ActionEvent event) {

    }

    @FXML
    void btnTrackOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewFavoritesOnAction(ActionEvent event) {

    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnUserProfileOnAction(ActionEvent actionEvent) {
    }
}
