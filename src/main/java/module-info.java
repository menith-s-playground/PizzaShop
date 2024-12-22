module lk.kingston.cs.pizzaShopApp {
    requires javafx.controls;
    requires javafx.fxml;

    opens lk.kingston.cs.pizzaShopApp.view_controller to javafx.fxml;
    exports lk.kingston.cs.pizzaShopApp;
}
