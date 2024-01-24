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

        profit = revenuePerShirt - amountPaid;
        if(hoodie) profit -= 3;
        if(design) profit -= 2;

        profit -= payment.getTransactionFee(revenuePerShirt);

    }

}
