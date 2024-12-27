package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProfileController {

    @FXML private VBox profileVBox;
    @FXML private ListView<String> favoritePizzasListView;
    @FXML private Button addFavoriteButton;
    @FXML private Button removeFavoriteButton;
    @FXML private Button reorderFavoriteButton;
    @FXML private Button editProfileButton;

    private static final String[] favoritePizzas = {
            "Margherita", "Pepperoni", "BBQ Chicken", "Vegetarian"
    };

    @FXML
    public void initialize() {
        favoritePizzasListView.getItems().addAll(favoritePizzas);
    }

    @FXML
    private void editProfile(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Edit Profile");
        alert.setHeaderText(null);
        alert.setContentText("Profile editing is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    private void addFavorite(ActionEvent event) {
        String newFavoritePizza = "Hawaiian";
        favoritePizzasListView.getItems().add(newFavoritePizza);
        showAlert("Favorite Added", "You have added " + newFavoritePizza + " to your favorites.");
    }

    @FXML
    private void removeFavorite(ActionEvent event) {
        String selectedPizza = favoritePizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            favoritePizzasListView.getItems().remove(selectedPizza);
            showAlert("Favorite Removed", "You have removed " + selectedPizza + " from your favorites.");
        } else {
            showAlert("No Pizza Selected", "Please select a pizza to remove from your favorites.");
        }
    }

    @FXML
    private void reorderFavorite(ActionEvent event) {
        String selectedPizza = favoritePizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            showAlert("Reorder Favorite", "You have reordered " + selectedPizza + ".");
        } else {
            showAlert("No Pizza Selected", "Please select a pizza to reorder.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
