package lk.kingston.cs.pizzaShopApp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.kingston.cs.pizzaShopApp.customization.*;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.subject.OrderTracker;

import java.util.concurrent.atomic.AtomicInteger;

public class PizzaOrderController {

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
    public Spinner<Integer> quantitySpinner;
    @FXML
    public RadioButton pickupRadio;
    @FXML
    public RadioButton deliveryRadio;
    @FXML
    public TextField addressField;
    @FXML
    private TextArea orderReviewTextArea;
    @FXML
    private Button submitOrderButton;

    private final String[] crustOptions = {"Thin Crust", "Thick Crust", "Gluten-Free Crust"};
    private final String[] sauceOptions = {"Tomato Sauce", "BBQ Sauce", "Pesto Sauce"};
    private final String[] cheeseOptions = {"Mozzarella", "Cheddar", "Vegan Cheese"};
    private final String[] toppingOptions = {"Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon", "Extra Cheese"};

    private final AtomicInteger orderIdGenerator = new AtomicInteger(1); // Auto-incrementing orderId
    private final OrderTracker orderTracker = OrderTracker.getInstance(); // Singleton for managing orders

    private CustomizationHandler handlerChain;

    @FXML
    public void initialize() {
        crustComboBox.getItems().addAll(crustOptions);
        sauceComboBox.getItems().addAll(sauceOptions);
        cheeseComboBox.getItems().addAll(cheeseOptions);

        for (String topping : toppingOptions) {
            CheckBox checkBox = new CheckBox(topping);
            toppingsVBox.getChildren().add(checkBox);
        }

        crustComboBox.setValue(crustOptions[0]);
        sauceComboBox.setValue(sauceOptions[0]);
        cheeseComboBox.setValue(cheeseOptions[0]);

        quantitySpinner.getValueFactory().setValue(1);

        CustomizationHandler crustHandler = new CrustHandler();
        CustomizationHandler sauceHandler = new SauceHandler();
        CustomizationHandler cheeseHandler = new CheeseHandler();
        CustomizationHandler deliveryHandler = new DeliveryHandler();


        crustHandler.setNext(sauceHandler);
        sauceHandler.setNext(cheeseHandler);
        cheeseHandler.setNext(deliveryHandler);

        handlerChain = crustHandler;

    }

    @FXML
    private void updateOrderReview(MouseEvent event) {
        PizzaOrder.Builder orderBuilder = new PizzaOrder.Builder();
        handlerChain.handleCustomization(orderBuilder, this); // Process customization

        PizzaOrder order = orderBuilder.build();

        String orderReview = String.format("Pizza Name: %s\nCrust: %s\nSauce: %s\nCheese: %s\nToppings: %s\nQuantity: %d\nDelivery: %s%s",
                order.getPizzaName(), order.getCrust(), order.getSauce(), order.getCheese(), order.getToppings(), order.getQuantity(), order.getDeliveryOption(), order.getDeliveryAddress());

        orderReviewTextArea.setText(orderReview);
    }

    @FXML
    private void submitOrder(ActionEvent event) {
        String orderDetails = orderReviewTextArea.getText();

        if (orderDetails.isEmpty()) {
            showAlert("Error", "Please complete the pizza customization and ordering process.");
            return;
        }

        int orderId = orderIdGenerator.getAndIncrement();

        PizzaOrder.Builder orderBuilder = new PizzaOrder.Builder();
        handlerChain.handleCustomization(orderBuilder, this); // Process customization

        PizzaOrder order = orderBuilder.setOrderId(orderId).build();

        orderTracker.addOrder(order);

        System.out.println("Order Submitted: \n" + order);
        showAlert("Success", "Your order has been submitted successfully!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
