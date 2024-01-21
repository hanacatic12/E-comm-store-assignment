public class MasterCardPayment implements PaymentStrategy {
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.03;
    }
}
