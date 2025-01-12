package nio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Alias {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();

        List<String> words = readWordsFromFile("words.txt");

        // если слов меньше, чем участников, то выведите сообщение:
        // "Недостаточно слов в файле. Добавьте слова и обновите файл."
        // и завершите выполнение программы

        // воспользуйтесь статическим методом Collections.shuffle(List<?> list),
        // чтобы поменять порядок слов случайным образом
        if (words.size() < playersNumber) {
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return;
        }

        Collections.shuffle(words);

        int wordsNumber = words.size() / playersNumber;

        for (int i = 0; i < playersNumber; i++) {
            String filename = String.format("player%s.txt", i + 1);
            List<String> subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);

            writeListToFile(subList, filename);
        }

        System.out.println("Карточки готовы!");
    }

    private static List<String> readWordsFromFile(String filename) throws IOException {
        // добавьте построчное чтение из файла с помощью BufferedReader
        // в случае ошибки выведите сообщение: "Произошла ошибка во время чтения файла."
        List<String> strings = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filename, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fileReader)) {
            while (br.ready()) {
                String line = br.readLine();
                strings.add(line);
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        return strings;
    }

    private static void writeListToFile(List<String> list, String filename) throws IOException {
        // добавьте запись слов в файл с помощью FileWriter
        // в случае ошибки выведите сообщение: "Произошла ошибка во время записи файла."
        try (FileWriter fileWriter = new FileWriter(filename, StandardCharsets.UTF_8)) {
            fileWriter.write(list.toString());
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }
    }
}
