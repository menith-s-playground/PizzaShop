package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lk.kingston.cs.pizzaShopApp.util.AppState;

import java.util.ArrayList;
import java.util.List;

public class FavoriteController {

    @FXML
    private ListView<String> favoritePizzaList;

    @FXML
    public void initialize() {
        List<String> favoritePizzas = getFavoritePizzas();
        favoritePizzaList.getItems().addAll(favoritePizzas);
    }

    private List<String> getFavoritePizzas() {
        List<String> favoritePizzas = new ArrayList<>();
        AppState appState = AppState.getInstance();

        if (appState.isFavorite()) {
            favoritePizzas.add(appState.getOrderName());
        }

        return favoritePizzas;
    }
}
