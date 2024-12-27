package lk.kingston.cs.pizzaShopApp.customization;

import lk.kingston.cs.pizzaShopApp.customization.CustomizationHandler;
import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.view_controller.PizzaOrderController;

public class DeliveryHandler implements CustomizationHandler {
    @Override
    public void setNext(CustomizationHandler handler) {

    }

    @Override
    public void handleCustomization(PizzaOrder.Builder orderBuilder, PizzaOrderController controller) {
        String deliveryOption = controller.pickupRadio.isSelected() ? "Pickup" : "Delivery";
        String deliveryAddress = controller.deliveryRadio.isSelected() ? controller.addressField.getText() : "";
        orderBuilder.setDeliveryOption(deliveryOption);
        orderBuilder.setDeliveryAddress(deliveryAddress);
    }
}
