package StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Orders {

    private int id;
    private String customerName;
    private double amount;
    private String status;

    public Orders(int id, String customerName, double amount, String status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "status='" + status + '\'' +
                ", amount=" + amount +
                ", customerName='" + customerName + '\'' +
                ", id=" + id +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void main(String[] args) {
        Orders order1 = new Orders(1, "Bob", 111.11, "NEW");
        Orders order2 = new Orders(2, "Alice", 222.22, "PROCESSING");
        Orders order3 = new Orders(3, "Jack", 333.33, "COMPLETED");
        Orders order4 = new Orders(4, "Tom", 444.44, "CANCELLED");
        Orders order5 = new Orders(5, "Ron", 555.55, "COMPLETED");
        Orders order6 = new Orders(6, "Anna", 666.66, "COMPLETED");
        Orders order7 = new Orders(7, "Ron", 777.77, "COMPLETED");
        Orders order8 = new Orders(8, "Jack", 888.88, "COMPLETED");

        List<Orders> orders = List.of(order1, order2, order3, order4, order5, order6, order7, order8);

//        Отфильтровать только завершённые заказы (COMPLETED).
//        Сгруппировать заказы по имени клиента.
//        Вычислить общую сумму заказов для каждого клиента.
//        Отсортировать клиентов по убыванию общей суммы заказов.
//        Вывести топ-3 клиентов с наибольшей суммой заказов.

        List<Orders> completedOrders = orders.stream()
                .filter(o -> o.getStatus().equals("COMPLETED"))
                .collect(Collectors.toList());

//        System.out.println(completedOrders);

        Map<String, List<Orders>> completedOrdersGroupingByName = orders.stream()
                .collect(Collectors.groupingBy(Orders::getCustomerName));

//        System.out.println(completedOrdersGroupingByName);

        Map<String, Double> getAmountCustomerOrders = orders.stream()
                .filter(o -> o.getStatus().equals("COMPLETED"))
                .collect(Collectors.groupingBy(Orders::getCustomerName, Collectors.summingDouble(Orders::getAmount)));

//        System.out.println(getAmountCustomerOrders);

        Map<String, Double> completed = orders.stream()
                .filter(o -> o.getStatus().equals("COMPLETED"))
                .collect(Collectors.groupingBy(Orders::getCustomerName, Collectors.summingDouble(Orders::getAmount)))
                .entrySet()
                .stream()
                .sorted(Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        completed.forEach((customer, totalAmount) -> System.out.println(customer + ' ' + totalAmount));

    }
}

