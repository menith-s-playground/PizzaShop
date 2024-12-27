package lk.kingston.cs.pizzaShopApp.customization;

import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.view_controller.PizzaOrderController;

public interface CustomizationHandler {
    void setNext(CustomizationHandler handler);
    void handleCustomization(PizzaOrder.Builder orderBuilder, PizzaOrderController controller);
}
