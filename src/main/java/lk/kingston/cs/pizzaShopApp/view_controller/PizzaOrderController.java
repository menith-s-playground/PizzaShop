package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.customization.CheeseHandler;
import lk.kingston.cs.pizzaShopApp.customization.CrustHandler;
import lk.kingston.cs.pizzaShopApp.customization.CustomizationHandler;
import lk.kingston.cs.pizzaShopApp.customization.SauceHandler;
import lk.kingston.cs.pizzaShopApp.subject.OrderTracker;
import lk.kingston.cs.pizzaShopApp.util.AppState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PizzaOrderController {

    @FXML
    public ComboBox<String> pizzaSelectionComboBox;
    @FXML
    private RadioButton deliveryRadioButton;

    @FXML
    private ToggleGroup deliveryPickupToggleGroup;

    @FXML
    private RadioButton pickupRadioButton;
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


    private final String[] availablePizzas = {"Margherita M", "Margherita L", "Pepperoni M", "Pepperoni L", "BBQ Chicken M","BBQ Chicken L", "Veggie Delight M", "Veggie Delight L"};
    private final String[] crustOptions = {"Thin Crust = LKR 80", "Thick Crust = LKR 100", "Gluten-Free Crust = LKR 120"};
    private final String[] sauceOptions = {"Tomato Sauce =LKR 50", "BBQ Sauce = LKR 90", "Pesto Sauce = LKR 200"};
    private final String[] cheeseOptions = {"Mozzarella =LKR 100", "Cheddar =LKR 222.50", "Vegan Cheese =LKR 322.00"};
    private final String[] toppingOptions = {"Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon", "Extra Cheese"};

    private final double[] crustPrices = {80.00, 100.00, 120.00};
    private final double[] saucePrices = {50.00, 90.00, 200.00};
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
        boolean isDelivery = deliveryRadioButton.isSelected();
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
                .setDelivery(isDelivery)
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
        AppState.getInstance().setDelivery(order.isDelivery());
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
        if (deliveryPickupToggleGroup.getSelectedToggle() == null) {
            showAlert("Error", "Please select either Delivery or Pickup.");
            return;
        }

        if (order == null) {
            reviewOrder(new ActionEvent());
        }

        if (order == null) {
            showAlert("Error", "Please complete the pizza customization process.");
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

    @FXML
    public void btnpaymentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaymentOptions.fxml"));
            PaymentOptionController controller = loader.getController();
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            controller.setPaymentAmount(calculateTotalPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
