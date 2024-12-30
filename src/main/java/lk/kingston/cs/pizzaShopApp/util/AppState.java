package lk.kingston.cs.pizzaShopApp.util;

import java.util.List;

public class AppState {
    private static AppState instance;
    private String orderId;
    private String orderName;
    private int qty;
    private double amount;
    private String address;
    private  List<String> toppings;
    private String crust;
    private String sauce;
    private String cheese;
    private boolean isDelivery;
    private boolean isFavorite;
    private boolean isPayed;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    private AppState() {}

    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getOrderName() {
        return orderName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isDelivery() {
        return isDelivery;
    }
    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}
