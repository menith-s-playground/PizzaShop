package lk.kingston.cs.pizzaShopApp.observer;

public interface OrderObserver {
    void update(String status, double progress, String notification);
}
