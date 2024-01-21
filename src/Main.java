import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        File f = new File("src/customer_orders.csv");
        List<Order> orders = new ArrayList<>();

        ScanFile(f, orders);

        Map<String, Double> profitPerShirtSize = new HashMap<>();

        Integer size = orders.size() / 2;

        List<OrderWorker> orderWorkers = new ArrayList<>();
        orderWorkers.add(new OrderWorker(orders.subList(0, size)));
        orderWorkers.add(new OrderWorker(orders.subList(size, orders.size())));

        List<Thread> threads = new ArrayList<>();
        for(OrderWorker worker : orderWorkers) {
            threads.add(new Thread(worker));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads)
            t.join();

        Double profit = 0.0;
        Double revenue = 0.0;

        for(OrderWorker w : orderWorkers) {
            profit += w.totalProfit;
            revenue += w.revenue;

            profitPerShirtSize = Stream.concat(profitPerShirtSize.entrySet().stream(), w.profitPerShirtSize.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            Double::sum
                    ));
        }

        writeRevenue(revenue);
        writeProfit(profit);
        writeProfitPerSize(profitPerShirtSize);

    }

    public static void ScanFile(File f, List<Order> orders) {
        Map<String, PaymentStrategy> paymentStrategies = new HashMap<>();
        paymentStrategies.put("wallet", new WalletPayment());
        paymentStrategies.put("bankcard", new BankCardPayment());
        paymentStrategies.put("mastercard", new MasterCardPayment());
        paymentStrategies.put("visa", new VisaPayment());

        try {
            Scanner s = new Scanner(f);

            if(s.hasNextLine()) s.nextLine();

            while(s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineParts = line.split(",");

                String customer = lineParts[0].strip();
                String size = lineParts[1].strip();
                Boolean design = Boolean.parseBoolean(lineParts[2].strip());
                Boolean hoodie = Boolean.parseBoolean(lineParts[3].strip());
                String payment = lineParts[4].strip();
                PaymentStrategy paymentStrategy = paymentStrategies.getOrDefault(payment, new OtherPayment());

                orders.add(new Order(customer, size, design, hoodie, paymentStrategy));

            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }


    public static void writeRevenue(Double revenue) {
        try {
            FileWriter writer = new FileWriter("src/totalrevenue.txt");
            writer.write("Total revenue of eComm: " + revenue);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeProfit(Double profit) {
        try {
            FileWriter writer = new FileWriter("src/totalprofit.txt");
            writer.write("Total profit of eComm: " + profit);
            writer.close();
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeProfitPerSize(Map<String, Double> profitPerShirtSize) {
        try {
            FileWriter writer = new FileWriter("src/profit_per_shirt_size.csv");
            writer.write("Size,profit\n");
            for(Map.Entry<String, Double> entry : profitPerShirtSize.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}