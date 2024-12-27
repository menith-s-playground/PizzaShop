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
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("Feedback and Ratings");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/FeedbackAndRatings.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }
        newOrderStage.show();
    }

    @FXML
    void btnLoyaltyPointsOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("Payment Loyalty Points");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
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

    @FXML
    public void btnNewOrderOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("New Order");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
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


    @FXML
    void btnPromotionsOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("promotions");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SeasonalSpecials.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        newOrderStage.show();
    }

    @FXML
    void btnTrackOrderOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("Order Tracking");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/OrderTracking.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        newOrderStage.show();
    }

    @FXML
    void btnViewFavoritesOnAction(ActionEvent event) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("Favorite Pizzas");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Favorite.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        newOrderStage.show();
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnUserProfileOnAction(ActionEvent actionEvent) {
        Stage newOrderStage = new Stage();
        newOrderStage.setResizable(false);
        newOrderStage.setTitle("User Profile");
        newOrderStage.centerOnScreen();

        Window currentStage = ((Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow());
        newOrderStage.initOwner(currentStage);
        newOrderStage.initModality(Modality.WINDOW_MODAL);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Profile.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newOrderStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Failed to load the New Order view. Please try again.");
            return;
        }

        newOrderStage.show();
    }
}
