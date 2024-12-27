package lk.kingston.cs.pizzaShopApp.customization;

import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.view_controller.PizzaOrderController;

public class SauceHandler implements CustomizationHandler {
    private CustomizationHandler nextHandler;

    @Override
    public void setNext(CustomizationHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleCustomization(PizzaOrder.Builder orderBuilder, PizzaOrderController controller) {
        // Apply the sauce customization
        String sauce = controller.sauceComboBox.getValue();
        orderBuilder.setSauce(sauce);

        // Pass the order to the next handler in the chain
        if (nextHandler != null) {
            nextHandler.handleCustomization(orderBuilder, controller);
        }
    }
}
