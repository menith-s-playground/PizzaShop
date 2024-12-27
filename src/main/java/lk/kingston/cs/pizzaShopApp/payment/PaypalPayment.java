package lk.kingston.cs.pizzaShopApp.payment;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Paypal payment of amount: $" + amount);
    }
}
