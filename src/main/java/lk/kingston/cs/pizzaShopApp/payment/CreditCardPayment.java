package lk.kingston.cs.pizzaShopApp.payment;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of amount: $" + amount);
    }
}
