
package Optional;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Доработайте данный метод, чтобы на экран выводилось
        // сообщение в соответствии с заданием

        SearchService searchService = new SearchService();


        Optional<Candy> find = searchService.search("Два верблюда");
        if (find.isPresent()) {
            Candy candy = find.get();
            System.out.println("Товар " + candy.getName()
                    + " доступен в количестве " + candy.getAmount()
                    + " кг по цене " + candy.getPrice()
                    + " руб за кг.");
        } else {
            System.out.println("Данный товар не найден.");
        }
    }
}

class Candy {
    // название
    final String name;
    // цена
    final double price;
    //проданное количество
    final int amount;
    // другие варианты названия
    final Set<String> alternativeNames;



    public Candy(String name, double price, int amount, Collection<String> alternativeNames) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.alternativeNames = Set.copyOf(alternativeNames);
    }

    public Candy(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.alternativeNames = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}

//содержимое этого класса не нужно менять
class SRM {
    private final Map<String, List<Candy>> suppliersProducts = new HashMap<>();

    public SRM() {
        suppliersProducts.put("Первая кондитерская фабрика", List.of(
                new Candy("Мишка на севере", 34.4, 100, Set.of("Мишка косолапый", "Мишка")),
                new Candy("Победа",56, 35, Set.of("Победа вкуса")),
                new Candy("Два верблюда",20, 47, Set.of("Каракум", "Африка")),
                new Candy("Красная шапочка",35, 3, Set.of("КРАСНАЯ ШАПОЧКА"))
        ));

        suppliersProducts.put("Триумф", List.of(
                new Candy("Мишка в лесу", 34.2, 63, Set.of("Мишка косолапый")),
                new Candy("Трюфель",21, 25, Set.of("Трюфель классический", "Трюфель шоколадный"))
        ));

        suppliersProducts.put("Сладости Везде", List.of(
                new Candy("Шоколадный восторг",33.98, 257, Collections.emptySet()),
                new Candy("Африка",25, 114, Set.of("Каракум"))
        ));
        suppliersProducts.put("ООО Дом Шоколада", List.of(
                new Candy("ШокоБомб",20, 1, Set.of("Шоко_бомб")),
                new Candy("Трюфель классический",35, 94, Set.of("Трюфель шоколадный"))
        ));

    }

    // Возвращает название всех поставщиков
    public Set<String> listSuppliers() {
        // Создаём новую коллекцию на основе множества имеющихся поставщиков
        // Создание новой коллекции необходимо, чтобы клиентский код не смог
        // повлиять на содержимое, хранящееся в Map
        return new HashSet<>(suppliersProducts.keySet());
    }

    // Возвращает информацию о товаре на складе поставщика
    // Если поставщик или товар не найден, возвращает null
    public Candy getProduct(String supplierName, String candyName) {
        List<Candy> candies = suppliersProducts.get(supplierName);
        if(candies != null) {
            for (Candy candy : candies) {
                if(candy.name.equals(candyName)
                        || candy.alternativeNames.contains(candyName)) {
                    return candy;
                }
            }
        }
        return null;
    }
}

class Warehouse {
    private List<Candy> candies;

    public Warehouse() {
        this.candies = List.of(
                new Candy("Мишка в лесу", 32, 2, Set.of("Мишка косолапый")),
                new Candy("Трюфель", 44, 5, Set.of("Трюфель классический", "Трюфель шоколадный")));
    }

    // Ищет конфеты на складе по их названию
    // Возвращает пустой Optional, если конфеты отсутствуют
    // или в обратном случае Optional, содержащий соответствующие конфеты
    public Optional<Candy> search(String name) {
        // Реализуйте данный метод с использованием Stream API
        return candies.stream()
                .filter(candy -> candy.name.equalsIgnoreCase(name))
                .findAny();
    }
}

class SearchService {
    // Создаём объект класса, отвечающий за склад магазина
    private final Warehouse warehouse = new Warehouse();
    // Создаём объект класса, отвечающий за работу с поставщиками
    public final SRM srm = new SRM();

    // Основной метод поиска
    // Проверяет наличие товара с указанным именем на складе магазина
    // Если товар отсутствует, то проверяются склады поставщиков,
    // предпочтение отдаётся тому поставщику, у которого наименьшая цена товара.
    // Для поиска товара на складе поставщиков используется метод supplierSearch
    // Если товар нигде не найден, то возвращается пустой Optional
    public Optional<Candy> search(String candyName) {
        // Реализуйте данный метод, с использованием методов Optional
        Warehouse warehouse1 = new Warehouse();
        if (warehouse1.search(candyName).isPresent()) {
            return warehouse1.search(candyName);
        } else {
            return supplierSearch(candyName);
        }
    }

    // Ищет товар с указанным именем на складах поставщиков
    // Возвращает Optional с самым дешевым вариантом товара среди всех
    // поставщиков или пустой Optional, если товар не найден
    private Optional<Candy> supplierSearch(String candyName) {
        // Реализуйте данный метод при помощи Stream API и Optional,
        // используйте метод min из Stream API для нахождения товара с наименьшей ценой
        return srm.listSuppliers().stream()
                .map(supplier -> srm.getProduct(supplier, candyName))
                .filter(Objects::nonNull)
                .min(Comparator.comparingDouble(candy -> candy.price));
    }
}