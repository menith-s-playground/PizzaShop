package lk.kingston.cs.pizzaShopApp.customization;

import javafx.scene.control.CheckBox;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.view_controller.PizzaOrderController;

public class ToppingHandler implements CustomizationHandler {

    private CustomizationHandler nextHandler;

    @Override
    public void setNext(CustomizationHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleCustomization(PizzaOrder.Builder orderBuilder, PizzaOrderController controller) {
        StringBuilder toppings = new StringBuilder();
        for (var node : controller.toppingsVBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    toppings.append(checkBox.getText()).append(", ");
                }
            }
        }
        if (toppings.length() > 0) {
            toppings.setLength(toppings.length() - 2);
        }
        orderBuilder.addTopping(toppings.toString());

        if (nextHandler != null) {
            nextHandler.handleCustomization(orderBuilder, controller);
        }
    }
}
