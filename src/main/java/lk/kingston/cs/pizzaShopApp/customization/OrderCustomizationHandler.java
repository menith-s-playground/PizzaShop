package lk.kingston.cs.pizzaShopApp.customization;

public abstract class OrderCustomizationHandler {

    protected OrderCustomizationHandler nextHandler;

    public void setNextHandler(OrderCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleCustomization(String customizationType, String customizationValue);
}
