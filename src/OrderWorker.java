import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderWorker implements  Runnable{
    List<Order> orders;
    Double totalProfit = 0.0;
    Double revenue = 0.0;

    Map<String, Double> profitPerShirtSize = new HashMap<>();
    public OrderWorker(List<Order> orders) {
        this.orders = orders;
    }

    public void calculateRevenue() {
        for(Order order : orders) {
            revenue += order.revenuePerShirt;
        }
    }

    public void calculateProfit() {
        for(Order order : orders) {
            totalProfit += order.profit;
        }
    }

    public void getProfitPerSize() {
        for(Order order : orders) {
            if(!profitPerShirtSize.containsKey(order.size))
                profitPerShirtSize.put(order.size, order.profit);
            else
                profitPerShirtSize.put(order.size, profitPerShirtSize.get(order.size) + order.profit);
        }
    }

    @Override
    public void run() {
        calculateRevenue();
        calculateProfit();
        getProfitPerSize();
    }
}
