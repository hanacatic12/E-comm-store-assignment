public class OtherPayment implements PaymentStrategy {
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.1;
    }
}
