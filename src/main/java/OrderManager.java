import java.util.*;
import java.util.stream.Collectors;

public class OrderManager {

    // Класс заказа
    static class Order {
        private final String customerName;
        private final String productName;
        private final int quantity;

        public Order(String customerName, String productName, int quantity) {
            this.customerName = customerName;
            this.productName = productName;
            this.quantity = quantity;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "customerName='" + customerName + '\'' +
                    ", productName='" + productName + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    // Группировка заказов по именам покупателей….
    public static Map<String, List<Order>> groupOrdersByCustomer(List<Order> orders) {
        Map<String, List<Order>> groupedOrders = new HashMap<>();
        for (Order order : orders) {
            String customerName = order.getCustomerName();
            groupedOrders.computeIfAbsent(customerName, k -> new ArrayList<>()).add(order);

//            groupedOrders.getOrDefault(customerName, new ArrayList<>()).add(order);

//            if (!groupedOrders.containsKey(customerName)) {
//                groupedOrders.put(customerName, new ArrayList<>());
//            }
//            groupedOrders.get(customerName).add(order);
        }
        return groupedOrders;
    }

        // Возвращает топ-3 покупателей по количеству заказанных товаров
    public static List<String> getTopCustomers(List<Order> orders) {
        Map<String, Integer> topCustomers = new HashMap<>();
        for (Order order : orders) {
            String customerName = order.getCustomerName();
            int quantity = order.getQuantity();
            topCustomers.put(customerName, topCustomers.getOrDefault(customerName, 0) + quantity);
        }

        return topCustomers.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

//        // Подсчёт уникальных товаров для каждого покупателя
//    public static Map<String, Integer> getDistinctProductCounts(List<Order> orders) {
//        Map<String, Integer> customerProducts = new HashMap<>();
//        for (Order order : orders) {
//            String customerName = order.getCustomerName();
//            String productName = order.getProductName();
//            int quantity = order.getQuantity();
//
//            // Если покупатель ещё не добавлен в карту, создаём для него новое множество
//            customerProducts.putIfAbsent(customerName, new HashSet<>());
//            customerProducts.put(customerName, customerProducts.getOrDefault(customerName, 0) + quantity);
//
//            // Добавляем товар в множество покупателя
//            customerProducts.get(customerName).add(productName);
//        }
//
//        // Преобразуем Map<String, Set<String>> в Map<String, Integer>, подсчитывая уникальные товары
//        Map<String, Integer> distinctProductCounts = new HashMap<>();
//        for (Map.Entry<String, Set<String>> entry : customerProducts.entrySet()) {
//            distinctProductCounts.put(entry.getKey(), entry.getValue().size());
//        }
//
//        return distinctProductCounts;
//    }

    // Подсчёт уникальных товаров для каждого покупателя
    public static Map<String, Integer> getDistinctProductCounts(List<Order> orders) {
        // Map для хранения уникальных товаров каждого покупателя
        Map<String, Set<String>> customerProducts = new HashMap<>();

        for (Order order : orders) {
            String customerName = order.getCustomerName();
            String productName = order.getProductName(); // Предполагаем, что есть метод getProductName()

            // Если покупатель ещё не добавлен в карту, создаём для него новое множество
            customerProducts.putIfAbsent(customerName, new HashSet<>());

            // Добавляем товар в множество покупателя
            customerProducts.get(customerName).add(productName);
        }

        // Преобразуем Map<String, Set<String>> в Map<String, Integer>, подсчитывая уникальные товары
        Map<String, Integer> distinctProductCounts = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : customerProducts.entrySet()) {
            distinctProductCounts.put(entry.getKey(), entry.getValue().size());
        }

        return distinctProductCounts;
    }


    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Alice", "Laptop", 1),
                new Order("Bob", "Smartphone", 2),
                new Order("Alice", "Tablet", 3),
                new Order("Charlie", "Headphones", 5),
                new Order("Bob", "Monitor", 1),
                new Order("Charlie", "Keyboard", 2)
        );

        // Группировка заказов по покупателям
        Map<String, List<Order>> groupedOrders = groupOrdersByCustomer(orders);
        System.out.println("Grouped Orders: " + groupedOrders);

        // Топ-3 покупателя
        List<String> topCustomers = getTopCustomers(orders);
        System.out.println("Top Customers: " + topCustomers);
        // Уникальные товары для каждого покупателя
        Map<String, Integer> distinctProductCounts = getDistinctProductCounts(orders);
        System.out.println("Distinct Product Counts: " + distinctProductCounts);
    }
}