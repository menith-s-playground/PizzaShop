package lk.kingston.cs.pizzaShopApp.model;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrder {
    private int orderId;
    private String pizzaName;
    private String crust;
    private String sauce;
    private String cheese;
    private List<String> toppings;
    private int quantity;
    private String deliveryOption;
    private String deliveryAddress;

    private PizzaOrder(Builder builder) {
        this.orderId = builder.orderId;
        this.pizzaName = builder.pizzaName;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
        this.quantity = builder.quantity;
        this.deliveryOption = builder.deliveryOption;
        this.deliveryAddress = builder.deliveryAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public String getCheese() {
        return cheese;
    }

    public String getToppings() {
        return String.join(", ", toppings);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public static class Builder {
        private int orderId;
        private String pizzaName = "Custom Pizza";
        private String crust;
        private String sauce;
        private String cheese;
        private List<String> toppings = new ArrayList<>();
        private int quantity = 1;
        private String deliveryOption;
        private String deliveryAddress;

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setPizzaName(String pizzaName) {
            this.pizzaName = pizzaName;
            return this;
        }

        public Builder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public Builder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setDeliveryOption(String deliveryOption) {
            this.deliveryOption = deliveryOption;
            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public PizzaOrder build() {
            return new PizzaOrder(this);
        }
    }
}
