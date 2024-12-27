package lk.kingston.cs.pizzaShopApp.customization;

import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.view_controller.PizzaOrderController;

public class CrustHandler implements CustomizationHandler {

    private CustomizationHandler nextHandler;

    @Override
    public void setNext(CustomizationHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleCustomization(PizzaOrder.Builder orderBuilder, PizzaOrderController controller) {
        String crust = controller.crustComboBox.getValue();
        orderBuilder.setCrust(crust);

        if (nextHandler != null) {
            nextHandler.handleCustomization(orderBuilder, controller);
        }
    }
}
