package lk.kingston.cs.pizzaShopApp.model;

import java.util.List;

public class PizzaOrder {
    private final String orderId;
    private final String pizzaName;
    private final String crust;
    private final String sauce;
    private final String cheese;
    private final List<String> toppings;
    private final int quantity;
    private final String deliveryOption;
    private final String deliveryAddress;
    private final double totalPrice;
    private final boolean isFavorite; // Add the isFavorite field

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
        this.totalPrice = builder.totalPrice;
        this.isFavorite = builder.isFavorite; // Set the isFavorite value
    }

    public String getOrderId() {
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

    public List<String> getToppings() {
        return toppings;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %s\nPizza: %s\nCrust: %s\nSauce: %s\nCheese: %s\nToppings: %s\nQuantity: %d\nFavorite: %s",
                orderId, pizzaName, crust, sauce, cheese, String.join(", ", toppings), quantity, isFavorite ? "Yes" : "No");
    }

    public static class Builder {
        private String orderId;
        private String pizzaName;
        private String crust;
        private String sauce;
        private String cheese;
        private List<String> toppings;
        private int quantity;
        private String deliveryOption;
        private String deliveryAddress;
        private double totalPrice;
        private boolean isFavorite; // Add the isFavorite field to the builder

        public Builder setOrderId(String orderId) {
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

        public Builder setToppings(List<String> toppings) {
            this.toppings = toppings;
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

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite; // Set the value for isFavorite
            return this;
        }

        public PizzaOrder build() {
            return new PizzaOrder(this);
        }
    }
}
