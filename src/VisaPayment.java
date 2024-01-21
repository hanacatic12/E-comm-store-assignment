public class VisaPayment implements PaymentStrategy {
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.02;
    }
}
