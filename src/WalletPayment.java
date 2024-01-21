public class WalletPayment implements PaymentStrategy {
    @Override
    public double getTransactionFee(double amount) {
        return amount;
    }
}
