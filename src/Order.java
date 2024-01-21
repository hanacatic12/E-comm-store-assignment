public class Order {
    Double revenuePerShirt = 40.0;
    Double profit;
    Double amountPaid = 14.0;
    String customer;
    String size;
    Boolean design;
    Boolean hoodie;
    PaymentStrategy payment;

    public Order(String customer, String size, Boolean design, Boolean hoodie, PaymentStrategy payment) {

        this.customer = customer;
        this.size = size;
        this.design = design;
        this.hoodie = hoodie;
        this.payment = payment;

        if(hoodie) amountPaid += 3;
        if(design) amountPaid += 2;

        amountPaid += payment.getTransactionFee(revenuePerShirt);

        profit = revenuePerShirt - amountPaid;
    }

}
