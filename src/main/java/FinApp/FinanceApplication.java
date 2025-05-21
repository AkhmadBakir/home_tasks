package FinApp;
import FinApp.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class FinanceApplication {
    double balance;
    HashMap<String, ArrayList<Double>> expenses;
    Converter converter;
    Scanner scanner;

    public FinanceApplication(double rubles, Scanner scanner) {
        balance = rubles;
        expenses = new HashMap<>();
        converter = new Converter();
        this.scanner = scanner;
    }

    void convert() {
        System.out.println("Ваши сбережения: " + balance + " RUB");
        System.out.println("В какую валюту хотите конвертировать? Введите валюту в формате: USD, EUR, JPY: ");
        scanner.nextLine();
        String currency = scanner.nextLine().toUpperCase(Locale.ROOT);
        converter.convert(balance, currency);
    }

    void saveExpense() {
        System.out.println("Введите название категории:");
        scanner.nextLine();
        String categoryName = scanner.nextLine();

        if (!expenses.containsKey(categoryName)) {
            expenses.put(categoryName, new ArrayList<Double>());
        }
        System.out.println("Введите размер траты:");
        double expense = scanner.nextDouble();
        if (balance >= expense) {
            ArrayList<Double> category = expenses.get(categoryName);
            category.add(expense);
            System.out.println("Значение сохранено!");
            balance = balance - expense;
        } else {
            System.out.println("На балансе недостаточно средств.");
        }
    }

    void printAllExpenses() {
        for (String categoryName : expenses.keySet()) {
            System.out.println(categoryName + ":");
            ArrayList<Double> expensesInCategory = expenses.get(categoryName);
            for (Double expense : expensesInCategory) {
                System.out.println("  " + expense);
            }
        }
    }
}
/*

Доработайте класс Converter .
Добавьте скрытое неизменяемое поле client с типом данных HttpClient. Поле должно быть проинициализировано в публичном конструкторе по умолчанию.
Добавьте метод getRate, который:
является приватным;
в качестве параметра принимает символ валюты (например, JPY для японской иены), в которую необходимо конвертировать сумму в рублях;
возвращает котировку рубля к переданной валюте.
Реализация метода getRate должна запрашивать котировку рубля к валюте, символ которой был передан в параметре. Для получения
котировок воспользуйтесь нашим сервисом, чтобы отправить курсы валют. Они не актуальные, но вы можете использовать их
в ознакомительных целях. Отправьте GET-запрос на https://functions.yandexcloud.net/d4ed1i6t3f80hf0p7mer и укажите
в качестве строки параметров рубль как базовую валюту (параметр base) и переданный символ в качестве котируемой валюты
(параметр symbols).
Уберите фиксированные курсы валют. Вместо них при каждом вызове метода convert(double rubles, int currency) запрашивайте
актуальный курс рубля к заданной валюте при помощи метода getRate.
Для получения курса рубля к долларам используйте символ USD, для евро — EUR, для иен — JPY.
Исправьте вычисление суммы при конвертации. Учитывайте, что раньше хранился курс других валют к рублю, а сейчас — наоборот.
Доработайте класс FinApp.FinanceApplication — исправьте создание объекта класса Converter.

*/