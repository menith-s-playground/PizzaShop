package lk.kingston.cs.pizzaShopApp.logs;

import lk.kingston.cs.pizzaShopApp.observer.OrderObserver;

public class OrderLogger implements OrderObserver {
    @Override
    public void update(String status, double progress, String notification) {
        System.out.println("Log: Status - " + status + ", Progress - " + progress + ", Notification - " + notification);
    }
}
