package lk.kingston.cs.pizzaShopApp.subject;

import lk.kingston.cs.pizzaShopApp.model.PizzaOrder;
import lk.kingston.cs.pizzaShopApp.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderTracker {

    private final List<OrderObserver> observers = new ArrayList<>();
    private String status;
    private double progress;
    private String notification;
    private static OrderTracker instance;
    private List<PizzaOrder> orders = new ArrayList<>();

    private int nextOrderId;

    public static OrderTracker getInstance() {
        if (instance == null) {
            instance = new OrderTracker();
        }
        return instance;
    }

    public void addOrder(PizzaOrder order) {
        orders.add(order);
    }
    public int getNextOrderId() {
        return nextOrderId++;
    }



    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(status, progress, notification);
        }
    }

    public void updateOrder(String status, double progress, String notification) {
        this.status = status;
        this.progress = progress;
        this.notification = notification;
        notifyObservers();
    }

}
