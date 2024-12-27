package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.customization.CheeseHandler;
import lk.kingston.cs.pizzaShopApp.customization.CrustHandler;
import lk.kingston.cs.pizzaShopApp.customization.CustomizationHandler;
import lk.kingston.cs.pizzaShopApp.customization.SauceHandler;
import lk.kingston.cs.pizzaShopApp.subject.OrderTracker;
import lk.kingston.cs.pizzaShopApp.util.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PizzaOrderController {

    @FXML
    public ComboBox<String> pizzaSelectionComboBox;
    @FXML
    public ComboBox<String> crustComboBox;
    @FXML
    public ComboBox<String> sauceComboBox;
    @FXML
    public ComboBox<String> cheeseComboBox;
    @FXML
    public VBox toppingsVBox;
    @FXML
    private TextField pizzaNameField;
    @FXML
    private TextArea orderReviewTextArea;
    @FXML
    private TextField qtyField;

    @FXML
    private TextField adrressField;

    private final String[] availablePizzas = {"Margherita", "Pepperoni", "BBQ Chicken", "Veggie Delight"};
    private final String[] crustOptions = {"Thin Crust", "Thick Crust", "Gluten-Free Crust"};
    private final String[] sauceOptions = {"Tomato Sauce", "BBQ Sauce", "Pesto Sauce"};
    private final String[] cheeseOptions = {"Mozzarella", "Cheddar", "Vegan Cheese"};
    private final String[] toppingOptions = {"Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon", "Extra Cheese"};

    private final double[] crustPrices = {80.00, 100.00, 120.00};
    private final double[] saucePrices = {50.50, 90.00, 200.50};
    private final double[] cheesePrices = {100.00, 222.50, 322.00};
    private final double toppingPrice = 112.00;
    private final AtomicInteger orderIdGenerator = new AtomicInteger(1);


    private final List<PizzaOrder> favoritePizzas = new ArrayList<>();
    private CustomizationHandler handlerChain;

    private final OrderTracker orderTracker = OrderTracker.getInstance();


    @FXML
    public void initialize() {
        pizzaSelectionComboBox.getItems().addAll(availablePizzas);
        crustComboBox.getItems().addAll(crustOptions);
        sauceComboBox.getItems().addAll(sauceOptions);
        cheeseComboBox.getItems().addAll(cheeseOptions);

        for (String topping : toppingOptions) {
            CheckBox checkBox = new CheckBox(topping);
            toppingsVBox.getChildren().add(checkBox);
        }

        CustomizationHandler crustHandler = new CrustHandler();
        CustomizationHandler sauceHandler = new SauceHandler();
        CustomizationHandler cheeseHandler = new CheeseHandler();

        crustHandler.setNext(sauceHandler);
        sauceHandler.setNext(cheeseHandler);

        handlerChain = crustHandler;

        pizzaSelectionComboBox.setOnAction(event -> pizzaNameField.setText(pizzaSelectionComboBox.getValue()));
    }

    private boolean isFavorite = false;

    @FXML
    private void favoritePizza(ActionEvent event) {
        isFavorite = true;
        PizzaOrder order = buildPizzaOrder();
        favoritePizzas.add(order);
        showAlert("Success", "Pizza added to favorites.");
    }

    PizzaOrder order;
    @FXML
    private void reviewOrder(ActionEvent event) {
        String orderId = "ORD" + orderIdGenerator.getAndIncrement();

        double totalPrice = calculateTotalPrice();

        order = new PizzaOrder.Builder()
                .setOrderId(orderId)
                .setPizzaName(pizzaNameField.getText())
                .setCrust(crustComboBox.getValue())
                .setSauce(sauceOptions[sauceComboBox.getSelectionModel().getSelectedIndex()])
                .setCheese(cheeseOptions[cheeseComboBox.getSelectionModel().getSelectedIndex()])
                .setQuantity(qtyField.getText().isEmpty() ? 1 : Integer.parseInt(qtyField.getText()))
                .setDeliveryAddress(adrressField.getText())
                .setToppings(getSelectedToppings())
                .setTotalPrice(totalPrice)
                .setFavorite(isFavorite)
                .build();
        AppState.getInstance().setOrderId(order.getOrderId());
        AppState.getInstance().setToppings(order.getToppings());
        AppState.getInstance().setAddress(order.getDeliveryAddress());
        AppState.getInstance().setCrust(order.getCrust());
        AppState.getInstance().setSauce(order.getSauce());
        AppState.getInstance().setCheese(order.getCheese());
        AppState.getInstance().setAmount(order.getTotalPrice());
        AppState.getInstance().setQty(order.getQuantity());
        AppState.getInstance().setOrderName(order.getPizzaName());
        AppState.getInstance().setFavorite(order.isFavorite());
        orderReviewTextArea.setText(order.toString());

    }

    private PizzaOrder buildPizzaOrder() {
        PizzaOrder.Builder builder = new PizzaOrder.Builder();
        handlerChain.handleCustomization(builder, this);

        List<String> selectedToppings = new ArrayList<>();
        for (var node : toppingsVBox.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                selectedToppings.add(checkBox.getText());
            }
        }

        return builder.setPizzaName(pizzaNameField.getText())
                .setToppings(selectedToppings)
                .build();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void submitOrder(ActionEvent actionEvent) {
        String orderDetails = orderReviewTextArea.getText();

        if (orderDetails.isEmpty()) {
            showAlert("Error", "Please complete the pizza customization and ordering process.");
            return;
        }
        orderTracker.addOrder(order);

        String successMessage = String.format("Your order has been submitted successfully! Order ID: %s\nTotal Price: Rs%.2f", order.getOrderId(), order.getTotalPrice());
        showAlert("Success", successMessage);

    }

    private double calculateTotalPrice() {
        double total = crustPrices[crustComboBox.getSelectionModel().getSelectedIndex()]
                + saucePrices[sauceComboBox.getSelectionModel().getSelectedIndex()]
                + cheesePrices[cheeseComboBox.getSelectionModel().getSelectedIndex()];

        int quantity = qtyField.getText().isEmpty() ? 1 : Integer.parseInt(qtyField.getText());
        total += getSelectedToppings().size() * toppingPrice * quantity;

        return total;
    }


    private List<String> getSelectedToppings() {
        List<String> selectedToppings = new ArrayList<>();
        for (var node : toppingsVBox.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                selectedToppings.add(checkBox.getText());
            }
        }
        return selectedToppings;
    }

}
