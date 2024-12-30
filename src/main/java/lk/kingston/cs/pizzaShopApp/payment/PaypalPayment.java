package lk.kingston.cs.pizzaShopApp.payment;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        return ("Processing Paypal payment of amount: $" + amount);
    }
}
