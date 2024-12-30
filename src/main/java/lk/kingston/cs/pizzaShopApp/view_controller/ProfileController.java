package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.kingston.cs.pizzaShopApp.util.AppState;

import java.io.IOException;

public class ProfileController {

    @FXML private VBox profileVBox;
    @FXML private ListView<String> favoritePizzasListView;

    @FXML
    private void initialize() {
        String favoritePizza = AppState.getInstance().getOrderName();
        if (AppState.getInstance().isFavorite() && favoritePizza != null) {
            favoritePizzasListView.getItems().add(favoritePizza);
        }
    }

    @FXML
    private void addFavorite(ActionEvent event) {
        String selectedPizza = favoritePizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            AppState.getInstance().setOrderName(selectedPizza);
            AppState.getInstance().setFavorite(true);

            favoritePizzasListView.getItems().clear();
            favoritePizzasListView.getItems().add(selectedPizza);

            showAlert("Favorite Added", selectedPizza + " has been set as your favorite pizza!");
        } else {
            showAlert("No Pizza Selected", "Please select a pizza to add to your favorites.");
        }
    }

    @FXML
    private void removeFavorite(ActionEvent event) {
        String selectedPizza = favoritePizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null && selectedPizza.equals(AppState.getInstance().getOrderName())) {
            AppState.getInstance().setOrderName(null);
            AppState.getInstance().setFavorite(false);

            favoritePizzasListView.getItems().clear();

            showAlert("Favorite Removed", selectedPizza + " has been removed from your favorites.");
        } else {
            showAlert("No Favorite Match", "Please select your current favorite pizza to remove.");
        }
    }

    @FXML
    private void reorderFavorite(ActionEvent event) {
        if (AppState.getInstance().isFavorite() && AppState.getInstance().getOrderName() != null) {
            String favoritePizza = AppState.getInstance().getOrderName();
            showAlert("Reorder Favorite", "You have reordered your favorite pizza: " + favoritePizza + ".");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentOptions.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No Favorite Pizza", "You don't have a favorite pizza to reorder.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void editProfile(ActionEvent actionEvent) {
    }
}
