package lk.kingston.cs.pizzaShopApp.payment;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        return ("Processing Credit Card payment of amount: $" + amount);
    }
}
