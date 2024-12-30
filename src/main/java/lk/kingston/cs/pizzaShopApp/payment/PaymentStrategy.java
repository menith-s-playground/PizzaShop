package lk.kingston.cs.pizzaShopApp.payment;

public interface PaymentStrategy {
    String processPayment(double amount);
}
